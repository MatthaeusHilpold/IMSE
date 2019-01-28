package com.imse.imse.service;


import com.imse.imse.domain.Schooling;

import java.sql.SQLException;
import java.util.List;

public interface ISchoolingService {
    Schooling findById(String ID);

    String findByName(String schoolingName) throws SQLException;

    void saveSchooling(Schooling schooling) throws SQLException;

    void deleteSchooling(int id) throws SQLException;

    String getAllSchoolings() throws SQLException;
}
