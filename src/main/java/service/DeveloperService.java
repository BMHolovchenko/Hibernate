package service;

import Dao.DeveloperDao;
import model.Developer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class DeveloperService {

    private static DeveloperService instance;
    private static final Logger LOGGER = LogManager.getLogger(DeveloperService.class);
    private static final DeveloperDao dao = DeveloperDao.getInstance();


    public static DeveloperService getInstance() {
        if (instance == null){
            instance = new DeveloperService();
        }
        return instance;
    }

    public List<Developer> getAll(){
        return dao.getAll();
    }

    public void create(Developer developer) {
        dao.create(developer);
    }

    public Optional<Developer> get(long id) {
        return dao.get(id);
    }

    public void delete(Developer developer) {
        dao.delete(developer);
    }

    public void update(Developer developer) {
        dao.update(developer);
    }

}
