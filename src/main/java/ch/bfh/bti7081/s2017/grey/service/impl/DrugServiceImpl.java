package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.DrugDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.service.DrugService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class DrugServiceImpl implements DrugService {
    private DrugDao dao;

    public DrugServiceImpl() {
        dao = new DrugDao();
    }

    /**
     * Creates a new drug
     * @param name Name of the drug
     */
    @Override
    public void createDrug(String name) {
        Instant instant = Instant.now();
        Drug drug = new Drug();
        drug.setName(name);
        drug.setCreated(new Timestamp(instant.toEpochMilli()));
        drug.setChanged(new Timestamp(instant.toEpochMilli()));

        dao.create(drug);
    }

    /**
     * Changes the drug name
     * @param drug Drug to be added
     * @param newName New name of the drug
     */
    @Override
    public void updateDrugName(Drug drug, String newName) {
        drug.setName(newName);
        Instant instant = Instant.now();
        drug.setChanged(new Timestamp(instant.toEpochMilli()));

        dao.update(drug);
    }

    /**
     * Finds a drug by it's id
     * @param id Id of the drug
     * @return Drug if found
     */
    @Override
    public Drug getDrugById(long id) {
        return dao.find(id);
    }

    /**
     * Returns all drugs
     * @return List of drugs
     */
    @Override
    public List<Drug> getAllDrugs() {
        return dao.findAll();
    }

}
