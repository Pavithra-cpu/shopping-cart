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

public class Writeitem extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
    String biname = request.getParameter("it");
    ServletContext context = request.getServletContext();
    HttpSession session = request.getSession();
    String uname = (String) session.getAttribute("uname");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    Connection conw = null;
    Statement stmtw = null;
    try{
      Class.forName("org.postgresql.Driver");
      conw = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
      DatabaseMetaData meta = conw.getMetaData();
      ResultSet rs = meta.getTables(null,null,uname,null);
      if(rs.next()){
        stmtw = conw.createStatement();
        String rta = "INSERT INTO "+uname+"(itemname)"+" VALUES('"+biname+"')";
        stmtw.executeUpdate(rta);
        stmtw.close();
        conw.close();
      }
      else{
        stmtw = conw.createStatement();
        String ct = "CREATE TABLE "+uname+
                    "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                    "itemname VARCHAR(30) NOT NULL)";
        stmtw.executeUpdate(ct);
        String au = "INSERT INTO "+uname+"(itemname)"+" VALUES('"+biname+"')";
        stmtw.executeUpdate(au);
        stmtw.close();
        conw.close();
      }
    }
    catch(Exception ex){
        out.println(ex.getClass().getName()+":"+ex.getMessage());
    }

    RequestDispatcher rd = request.getRequestDispatcher("DispitemU");
    rd.include(request,response);
    out.close();
  }
}
