package ch.bfh.bti7081.s2017.grey.util;

import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;

/**
 * Created by gabor on 17/05/17.
 */
public class UnsupportedStatusTransitionException extends Exception {

    public UnsupportedStatusTransitionException(String operation, AppointmentStatus status) {
        super("Cant use operation " + operation + " with status " + status.toString());
    }
}
