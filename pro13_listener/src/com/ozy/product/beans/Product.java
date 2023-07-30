package com.ozy.product.beans;


import java.math.BigInteger;

//商品类实现，只设置了部分属性
public class Product {
    private Integer id;
    private String productName;
    private double price;
    private double productRemain;
    private double inPrice;
    public Product(){}
    public Product( String productName, double price, double productRemain, double inPrice) {
        this.productName = productName;
        this.price = price;
        this.productRemain = productRemain;
        this.inPrice = inPrice;
    }
    public Product(Integer id, String productName, double price, double productRemain, double inPrice) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productRemain = productRemain;
        this.inPrice = inPrice;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProductRemain() {
        return productRemain;
    }

    public void setProductRemain(double productRemain) {
        this.productRemain = productRemain;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    // toString method
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productRemain=" + productRemain +
                ", inPrice=" + inPrice +
                '}';
    }
}
