package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.Trainer;
import ega.spring.FitnessClub.repositories.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAllByDeletedFalse();
    }

    public Trainer getTrainerById(int id) {
        if (trainerRepository.existsById(id)) {
            return trainerRepository.findById(id);
        }
        else return null;
    }

    public void deleteTrainerById(int id) {
        Trainer employee = trainerRepository.findById(id);
        if (employee != null) {
            employee.setDeleted(true);
            trainerRepository.save(employee);
        }
    }

    public void updateTrainer(int id, Trainer updatedTrainer) {
        Trainer trainer = trainerRepository.findById(id);
        if (trainer != null) {
            trainer.setName(updatedTrainer.getName());
            trainer.setSpecialization(updatedTrainer.getSpecialization());
            trainerRepository.save(trainer);
        }
    }

    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }
}
