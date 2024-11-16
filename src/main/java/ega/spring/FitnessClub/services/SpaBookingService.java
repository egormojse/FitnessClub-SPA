package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.GymBooking;
import ega.spring.FitnessClub.models.SpaBooking;
import ega.spring.FitnessClub.repositories.SpaBookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
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
        Date startOfDay = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(date.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        List<SpaBooking> bookings = spaBookingRepository.findByEmployeeIdAndDate(employeeId, startOfDay, endOfDay);

        return bookings.stream()
                .map(booking -> booking.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString())
                .collect(Collectors.toList());
    }

    public boolean isTimeOccupied(int employeeId, Date date, String time) {
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime dateTime = LocalDateTime.of(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), localTime);
        Date dateTimeAsDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        List<SpaBooking> existingBookings = spaBookingRepository.findByEmployeeIdAndDate(employeeId, dateTimeAsDate);
        return !existingBookings.isEmpty();
    }

    public List<SpaBooking> getUserSpaBookings(int userId) {
        return spaBookingRepository.findByUserId(userId);
    }

    public void deleteById(int bookingId) {
        spaBookingRepository.deleteById(bookingId);
    }

    public SpaBooking getBookingById(int bookingId) {
        return spaBookingRepository.findById(bookingId).orElse(null);
    }

    public void updateBooking(int bookingId, SpaBooking updatedBooking) {
        SpaBooking spaBooking = spaBookingRepository.findById(bookingId).orElse(null);
        if (spaBooking != null) {
            spaBooking.setStatus(updatedBooking.getStatus());
            spaBooking.setDate(updatedBooking.getDate());
            spaBookingRepository.save(spaBooking);
        }
    }

    public List<SpaBooking> getAllBookings() {
        return spaBookingRepository.findAllByDeletedFalse();
    }
}
