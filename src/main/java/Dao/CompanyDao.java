package Dao;

import config.PersistenceProvider;
import model.Company;

public class CompanyDao extends AbstractDao<Company>{

    private static CompanyDao instance;

    public CompanyDao() {
        super(Company.class);
    }

    public static CompanyDao getInstance() {
        if (instance == null) {
            instance = new CompanyDao();
        }
        return instance;
    }
}
