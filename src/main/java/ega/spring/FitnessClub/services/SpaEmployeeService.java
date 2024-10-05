package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.SpaEmployee;
import ega.spring.FitnessClub.repositories.SpaEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaEmployeeService {

    private final SpaEmployeeRepository spaEmployeeRepository;

    public SpaEmployeeService( SpaEmployeeRepository spaEmployeeRepository) {
        this.spaEmployeeRepository = spaEmployeeRepository;
    }

    public List<SpaEmployee> getAllEmployees() {
        return spaEmployeeRepository.findAll();
    }

    public SpaEmployee getEmployeeById(int id) {
        return spaEmployeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<SpaEmployee> getEmployeesBySpecialization(String specialization) {
        return spaEmployeeRepository.findBySpecialization(specialization);
    }


}
