package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

/**
 * @Author Quentin
 */
public interface StaffService {

    public Staff findStaffByLogin(String login);
    public Staff createStaff(String firstname, String lastname, String login, String pwhash, Role role);
    public void deleteStaff(Staff staff);
}
