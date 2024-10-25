package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.SpaEmployeeProcedure;
import ega.spring.FitnessClub.repositories.SpaEmployeeProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaEmployeeProcedureService {

    private final SpaEmployeeProcedureRepository spaEmployeeProcedureRepository;

    @Autowired
    public SpaEmployeeProcedureService(SpaEmployeeProcedureRepository spaEmployeeProcedureRepository) {
        this.spaEmployeeProcedureRepository = spaEmployeeProcedureRepository;
    }

    public void save(SpaEmployeeProcedure spaEmployeeProcedure) {
        spaEmployeeProcedureRepository.save(spaEmployeeProcedure);
    }


}
