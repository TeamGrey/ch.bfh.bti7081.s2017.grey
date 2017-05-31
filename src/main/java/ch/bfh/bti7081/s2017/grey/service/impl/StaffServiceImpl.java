package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.StaffDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.StaffService;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin.
 */
public class StaffServiceImpl implements StaffService {

    StaffDao dao;

    public StaffServiceImpl() {
        dao = new StaffDao();
    }

    @Override
    public Staff findStaffByLogin(String login) {
        return dao.getStaffByLogin(login);
    }

    @Override
    public Staff createStaff(String firstname, String lastname, String login, String pwhash, Role role) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Staff staff = new Staff();
        staff.setFirstname(firstname);
        staff.setLastname(lastname);
        staff.setLogin(login);
        staff.setPwhash(pwhash);
        staff.setRoles(role);
        staff.setChanged(timestamp);
        staff.setCreated(timestamp);

        return dao.create(staff);
    }

    @Override
    public void deleteStaff(Staff staff) {
        dao.delete(staff.getId());
    }
}
