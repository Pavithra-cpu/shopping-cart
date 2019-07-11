import java.io.*;
import java.util.*;
import queryclass.Order;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCheckout extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    //String usname = request.getParameter("us");
    //int uid = Integer.parseInt(request.getParameter("uid"));
    Object pid = request.getParameter("pid");
    List<Order> order = new ArrayList<Order>();
    Order O = new Order();
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      order = Q.viewCheckout(pid);
    }
    catch(Exception ex){
      out.println(ex);
    }
    request.setAttribute("order",order);
    RequestDispatcher rd = request.getRequestDispatcher("ViewCheckout.jsp");
    rd.include(request,response);
  }
}
