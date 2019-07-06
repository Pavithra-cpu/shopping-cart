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

public class DispitemU extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    String choi = (String) session.getAttribute("choice");
    List<Items> items = new ArrayList<Items>();
    RequestDispatcher rd = request.getRequestDispatcher("Viewtab.jsp");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try{
      Query Q = new Query();
      items = Q.dispItem();
    }
    catch(Exception ex){
      out.println(ex);
    }
    request.setAttribute("items",items);
    session.setAttribute("choi",choi);
    rd.include(request,response);
  }
}
