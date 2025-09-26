package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // meus métodos novos:
    Page<Appointment> findByUser_Id(Long userId, Pageable pageable);

    List<Appointment> findByUser_IdAndPatient_Id(Long userId, Long patientId);

    Optional<Appointment> findByIdAndUser_Id(Long id, Long userId);


    // meus métodos já existentes (Usado no Dashboard - MANTER):
    long countByDate(LocalDate date);
    long countByDateBetween(LocalDate start, LocalDate end);
    long countByStatus(String status);
    long countByPaidTrue();
    long countByPaidFalse();

    List<Appointment> findTop5ByOrderByDateDesc();

}
