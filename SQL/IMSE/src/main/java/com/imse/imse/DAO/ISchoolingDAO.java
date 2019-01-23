package com.imse.imse.DAO;


import com.imse.imse.domain.Schooling;

import java.sql.SQLException;
import java.util.List;

public interface ISchoolingDAO {

        Schooling findById(String id);

        Schooling findByName(String schooling);

        void saveSchooling(Schooling schooling) throws SQLException;

        void deleteSchooling(int id) throws SQLException;


}
