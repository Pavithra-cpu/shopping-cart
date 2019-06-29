<html>
  <head>
    <title>Shopping : Homepage</title>
  </head>
  <body style="background-color : #d4d8dd;">
    <div style="background-color : #ffffff"></br>
      <center><b>Shopping</b></center>
    </br></div></br>
    <center><i><%
      out.println("Welcome to Shopping cart!!!");
    %></i></center></br>
    <center><div class="container" style="background-color : #d4d8dd;">
      <div class="col" style="background-color : #ffffff;">
      </br>
        <div class="row" style="background-color : #ffffff;">
          <%
          String choi = "Admin";
          session.setAttribute("choice",choi);
          %>
          <a href = "DispitemU" style="color:black;"><b>1.Admin</b></a>
        </div>
      </br></br>
        <div class="row" style="background-color : #ffffff;">
          <a href = "Login.jsp" style="color:black;"><b>2.User</b></a>
        </div>
      </br>
      </div>
    </div></center>
    </body>
</html>
