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

public class Dispuser extends HttpServlet{
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  String usname = request.getParameter("us");
  List<String> uspur = new ArrayList<String>();
  PrintWriter out = response.getWriter();

  Connection con = null;
  Statement stmt = null;
  try{
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
    DatabaseMetaData meta = con.getMetaData();
    ResultSet rs = meta.getTables(null,null,usname,null);
    if(rs.next()){
      stmt = con.createStatement();
      String rt = "SELECT * FROM "+usname;
      ResultSet rsd = stmt.executeQuery(rt);
      while(rsd.next()){
        uspur.add(rsd.getString("itemname"));
      }
      stmt.close();
      con.close();
    }
    else{
      stmt = con.createStatement();
      String ct = "CREATE TABLE "+usname+
                  "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                  "itemname VARCHAR(30) NOT NULL)";
      stmt.executeUpdate(ct);
      stmt.close();
      con.close();

    }
  }
  catch(Exception ex){
    out.println(ex.getClass().getName()+":"+ex.getMessage());
  }

  request.setAttribute("uspur",uspur);
  RequestDispatcher rd = request.getRequestDispatcher("Viewuser.jsp");
  rd.include(request,response);
}
}
