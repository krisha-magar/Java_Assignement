
package org.personal.departmentstore.home;

import java.sql.SQLException;
import java.util.List;
public interface Productsdao {
    
    int save(Products product) throws SQLException, ClassNotFoundException;

    int update(Products product, int id) throws SQLException, ClassNotFoundException;

    int remove(int id) throws SQLException, ClassNotFoundException;

    Products findOne(int id) throws SQLException, ClassNotFoundException;

    List<Products> findAll() throws SQLException, ClassNotFoundException;
    
    List<Products> search(String query) throws SQLException, ClassNotFoundException;
}

