package pl.edu.pwr.tkubik.ism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.tkubik.ism.model.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
