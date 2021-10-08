package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Shawn
 */
public class Product {
    
    //fields
    public static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int product_id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    
    //constructor
    public Product(int id, String name, double price, int stock, int min, int max){
        this.product_id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        
    }
    
    //methods
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public void addAssociatedPart(){
        
    }
    
    public void deleteAssociatedPart(){
        
    }
  
    public static ObservableList<Part> getAllAssociatedParts(Product product){
        return associatedParts;
    }
}
