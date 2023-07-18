package com.ozy.Products.dao.impl;

import com.ozy.Products.dao.ProductsDao;
import com.ozy.beans.Product;
import com.ozy.myssm.basedao.BaseDao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class ProductsDaoImpl extends BaseDao<Product> implements ProductsDao {
    @Override
    public List<Product> getProductsList() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return super.executeQuery(Product.class,"select * from products");
    }

    @Override
    public Product getProductById(BigInteger id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Product>list =super.executeQuery(Product.class,"select * from products where id=?",id);
        return list.get(0);
    }
    @Override
    public Product getProductByName(String searchName) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Product>list =super.executeQuery(Product.class,"select * from products where productName=?",searchName);
        return list.get(0);
    }
    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql="update products set productName=?,price=?,productRemain=?,inPrice=? where id=?";
        super.executeUpdate(sql,product.getProductName(),product.getPrice(),product.getProductRemain(),product.getInPrice(),product.getId());

    }
    @Override
    public void deleteProduct(BigInteger id) throws SQLException {
        String sql="DELETE FROM products WHERE id = ?";
        super.executeUpdate(sql,id);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql="INSERT INTO products (productName, price, productRemain, inPrice) VALUES (?, ?, ?, ?)";
        super.executeUpdate(sql,product.getProductName(),product.getPrice(),product.getProductRemain(),product.getInPrice());
    }



}
