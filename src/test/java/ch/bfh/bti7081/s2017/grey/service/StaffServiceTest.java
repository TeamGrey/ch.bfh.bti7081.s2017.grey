package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.impl.RoleServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gabor on 29/05/17.
 */
public class StaffServiceTest {
    private StaffService staffService;
    private Staff staff;

    @Before
    public void setup() {
        staffService = new StaffServiceImpl();
        RoleService roleService = new RoleServiceImpl();

        Role role = roleService.findRoleById(1);

        staff = staffService.createStaff("TestF", "TestL", "test", "password", role);
    }

    @After
    public void cleanUp() {
        staffService.deleteStaff(staff);
    }

    @Test
    public void testCreateStaff() {
        assertNotNull(staff);
        Staff teststaff = staffService.findStaffByLogin("test");
        assertEquals(staff, teststaff);
    }
}
