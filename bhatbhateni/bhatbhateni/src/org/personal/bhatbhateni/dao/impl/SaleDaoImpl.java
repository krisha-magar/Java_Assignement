/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.bhatbhateni.dao.impl;

import org.personal.bhatbhateni.model.Sales;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.personal.bhatbhateni.connection.ConnectionFactory.getConnection;
import org.personal.bhatbhaneti.dao.SaleDao;

public class SaleDaoImpl implements SaleDao {

    @Override
    public int saleView (Sales sale) throws SQLException, ClassNotFoundException {
        String insertSQL = "insert into sales (customername,quantity, productname) values(?,?,?)";
        PreparedStatement prepareStatement = getConnection().prepareStatement(insertSQL);
        prepareStatement.setString(1, sale.getcustomerName());
        prepareStatement.setInt(2, sale.getproductQuantity());
        prepareStatement.setString(3, sale.getproductName());
        return prepareStatement.executeUpdate();
    }

    

   @Override
    public Sales saleFindOne(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from sales where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Sales sale = new Sales();
        while (resultSet.next()) {
            sale.setId(resultSet.getInt("id"));
            sale.setcustomerName(resultSet.getString("customername"));
            sale.setproductQuantity(resultSet.getInt("quantity"));
            sale.setproductName(resultSet.getString("productname"));
        }
        return sale;
    }

    @Override
    public List<Sales> saleFindAll() throws SQLException, ClassNotFoundException {
        List<Sales> sales = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from sales");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Sales sale = new Sales();
            sale.setId(resultSet.getInt("id"));
            sale.setcustomerName(resultSet.getString("customername"));
            
            sale.setproductQuantity(resultSet.getInt("quantity"));
             sale.setproductName(resultSet.getString("productname"));
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public List<Sales> saleSearch(String query) throws SQLException, ClassNotFoundException {
        List<Sales> sales = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(
                "select *from sales where customername like concat ('%' ? '%')"
                + "or customername like concat ('%' ? '%') "
                + "or cast(id as char) like ('%' ? '%') ");
        preparedStatement.setString(1, query);
        preparedStatement.setString(2, query);
        preparedStatement.setString(3, query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Sales sale = new Sales();
            sale.setId(resultSet.getInt("id"));
            sale.setcustomerName(resultSet.getString("customername"));
            sale.setproductQuantity(resultSet.getInt("quantity"));
          
            sales.add(sale);
        }
        return sales;
    } 
}
