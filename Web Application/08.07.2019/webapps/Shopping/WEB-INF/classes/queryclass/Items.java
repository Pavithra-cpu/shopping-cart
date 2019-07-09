package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Items implements Serializable {

    private int prod_id;
    private String iname;
    private int quantity;
    private int price;

    public void setId(int prod_id){
        this.prod_id = prod_id;
    }

    public int getId(){
        return this.prod_id;
    }

    public void setIname(String iname){
        this.iname = iname;
    }

    public String getIname() {
        return this.iname;
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
