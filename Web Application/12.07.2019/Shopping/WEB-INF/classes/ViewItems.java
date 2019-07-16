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
import java.sql.SQLException;

public class ViewItems extends HttpServlet
{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession session = request.getSession(false);
    if(session==null){
      response.sendRedirect("Home.jsp");
    }
    String choi = null;
    List<Items> items = new ArrayList<Items>();
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems.jsp");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      items = Q.viewItems();
    }
    catch(SQLException e){
      out.println(e);
    }
    catch(ClassNotFoundException ex){
      out.println(ex);
    }
    request.setAttribute("items",items);
    rd.include(request,response);
  }
}
