package com.akhadidja.kitchensink.volley;

public class Product {
    private String condition;
    private String customerReviewAverage;
    private int customerReviewCount;
    private String manufacturer;
    private String name;
    private double regularPrice;
    private double salePrice;
    private String image;

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Product{" +
                "condition='" + condition + '\'' +
                ", customerReviewAverage='" + customerReviewAverage + '\'' +
                ", customerReviewCount=" + customerReviewCount +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", regularPrice=" + regularPrice +
                ", salePrice=" + salePrice +
                ", image='" + image + '\'' +
                '}';
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCustomerReviewAverage() {
        return customerReviewAverage;
    }

    public void setCustomerReviewAverage(String customerReviewAverage) {
        this.customerReviewAverage = customerReviewAverage;
    }

    public int getCustomerReviewCount() {
        return customerReviewCount;
    }

    public void setCustomerReviewCount(int customerReviewCount) {
        this.customerReviewCount = customerReviewCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
