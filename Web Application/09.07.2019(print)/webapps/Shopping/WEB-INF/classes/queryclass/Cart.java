package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;

public class Cart implements Serializable{
  private int iid;
  private String itemname;
  private int aquantity;
  private int price;
  private int cquantity;
  //private int cprice;

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

  public void setCquantity(int cquantity){
    this.cquantity = cquantity;
  }
  public int getCquantity(){
    return this.cquantity;
  }

  /*public void setCprice(int cprice){
    this.cprice = cprice;
  }
  public int getCprice(){
    return this.cprice;
  }*/

}
