package com.kaishengit.pojo;


import java.util.Set;

public class Student2 {
    private Integer id;
    private String stuname;
    private Set<Teacher2> teacher2Set;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Set<Teacher2> getTeacher2Set() {
        return teacher2Set;
    }

    public void setTeacher2Set(Set<Teacher2> teacher2Set) {
        this.teacher2Set = teacher2Set;
    }
}
