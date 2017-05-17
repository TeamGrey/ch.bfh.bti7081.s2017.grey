package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;

/**
 * Created by gabor on 17/05/17.
 */
public class AppointmentStatusDao {

    public AppointmentStatus getAppointmentStatusById(long id) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        AppointmentStatus status = entityManager.find(AppointmentStatus.class, id);
        return status;
    }
    public AppointmentStatus getAppointmentStatusByName(String name) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        AppointmentStatus status = entityManager.find(AppointmentStatus.class, name);
        return status;
    }
}
