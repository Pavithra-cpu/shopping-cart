package queryclass;
import java.io.Serializable;
public class Items implements Serializable {

    private int prod_id;
    private String itemname="itemname";
    private int quantity="quantity";
    private int price;

    public Items(){}

    public void setId(int prod_id){
        this.prod_id = prod_id;
    }

    public int getId(){
        return this.prod_id;
    }

    public void setItemname(String itemname){
        this.itemname = itemname;
    }

    public String getItemname() {
        return this.itemname;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
