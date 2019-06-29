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

public class Disppur extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  List<String> maspur = new ArrayList<String>();
  List<Integer> count = new ArrayList<Integer>();
  PrintWriter out = response.getWriter();

  Connection con = null;
  Statement stmt1 = null;
  Statement stmt2 = null;
  try{
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
    DatabaseMetaData meta = con.getMetaData();
    ResultSet rs = meta.getTables(null,null,"users",null);
    if(rs.next()){
      stmt1 = con.createStatement();
      String rt = "SELECT * FROM users";
      ResultSet rsd = stmt1.executeQuery(rt);
      while(rsd.next()){
        int counti = 0;
        String user = rsd.getString("username");
        maspur.add(user);
        //String cou = "SELECT COUNT(*) FROM "+ (rsd.getString("username"));
        try{
        stmt2 = con.createStatement();
        ResultSet cod =stmt2.executeQuery("SELECT COUNT(*) FROM "+user);
        while(cod.next()){
          counti = cod.getInt(1);
        }
        stmt2.close();
      }
      catch(Exception ex){
        out.println(ex.getClass().getName()+":"+ex.getMessage());
      }
        count.add(counti);
      }
      stmt1.close();
      con.close();
    }
    else{
      stmt1 = con.createStatement();
      String ct = "CREATE TABLE users" +
                  "(id BIGSERIAL NOT NULL PRIMARY KEY,"+
                  "username VARCHAR(30) NOT NULL)";
      stmt1.executeUpdate(ct);
      stmt1.close();
      con.close();
    }
  }
  catch(Exception ex){
    out.println(ex.getClass().getName()+":"+ex.getMessage());
  }

request.setAttribute("maspur",maspur);
request.setAttribute("count",count);
RequestDispatcher rd = request.getRequestDispatcher("ViewtabA.jsp");
rd.include(request,response);
}
}
