package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.RoleDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.service.RoleService;
import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class RoleServiceImpl implements RoleService {

  private RoleDao dao;

  public RoleServiceImpl(EntityManager em) {
    dao = new RoleDao(em);
  }

  /**
   * @see RoleService#findRoleById(long)
   */
  @Override
  public Role findRoleById(long id) {
    return dao.find(id);
  }

  /**
   * @see RoleService#createRole(String)
   */
  @Override
  public void createRole(String name) {
    Instant instant = Instant.now();
    Timestamp timestamp = new Timestamp(instant.toEpochMilli());

    Role role = new Role();
    role.setName(name);
    role.setChanged(timestamp);
    role.setChanged(timestamp);

    dao.create(role);
  }
}
