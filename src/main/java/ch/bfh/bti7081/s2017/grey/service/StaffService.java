package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import java.util.List;

/**
 * @Author Quentin
 */
public interface StaffService {
    /**
     * Finds a task by login name
     *
     * @param login Login name
     * @return Staff if found
     */
    Staff findStaffByLogin(String login);
    /**
     * Creates a new staff
     *
     * @param firstname First name of the staff
     * @param lastname Last name of the staff
     * @param login Login name of the staff
     * @param password Password of the staff
     * @param role Role that the staff should have
     * @return created staff
     */
    Staff createStaff(String firstname, String lastname, String login, String password, Role role);
    /**
     * Deletes a staff
     *
     * @param staff staff to be deleted
     */
    void deleteStaff(Staff staff);
    /**
     * Finds all staff
     *
     * @return List of staff
     */
    List<Staff> getAllStaff();
    /**
     * Update a list of staff
     *
     * @param allStaff List of staff to be edited
     */
    void saveAllStaff(List<Staff> allStaff);
}
