package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.EmergencyContactService;
import ch.bfh.bti7081.s2017.grey.service.HabitService;
import ch.bfh.bti7081.s2017.grey.service.impl.DrugServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.HabitServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {
    private EntityManager em = EntityManagerSingleton.getInstance();
    private EmergencyContactService emergencyContactService = new EmergencyContactServiceImpl(em);
    private PatientServiceImpl patientService = new PatientServiceImpl(em);
    private DrugServiceImpl drugService = new DrugServiceImpl(em);
    private HabitService habitService = new HabitServiceImpl(em);
    private Patient patient = null;

    public void setPatient(Patient newpatient) {
        this.patient = newpatient;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Drug> getAllDrugs() {
        return drugService.getAllDrugs();
    }

    public List<Drug> getDrugList() {
        List<Drug> drugs = new ArrayList<>();
        if (patient != null)
            for (PatientDrugAssociation assoc : patient.getDrugs()) {
                drugs.add(assoc.getDrug());
            }
        return drugs;
    }

    public void updateDrugs(List<Drug> add, List<Drug> remove) {
        Patient pat = getPatient();
        patientService.addDrugsToPatient(pat, add);
        patientService.removeDrugsFromPatient(pat, remove);
    }

    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }

    public List<Habit> getHabitList() {
        List<Habit> habits = new ArrayList<>();
        if (patient != null)
            for (PatientHabitAssociation element : patient.getHabits()) {
                habits.add(element.getHabit());
            }
        return habits;
    }

    public List<EmergencyContact> getEmContact() {
        return emergencyContactService.findEmergencyContactForPatient(patient);
    }

    public void updateHabits(List<Habit> added, List<Habit> removed) {
        Patient pat = getPatient();
        patientService.addHabitsToPatient(pat, added);
        patientService.removeHabitsFromPatient(pat, removed);
    }

    public void addEemergenyContact(String firstName, String lastName, String phone) {
        emergencyContactService.createEmergencyContact(firstName, lastName, phone, patient);
    }

    public void updateEmergencyContact(String firstName, String lastName, String phone, long id) {
        EmergencyContact contact = emergencyContactService.getEmergencyContactById(id);
        contact.setFirstname(firstName);
        contact.setLastname(lastName);
        contact.setPhonenumber(phone);
        emergencyContactService.updateEmergencyContact(contact);
    }
}
