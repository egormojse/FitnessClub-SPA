package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    Trainer findById(int id);

    List<Trainer> findAllByDeletedFalse();

}
