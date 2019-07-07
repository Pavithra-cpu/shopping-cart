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

public class AddItem extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String iname = request.getParameter("iname");
    Items I = new Items();
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    int price = Integer.parseInt(request.getParameter("price"));
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      I.setIname(iname);
      I.setQuantity(quantity);
      I.setPrice(price);
      Q.addItem(I);
    }
    catch(Exception ex){
      out.println(ex);
    }
    String msg="Item Added...";
    request.setAttribute("msg",msg);
    RequestDispatcher rd = request.getRequestDispatcher("AddItem.jsp");
    rd.include(request,response);
  }
}
