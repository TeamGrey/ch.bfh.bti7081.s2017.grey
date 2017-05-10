package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.dao.StaffDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

/**
 * Service for User authentication
 *
 * @author Joel
 */
public class Authentication {

    /**
     * Authenticate user by login and password
     * compare with database
     *
     * @param login login from user
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(String login, String password) {
        Staff staff = (new StaffDao()).getStaffByLogin(login);
        return  (staff.getPwhash().equals(Authentication.createHash(password)));
    }


    public static String createHash(String plain)  {
        return plain; // FIXME create hash
        //return String.valueOf(plain.hashCode());
    }
}
