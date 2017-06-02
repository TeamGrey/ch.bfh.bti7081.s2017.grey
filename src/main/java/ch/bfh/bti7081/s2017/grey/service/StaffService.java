package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import java.util.List;

/**
 * @Author Quentin
 */
public interface StaffService {
    Staff findStaffByLogin(String login);
    Staff createStaff(String firstname, String lastname, String login, String pwhash, Role role);
    void deleteStaff(Staff staff);
    List<Staff> getAllStaff();
    void saveAllStaff(List<Staff> allStaff);
}
