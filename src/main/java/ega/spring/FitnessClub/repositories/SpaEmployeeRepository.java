package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.SpaEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpaEmployeeRepository extends JpaRepository<SpaEmployee, Integer> {
    List<SpaEmployee> findBySpecialization(String specialization);

}
