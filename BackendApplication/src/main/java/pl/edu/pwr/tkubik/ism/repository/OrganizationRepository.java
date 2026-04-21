package pl.edu.pwr.tkubik.ism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.tkubik.ism.model.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long>  {
}
