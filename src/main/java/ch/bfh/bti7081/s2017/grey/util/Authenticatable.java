package ch.bfh.bti7081.s2017.grey.util;

/**
 * Providing interface for authentication
 *
 * @author Joel
 */
public interface Authenticatable {

    /**
     *
     * @return whether authentication was successful
     */
    boolean authenticate();

    /**
     *
     * @return return current state of authentication
     */
    boolean isAuthenticated();
}
