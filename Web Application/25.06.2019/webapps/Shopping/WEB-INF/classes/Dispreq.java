import java.io.*;
import java.util.*;
import servletclass.Requests;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispreq extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  ServletContext context = request.getServletContext();
  List<Requests> re = new ArrayList<Requests>();
  PrintWriter out = response.getWriter();
  FileInputStream fis = new FileInputStream("relis.txt");
  ObjectInputStream ois = new ObjectInputStream(fis);
while(true)
{
try{
  Requests R = (Requests) ois.readObject();
  re.add(R);
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
request.setAttribute("re",re);
RequestDispatcher rd = request.getRequestDispatcher("Viewreq.jsp");
rd.include(request,response);
}
}
