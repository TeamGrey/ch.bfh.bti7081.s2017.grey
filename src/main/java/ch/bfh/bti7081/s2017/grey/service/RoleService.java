package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;

/**
 * @Author Quentin
 */
public interface RoleService {
    /**
     * Finds a role by it's id
     *
     * @param id Id of the role
     * @return Role if found
     */
    Role findRoleById(long id);
    /**
     * Creates a new role
     *
     * @param name Name of the role
     */
    void createRole(String name);
}
