package pl.edu.pwr.tkubik.ism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import pl.edu.pwr.tkubik.ism.model.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    // override findAll to force join sql in one request
    @Query("SELECT e FROM EventEntity e LEFT JOIN FETCH e.creators c LEFT JOIN FETCH c.organization")
    List<EventEntity> findAll();
}
