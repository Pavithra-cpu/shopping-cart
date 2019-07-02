package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;

public class Order implements Serializable{
  private int pid;
  private int iid;
  private String itemname;

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

  public void setPid(int pid){
    this.pid = pid;
  }
  public int getPid(){
    return this.pid;
  }

}
