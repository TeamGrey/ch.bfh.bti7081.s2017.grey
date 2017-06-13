package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.StaffDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.util.Authentication;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin.
 */
public class StaffServiceImpl implements StaffService {

  StaffDao dao;

  public StaffServiceImpl(EntityManager em) {
    dao = new StaffDao(em);
  }

  /**
   * Finds a task by login name
   *
   * @param login Login name
   * @return Staff if found
   */
  @Override
  public Staff findStaffByLogin(String login) {
    return dao.getStaffByLogin(login);
  }

  /**
   * Creates a new staff
   *
   * @param firstname First name of the staff
   * @param lastname Last name of the staff
   * @param login Login name of the staff
   * @param password Password of the staff
   * @param role Role that the staff should have
   * @return created staff
   */
  @Override
  public Staff createStaff(String firstname, String lastname, String login, String password,
      Role role) {
    Instant instant = Instant.now();
    Timestamp timestamp = new Timestamp(instant.toEpochMilli());

    Staff staff = new Staff();
    staff.setFirstname(firstname);
    staff.setLastname(lastname);
    staff.setLogin(login);
    String salt = Authentication.generateSalt();
    staff.setPwhash(Authentication.generateHash(password, salt));
    staff.setSalt(salt);
    staff.setRoles(role);
    staff.setChanged(timestamp);
    staff.setCreated(timestamp);

    return dao.create(staff);
  }

  /**
   * Deletes a staff
   *
   * @param staff staff to be deleted
   */
  @Override
  public void deleteStaff(Staff staff) {
    dao.delete(staff.getId());
  }

  /**
   * Finds all staff
   *
   * @return List of staff
   */
  @Override
  public List<Staff> getAllStaff() {
    return dao.findAll();
  }

  /**
   * Update a list of staff
   *
   * @param allStaff List of staff to be edited
   */
  @Override
  public void saveAllStaff(List<Staff> allStaff) {
    for (Staff staff : allStaff) {
      dao.update(staff);
    }
  }
}
