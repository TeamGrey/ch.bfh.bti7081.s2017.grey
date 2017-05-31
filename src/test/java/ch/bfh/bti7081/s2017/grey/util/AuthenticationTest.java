package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.RoleService;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.RoleServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Joel
 */
public class AuthenticationTest {
    private RoleService roleService;
    private StaffService staffService;
    private Staff staff;

    @Before
    public void prepare() {
        roleService = new RoleServiceImpl();
        staffService = new StaffServiceImpl();
        Role role = roleService.findRoleById(1);

        staff = staffService.createStaff("TestA", "TestL", "test", "password", role);
    }

    @Test
    public void testAuthentication() {
        assertFalse(Authentication.authenticate("test", "wrong password"));
        assertFalse(Authentication.authenticate("wrong username", "password"));
        assertTrue(Authentication.authenticate("test", "password"));
    }

    @After
    public void cleanUp() {
        staffService.deleteStaff(staff);
    }
}
