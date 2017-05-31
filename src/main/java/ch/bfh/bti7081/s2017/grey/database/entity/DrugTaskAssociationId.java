package ch.bfh.bti7081.s2017.grey.database.entity;

import java.io.Serializable;

/**
 * @author Quentin
 * @version 21.05.2017
 */
public class DrugTaskAssociationId implements Serializable {
    private long drugId;
    private long taskId;

    public int hashCode() {
        return (int)(drugId + taskId);
    }

    public boolean equals(Object object) {
        if (object instanceof DrugTaskAssociationId) {
            DrugTaskAssociationId otherId = (DrugTaskAssociationId) object;
            return (otherId.taskId == this.taskId) && (otherId.drugId == this.drugId);
        }
        return false;
    }
}
