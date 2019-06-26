import java.io.*;
import java.util.*;
import servletclass.Items;
import servletclass.Masterpurchase;
import servletclass.ObjectToFileWrite;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Username extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String choice = "User";
    String usname = request.getParameter("usname");
    HttpSession session = request.getSession();
    session.setAttribute("uname",usname);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    //List<Items> items = new ArrayList<Items>();
    Masterpurchase MP = new Masterpurchase();
    MP.setUsername(usname);
    addMpurchase(MP);
    session.setAttribute("choice",choice);
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
  public void addMpurchase(Masterpurchase MP) throws IOException
  {
    ObjectToFileWrite<Masterpurchase> obm = new ObjectToFileWrite<>();
    obm.saveItem("maspurchase.txt", MP);
  }
}
