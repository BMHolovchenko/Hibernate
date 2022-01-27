package service;

import Dao.SkillDao;
import model.Skill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SkillService {

    private static SkillService instance;
    private static final Logger LOGGER = LogManager.getLogger(SkillService.class);
    private static final SkillDao dao = SkillDao.getInstance();

    public static SkillService getInstance() {
        if (instance == null){
            instance = new SkillService();
        }
        return instance;
    }

    public List<Skill> getAll() {
        return dao.getAll();
    }

    public void create(Skill skill) {
        dao.create(skill);
    }

    public Optional<Skill> get(long id) {
        return dao.get(id);
    }

    public void delete(Skill skill) {
        dao.delete(skill);
    }

    public void update(Skill skill) {
        dao.update(skill);
    }

}