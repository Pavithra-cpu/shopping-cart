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

public class Writeitem extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    int bitid = Integer.parseInt(request.getParameter("it"));
    HttpSession session = request.getSession();
    int uid = (Integer) session.getAttribute("uid");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println(bitid+""+uid);
    try{
      Query Q  = new Query();
      Q.writeitem(uid,bitid);
    }
    catch(Exception ex){
      out.println(ex);
      ex.printStackTrace();
    }
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
}
