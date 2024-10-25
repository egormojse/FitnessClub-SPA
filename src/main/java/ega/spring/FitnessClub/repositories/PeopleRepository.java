package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);

    Person findById(int id);

    List<Person> findAllByDeletedFalse();

}
