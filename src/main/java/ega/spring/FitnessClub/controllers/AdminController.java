package ega.spring.FitnessClub.controllers;

import ega.spring.FitnessClub.DTO.EmployeeRegistrationDto;
import ega.spring.FitnessClub.models.SpaEmployee;
import ega.spring.FitnessClub.models.Trainer;
import ega.spring.FitnessClub.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("employee", new EmployeeRegistrationDto());
        return "admin";
    }

    @PostMapping("/admin/register-employee")
    public String registerEmployee(
            @Valid EmployeeRegistrationDto employeeDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Пожалуйста, исправьте ошибки в форме.");
            return "admin";
        }

        try {
            if ("SPA".equalsIgnoreCase(employeeDto.getRole())) {
                SpaEmployee spaEmployee = new SpaEmployee();
                spaEmployee.setName(employeeDto.getName());
                spaEmployee.setUsername(employeeDto.getEmail());
                spaEmployee.setEmail(employeeDto.getEmail());
                spaEmployee.setSpecialization(employeeDto.getSpecialization());
                spaEmployee.setExperience(employeeDto.getExperience());
                spaEmployee.setBio(employeeDto.getBio());
                adminService.register(spaEmployee);
            } else if ("TRAINER".equalsIgnoreCase(employeeDto.getRole())) {
                Trainer trainer = new Trainer();
                trainer.setName(employeeDto.getName());
                trainer.setUsername(employeeDto.getEmail());
                trainer.setEmail(employeeDto.getEmail());
                trainer.setSpecialization(employeeDto.getSpecialization());
                trainer.setExperience(employeeDto.getExperience());
                trainer.setBio(employeeDto.getBio());
                adminService.register(trainer);
            } else {
                model.addAttribute("error", "Неверная роль выбранного сотрудника.");
                return "admin";
            }

            model.addAttribute("success", "Сотрудник успешно зарегистрирован!");
            model.addAttribute("employee", new EmployeeRegistrationDto());
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка при регистрации сотрудника.");
        }

        return "admin";
    }
}