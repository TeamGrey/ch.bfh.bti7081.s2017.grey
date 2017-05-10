package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.AppointmentDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Quentin
 */
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDao appointmentDao;

    public AppointmentServiceImpl(){
        appointmentDao = new AppointmentDao();
    }

    @Override
    public List<Appointment> findAppointmentsByStaffAndDate(Staff staff, LocalDate date) {
        return appointmentDao.findAppointmentsForStaffAndDay(staff, date);
    }

    @Override
    public void createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date) {
        appointmentDao.createAppointment(date, title, description, staff, patient);
    }
}
