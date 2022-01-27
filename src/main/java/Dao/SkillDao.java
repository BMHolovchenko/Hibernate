package Dao;

import model.Skill;

public class SkillDao extends AbstractDao<Skill> {

    private static SkillDao instance;

    public SkillDao() {
        super(Skill.class);
    }

    public static SkillDao getInstance() {
        if (instance == null) {
            instance = new SkillDao();
        }
        return instance;
    }
}