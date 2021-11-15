/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.bhatbhateni.dao.impl;

import org.personal.bhatbhateni.model.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.personal.bhatbhateni.connection.ConnectionFactory.getConnection;
import org.personal.bhatbhaneti.dao.ProductDao;

public class ProductDaoImpl implements ProductDao {

    @Override
    public int save(Products product) throws SQLException, ClassNotFoundException {
        String insertSQL = "insert into products (name, category, quantity, price) values(?,?,?,?)";
        PreparedStatement prepareStatement = getConnection().prepareStatement(insertSQL);
        prepareStatement.setString(1, product.getproductName());
        prepareStatement.setString(2, product.getproductCategory());
        prepareStatement.setInt(3, product.getproductQuantity());
        prepareStatement.setDouble(4, product.getproductPrice());
        return prepareStatement.executeUpdate();
    }

    @Override
    public int update(Products product, int id) throws SQLException, ClassNotFoundException {
        String updateSQL = "update products set name = ?, category = ?,"
                + " quantity = ?, price = ? where id = ?";
        PreparedStatement prepareStatement = getConnection().prepareStatement(updateSQL);
        prepareStatement.setString(1, product.getproductName());
        prepareStatement.setString(2, product.getproductCategory());
        prepareStatement.setInt(3, product.getproductQuantity());
        prepareStatement.setDouble(4, product.getproductPrice());
        prepareStatement.setInt(5, product.getId());
        return prepareStatement.executeUpdate();
    }

    @Override
    public int remove(int id) throws SQLException, ClassNotFoundException {
        String deleteSQL = "delete from products where id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteSQL);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public Products findOne(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from products where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Products product = new Products();
        while (resultSet.next()) {
            product.setId(resultSet.getInt("id"));
            product.setproductName(resultSet.getString("name"));
            product.setproductCategory(resultSet.getString("category"));
            product.setproductQuantity(resultSet.getInt("quantity"));
            product.setproductPrice(resultSet.getDouble("price"));
        }
        return product;
    }

    @Override
    public List<Products> findAll() throws SQLException, ClassNotFoundException {
        List<Products> products = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select *from products");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Products product = new Products();
            product.setId(resultSet.getInt("id"));
            product.setproductName(resultSet.getString("name"));
            product.setproductCategory(resultSet.getString("category"));
            product.setproductQuantity(resultSet.getInt("quantity"));
            product.setproductPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Products> search(String query) throws SQLException, ClassNotFoundException {
        List<Products> products = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(
                "select *from products where name like concat ('%' ? '%')"
                + "or name like concat ('%' ? '%') "
                + "or  category like concat ('%' ? '%') "
                + "or cast(id as char) like ('%' ? '%') "
                + "or cast(price as char) like ('%' ? '%')");
        preparedStatement.setString(1, query);
        preparedStatement.setString(2, query);
        preparedStatement.setString(3, query);
        preparedStatement.setString(4, query);
        preparedStatement.setString(5, query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Products product = new Products();
            product.setId(resultSet.getInt("id"));
            product.setproductName(resultSet.getString("name"));
            product.setproductCategory(resultSet.getString("category"));
            product.setproductQuantity(resultSet.getInt("quantity"));
            product.setproductPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        return products;
    }
}
