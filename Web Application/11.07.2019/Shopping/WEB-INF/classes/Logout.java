import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    String choice = request.getParameter("logout");
    if(session != null){
      session.invalidate();
    }
    if(choice.equals("userl")){
      response.sendRedirect("Login.jsp");
    }
    if(choice.equals("adminl")){
      response.sendRedirect("Home.jsp");
    }
  }
}
