package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.model.Appointment;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;
    @Override
    public Appointment createAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existing = getAppointmentById(id);
        existing.setDate(updatedAppointment.getDate());
        existing.setTime(updatedAppointment.getTime());
        existing.setDescription(updatedAppointment.getDescription());
        existing.setStatus(updatedAppointment.getStatus());
        existing.setPaid(updatedAppointment.isPaid());
        existing.setPatient(updatedAppointment.getPatient());
        existing.setUser(updatedAppointment.getUser());
        return repository.save(existing);
    }



    @Override
    public void deleteAppointment(Long id) {

    }
}
