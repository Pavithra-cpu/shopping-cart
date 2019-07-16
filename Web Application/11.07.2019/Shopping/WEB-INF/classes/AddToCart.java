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

public class AddToCart extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    int bitid = Integer.parseInt(request.getParameter("it"));
    int quan = Integer.parseInt(request.getParameter("qu"));
    HttpSession session = request.getSession(false);
    int uid = (Integer) session.getAttribute("uid");
    response.setContentType("text/html");
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems");
    PrintWriter out = response.getWriter();
    String msg = null;
    out.println(bitid+""+uid);
    if(quan>0){
      try{
        Query Q  = new Query();
        Q.addToCart(uid,bitid);
      }
      catch(Exception ex){
        out.println(ex);
        ex.printStackTrace();
      }
      msg = "Added to cart..";
    }
    else{
      msg = "Item not available,you may request...";
    }
    request.setAttribute("msg",msg);
    rd.include(request,response);
    out.close();
  }
}
