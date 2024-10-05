package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.SpaEmployeeProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaEmployeeProcedureRepository extends JpaRepository<SpaEmployeeProcedure, Integer> {
}
