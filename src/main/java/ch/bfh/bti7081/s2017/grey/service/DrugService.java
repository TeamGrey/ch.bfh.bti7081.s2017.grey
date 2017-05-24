package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;

/**
 * @Author Quentin
 */
public interface DrugService {
    void createDrug(String name);

    Drug getDrugById(long id);
}
