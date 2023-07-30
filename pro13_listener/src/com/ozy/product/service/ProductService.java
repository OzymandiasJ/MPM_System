package com.ozy.product.service;

import com.ozy.product.beans.Product;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> getProductList(String keyword, Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    void addProduct(Product product) throws SQLException;
    Product getProductByPid(Integer pid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    void delete(Integer pid) throws SQLException;
    Integer getPageCount(String keyword) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    void updateProduct(Product product) throws SQLException;
    Product getProductByName(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
}
