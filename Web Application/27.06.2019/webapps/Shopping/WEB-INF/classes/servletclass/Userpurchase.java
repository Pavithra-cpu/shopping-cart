package servletclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Userpurchase implements Serializable{
  private String itemname;
  public void setItemname(String itemname){
    this.itemname = itemname;
  }
  public String getItemname(){
    return this.itemname;
  }
}
