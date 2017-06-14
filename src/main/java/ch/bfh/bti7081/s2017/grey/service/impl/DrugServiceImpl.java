package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.DrugDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.service.DrugService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class DrugServiceImpl implements DrugService {

  private DrugDao dao;

  public DrugServiceImpl(EntityManager em) {
    dao = new DrugDao(em);
  }

  /** @see DrugService#createDrug(String) */
  @Override
  public void createDrug(String name) {
    Instant instant = Instant.now();
    Drug drug = new Drug();
    drug.setName(name);
    drug.setCreated(new Timestamp(instant.toEpochMilli()));
    drug.setChanged(new Timestamp(instant.toEpochMilli()));

    dao.create(drug);
  }

  /** @see DrugService#updateDrugName(Drug, String) */
  @Override
  public void updateDrugName(Drug drug, String newName) {
    drug.setName(newName);
    Instant instant = Instant.now();
    drug.setChanged(new Timestamp(instant.toEpochMilli()));

    dao.update(drug);
  }

  /** @see DrugService#getDrugById(long) */
  @Override
  public Drug getDrugById(long id) {
    return dao.find(id);
  }

  /** @see DrugService#getAllDrugs() */
  @Override
  public List<Drug> getAllDrugs() {
    return dao.findAll();
  }
}
