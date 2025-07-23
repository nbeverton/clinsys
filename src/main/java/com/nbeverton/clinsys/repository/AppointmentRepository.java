package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.dto.dashboard.LastAppointmentsDTO;
import com.nbeverton.clinsys.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    long countByDate(LocalDate date);
    long countByDateBetween(LocalDate start, LocalDate end);
    long countByStatus(String status);
    long countByPaidTrue();
    long countByPaidFalse();


    @Query("""
        SELECT new com.nbeverton.clinsys.dto.dashboard.LastAppointmentsDTO(
            a.id, p.name, a.date, a.status, a.paid
        )
        FROM Appointment a
        JOIN a.patient p
        ORDER BY a.date DESC
    """)

    List<LastAppointmentsDTO> findTop5ByOrderByDateDesc();

}
