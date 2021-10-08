package models;

/**
 *
 * @author Shawn
 */
public class Outsourced extends Part {
    
    //fields
    private String companyName;

    //constructor
    public Outsourced(int part_id, String name, double price, int stock, int min, int max, String companyName) {
        super(part_id, name, price, stock, min, max);
        super.setPart_id(part_id);
        super.setName(name);
        super.setPrice(price);
        super.setStock(stock);
        super.setMin(min);
        super.setMax(max);
        setCompanyName(companyName);
    }
    
    //methods
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
