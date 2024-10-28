package org.example.dto;

public class StudentDTO {
    private int sId;
    private String name;
    private String contact;
    private double payment;
    private String registeredDate;

    public StudentDTO() {
    }

    public StudentDTO(int sId, String name, String contact, double payment, String registeredDate) {
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sId=" + sId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", payment='" + payment + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
