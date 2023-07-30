package com.ozy.product.dao.impl;

import com.ozy.product.dao.ProductDao;
import com.ozy.product.beans.Product;
import com.ozy.myssm.basedao.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {
    @Override
    public List<Product> getProductsList(String keyword, Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if(keyword.equals("all")){
            return super.executeQuery(Product.class,"select * from products  limit ?,5",(pageNo-1)*5);
        }else {
            return super.executeQuery(Product.class,"select * from products  where productName like ?","%"+keyword+"%");
        }
    }

    @Override
    public List<Product> getProductsList() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.executeQuery(Product.class,"select * from products");
    }

    @Override
    public Product getProductById(Integer id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Product>list =super.executeQuery(Product.class,"select * from products where id=?",id);
        return list.get(0);
    }
    @Override
    public Product getProductByName(String searchName) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Product>list =super.executeQuery(Product.class,"select * from products where productName=?",searchName);
        return list.get(0);
    }

    @Override
    public int getProductsCount() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Product>list =super.executeQuery(Product.class,"select * from products");
        return list.size();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql="update products set productName=?,price=?,productRemain=?,inPrice=? where id=?";
        super.executeUpdate(sql,product.getProductName(),product.getPrice(),product.getProductRemain(),product.getInPrice(),product.getId());

    }
    @Override
    public void deleteProduct(Integer id) throws SQLException {
        String sql="DELETE FROM products WHERE id = ?";
        super.executeUpdate(sql,id);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql="INSERT INTO products (productName, price, productRemain, inPrice) VALUES (?, ?, ?, ?)";
        super.executeUpdate(sql,product.getProductName(),product.getPrice(),product.getProductRemain(),product.getInPrice());
    }
}
