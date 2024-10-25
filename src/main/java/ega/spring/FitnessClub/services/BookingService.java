package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.GymBooking;
import ega.spring.FitnessClub.models.Person;
import ega.spring.FitnessClub.models.PersonMembership;
import ega.spring.FitnessClub.models.Trainer;
import ega.spring.FitnessClub.repositories.PersonMembershipRepository;
import ega.spring.FitnessClub.repositories.WorkoutBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookingService {

    private final WorkoutBookingRepository trainingSessionRepository;

    @Autowired
    private PersonMembershipRepository personMembershipRepository;

    public BookingService(WorkoutBookingRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public void save(GymBooking session) {
        trainingSessionRepository.save(session);
    }

    public List<GymBooking> getUserWorkouts(int userId) {
        return trainingSessionRepository.findByUserId(userId);
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

    public List<GymBooking> getAllBookings() {
        return trainingSessionRepository.findAllByDeletedFalse();
    }

    public void deleteBookingById(int id) {
        GymBooking booking = trainingSessionRepository.findById(id);
        if (booking != null) {
            booking.setDeleted(true);
            trainingSessionRepository.save(booking);
        }
    }

    public void updateBooking(int id, GymBooking updatedBooking) {
        GymBooking booking = trainingSessionRepository.findById(id);
        if (booking != null) {
            booking.setStatus(updatedBooking.getStatus());
            booking.setDate(updatedBooking.getDate());
            trainingSessionRepository.save(booking);
        }
    }

    public GymBooking getBookingById(int bookingId) {
        return trainingSessionRepository.findById(bookingId);
    }
}

