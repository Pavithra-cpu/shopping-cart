import java.io.*;
import java.util.*;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String iid = request.getParameter("iid").trim();
    String cquan = request.getParameter("cquan").trim();
    HttpSession session = request.getSession();
    int uid = (Integer) session.getAttribute("uid");
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    try{
      Query Q  = new Query();
      Q.updateCart(uid,iid,cquan);
    }
    catch(Exception ex){
      out.write("Exception");
    }
  }
}
