package models;

/**
 *
 * @author Shawn
 */
public class InHouse extends Part {
    
    //fields
    private int machine_id;

    public InHouse(int part_id, String name, double price, int stock, int min, int max, int machine_id) {
        super(part_id, name, price, stock, min, max);
        super.setPart_id(part_id);
        super.setName(name);
        super.setPrice(price);
        super.setStock(stock);
        super.setMin(min);
        super.setMax(max);
        this.machine_id = machine_id;
    }

    //methods
    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public int getMachine_id() {
        return machine_id;
    }

}
