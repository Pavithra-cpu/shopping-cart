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

public class Dispreq extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {

  List<String> re = new ArrayList<String>();
  PrintWriter out = response.getWriter();

  Connection con = null;
  Statement stmt = null;
  try{
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
    DatabaseMetaData meta = con.getMetaData();
    ResultSet rs = meta.getTables(null,null,"requests",null);
    if(rs.next()){
      stmt = con.createStatement();
      String query = "SELECT * FROM requests";
      ResultSet rsd = stmt.executeQuery(query);
      while(rsd.next()){
        re.add(rsd.getString("request"));
      }
      stmt.close();
      con.close();
    }
    else{
      stmt = con.createStatement();
      String ct = "CREATE TABLE requests" +
                  "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                  "request VARCHAR(30) NOT NULL)";
      stmt.executeUpdate(ct);
      stmt.close();
      con.close();

    }
  }
  catch(Exception ex){
    out.println(ex.getClass().getName()+":"+ex.getMessage());
  }

request.setAttribute("re",re);
RequestDispatcher rd = request.getRequestDispatcher("Viewreq.jsp");
rd.include(request,response);
}
}
