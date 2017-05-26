package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.RoleDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.service.RoleService;

/**
 * @Author Quentin
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDao();
    }

    @Override
    public Role findRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void createRole(String name) {
        roleDao.createRole(name);
    }
}
