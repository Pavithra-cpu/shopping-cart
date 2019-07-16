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
    PrintWriter out = response.getWriter();
    String query = null;
    query = "SELECT * FROM items WHERE ";
    String[] attribute=(String[]) request.getParameterValues("ro1");
    String[] constraint=(String[]) request.getParameterValues("ro2");
    String[] value=(String[]) request.getParameterValues("ro3");
    String[] condition=(String[]) request.getParameterValues("ro4");
    String attr = attribute[0];
    String cons = constraint[0];
    String val = value[0];
    String cond = condition[0];
    String[] att = attr.split(",");
    String[] con = cons.split(",");
    String[] va = val.split(",");
    String[] co = cond.split(",");
    int n = att.length;
    co[n-1]=" ";
    for(int i=0;i<n;i++){
      if(con[i].equals("LIKE_%")){
        con[i]="LIKE";
        va[i]=va[i]+"%";
      }
      if(con[i].equals("LIKE%_")){
        con[i]="LIKE";
        va[i]="%"+va[i];
      }
      if(con[i].equals("LIKE%%")){
        con[i]="LIKE";
        va[i]="%"+va[i]+"%";
      }
    }
    for(int j=0;j<n;j++){
      query += " "+att[j]+" "+con[j]+" '"+va[j]+"' "+co[j];
    }
    out.println(query);
    List<Items> items = new ArrayList<Items>();
    RequestDispatcher rd = request.getRequestDispatcher("ViewItems.jsp");
    response.setContentType("text/html");
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
