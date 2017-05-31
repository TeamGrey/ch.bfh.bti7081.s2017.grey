package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;

/**
 * @Author Quentin
 */
public interface DrugService {
    public void createDrug(String name);
    public void updateDrugName(Drug drug, String newName);
    public Drug getDrugById(long id);
}
