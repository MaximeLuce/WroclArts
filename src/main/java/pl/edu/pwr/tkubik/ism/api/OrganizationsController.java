package pl.edu.pwr.tkubik.ism.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.tkubik.ism.model.OrganizationEntity;
import pl.edu.pwr.tkubik.ism.service.OrganizationService;
import pl.edu.pwr.tkubik.ism.api.OrganizationApi;
import pl.edu.pwr.tkubik.ism.model.Organization;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganizationsController implements OrganizationApi {

    @Autowired
    private OrganizationService organizationService;

    // private method to convert an Entity to a DTO
    private Organization convertToDto(OrganizationEntity entity) {
        Organization dto = new Organization();
        dto.setOrgId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNameOrga(entity.getNameOrga());
        dto.setKrsNumber(entity.getKrsNumber());
        dto.setSubscriptionDate(entity.getSubscriptionDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    // POST /organizations
    @Override
    public ResponseEntity<Organization> addOrganization(Organization organizationDto) {
        // we instance the entity
        OrganizationEntity entity = new OrganizationEntity();

        // 2mapping DTO to Entity
        entity.setEmail(organizationDto.getEmail());
        entity.setNameOrga(organizationDto.getNameOrga());
        entity.setKrsNumber(organizationDto.getKrsNumber());
        entity.setSubscriptionDate(organizationDto.getSubscriptionDate());
        entity.setStatus(organizationDto.getStatus());

        // call the service to save in the DB
        OrganizationEntity savedEntity = organizationService.addOrganization(entity);

        // get the final ID set it in the DTO
        organizationDto.setOrgId(savedEntity.getId());

        // return the final DTO with a 200 OK status
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

    // GET /organizations
    @Override
    public ResponseEntity<List<Organization>> findAllOrganizations() {
        // get all entities from the database
        List<OrganizationEntity> entities = organizationService.findAllOrganizations();

        List<Organization> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(convertToDto(entity)));

        // return the list with HTTP code status
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // GET /organizations/{orgId}
    @Override
    public ResponseEntity<Organization> findOrganizationById(Long orgId) {
        // find the entity
        OrganizationEntity entity = organizationService.findOrganizationById(orgId);

        // if no entity, return 404
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // if entity, convert and return it with 200
        return new ResponseEntity<>(convertToDto(entity), HttpStatus.OK);
    }

    // PUT /organizations
    @Override
    public ResponseEntity<Organization> updateOrganization(Organization organizationDto) {
        // check if the ID is present in the DTO
        if (organizationDto.getOrgId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // check if the organization exists in DB
        OrganizationEntity existingEntity = organizationService.findOrganizationById(organizationDto.getOrgId());

        if (existingEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // update the fields of the existing entity
        existingEntity.setEmail(organizationDto.getEmail());
        existingEntity.setNameOrga(organizationDto.getNameOrga());
        existingEntity.setKrsNumber(organizationDto.getKrsNumber());
        existingEntity.setSubscriptionDate(organizationDto.getSubscriptionDate());
        existingEntity.setStatus(organizationDto.getStatus());

        // save the update in DB
        OrganizationEntity updatedEntity = organizationService.addOrganization(existingEntity);

        // return the updated DTO
        return new ResponseEntity<>(convertToDto(updatedEntity), HttpStatus.OK);
    }
}