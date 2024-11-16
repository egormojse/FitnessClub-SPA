package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.GymBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkoutBookingRepository extends JpaRepository<GymBooking, Integer> {


    @Query("SELECT wb FROM GymBooking wb WHERE wb.trainer.id = :trainerId AND wb.date BETWEEN :startOfDay AND :endOfDay")
    List<GymBooking> findByTrainerIdAndDate(int trainerId, Date startOfDay, Date endOfDay);

    List<GymBooking> findByTrainerIdAndDate(int trainer_id, Date date);

    List<GymBooking> findByUserId(int userId);

    GymBooking findById(int id);

    List<GymBooking> findAllByDeletedFalse();
}
