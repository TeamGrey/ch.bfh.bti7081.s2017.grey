package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.util.Authentication;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Joel
 */
public class AuthenticationTest {

    @Test
    public void testAuthentication() {
        // prepare
        StaffDao staffDao = new StaffDao();
        Role role = RoleDao.getRoleById(1);
        staffDao.createStaff("TestF", "TestL", "test", "password", role);
        Staff staff = staffDao.getStaffByLogin("test");

        // test
        assertFalse(Authentication.authenticate("test", "wrong password"));
        assertFalse(Authentication.authenticate("wrong username", "password"));
        assertTrue(Authentication.authenticate("test", "password"));

        //clean up
        staffDao.removeStaff(staff.getId());
    }

}