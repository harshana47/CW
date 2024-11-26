package org.example.entity;

import jakarta.persistence.*;

@Entity
public class courseStudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String duration;
    private double fee;
    private double payment;

    @ManyToOne
    @JoinColumn(name = "cId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "sId")
    private Student student;

    public courseStudentDetails() {
    }

    public courseStudentDetails(int id, String duration, double fee, double payment, Course course, Student student) {
        this.id = id;
        this.duration = duration;
        this.fee = fee;
        this.payment = payment;
        this.course = course;
        this.student = student;
    }

    public courseStudentDetails(Course course, Student student, double fee, String duration) {
        this.course = course;
        this.student = student;
        this.fee = fee;
        this.duration = duration;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public courseStudentDetails(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", payment=" + payment +
                ", course=" + course +
                ", student=" + student +
                '}';
    }
}
