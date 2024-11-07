package ega.spring.FitnessClub.controllers;

import ega.spring.FitnessClub.models.*;
import ega.spring.FitnessClub.security.PersonDetails;
import ega.spring.FitnessClub.services.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HelloController {

    private final PersonDetailsService personDetailsService;

    public HelloController(PersonDetailsService personDetailsService, BookingService workoutService, OrderService orderService, SpaBookingService spaService) {
        this.personDetailsService = personDetailsService;
        this.workoutService = workoutService;
        this.orderService = orderService;
        this.spaService = spaService;
    }

    @GetMapping("/FitnessClub")
    public String index() {
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
    private final BookingService workoutService;
    private final OrderService orderService;
    private final SpaBookingService spaService;

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal PersonDetails userDetails) {
        int userId = userDetails.getUserId();
        Person user = personDetailsService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);

        List<GymBooking> workouts = workoutService.getUserWorkouts(userId);
        List<SpaBooking> spaBookings = spaService.getUserSpaBookings(userId);
        List<Order> orders = orderService.getUserOrders(userId);

        model.addAttribute("workouts", workouts);
        model.addAttribute("spaBookings", spaBookings);
        model.addAttribute("orders", orders);

        return "profile";
    }




}
