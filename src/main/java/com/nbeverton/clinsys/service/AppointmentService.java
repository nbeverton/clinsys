package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, Appointment updatedAppointment);
    void deleteAppointment(Long id);

}
