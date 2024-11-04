package org.example.config;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.courseStudentDetails;
import org.example.entity.user;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration configuration = new Configuration()
                .addAnnotatedClass(user.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(courseStudentDetails.class);

        configuration.setProperties(properties);
        sessionFactory = configuration.buildSessionFactory();
        System.out.println("SessionFactory initialized successfully.");
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
