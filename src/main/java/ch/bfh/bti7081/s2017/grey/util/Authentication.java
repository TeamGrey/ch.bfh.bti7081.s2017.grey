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
     * @param login user reference
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(String login, String password) {
        Staff staff = StaffDao.getStaffByLogin(login);
        return authenticate(staff, password);
    }

    /**
     * Authenticate user by entity and password
     *
     * @param user user entity
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(Staff user, String password){
        return (user.getPwhash().equals(Authentication.createHash(password)));
    }


    public static String createHash(String plain)  {
        return plain; // FIXME create hash
        //return String.valueOf(plain.hashCode());
    }
}
