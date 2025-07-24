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

    List<Appointment> findTop5ByOrderByDateDesc();

}
