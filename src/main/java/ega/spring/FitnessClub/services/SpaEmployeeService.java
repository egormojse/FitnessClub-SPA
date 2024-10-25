package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.Order;
import ega.spring.FitnessClub.models.SpaEmployee;
import ega.spring.FitnessClub.models.Trainer;
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
        return spaEmployeeRepository.findAllByDeletedFalse();
    }

    public SpaEmployee getEmployeeById(int id) {
        if (spaEmployeeRepository.existsById(id)) {
            return spaEmployeeRepository.findById(id);
        }
        else return null;
    }

    public List<SpaEmployee> getEmployeesBySpecialization(String specialization) {
        return spaEmployeeRepository.findBySpecialization(specialization);
    }

    public void save(SpaEmployee spaEmployee) {
        spaEmployeeRepository.save(spaEmployee);
    }


    public void updateEmployee(int id, SpaEmployee updatedSpaEmployee) {
        SpaEmployee spaEmployee = spaEmployeeRepository.findById(id);
        if (spaEmployee != null) {
            spaEmployee.setName(updatedSpaEmployee.getName());
            spaEmployee.setSpecialization(updatedSpaEmployee.getSpecialization());
            spaEmployeeRepository.save(spaEmployee);
        }
    }

    public void deleteSpaEmployeeById(int spaEmployeeId) {
        SpaEmployee employee = spaEmployeeRepository.findById(spaEmployeeId);
        if (employee != null) {
            employee.setDeleted(true);
            spaEmployeeRepository.save(employee);
        }
    }
}
