import java.io.*;
import java.util.*;
import servletclass.ObjectToFileWrite;
import servletclass.Userpurchase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Writeitem extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String biname = request.getParameter("it");
    ServletContext context = request.getServletContext();
    HttpSession session = request.getSession();
    String uname = (String) session.getAttribute("uname");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    Userpurchase UP = new Userpurchase();
    UP.setItemname(biname);
    addPurchase(UP,uname);
    //context.setAttribute("una",uname);
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
  public void addPurchase(Userpurchase UP,String uname) throws IOException
  {
    ObjectToFileWrite<Userpurchase> obu = new ObjectToFileWrite<>();
    obu.saveItem("Purchases\\"+uname+".txt", UP);
  }
}
