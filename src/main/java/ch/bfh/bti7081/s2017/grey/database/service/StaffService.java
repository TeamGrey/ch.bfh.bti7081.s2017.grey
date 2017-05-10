package ch.bfh.bti7081.s2017.grey.database.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

/**
 * @Author Quentin
 */
public interface StaffService {

    Staff findStaffByLogin(String login);

    void createStaff(String firstname, String lastname, String login, String pwhash, Role role);
}
