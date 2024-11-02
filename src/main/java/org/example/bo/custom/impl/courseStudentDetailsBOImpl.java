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
    public List<courseStudentDetailsDTO> getAll() throws Exception {
        List<courseStudentDetails> results = studentDetailsDAO.getAll();
        List<courseStudentDetailsDTO> courseStudentDetailsList = new ArrayList<>();

        for (courseStudentDetails entity : results) {
            courseStudentDetailsDTO dto = new courseStudentDetailsDTO(
                    entity.getCourse().getcId(),
                    entity.getStudent().getsId(),
                    entity.getFee(),
                    entity.getDuration()
            );
            courseStudentDetailsList.add(dto);
        }

        return courseStudentDetailsList;
    }


    @Override
    public courseStudentDetailsDTO findById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<courseStudentDetailsDTO> getByCourseId(int courseId) throws Exception {
        List<courseStudentDetails> results = studentDetailsDAO.getDetailsByCourseIds(courseId);
        List<courseStudentDetailsDTO> courseStudentDetailsList = new ArrayList<>();

        for (courseStudentDetails entity : results) {
            courseStudentDetailsDTO dto = new courseStudentDetailsDTO(
                    entity.getCourse().getcId(),
                    entity.getStudent().getsId(),
                    entity.getFee(),
                    entity.getDuration()
            );
            courseStudentDetailsList.add(dto);
        }

        return courseStudentDetailsList;
    }

}


