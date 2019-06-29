package servletclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Requests implements Serializable{
  private String reqItem;
  public void setRequest(String reqItem){
    this.reqItem = reqItem;
  }
  public String getRequest(){
    return this.reqItem;
  }
}
