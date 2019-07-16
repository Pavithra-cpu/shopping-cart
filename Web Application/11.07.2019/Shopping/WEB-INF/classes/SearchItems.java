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
import java.sql.SQLException;

public class SearchItems extends HttpServlet
{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    HttpSession session = request.getSession(false);
    String attribute1=(String) request.getParameter("attribute1");
    String attribute2=(String) request.getParameter("attribute2");
    String constraint1=(String) request.getParameter("constraint1");
    String constraint2=(String) request.getParameter("constraint2");
    String value1=(String) request.getParameter("data1");
    String value2=(String) request.getParameter("data2");
    String condition=(String) request.getParameter("condition");
    if(constraint1.equals("LIKE_%")){
      constraint1="LIKE";
      value1=value1+"%";
    }
    if(constraint2.equals("LIKE_%")){
      constraint2="LIKE";
      value2=value2+"%";
    }
    if(constraint1.equals("LIKE%_")){
      constraint1="LIKE";
      value1="%"+value1;
    }
    if(constraint2.equals("LIKE%_")){
      constraint2="LIKE";
      value2="%"+value2;
    }
    if(constraint1.equals("LIKE%%")){
      constraint1="LIKE";
      value1="%"+value1+"%";
    }
    if(constraint2.equals("LIKE%%")){
      constraint2="LIKE";
      value2="%"+value2+"%";
    }
    List<Items> items = new ArrayList<Items>();
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems.jsp");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String query="SELECT * FROM items WHERE "+attribute1+" "+constraint1+" '"+value1+"' "+condition+" "+attribute2+" "+constraint2+" '"+value2+"'";
    out.println(query);
    try{
      Query Q = new Query();
      items = Q.searchItems(query);
    }
    catch(SQLException e){
      out.println(e);
    }
    catch(ClassNotFoundException ex){
      out.println(ex);
    }
    request.setAttribute("items",items);
    rd.include(request,response);
  }
}
