package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.service.StaffService;
import ch.bfh.bti7081.s2017.grey.service.impl.StaffServiceImpl;

import javax.persistence.NoResultException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * This Util provides handy functions for the authentication
 *
 * @author Joel
 */
public class Authentication {

    private static StaffService staffService = null;

    /**
     * Authenticate user by username and password
     * compare with database
     *
     * @param login user reference
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(String login, String password) {
        if (staffService == null)
        {
            // kind of singleton pattern for staffService
            staffService = new StaffServiceImpl();
        }
        Staff user;
        try {
            user = staffService.findStaffByLogin(login);
        } catch (NoResultException ex){
            return false;
        }
        return authenticate(user, password);
    }

    /**
     * Authenticate user by entity and password
     *
     * @param user user entity
     * @param password password used by user
     * @return {Boolean}
     */
    public static Boolean authenticate(Staff user, String password){
        return (user.getPwhash().equals(Authentication.generateHash(password, user.getSalt())));
    }


    /**
     * Hashing function used for hashing user passwords
     *
     * @param password plaintext string to be hashed
     * @return hashed string
     */
    public static String generateHash(String password, String salt)  {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final String passwordAndSalt = password + salt;
            return Base64.getEncoder().encodeToString(digest.digest(passwordAndSalt.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSalt() {
        final Random r = new SecureRandom();
        final byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
