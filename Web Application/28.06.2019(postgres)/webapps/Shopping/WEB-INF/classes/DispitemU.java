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

public class DispitemU extends HttpServlet{
  protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
  {
  HttpSession session = request.getSession();
  String choi = (String) session.getAttribute("choice");
  RequestDispatcher rd = request.getRequestDispatcher("Viewtab.jsp");
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  List<String> itemname = new ArrayList<String>();
  List<Integer> price = new ArrayList<Integer>();

  Connection con = null;
  Statement stmt = null;
  try{
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
    DatabaseMetaData meta = con.getMetaData();
    ResultSet rs = meta.getTables(null,null,"items",null);
    if(rs.next()){
      stmt = con.createStatement();
      String rt = "SELECT * FROM items";
      ResultSet rsd = stmt.executeQuery(rt);
      while(rsd.next()){
        itemname.add(rsd.getString("itemname"));
        price.add(rsd.getInt("price"));
      }
      stmt.close();
      con.close();
    }
    else{
      stmt = con.createStatement();
      String ct = "CREATE TABLE items" +
                  "(id BIGSERIAL NOT NULL,"+
                  "itemname VARCHAR(30) NOT NULL,"+
                  "price INT NOT NULL)";
      stmt.executeUpdate(ct);
      stmt.close();
      con.close();

    }
  }
  catch(Exception ex){
    out.println(ex.getClass().getName()+":"+ex.getMessage());
  }
    request.setAttribute("itemname",itemname);
    request.setAttribute("price",price);
    session.setAttribute("choi",choi);
    rd.include(request,response);
  }
}
