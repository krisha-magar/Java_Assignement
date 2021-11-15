package org.personal.departmentstore.home;

public class Products {
    private int id;

    private String productName;

    private String productCategory;
    
    private int productQuantity;
    
    private Double productPrice;


public Products(){
}

public Products (int id, String productName,String productCategory,int productQuantity,Double productPrice){
    this.id = id;
    this.productName = productName;
    this.productCategory = productCategory;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
}
public Products ( String productName,String productCategory,int productQuantity,Double productPrice){
    this.productName = productName;
    this.productCategory = productCategory;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
}
public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName ) {
        this.productName = productName;
    }

    public String getproductCategory() {
        return productCategory;
    }

    public void setproductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getproductQuantity() {
        return productQuantity;
    }

    public void setproductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getproductPrice() {
        return productPrice;
    }

    public void setproductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    
}

