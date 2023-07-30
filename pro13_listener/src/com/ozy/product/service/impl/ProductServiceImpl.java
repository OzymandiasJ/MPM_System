package com.ozy.product.service.impl;

import com.ozy.product.beans.Product;
import com.ozy.product.service.ProductService;
import com.ozy.product.dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao =null;
    @Override
    public List<Product> getProductList(String keyword, Integer pageNo) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return productDao.getProductsList(keyword,pageNo);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        productDao.addProduct(product);
    }

    @Override
    public Product getProductByPid(Integer pid) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return productDao.getProductById(pid);
    }

    @Override
    public void delete(Integer pid) throws SQLException {
        productDao.deleteProduct(pid);
    }

    @Override
    public Integer getPageCount(String keyword) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        int productsCount= productDao.getProductsCount();
        int pageCount=(int)Math.ceil(productsCount*1.0/5);
        return pageCount;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productDao.updateProduct(product);
    }

    @Override
    public Product getProductByName(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return productDao.getProductByName(name);
    }
}
