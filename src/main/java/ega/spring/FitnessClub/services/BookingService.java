package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.GymBooking;
import ega.spring.FitnessClub.repositories.WorkoutBookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingService {

    private final WorkoutBookingRepository trainingSessionRepository;

    public BookingService(WorkoutBookingRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public void save(GymBooking session) {
        trainingSessionRepository.save(session);
    }

    public List<String> getOccupiedTimes(int trainerId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<GymBooking> bookings = trainingSessionRepository.findByTrainerIdAndDate(trainerId, startOfDay, endOfDay);

        return bookings.stream()
                .map(booking -> booking.getDate().toLocalTime().toString())
                .collect(Collectors.toList());
    }


    public boolean isTimeOccupied(int trainerId, LocalDate trainingDate, String trainingTime) {
        LocalDateTime dateTime = LocalDateTime.of(trainingDate, LocalTime.parse(trainingTime));
        List<GymBooking> existingBookings = trainingSessionRepository.findByTrainerIdAndDate(trainerId, dateTime);
        return !existingBookings.isEmpty();
    }

}

