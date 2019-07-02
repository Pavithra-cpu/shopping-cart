import java.io.*;
import java.util.*;
import queryclass.Purchases;
import queryclass.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Disppur extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  List<Purchases> purchase = new ArrayList<Purchases>();
  PrintWriter out = response.getWriter();

  try{
    Query Q = new Query();
    purchase = Q.dispPurchase();
  }
  catch(Exception ex){
    out.println(ex);
  }

  request.setAttribute("purchase",purchase);
  RequestDispatcher rd = request.getRequestDispatcher("ViewtabA.jsp");
  rd.include(request,response);
}
}
