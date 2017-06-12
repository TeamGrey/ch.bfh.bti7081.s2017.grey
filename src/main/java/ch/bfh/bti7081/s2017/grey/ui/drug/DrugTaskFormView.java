package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskFormViewListener;

import java.util.Collection;

/**
 * @author Joel
 *         <p>
 *         View for a Formular for editing a Drug Task
 */
public interface DrugTaskFormView {

    void open(Long task_id, String taskName, Drug drug, Integer amount, String unit);

    void open();

    void addDrugTaskFormViewListener(DrugTaskFormViewListener listener);

    void setDrugList(Collection<Drug> drugs);

    Long getTaskId();

    String getTaskName();

    Drug getDrug();

    Integer getAmount();

    String getAmountUnit();
}
