import java.io.*;
import java.util.*;
import queryclass.Cart;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispcart extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  //String usname = request.getParameter("us");
  HttpSession session = request.getSession();
  int uid = (Integer) session.getAttribute("uid");
  List<Cart> cart = new ArrayList<Cart>();
  Cart C = new Cart();
  PrintWriter out = response.getWriter();
  try{
    Query Q = new Query();
    cart = Q.viewcart(uid);
  }
  catch(Exception ex){
    out.println(ex);
  }
  request.setAttribute("cart",cart);
  RequestDispatcher rd = request.getRequestDispatcher("Viewcart.jsp");
  rd.include(request,response);
}
}
