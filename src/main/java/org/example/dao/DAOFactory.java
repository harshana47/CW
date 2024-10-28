package org.example.dao;

import org.example.dao.custom.impl.CourseDAOImpl;
import org.example.dao.custom.impl.StudentDAOImpl;
import org.example.dao.custom.impl.courseStudentDetailsDAOImpl;
import org.example.dao.custom.impl.userDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Course, Student, User, courseStudentDetails
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Course:
                return new CourseDAOImpl();
            case Student:
                return new StudentDAOImpl();
            case User:
                return new userDAOImpl();
            case courseStudentDetails:
                return new courseStudentDetailsDAOImpl();
            default:
                throw new IllegalStateException("Unexpected DAO Type");
        }
    }
}
