import java.io.*;
import java.util.*;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import org.postgresql.Driver;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Additem extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String iname = request.getParameter("iname");
    int price = Integer.parseInt(request.getParameter("price"));
    PrintWriter out = response.getWriter();
    Connection cona = null;
    Statement stmta = null;
    try{
      Class.forName("org.postgresql.Driver");
      cona = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
      stmta = cona.createStatement();
      String rta = "INSERT INTO items(itemname,price)"+" VALUES('"+iname+"',"+price+")";
      stmta.executeUpdate(rta);
      stmta.close();
      cona.close();
    }
    catch(Exception ex){
        out.println(ex.getClass().getName()+":"+ex.getMessage());
    }
    RequestDispatcher rd = request.getRequestDispatcher("Addedmsg.jsp");
    rd.include(request,response);
  }
}
