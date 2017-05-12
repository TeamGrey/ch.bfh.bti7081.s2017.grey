package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;

/**
 * Created by jo-ra on 05.05.2017.
 */
public class Authentication {

    /**
     * Authenticate user by username and password
     * compare with database
     *
     * @param login user reference
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(String login, String password) {
        Staff staff = (new StaffServiceImpl()).findStaffByLogin(login);
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
