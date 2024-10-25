package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.SpaBooking;
import ega.spring.FitnessClub.repositories.SpaBookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaBookingService {

    private final SpaBookingRepository spaBookingRepository;

    public SpaBookingService(SpaBookingRepository spaBookingRepository) {
        this.spaBookingRepository = spaBookingRepository;
    }


    public void save(SpaBooking session) {
        spaBookingRepository.save(session);
    }

    public List<String> getOccupiedTimes(int employeeId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<SpaBooking> bookings = spaBookingRepository.findByEmployeeIdAndDate(employeeId, startOfDay, endOfDay);

        return bookings.stream()
                .map(booking -> booking.getDate().toLocalTime().toString())
                .collect(Collectors.toList());
    }

    public boolean isTimeOccupied(int employeeId, LocalDate date, String time) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.parse(time));
        List<SpaBooking> existingBookings = spaBookingRepository.findByEmployeeIdAndDate(employeeId, dateTime);
        return !existingBookings.isEmpty();
    }

    public List<SpaBooking> getUserSpaBookings(int userId) {
        return spaBookingRepository.findByUserId(userId);
    }
}
