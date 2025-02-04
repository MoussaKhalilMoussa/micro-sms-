package moussed.edu.tr.repository;

import moussed.edu.tr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);

    Optional<Person> findByUserName(String username);
}
