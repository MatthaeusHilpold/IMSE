package com.imse.imse.DAO;

import com.imse.imse.config.DbConnection;
import com.imse.imse.domain.Spouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SpouseDAO implements ISpouseDAO {

    private static final Logger logger = LogManager.getLogger(SpouseDAO.class);

    @Override
    public Spouse findById(String id) {
        return null;
    }

    @Override
    public Spouse findBySurname(String surname) {
        return null;
    }

//    @Override
//    public void updateSpouse(SpouseUpdatePayload spouseUpdatePayload) throws SQLException {
//
//        StringBuilder query = new StringBuilder();
//        query.append("UPDATE Spouse SET ");
//        query.append(spouseUpdatePayload.getWhatToUpdate());
//        query.append(" WHERE ");
//        query.append(spouseUpdatePayload.getWhereToUpdate());
//
//        DbConnection.executeQuery(query.toString());
//    }

    @Override
    public void deleteSpouseById(int id) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM Spouse WHERE SpouseId = ");
        query.append(id);

        DbConnection.executeQuery(query.toString());
    }
}

