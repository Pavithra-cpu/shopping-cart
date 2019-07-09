import java.io.*;
import java.util.*;
import queryclass.Query;
import queryclass.SendEmail;
import queryclass.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCheckout extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession session = request.getSession(false);
    int uid = (Integer) session.getAttribute("uid");
    List<Order> order = new ArrayList<Order>();
    List<Object> p = null;
    String email = null;
    Object pid = null;
    int count = 0;
    int price = 0;
    String msg = null;
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      p = Q.addCheckout(uid);
      email = String.valueOf(p.get(0));
      pid = p.get(1);
      count = (Integer) p.get(2);
      price = (Integer) p.get(3);
      Query Qu = new Query();
      order = Qu.viewCheckout(pid);
    }
    catch(Exception ex){
      out.println(ex);
    }
    RequestDispatcher rd = request.getRequestDispatcher("ViewCheckout.jsp");
    try{
      SendEmail mail = new SendEmail();
      mail.sendMail(email,order,count,price);
    }
    catch(Exception ex){
      out.println(ex);
    }
    msg = "Your Order details have been sent to your registered email:"+email;
    request.setAttribute("msgo",msg);
    request.setAttribute("order",order);
    request.setAttribute("count",count);
    request.setAttribute("price",price);
    rd.include(request,response);
  }
}
