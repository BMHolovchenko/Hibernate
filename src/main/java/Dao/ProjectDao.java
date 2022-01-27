package Dao;

import model.Project;

public class ProjectDao extends AbstractDao <Project> {

    private static ProjectDao instance;

    public ProjectDao() {
        super(Project.class);
    }

    public static ProjectDao getInstance() {
        if (instance == null) {
            instance = new ProjectDao();
        }
        return instance;
    }

}