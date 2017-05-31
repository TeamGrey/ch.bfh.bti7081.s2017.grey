package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.DrugDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.service.DrugService;

/**
 * @Author Quentin
 */
public class DrugServiceImpl implements DrugService {

    private DrugDao drugDao;

    public DrugServiceImpl() {
        drugDao = new DrugDao();
    }

    @Override
    public void createDrug(String name) {
        drugDao.createDrug(name);
    }

    @Override
    public Drug getDrugById(long id) {
        return drugDao.getDrugById(id);
    }

}
