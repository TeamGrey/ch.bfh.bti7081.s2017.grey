package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.DrugDao;
import ch.bfh.bti7081.s2017.grey.database.dao.impl.DrugDaoImpl;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.service.DrugService;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class DrugServiceImpl implements DrugService {
    private DrugDao dao;

    public DrugServiceImpl() {
        dao = new DrugDaoImpl();
    }

    @Override
    public void createDrug(String name) {
        Instant instant = Instant.now();
        Drug drug = new Drug();
        drug.setName(name);
        drug.setCreated(new Timestamp(instant.toEpochMilli()));
        drug.setChanged(new Timestamp(instant.toEpochMilli()));

        dao.create(drug);
    }

    @Override
    public void updateDrugName(Drug drug, String newName) {
        drug.setName(newName);
        Instant instant = Instant.now();
        drug.setChanged(new Timestamp(instant.toEpochMilli()));

        dao.update(drug);
    }

    @Override
    public Drug getDrugById(long id) {
        return dao.find(id);
    }

}
