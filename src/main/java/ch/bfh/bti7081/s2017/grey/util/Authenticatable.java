package ch.bfh.bti7081.s2017.grey.util;

/**
 * Created by jo-ra on 05.05.2017.
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
