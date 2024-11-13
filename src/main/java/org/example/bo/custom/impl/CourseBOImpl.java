package org.example.bo.custom.impl;

import org.example.bo.custom.CourseBO;
import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CourseDAO;
import org.example.dao.custom.courseStudentDetailsDAO;
import org.example.dto.CourseDTO;
import org.example.entity.Course;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Course);
    private courseStudentDetailsDAO studentDetailsDAO = (courseStudentDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.courseStudentDetails); ;

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        return courseDAO.save(new Course(courseDTO.getcId(), courseDTO.getName(), courseDTO.getDuration(), courseDTO.getFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.update(new Course(courseDTO.getcId(), courseDTO.getName(), courseDTO.getDuration(), courseDTO.getFee()));
    }

    @Override
    public boolean deleteCourse(int cId) {
        Session session = null;
        Transaction transaction = null;
        boolean isDeleted = false;
        try {
            // Open a session and start a transaction
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            List<courseStudentDetails> details = studentDetailsDAO.getDetailsByCourseId(cId, session);
            for (courseStudentDetails detail : details) {
                if (!studentDetailsDAO.delete(detail.getId(), session)) {
                    transaction.rollback();
                    return false;
                }
            }

            isDeleted = courseDAO.delete(cId, session);

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
    public List<CourseDTO> getAllCoursers() {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        List<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getcId(),course.getName(),course.getDuration(),course.getFee());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public CourseDTO findCourseById(int id) {
        Course course = courseDAO.search(id);
        CourseDTO courseDTO = new CourseDTO(course.getcId(), course.getName(), course.getDuration(), course.getFee());
        return courseDTO;
    }

    public int getNextCourseId(){
        return courseDAO.getNextId();
    }

    @Override
    public int getCourseCount() {
        return courseDAO.getCount();
    }
}
