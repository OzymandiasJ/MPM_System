package com.ozy.Products.dao;

import com.ozy.beans.Product;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public interface ProductsDao {
    //获取所有的库存列表信息
    List<Product> getProductsList() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    //根据id获取商品信息
    Product getProductById(BigInteger id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    //修改商品信息
    void updateProduct(Product product) throws SQLException;
    //删除商品
    void deleteProduct(BigInteger id) throws SQLException;
    //添加商品
    void addProduct(Product product) throws SQLException;

    Product getProductByName(String searchName) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}