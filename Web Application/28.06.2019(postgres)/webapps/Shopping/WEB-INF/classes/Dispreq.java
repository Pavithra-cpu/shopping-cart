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
  protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {

  List<String> re = new ArrayList<String>();
  PrintWriter out = response.getWriter();

  Connection conr = null;
  Statement stmtr = null;
  try{
    Class.forName("org.postgresql.Driver");
    conr = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
    DatabaseMetaData meta = conr.getMetaData();
    ResultSet rsr = meta.getTables(null,null,"requests",null);
    if(rsr.next()){
      stmtr = conr.createStatement();
      String rt = "SELECT * FROM requests";
      ResultSet rsd = stmtr.executeQuery(rt);
      while(rsd.next()){
        re.add(rsd.getString("request"));
      }
      stmtr.close();
      conr.close();
    }
    else{
      stmtr = conr.createStatement();
      String ct = "CREATE TABLE requests" +
                  "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                  "request VARCHAR(30) NOT NULL)";
      stmtr.executeUpdate(ct);
      stmtr.close();
      conr.close();

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
