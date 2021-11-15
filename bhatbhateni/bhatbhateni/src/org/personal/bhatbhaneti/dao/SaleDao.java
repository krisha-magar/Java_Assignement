package org.personal.bhatbhaneti.dao;

import org.personal.bhatbhateni.model.Sales;
import java.sql.SQLException;
import java.util.List;

public interface SaleDao {

    int saleView (Sales sale) throws SQLException, ClassNotFoundException;

    Sales saleFindOne(int id) throws SQLException, ClassNotFoundException;

    List<Sales> saleFindAll() throws SQLException, ClassNotFoundException;

    List<Sales> saleSearch(String query) throws SQLException, ClassNotFoundException;

    
}
