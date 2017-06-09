package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.RoleDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.service.RoleService;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao dao;

    public RoleServiceImpl() {
        dao = new RoleDao();
    }

    /**
     * Finds a role by it's id
     * @param id Id of the role
     * @return Role if found
     */
    @Override
    public Role findRoleById(long id) {
        return dao.find(id);
    }

    /**
     * Creates a new role
     * @param name Name of the role
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
