package pl.edu.pwr.tkubik.ism.api;

import pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime;
import pl.edu.pwr.tkubik.ism.aspect.LogMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.edu.pwr.tkubik.ism.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
public class OrganizationsControllerOld implements OrganizationApi {

    @Autowired
    private NativeWebRequest nativeWebRequest = null;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    @Override
    public ResponseEntity<List<Organization>> findAllOrganizations() {
        List<Organization> orgList = new ArrayList<>();

        Organization org1 = new Organization();
        org1.setOrgId(1L);
        org1.setEmail("contact@ceramicstudio.pl");
        org1.setKrsNumber("0000123456");
        org1.setStatus("Verified");

        orgList.add(org1);

        return new ResponseEntity<>(orgList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Organization> addOrganization(Organization organization) {
        // fake good creation (we give fake ID)
        organization.setOrgId(99L);
        organization.setStatus("PendingVerification");
        System.out.println("New organization registered : " + organization.getEmail());

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Organization> findOrganizationById(Long orgId) {
        Organization org = new Organization();
        org.setOrgId(orgId);
        org.setEmail("info@wroclaw-arts.pl");
        org.setKrsNumber("0000987654");
        org.setStatus("Verified");

        return new ResponseEntity<>(org, HttpStatus.OK);
    }
}