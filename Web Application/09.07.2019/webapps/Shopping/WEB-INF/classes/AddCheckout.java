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
    HttpSession session = request.getSession();
    int uid = (Integer) session.getAttribute("uid");
    List<Order> order = new ArrayList<Order>();
    List<Object> p = null;
    String email = null;
    Object pid = null;
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      p = Q.addCheckout(uid);
      email = String.valueOf(p.get(0));
      pid = p.get(1);
      Query Qu = new Query();
      order = Qu.viewCheckout(pid);
    }
    catch(Exception ex){
      out.println(ex);
    }
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems");
    try{
      SendEmail mail = new SendEmail();
      mail.sendMail(email,order);
    }
    catch(Exception ex){
      out.println(ex);
    }
    rd.include(request,response);
  }
}
