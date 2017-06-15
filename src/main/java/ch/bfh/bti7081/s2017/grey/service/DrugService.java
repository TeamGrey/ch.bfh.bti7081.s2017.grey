package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import java.util.List;

/**
 * @Author Quentin
 */
public interface DrugService {

  /**
   * Creates a new drug
   *
   * @param name Name of the drug
   */
  void createDrug(String name);

  /**
   * Changes the drug name
   *
   * @param drug Drug to be added
   * @param newName New name of the drug
   */
  void updateDrugName(Drug drug, String newName);

  /**
   * Finds a drug by it's id
   *
   * @param id Id of the drug
   * @return Drug if found
   */
  Drug getDrugById(long id);

  /**
   * Returns all drugs
   *
   * @return List of drugs
   */
  List<Drug> getAllDrugs();
}
