package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

/**
 * Created by gabor on 29/05/17.
 */
public interface StaffDao extends GenericDao<Staff> {
    public Staff getStaffByLogin(String login);
}
