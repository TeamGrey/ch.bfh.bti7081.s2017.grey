package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Quentin
 */
public class StaffDaoTest {

    @Test
    public void testCreateStaff() {
        // given
        StaffDao staffDao = new StaffDao();
        Role role = RoleDao.getRoleById(1);

        // when
        staffDao.createStaff("TestF", "TestL", "test", "password", role);

        // then
        Staff staff = staffDao.getStaffByLogin("test");
        assertNotNull(staff);

        //clean up
        staffDao.removeStaff(staff.getId());

    }

}