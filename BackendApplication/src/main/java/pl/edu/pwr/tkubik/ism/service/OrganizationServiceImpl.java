package pl.edu.pwr.tkubik.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime;
import pl.edu.pwr.tkubik.ism.aspect.LogMethod;
import pl.edu.pwr.tkubik.ism.model.OrganizationEntity;
import pl.edu.pwr.tkubik.ism.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    // please note, that here we operate on Entity type (not DTO)
    @LogMethod
    @LogExecutionTime
    @Override
    public OrganizationEntity addOrganization(OrganizationEntity organization) {
        return organizationRepository.save(organization);
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public OrganizationEntity deleteOrganizationById(long id) {
        Optional<OrganizationEntity> o = organizationRepository.findById(id);
        // o.ifPresent(OrganizationEntity -> organizationRepository.deleteById(id));
        // What if there is no organization ?
        if (o.isPresent()) {
            organizationRepository.deleteById(id);
            return o.get();
        }
        // return null if there is no event
        return null;
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public List<OrganizationEntity> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public OrganizationEntity updateOrganization(OrganizationEntity organization) {
        // save() run update if existed, insert if not
        return organizationRepository.save(organization);
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public OrganizationEntity findOrganizationById(long id) {
        // we return null if there is no organization
        Optional<OrganizationEntity> organization = organizationRepository.findById(id);
        return organization.orElse(null);
    }
}