package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class courseStudentDetailsBOImpl implements StudentBO {

    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Student);
    private courseStudentDetailsDAO studentDetailsDAO = (courseStudentDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.courseStudentDetails);

    @Override
    public boolean SaveStudent(StudentDTO studentDTO, List<courseStudentDetailsDTO> courseStudentDetailsDTOs) throws SQLException, ClassNotFoundException {
        Session session = null;
        Transaction transaction = null;
        boolean isSaved = false;

        try {
            // Open a session and start a transaction
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Convert DTO to entity and save the student
            Student studentEntity = new Student(studentDTO.getsId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getPayment(), studentDTO.getRegisteredDate());
            isSaved = studentDAO.save(studentEntity, session);

            // If the student was saved, save the course details
            if (isSaved) {
                for (courseStudentDetailsDTO detailDTO : courseStudentDetailsDTOs) {
                    courseStudentDetails detailEntity = new courseStudentDetails();
                    detailEntity.setStudent(studentEntity); // Associate the student
                    detailEntity.setCourse(detailDTO.getCourse()); // Set the course
                    detailEntity.setFee(detailDTO.getFee());
                    detailEntity.setDuration(detailDTO.getDuration());

                    // Save each course student detail
                    if (!studentDetailsDAO.save(detailEntity, session)) {
                        isSaved = false; // If any save fails, set isSaved to false
                        break;
                    }
                }
            }

            // Commit the transaction if all operations were successful
            if (isSaved) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            // Handle exceptions
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return isSaved;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            // Open a session and start a transaction
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Convert DTO to entity and update the student
            Student studentEntity = new Student(studentDTO.getsId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getPayment(), studentDTO.getRegisteredDate());
            isUpdated = studentDAO.update(studentEntity, session);

            if (isUpdated) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isUpdated;
    }

    @Override
    public boolean deleteStudent(int sId) {
        Session session = null;
        Transaction transaction = null;
        boolean isDeleted = false;

        try {
            // Open a session and start a transaction
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Delete the student
            isDeleted = studentDAO.delete(sId, session);

            if (isDeleted) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isDeleted;
    }

    @Override
    public StudentDTO findStudentById(int sId) {
        Student student = studentDAO.search(sId);
        if (student != null) {
            return new StudentDTO(student.getsId(), student.getName(), student.getContact(), student.getPayment(), student.getRegisteredDate());
        }
        return null; // Return null if not found
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(new StudentDTO(student.getsId(), student.getName(), student.getContact(), student.getPayment(), student.getRegisteredDate()));
        }
        return studentDTOs;
    }

    @Override
    public int getNextStudentId() throws SQLException {
        return 0;
    }

    @Override
    public List<CourseDTO> getRegisteredCourses(int studentId) throws SQLException {
        return List.of();
    }
}
