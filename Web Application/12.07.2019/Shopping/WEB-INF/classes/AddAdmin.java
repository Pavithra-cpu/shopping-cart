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

public class AddAdmin extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String choice = "Admin";
    HttpSession session = request.getSession(true);
    PrintWriter out = response.getWriter();
    session.setAttribute("choice",choice);
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems");
    rd.include(request,response);
    out.close();
  }
}
