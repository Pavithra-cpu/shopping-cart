package servletclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Masterpurchase implements Serializable{
  private String username;
  private int count;
  public void setUsername(String username){
    this.username = username;
  }
  public String getUsername(){
    return this.username;
  }
  public void setCount(int count){
    this.count = count;
  }
  public int getCount(){
    return this.count;
  }
}
