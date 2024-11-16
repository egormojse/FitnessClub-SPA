package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.GymBooking;
import ega.spring.FitnessClub.models.Person;
import ega.spring.FitnessClub.models.PersonMembership;
import ega.spring.FitnessClub.models.Trainer;
import ega.spring.FitnessClub.repositories.PersonMembershipRepository;
import ega.spring.FitnessClub.repositories.WorkoutBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class BookingService {

    private final WorkoutBookingRepository trainingSessionRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PersonMembershipRepository personMembershipRepository;

    public BookingService(WorkoutBookingRepository trainingSessionRepository, RedisTemplate<String, Object> redisTemplate) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.redisTemplate = redisTemplate;
    }

    public void save(GymBooking session) {
        trainingSessionRepository.save(session);
    }

    public List<GymBooking> getUserWorkouts(int userId) {
        return trainingSessionRepository.findByUserId(userId);
    }

    public List<String> getOccupiedTimes(int trainerId, Date date) {
        // Преобразуем Date в LocalDateTime
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Начало и конец дня в LocalDateTime
        LocalDateTime startOfDay = localDate.atStartOfDay();  // Начало дня (00:00)
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59);  // Конец дня (23:59:59)

        // Преобразуем LocalDateTime в Date для работы с репозиторием
        Date startOfDayDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Date endOfDayDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        List<GymBooking> bookings = trainingSessionRepository.findByTrainerIdAndDate(trainerId, startOfDayDate, endOfDayDate);

        return bookings.stream()
                .map(booking -> booking.getDate().toString())  // Преобразуем в строку
                .collect(Collectors.toList());
    }




    public boolean isTimeOccupied(int trainerId, Date trainingDate, String trainingTime) {
        // Преобразуем Date в LocalDate
        LocalDate localDate = trainingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Преобразуем строку времени в LocalTime и объединяем с LocalDate
        LocalDateTime dateTime = LocalDateTime.of(localDate, LocalTime.parse(trainingTime));

        // Преобразуем LocalDateTime в Date для работы с репозиторием
        Date dateTimeDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        List<GymBooking> existingBookings = trainingSessionRepository.findByTrainerIdAndDate(trainerId, dateTimeDate);

        return !existingBookings.isEmpty();  // Если есть записи, значит время занято
    }



    public List<GymBooking> getAllBookings() {
        List<GymBooking> bookings = trainingSessionRepository.findAllByDeletedFalse();
        return bookings;
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

