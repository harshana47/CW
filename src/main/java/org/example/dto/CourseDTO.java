package org.example.dto;

public class CourseDTO {
    private int cId;
    private String name;
    private String duration;
    private double fee;

    public CourseDTO() {
    }

    public CourseDTO(int cId, String name, String duration, double fee) {
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

    @Override
    public String toString() {
        return "CourseDTO{" +
                "cId=" + cId +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
