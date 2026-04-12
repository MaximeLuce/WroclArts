package pl.edu.pwr.tkubik.ism.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pwr.tkubik.ism.model.EventRegistration;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    // native query to get the total of users for a specific event
    @Query(value="SELECT e.title, count(er.user_id) "
            + "FROM events as e LEFT JOIN event_registrations as er ON e.id = er.event_id GROUP BY e.id", nativeQuery=true)
    public List<Tuple> getUsersEventCounts();
}
