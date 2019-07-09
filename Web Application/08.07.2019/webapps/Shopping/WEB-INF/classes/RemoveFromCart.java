import java.io.*;
import java.util.*;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromCart extends HttpServlet
{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
  {
    int id = Integer.parseInt(request.getParameter("id"));
    HttpSession session = request.getSession();
    int uid = (Integer) session.getAttribute("uid");
    PrintWriter out =response.getWriter();
    response.setContentType("text/html");

    try{
      Query Q = new Query();
      Q.removeFromCart(uid,id);
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    RequestDispatcher rd = request.getRequestDispatcher("ViewCart");
    rd.include(request,response);
  }
}
