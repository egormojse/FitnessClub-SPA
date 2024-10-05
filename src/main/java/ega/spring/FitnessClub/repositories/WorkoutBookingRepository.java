package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.GymBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutBookingRepository extends JpaRepository<GymBooking, Integer> {


    @Query("SELECT wb FROM GymBooking wb WHERE wb.trainer.id = :trainerId AND wb.date BETWEEN :startOfDay AND :endOfDay")
    List<GymBooking> findByTrainerIdAndDate(int trainerId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<GymBooking> findByTrainerIdAndDate(int trainer_id, LocalDateTime date);
}