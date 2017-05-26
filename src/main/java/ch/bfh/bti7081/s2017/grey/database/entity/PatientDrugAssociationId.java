package ch.bfh.bti7081.s2017.grey.database.entity;

import java.io.Serializable;

/**
 * @author Quentin
 * @version 21.05.2017
 */
public class PatientDrugAssociationId implements Serializable {
    private long patientId;
    private long drugId;

    public int hashCode() {
        return (int) (patientId + drugId);
    }

    public boolean equals(Object object) {
        if (object instanceof PatientDrugAssociationId) {
            PatientDrugAssociationId otherId = (PatientDrugAssociationId) object;
            return (otherId.patientId == this.patientId) && (otherId.drugId == this.drugId);
        }
        return false;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }
}
