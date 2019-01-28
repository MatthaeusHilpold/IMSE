package com.imse.imse.DAO;


import com.imse.imse.domain.Schooling;

import java.sql.SQLException;
import java.util.List;

public interface ISchoolingDAO {

        Schooling findById(String id);

        String findByName(String schoolingName) throws SQLException;

        void saveSchooling(Schooling schooling) throws SQLException;

        void deleteSchooling(int id) throws SQLException;

        String getAllSchoolings() throws SQLException;


}
