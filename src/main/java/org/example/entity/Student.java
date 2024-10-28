package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sId;
    private String name;
    private String contact;
    private double payment;
    private String registeredDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<courseStudentDetails> enrolledPrograms = new ArrayList<>();

    public Student() {
    }

    public Student(int sId, String name, String contact, double payment, String registeredDate, List<courseStudentDetails> enrolledPrograms) {
        this.sId = sId;
        this.name = name;
        this.contact = contact;
        this.payment = payment;
        this.registeredDate = registeredDate;
        this.enrolledPrograms = enrolledPrograms;
    }

    public Student(int sId, String name, String contact, double payment, String registeredDate) {
        this.sId = sId;
        this.name = name;
        this.contact = contact;
        this.payment = payment;
        this.registeredDate = registeredDate;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public List<courseStudentDetails> getEnrolledPrograms() {
        return enrolledPrograms;
    }

    public void setEnrolledPrograms(List<courseStudentDetails> enrolledPrograms) {
        this.enrolledPrograms = enrolledPrograms;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", payment='" + payment + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                ", enrolledPrograms=" + enrolledPrograms +
                '}';
    }
}
