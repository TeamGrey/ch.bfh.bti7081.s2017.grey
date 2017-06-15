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
   * @see StaffService#findStaffByLogin(String)
   */
  @Override
  public Staff findStaffByLogin(String login) {
    return dao.getStaffByLogin(login);
  }

  /**
   * @see StaffService#createStaff(String, String, String, String, Role)
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
   * @see StaffService#deleteStaff(Staff)
   */
  @Override
  public void deleteStaff(Staff staff) {
    dao.delete(staff.getId());
  }

  /**
   * @see StaffService#getAllStaff()
   */
  @Override
  public List<Staff> getAllStaff() {
    return dao.findAll();
  }

  /**
   * @see StaffService#saveAllStaff(List<Staff>)
   */
  @Override
  public void saveAllStaff(List<Staff> allStaff) {
    for (Staff staff : allStaff) {
      dao.update(staff);
    }
  }
}
