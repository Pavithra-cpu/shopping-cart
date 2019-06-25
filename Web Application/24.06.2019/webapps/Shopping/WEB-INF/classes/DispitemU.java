import java.io.*;
import java.util.*;
import servletclass.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispitemU extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  ServletContext context = request.getServletContext();
  List<Items> items = new ArrayList<Items>();
  PrintWriter out = response.getWriter();
  FileInputStream fis = new FileInputStream("itlis.txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
while(true)
{
try{
  Items I = (Items) ois.readObject();
  items.add(I);
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
request.setAttribute("items",items);
RequestDispatcher rd = request.getRequestDispatcher("ViewtabU.jsp");
rd.include(request,response);
}
}
