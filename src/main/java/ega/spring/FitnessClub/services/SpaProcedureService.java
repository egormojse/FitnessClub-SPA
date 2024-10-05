package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.SpaProcedure;
import ega.spring.FitnessClub.repositories.SpaProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaProcedureService {

    private SpaProcedureRepository spaProcedureRepository;

    public SpaProcedureService(SpaProcedureRepository spaProcedureRepository) {
        this.spaProcedureRepository = spaProcedureRepository;
    }

    public List<SpaProcedure> getAllProcedures() {
        return spaProcedureRepository.findAll();
    }

    public List<SpaProcedure> getProceduresByType(String type) {
        return spaProcedureRepository.findByType(type);
    }

    public SpaProcedure getProcedureById(int procedureId) {
        return spaProcedureRepository.findById(procedureId).get();
    }
}
