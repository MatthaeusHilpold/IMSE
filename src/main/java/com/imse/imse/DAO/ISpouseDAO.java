package com.imse.imse.DAO;

import com.imse.imse.SpouseUpdatePayload;
import com.imse.imse.domain.Spouse;

import java.sql.SQLException;

public interface ISpouseDAO {

    Spouse findById(String id);

    Spouse findBySurname(String surname);

    void updateSpouse(SpouseUpdatePayload spouseUpdatePayload) throws SQLException;

    void deleteSpouseById(int id) throws SQLException;

}
