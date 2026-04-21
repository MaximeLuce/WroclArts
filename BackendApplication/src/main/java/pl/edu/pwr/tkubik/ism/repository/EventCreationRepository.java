package pl.edu.pwr.tkubik.ism.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pwr.tkubik.ism.model.EventCreation;

import java.util.List;

public interface EventCreationRepository extends JpaRepository<EventCreation, Long> {
    // native query to get the total of events for each organization
    @Query(value="SELECT o.nameOrga, count(ec.event_id) "
            + "FROM organizations as o LEFT JOIN event_creations as ec ON o.id = ec.organization_id GROUP BY o.id", nativeQuery=true)
    public List<Tuple> getOrganizationEventCounts();
}
