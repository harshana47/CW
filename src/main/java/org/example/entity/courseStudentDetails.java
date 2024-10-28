package org.example.entity;

import jakarta.persistence.*;

@Entity
public class courseStudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "sId")
    private Student student;

    private double fee;

    private String duration;

    public courseStudentDetails() {
    }

    public courseStudentDetails(Course course, Student student, double fee, String duration) {
        this.course = course;
        this.student = student;
        this.fee = fee;
        this.duration = duration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "courseStudentDetails{" +
                "course=" + course +
                ", student=" + student +
                ", fee=" + fee +
                ", duration='" + duration + '\'' +
                '}';
    }
}
