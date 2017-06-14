package ch.bfh.bti7081.s2017.grey.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.impl.RoleServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gabor on 29/05/17.
 */
public class StaffServiceTest extends JPAHibernateTest{

  private StaffService staffService;
  private RoleService roleService;
  private Staff staff;

  @Before
  public void setup() {
    staffService = new StaffServiceImpl(em);
    roleService = new RoleServiceImpl(em);

    Role role = roleService.findRoleById(1);

    staff = staffService.createStaff("TestF", "TestL", "test", "password", role);
  }

  @Test
  public void testCreateStaff() {
    assertNotNull(staff);
    Staff testStaff = staffService.findStaffByLogin("test");
    assertEquals(staff, testStaff);
  }

}
