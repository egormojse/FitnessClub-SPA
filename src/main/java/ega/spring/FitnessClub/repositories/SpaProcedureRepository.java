package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.SpaProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaProcedureRepository extends JpaRepository<SpaProcedure, Integer> {
    List<SpaProcedure> findByType(String type);
}
