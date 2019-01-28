package com.imse.imse.service;

import com.imse.imse.DAO.EmployeeDAO;
import com.imse.imse.DAO.SchoolingDAO;
import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Schooling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class SchoolingService implements ISchoolingService {

    private SchoolingDAO schoolingDAO = new SchoolingDAO();

    @Override
    public Schooling findById(String id)  {
        return schoolingDAO.findById(id);
    };

    @Override
    public String findByName(String schoolingName) throws SQLException{
        return schoolingDAO.findByName(schoolingName);
    }

    @Override
    public void saveSchooling(Schooling schooling) throws SQLException {
        schoolingDAO.saveSchooling(schooling);
    }

    @Override
    public void deleteSchooling(int id) throws SQLException
    {
        schoolingDAO.deleteSchooling(id);
    }

    @Override
    public String getAllSchoolings() throws SQLException {
        return schoolingDAO.getAllSchoolings();
    }
}
