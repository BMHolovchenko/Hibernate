package service;

import Dao.ProjectDao;
import model.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProjectService {

    private static ProjectService instance;
    private static final Logger LOGGER = LogManager.getLogger(ProjectService.class);
    private static final ProjectDao dao = ProjectDao.getInstance();

    public static ProjectService getInstance() {
        if (instance == null){
            instance = new ProjectService();
        }
        return instance;
    }

    public List<Project> getAll() {
        return dao.getAll();
    }

    public void create(Project project) {
        dao.create(project);
    }

    public Optional<Project> get(long id) {
        return dao.get(id);
    }

    public void delete(Project project) {
        dao.delete(project);
    }

    public void update(Project project) {
        dao.update(project);
    }

}