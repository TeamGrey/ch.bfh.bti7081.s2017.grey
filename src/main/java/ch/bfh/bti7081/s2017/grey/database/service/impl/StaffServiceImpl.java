package ch.bfh.bti7081.s2017.grey.database.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.StaffDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.service.StaffService;

/**
 * @Author Quentin.
 */
public class StaffServiceImpl implements StaffService {

    StaffDao staffDao;

    public StaffServiceImpl() {
        staffDao = new StaffDao();
    }

    @Override
    public Staff findStaffByLogin(String login) {
        return staffDao.getStaffByLogin(login);
    }

    @Override
    public void createStaff(String firstname, String lastname, String login, String pwhash, Role role) {
        staffDao.createStaff(firstname, lastname, login, pwhash, role);
    }
}
