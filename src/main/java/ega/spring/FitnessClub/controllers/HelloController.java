package ega.spring.FitnessClub.controllers;

import ega.spring.FitnessClub.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HelloController {

    @GetMapping("/FitnessClub")
    public String FitnessClub() {
        return "index";
    }

    @GetMapping("/purchase")
    public String purchase() {
        return "purchase";
    }

    @GetMapping("/purchaseSpa")
    public String purchaseSpa() {
        return "purchaseSpa";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
}
