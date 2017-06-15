package ch.bfh.bti7081.s2017.grey.ui.drug;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.listener.DrugTaskFormViewListener;
import java.util.Collection;

/**
 * provide a Modal form for gathering Information about the new Task
 */
public interface DrugTaskFormView {

  /**
   * initialize form when opening
   *
   * @param task_id id of an existing task
   * @param taskName title for the task
   * @param drug assigned drug object
   * @param amount amount of drug as number
   * @param unit units for amount of drug
   */
  void open(Long task_id, String taskName, Drug drug, Integer amount, String unit);

  /**
   * open empty form
   */
  void open();

  /**
   * @param listener listener implementation of interface DrugTaskFormViewListener
   */
  void addDrugTaskFormViewListener(DrugTaskFormViewListener listener);

  /**
   * @param drugs collection of drugs to select the drug from
   */
  void setDrugList(Collection<Drug> drugs);

  Long getTaskId();

  String getTaskName();

  Drug getDrug();

  Integer getAmount();

  String getAmountUnit();
}
