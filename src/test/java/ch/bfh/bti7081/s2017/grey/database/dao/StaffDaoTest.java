package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.*;

/**
 * @Author Quentin
 */
public class StaffDaoTest {

    @Test
    public void testCreateStaff() {
        Instant instant = Instant.now();
        Role role = RoleDao.getRoleById(1);
        StaffDao.createStaff("TestF", "TestL", "test", "password",
                new Timestamp(instant.toEpochMilli()), new Timestamp(instant.toEpochMilli()), role);

        assertNotNull(StaffDao.getStaffByLogin("test"));
    }

}