package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.listener.CloseListener;

import java.util.Collection;

/**
 * @author Joel
 *         <p>
 *         View for a Formular for editing a Drug Task
 */
public interface DrugTaskFormView {

    void open(Long task_id, String taskName, Drug drug, Integer amount, String unit);

    void open();

    void addCloseListener(CloseListener listener);

    void setDrugList(Collection<Drug> drugs);

    Long getTaskId();

    String getTaskName();

    Drug getDrug();

    Integer getAmount();

    String getAmountUnit();
}
