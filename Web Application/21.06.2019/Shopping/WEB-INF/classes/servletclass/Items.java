package servletclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Items implements Serializable {

    private String iname;
    private int quantity;
    private String price;

    public void setIname(String iname) {

        this.iname = iname;
    }

    public String getIname() {
        return this.iname;
    }

    /*public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }*/

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }
}
