package org.example.bo;

import org.example.bo.custom.impl.CourseBOImpl;
import org.example.bo.custom.impl.StudentBOImpl;
import org.example.bo.custom.impl.courseStudentDetailsBOImpl;
import org.example.bo.custom.impl.userBOImpl;

public class BOFactory {
    private  static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)? boFactory = new BOFactory(): boFactory;
    }

    public enum BOTypes{
        Student, Course, User, courseStudentDetails
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Student:
                return new StudentBOImpl();
            case Course:
                return new CourseBOImpl();
            case User:
                return new userBOImpl();
            case  courseStudentDetails:
                return new courseStudentDetailsBOImpl();
            default:
                return null;
        }
    }


}
