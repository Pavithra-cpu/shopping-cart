import java.io.*;
import java.util.*;
import queryclass.Items;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewItems extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession oldsession = request.getSession(false);
    String choi = (String) oldsession.getAttribute("choice");
    HttpSession newsession = request.getSession();
    List<Items> items = new ArrayList<Items>();
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems.jsp");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      items = Q.viewItems();
    }
    catch(Exception ex){
      out.println(ex);
    }
    request.setAttribute("items",items);
    newsession.setAttribute("choi",choi);
    rd.include(request,response);
  }
}
