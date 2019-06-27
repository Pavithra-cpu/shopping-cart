import java.io.*;
import java.util.*;
import servletclass.Masterpurchase;
import servletclass.Userpurchase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Disppur extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  ServletContext context = request.getServletContext();
  List<Masterpurchase> maspur = new ArrayList<Masterpurchase>();
  List<Integer> count = new ArrayList<Integer>();
  PrintWriter out = response.getWriter();
  FileInputStream fis = new FileInputStream("maspurchase.txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
while(true)
{
try{
  Masterpurchase MP = (Masterpurchase) ois.readObject();
  maspur.add(MP);
  String usn;
  int coun = 0;
  usn = MP.getUsername();
  FileInputStream fisc = new FileInputStream("Purchases\\"+usn+".txt");
  ObjectInputStream oisc = new ObjectInputStream(fisc);
  while(true)
   {
     try
     {
       Userpurchase UP = (Userpurchase) oisc.readObject();
       coun++;
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
   count.add(coun);
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
request.setAttribute("maspur",maspur);
request.setAttribute("count",count);
RequestDispatcher rd = request.getRequestDispatcher("ViewtabA.jsp");
rd.include(request,response);
}
}
