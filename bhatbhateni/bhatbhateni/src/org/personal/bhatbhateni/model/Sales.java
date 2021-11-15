package org.personal.bhatbhateni.model;

public class Sales {

    private int id;

    private String customerName;
    
    private int productQuantity;
    
    private String productName;
    public Sales() {
    }

    public Sales(int id, String customerName, int productQuantity, String productName) {
        this.id = id;
        this.customerName = customerName;
        this.productQuantity = productQuantity;
        this.productName = productName;
    }

   public Sales(String customerName, int productQuantity, String productName) {
        this.customerName = customerName;
        this.productQuantity = productQuantity;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcustomerName() {
        return customerName;
    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getproductQuantity() {
        return productQuantity;
    }

    public void setproductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    
     public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;

    }
}
 
