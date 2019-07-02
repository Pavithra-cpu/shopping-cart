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

public class Checkout extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    int uid = (Integer) session.getAttribute("uid");
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      Q.addCheckout(uid);
    }
    catch(Exception ex){
      out.println(ex);
    }
    String msg="Order Received...";
    request.setAttribute("msg",msg);
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
  }
}
