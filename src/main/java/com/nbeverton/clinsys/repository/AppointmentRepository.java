package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
