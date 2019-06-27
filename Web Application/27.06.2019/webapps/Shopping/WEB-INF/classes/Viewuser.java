import java.io.*;
import java.util.*;
import servletclass.Userpurchase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Viewuser extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    int slnumber = 1;
    String usename = request.getParameter("usename");
  FileInputStream fis = new FileInputStream("Purchases\\"+usename+".txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
  PrintWriter out = response.getWriter();
  out.println("Items bought:\nSlot no.\tItem name\n");
  while(true)
  {
    try{
      Userpurchase UP = (Userpurchase) ois.readObject();
      out.println(slnumber+"\t\t"+UP.getItemname());
      slnumber++;
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

  out.println("Total number of products bought:"+(slnumber-1));
//  response.setContentType("text/html");
//  out.println("</br><a href="+"Admin.jsp"+" style='color:black;'><b>Back</b></a>");
}
}
