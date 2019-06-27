package servletclass;
import java.io.*;
import java.util.*;
public class Admin implements Serializable
{
public void addItem(Items I) throws IOException
{
  ObjectToFileWrite<Items> ob = new ObjectToFileWrite<>();
  ob.saveItem("itlistd.txt", I);
}

/*public void dispItem()throws IOException{
  int slnumber = 1;
  FileInputStream fis = new FileInputStream("itlis.txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
  while(true)
  {
    try{
      Items I = (Items) ois.readObject();
      System.out.println(I.getIname()+"\t\t"+I.getQuantity()+"\t\t"+I.getPrice()+"\n");
      slnumber++;
    }
    catch(EOFException ex){
      break;
    }
    catch(FileNotFoundException ex){
      System.out.println("No items added in the list..:(");
    }
    catch(ClassNotFoundException ex){
      ex.printStackTrace();
    }
    catch(IOException ex){
      ex.printStackTrace();
    }
  }
}
public void dispRequest()throws IOException{
  int slnumber = 1;
  FileInputStream fisd = new FileInputStream("relis.txt");
  ObjectInputStream oisd = new ObjectInputStream(fisd);
  while(true)
  {
    try{
      Requests R = (Requests) oisd.readObject();
      System.out.println(slnumber+"\t"+R.getRequest());
      slnumber++;
    }
    catch(EOFException ex){
      break;
    }
    catch(FileNotFoundException ex){
      System.out.println("No items added in the list..:(");
    }
    catch(ClassNotFoundException ex){
      ex.printStackTrace();
    }
    catch(IOException ex){
      ex.printStackTrace();
    }
  }
}

public void dispPurchase()throws IOException
{
  int slonumber = 1;
  System.out.println("Purchase no.:\tUsername:\tNo of products:\n");
  FileInputStream fispu = new FileInputStream("maspurchase.txt");
  ObjectInputStream oispu = new ObjectInputStream(fispu);
  BufferedReader brpu = new BufferedReader(new InputStreamReader(System.in));
try{
  while(true){
      Masterpurchase MP = (Masterpurchase) oispu.readObject();
      System.out.println(slonumber+"\t\t"+MP.getUsername()+"\t\t"+MP.getCount());
      slonumber++;
    }
  }
    catch(EOFException exp){
      System.out.println("Purchase list over...");
    }
    catch(FileNotFoundException exp){
      exp.printStackTrace();
    }
    catch(ClassNotFoundException exp){
      exp.printStackTrace();
    }
    catch(IOException exp){
      exp.printStackTrace();
    }
  boolean conp = true;
  while(conp){
    System.out.println("Enter the username to view details:");
    String usrname = brpu.readLine();
    viewUser(usrname);
    System.out.println("Change user:(yes/no)");
    String choop = brpu.readLine();
    String yes = "yes";
    if(choop.equals(yes)){
      conp = true;
    }
    else{
      conp = false;
      break;
    }
  }
}
public void viewUser(String uname)throws IOException{
  int slounumber = 1;
  System.out.println("User:"+uname);
  System.out.println("Sl.No.\tItems name");
  FileInputStream fisupu = new FileInputStream("Purchases\\"+uname+".txt");
  ObjectInputStream oisupu = new ObjectInputStream(fisupu);
  while(true){
    try{
      Userpurchase UP = (Userpurchase) oisupu.readObject();
      System.out.println(slounumber+"\t"+UP.getItemname());
      slounumber++;
    }
    catch(EOFException expu){
      System.out.println("Purchased items completed..");
      break;
    }
    catch(FileNotFoundException expu){
      expu.printStackTrace();
    }
    catch(ClassNotFoundException expu){
      expu.printStackTrace();
    }
    catch(IOException expu){
      expu.printStackTrace();
    }
  }
}*/
}
