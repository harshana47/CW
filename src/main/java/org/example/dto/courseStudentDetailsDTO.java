package org.example.dto;

import org.example.entity.Course;

public class courseStudentDetailsDTO {
    private int cId;
    private int sId;
    private double fee;
    private String duration;
    private Course course;
    private double payment;


    public courseStudentDetailsDTO() {
    }

    public courseStudentDetailsDTO(int cId, int sId, double fee, String duration, Course course, double payment) {
        this.cId = cId;
        this.sId = sId;
        this.fee = fee;
        this.duration = duration;
        this.course = course;
        this.payment = payment;
    }

    public courseStudentDetailsDTO(int cId, int sId, double fee, String duration, Course course) {
        this.cId = cId;
        this.sId = sId;
        this.fee = fee;
        this.duration = duration;
        this.course = course;
    }

    public courseStudentDetailsDTO(int cId, int sId, double fee, String duration) {
        this.cId = cId;
        this.sId = sId;
        this.fee = fee;
        this.duration = duration;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "courseStudentDetailsDTO{" +
                "cId=" + cId +
                ", sId=" + sId +
                ", fee=" + fee +
                ", duration='" + duration + '\'' +
                ", course=" + course +
                ", payment=" + payment +
                '}';
    }
}
