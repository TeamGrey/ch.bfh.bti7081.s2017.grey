package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.AppointmentDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.AppointmentService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;

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
    public void createAppointment(Patient patient, Staff staff, String description, String title, LocalDateTime date, LocalDateTime end) {
        appointmentDao.createAppointment(date, end, title, description, staff, patient);
    }

    @Override
    public void createAppointmentDummyPatient(Staff staff, String description, String title, LocalDateTime date, LocalDateTime end) {
        PatientService patientService = new PatientServiceImpl();
        Patient patient = patientService.getPatientByName("Test", "Test");
        createAppointment(patient, staff, description, title, date, end);
    }
}
