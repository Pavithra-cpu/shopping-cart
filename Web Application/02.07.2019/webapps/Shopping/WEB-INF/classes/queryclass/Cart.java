package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;

public class Cart implements Serializable{
  private int iid;
  private String itemname;
  private int aquantity;
  private int price;
  private int quantity;

  public void setIid(int iid){
    this.iid = iid;
  }
  public int getIid(){
    return this.iid;
  }

  public void setIname(String itemname){
    this.itemname = itemname;
  }
  public String getIname(){
    return this.itemname;
  }

  public void setAquantity(int aquantity){
    this.aquantity = aquantity;
  }
  public int getAquantity(){
    return this.aquantity;
  }

  public void setPrice(int price){
    this.price = price;
  }
  public int getPrice(){
    return this.price;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }
  public int getQuantity(){
    return this.quantity;
  }
}
