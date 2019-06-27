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

public class Dispuser extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  String usname = request.getParameter("us");
  ServletContext context = request.getServletContext();
  List<Userpurchase> uspur = new ArrayList<Userpurchase>();
  PrintWriter out = response.getWriter();
  FileInputStream fis = new FileInputStream("Purchases\\"+usname+".txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
while(true)
{
try{
  Userpurchase UP = (Userpurchase) ois.readObject();
  uspur.add(UP);
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
request.setAttribute("uspur",uspur);
RequestDispatcher rd = request.getRequestDispatcher("Viewuser.jsp");
rd.include(request,response);
}
}
