package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;
    private String name;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<courseStudentDetails> students = new ArrayList<>();

    public Course() {
    }

    public Course(int cId, String name, String duration, double fee, List<courseStudentDetails> students) {
        this.cId = cId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
        this.students = students;
    }

    public Course(int cId, String name, String duration, double fee) {
        this.cId = cId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<courseStudentDetails> getStudents() {
        return students;
    }

    public void setStudents(List<courseStudentDetails> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", students=" + students +
                '}';
    }
}
