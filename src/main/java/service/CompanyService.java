package service;

import Dao.CompanyDao;
import model.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CompanyService {

    private static CompanyService instance;
    private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
    private static final CompanyDao dao = CompanyDao.getInstance();

    public static CompanyService getInstance() {
        if (instance == null){
            instance = new CompanyService();
        }
        return instance;
    }

    public List<Company> getAll() {
        return dao.getAll();
    }

    public void create(Company company) {
        dao.create(company);
    }

    public Optional<Company> get(long id) {
        return dao.get(id);
    }

    public void delete(Company company) {
        dao.delete(company);
    }

    public void update(Company company) {
        dao.update(company);
    }

}