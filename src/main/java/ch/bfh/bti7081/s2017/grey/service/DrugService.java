package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;

/**
 * @Author Quentin
 */
public interface DrugService {
    void createDrug(String name);
    void updateDrugName(Drug drug, String newName);
    Drug getDrugById(long id);
}
