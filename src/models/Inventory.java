package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Shawn
 */
public class Inventory {
    
    //fields
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    //methods
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int partid){
        for(Part part : allParts){
            if(part.getPart_id() == partid){
                return part;                
            }
       }
        
       return null;
    }
    
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> tempParts = FXCollections.observableArrayList();
        for(Part part : allParts){
            if(part.getName().equalsIgnoreCase(partName)){
                tempParts.add(part);
            }
        }
        
        return tempParts;
    }
    
    public static Product lookupProduct(int productId){
        for(Product product : allProducts){
            if(product.getProduct_id() == productId){
                return product;                
            }
       }
        
       return null;
    }
    
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> tempProducts = FXCollections.observableArrayList();
        for(Product product : allProducts){
            if(product.getName().equalsIgnoreCase(productName)){
                tempProducts.add(product);
            }
        }
        
        return tempProducts;
    }
    
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart);
    }
    
    public static void deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    
}
