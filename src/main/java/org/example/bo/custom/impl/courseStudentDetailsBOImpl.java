package org.example.bo.custom.impl;

import org.example.bo.SuperBO;
import org.example.bo.custom.StudentBO;
import org.example.bo.custom.courseStudentDetailsBO;
import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.StudentDAO;
import org.example.dao.custom.courseStudentDetailsDAO;
import org.example.dto.CourseDTO;
import org.example.dto.StudentDTO;
import org.example.dto.courseStudentDetailsDTO;
import org.example.entity.Student;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class courseStudentDetailsBOImpl implements courseStudentDetailsBO {

    private courseStudentDetailsDAO studentDetailsDAO = (courseStudentDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.courseStudentDetails);


    @Override
    public boolean save(courseStudentDetailsDTO courseStudentDetailsDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(courseStudentDetailsDTO courseStudentDetailsDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public List<courseStudentDetailsDTO> getAll() throws Exception{
        List<courseStudentDetailsDTO> courseStudentDetailsList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            // HQL query to get all courseStudentDetails records
            String hql = "FROM courseStudentDetails"; // Assuming courseStudentDetails is your entity class
            Query<courseStudentDetails> query = session.createQuery(hql, courseStudentDetails.class);
            List<courseStudentDetails> results = query.list();

            // Convert results to DTOs
            for (courseStudentDetails entity : results) {
                courseStudentDetailsDTO dto = new courseStudentDetailsDTO(
                        entity.getCourse().getcId(), // Assuming you want to get course ID
                        entity.getStudent().getsId(), // Assuming you want to get student ID
                        entity.getFee(),
                        entity.getDuration()
                );
                courseStudentDetailsList.add(dto);
            }

            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback if there's an error
            }
            throw e; // Rethrow the exception for handling elsewhere
        }

        return courseStudentDetailsList;
    }

    @Override
    public courseStudentDetailsDTO findById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }
}


