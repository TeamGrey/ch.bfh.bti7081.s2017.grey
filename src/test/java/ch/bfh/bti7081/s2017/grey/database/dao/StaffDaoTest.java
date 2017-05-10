package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

/**
 * @Author Quentin
 */
public class StaffDaoTest {

    @Test
    public void testCreateStaff() {
        // given
        Role role = RoleDao.getRoleById(1);

        // when
        StaffDao.createStaff("TestF", "TestL", "test", "password", role);

        // then
        assertNotNull(StaffDao.getStaffByLogin("test"));
    }

}