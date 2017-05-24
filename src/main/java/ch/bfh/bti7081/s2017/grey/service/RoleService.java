package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;

/**
 * @Author Quentin
 */
public interface RoleService {
    Role findRoleById(long id);

    void createRole(String name);
}
