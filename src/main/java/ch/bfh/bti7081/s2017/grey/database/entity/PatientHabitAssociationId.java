package ch.bfh.bti7081.s2017.grey.database.entity;

import java.io.Serializable;

/**
 * @author Quentin
 * @version 21.05.2017
 */
public class PatientHabitAssociationId implements Serializable {

    private long patientId;

    private long habitId;

    public int hashCode() {
        return (int)(patientId + habitId);
    }

    public boolean equals(Object object) {
        if (object instanceof PatientHabitAssociationId) {
            PatientHabitAssociationId otherId = (PatientHabitAssociationId) object;
            return (otherId.patientId == this.patientId) && (otherId.habitId == this.habitId);
        }
        return false;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getHabitId() {
        return habitId;
    }

    public void setHabitId(long habitId) {
        this.habitId = habitId;
    }
}
