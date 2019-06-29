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

public class Deleteitem extends HttpServlet
{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
  {
    String diname = request.getParameter("itt");
    PrintWriter out =response.getWriter();
    response.setContentType("text/html");
    Connection cona = null;
    PreparedStatement stmta = null;
    String rta = "DELETE FROM items WHERE itemname = ?";
    try{
      Class.forName("org.postgresql.Driver");
      cona = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
      stmta = cona.prepareStatement(rta);
      stmta.setString(1,diname);
      stmta.executeUpdate();
      stmta.close();
      cona.close();
    }
    catch(Exception ex){
        out.println(ex.getClass().getName()+":"+ex.getMessage());
    }
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
  }
}
