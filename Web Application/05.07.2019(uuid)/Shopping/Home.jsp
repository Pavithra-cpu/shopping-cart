<html>
  <head>
    <title>Shopping : Homepage</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body >
    <center>
    <div >
      <b><h1>Shopping</h1></b>
    </div></br>
      <h2>choose who you are...</h2></br>
    <div >
      <%
      String choi = "Admin";
      session.setAttribute("choice",choi);
      %>
      <a href = "DispitemU" class="button" style="color:black;"><b>admin</b></a>
      <a href = "Login.jsp" class="button" style="color:black;"><b>user</b></a>
    </div>
    </center>
  </body>
</html>
