package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.dao.RoleDao;
import ch.bfh.bti7081.s2017.grey.database.dao.StaffDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Joel
 */
public class AuthenticationTest {
    private RoleDao roleDao;
    private StaffDao staffDao;
    private Staff staff;

    @Before
    public void prepare() {
        roleDao = new RoleDao();

        staffDao = new StaffDao();
        Role role = roleDao.getRoleById(1);
        staffDao.createStaff("TestF", "TestL", "test", "password", role);
        staff = staffDao.getStaffByLogin("test");
    }

    @Test
    public void testAuthentication() {
        assertFalse(Authentication.authenticate("test", "wrong password"));
        assertFalse(Authentication.authenticate("wrong username", "password"));
        assertTrue(Authentication.authenticate("test", "password"));
    }

    @After
    public void cleanUp() {
        staffDao.removeStaff(staff.getId());
    }
}
