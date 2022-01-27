package Dao;

import model.Developer;

public class DeveloperDao extends AbstractDao<Developer> {

    private static DeveloperDao instance;

    public DeveloperDao() {
        super(Developer.class);
    }

    public static DeveloperDao getInstance() {
        if (instance == null) {
            instance = new DeveloperDao();
        }
        return instance;
    }
}
