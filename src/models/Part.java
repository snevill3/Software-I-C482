package models;

/**
 *
 * @author Shawn
 */
public abstract class Part {

    //fields
    protected int part_id;
    protected String name;
    protected double price;
    protected int stock;
    protected int min;
    protected int max;
    
    //constructor
    public Part(int part_id, String name, double price, int stock, int min, int max){
        this.part_id = part_id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    //methods
    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getPart_id() {
        return part_id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
