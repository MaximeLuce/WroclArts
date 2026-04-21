package pl.edu.pwr.tkubik.ism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.tkubik.ism.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
