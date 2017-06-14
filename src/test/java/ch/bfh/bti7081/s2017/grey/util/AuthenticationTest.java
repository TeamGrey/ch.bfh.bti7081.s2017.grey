package ch.bfh.bti7081.s2017.grey.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.RoleService;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.RoleServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Author Joel
 */
public class AuthenticationTest extends JPAHibernateTest {

  private RoleService roleService;
  private StaffService staffService;

  @Before
  public void prepare() {
    roleService = new RoleServiceImpl(em);
    staffService = new StaffServiceImpl(em);
    Role role = roleService.findRoleById(1);
    staffService.createStaff("TestA", "TestL", "test", "password", role);
  }

  @Test
  public void testAuthentication() {
    assertFalse(Authentication.authenticate("test", "wrong password"));
    assertFalse(Authentication.authenticate("wrong username", "password"));
    Staff staff = staffService.findStaffByLogin("test");
    assertTrue(Authentication.authenticate(staff, "password"));
  }

}
