package com.ozy.product.dao;

import com.ozy.product.beans.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    //获取指定页码上的商品列表信息，每页5条
    List<Product>getProductsList(String keyword, Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    //获取所有的库存列表信息
    List<Product> getProductsList() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    //根据id获取商品信息
    Product getProductById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    //修改商品信息
    void updateProduct(Product product) throws SQLException;
    //删除商品
    void deleteProduct(Integer id) throws SQLException;
    //添加商品
    void addProduct(Product product) throws SQLException;

    Product getProductByName(String searchName) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    int getProductsCount() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException;
}