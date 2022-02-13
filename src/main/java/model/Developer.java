package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(generator = "developers_id_seq")
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developers_skills",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private transient List<Skill> skills = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developers_projects",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private transient List<Project> projects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", skills=" + skills +
                ", projects=" + projects +
                '}';
    }
}