import java.io.*;
import java.util.*;
import servletclass.Items;
import servletclass.ObjectToFileWrite;
import servletclass.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deleteitem extends HttpServlet
{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
  {
    String diname = request.getParameter("itt");
    Admin a = new Admin();
    File fid = null;
    List<Items> itemss = new ArrayList<Items>();
    PrintWriter out =response.getWriter();
    response.setContentType("text/html");
    FileInputStream fisd = null;
    ObjectInputStream oisd = null;
    try{
      fid = new File("itlistd.txt");
      fisd = new FileInputStream(fid);
      oisd = new ObjectInputStream(fisd);
    while(true)
    {
      try{
        Items I = (Items) oisd.readObject();
        itemss.add(I);
      }
      catch(EOFException ex){
        break;
      }
      catch(FileNotFoundException ex){
        out.println("No items added in the list..:(");
      }
      catch(ClassNotFoundException ex){
        ex.printStackTrace();
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
    }

    if(oisd!=null){
    oisd.close();
  }
  }
  catch(Exception ex){
    ex.printStackTrace();
  }
    /*try
    {
      File fil = new File("itlistd.txt");
      if(fil.delete()){
        out.println("deleted");
      }
      else{
        out.println("not deleted");
      }
      fil.createNewFile();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }*/
    fid.delete();
    try{
      /*Iterator i = items.iterator();
      Items it = null;
      while(i.hasNext()){
        it=(Items) i.next();
        if((it.getIname()).equals(diname)){
          i.remove();
          break;
        }
      }*/
      for(Items itemm:itemss){
      if((itemm.getIname()).equals(diname)){
        itemss.remove(itemm);
        break;
      }
    }
    //out.println(itemss.size());
    for(Items item:itemss){
      //a.addItem(item);
      ObjectToFileWrite<Items> obj = new ObjectToFileWrite<>();
      obj.saveItem("itlistd.txt",item);
    }
  }
  catch(Exception ex){
    ex.printStackTrace();
  }
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
  }
}
