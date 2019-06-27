import java.io.*;
import java.util.*;
import servletclass.Items;

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
  ServletContext context = request.getServletContext();
  HttpSession session = request.getSession();
  String choi = (String) session.getAttribute("choice");
  List<Items> items = new ArrayList<Items>();
  RequestDispatcher rd = request.getRequestDispatcher("Viewtab.jsp");
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  File fi = null;
  FileInputStream fis = null;
  ObjectInputStream ois = null;
  try
  {
    fi = new File("itlistd.txt");
    fis = new FileInputStream(fi);
    ois = new ObjectInputStream(fis);
    while(true)
    {
      try{
        Items I = (Items) ois.readObject();
        items.add(I);
      }
      catch(EOFException ex){
        break;
      }
      catch(FileNotFoundException ex){
        rd.include(request,response);
        //out.println("No items added in the list..:(");
      }
      catch(ClassNotFoundException ex){
        ex.printStackTrace();
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
    }if(ois!=null){
    ois.close();
  }
  }
  catch(Exception ex){
    ex.printStackTrace();
  }
    //out.println(items.size());
    request.setAttribute("items",items);
    session.setAttribute("choi",choi);
    rd.include(request,response);
  }
}
