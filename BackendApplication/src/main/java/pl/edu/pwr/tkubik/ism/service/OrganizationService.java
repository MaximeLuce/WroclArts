package pl.edu.pwr.tkubik.ism.service;

import pl.edu.pwr.tkubik.ism.model.OrganizationEntity;

import java.util.List;

public interface OrganizationService {
    public OrganizationEntity addOrganization(OrganizationEntity organization);
    public OrganizationEntity deleteOrganizationById(long id);
    public List<OrganizationEntity> findAllOrganizations();
    public OrganizationEntity updateOrganization(OrganizationEntity organization);
    public OrganizationEntity findOrganizationById(long id);
}