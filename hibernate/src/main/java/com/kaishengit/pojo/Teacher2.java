package com.kaishengit.pojo;


import java.util.Set;

public class Teacher2 {
    private Integer id;
    private String teaname;
    private Set<Student2> student2Set;

    @Override
    public String toString() {
        return "Teacher2{" +
                "id=" + id +
                ", teaname='" + teaname + '\'' +
                ", student2Set=" + student2Set +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public Set<Student2> getStudent2Set() {
        return student2Set;
    }

    public void setStudent2Set(Set<Student2> student2Set) {
        this.student2Set = student2Set;
    }
}
