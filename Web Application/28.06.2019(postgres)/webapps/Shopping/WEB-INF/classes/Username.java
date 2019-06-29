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

public class Username extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String choice = "User";
    String usname = request.getParameter("usname");
    HttpSession session = request.getSession();
    session.setAttribute("uname",usname);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    Connection conu = null;
    Statement stmtu = null;
    try{
      Class.forName("org.postgresql.Driver");
      conu = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
      DatabaseMetaData meta = conu.getMetaData();
      ResultSet rs = meta.getTables(null,null,"users",null);
      if(rs.next()){
        stmtu = conu.createStatement();
        String rta = "INSERT INTO users(username)"+" VALUES('"+usname+"')";
        stmtu.executeUpdate(rta);
        stmtu.close();
        conu.close();
      }
      else{
        stmtu = conu.createStatement();
        String ct = "CREATE TABLE users" +
                    "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                    "username VARCHAR(30) NOT NULL)";
        stmtu.executeUpdate(ct);
        String au = "INSERT INTO users(username)"+" VALUES('"+usname+"')";
        stmtu.executeUpdate(au);
        stmtu.close();
        conu.close();
      }
    }
    catch(Exception ex){
        out.println(ex.getClass().getName()+":"+ex.getMessage());
    }

    session.setAttribute("choice",choice);
    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
}
