package com.imse.imse.DAO;


import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Schooling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SchoolingDAO implements ISchoolingDAO {
    private static final Logger logger = LogManager.getLogger(SchoolingDAO.class);

    @Override
    public Schooling findById(String id) {
        return null;
    }

    @Override
    public Schooling findByName(String schooling) {
        return null;
    }

    @Override
    public void saveSchooling(Schooling schooling) throws SQLException {

        StringBuilder saveUserQuery = new StringBuilder();
        saveUserQuery.append("INSERT INTO Schooling(Schooling,Termin,CompanyUIDNumber,VALUES( ");
        saveUserQuery.append("'");
        saveUserQuery.append(schooling.getSchooling());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(schooling.getTermin());
        saveUserQuery.append("',");
        saveUserQuery.append("'");
        saveUserQuery.append(schooling.getCompanyUIDNUmber());
        saveUserQuery.append("')");


        DbConnection.executeQuery(saveUserQuery.toString());
    }

    @Override
    public void deleteSchooling(int id) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM Schooling WHERE SchoolingId = ");
        query.append(id);

        DbConnection.executeQuery(query.toString());
    }
}
