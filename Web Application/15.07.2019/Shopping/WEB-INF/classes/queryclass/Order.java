package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;

public class Order implements Serializable{
  private int iid;
  private String itemname;
  private int pquan;
  private int pprice;

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

  public void setPquantity(int pquan){
    this.pquan = pquan;
  }
  public int getPquantity(){
    return this.pquan;
  }

  public void setPprice(int pprice){
    this.pprice = pprice;
  }
  public int getPprice(){
    return this.pprice;
  }
  
}
