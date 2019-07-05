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

public class Username extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String choice = "User";
    String usname = request.getParameter("usname");
    String email = request.getParameter("email");
    int uid = 0;
    HttpSession session = request.getSession();
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      uid = Q.addUser(usname,email);
    }
    catch(Exception ex){
      out.println(ex);
    }
    out.println(uid);
    //session.setAttribute("tname",usname);
    session.setAttribute("uid",uid);
    session.setAttribute("choice",choice);
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
}
