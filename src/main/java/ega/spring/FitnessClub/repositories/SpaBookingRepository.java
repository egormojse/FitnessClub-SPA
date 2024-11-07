package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.SpaBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpaBookingRepository extends JpaRepository<SpaBooking, Integer> {

    @Query("SELECT sb FROM SpaBooking sb WHERE sb.employee.id = :employeeId " +
            "AND sb.date BETWEEN :startOfDay AND :endOfDay")
    List<SpaBooking> findByEmployeeIdAndDate(int employeeId,
                                             LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<SpaBooking> findByEmployeeIdAndDate(int employeeId, LocalDateTime date);


    List<SpaBooking> findByUserId(int userId);

    List<SpaBooking> findAllByDeletedFalse();
}
