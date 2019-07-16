<html>
  <head>
    <title>Shopping:User:Login</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
    input[type=text],[type=email]{
      width: 25%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
  </style>
  </head>
  <body>
    <center><b>Shopping</b></br>
    <a href = "Home.jsp" class="button1" style="color:black;"><b>Home</b></a></br>
    <b>Login</b></center>
    </br>
    <center><div class="container"><form action="AddUser" method="get">
      <label for="uname">name:</label>
      <input type="text" id="uname" name="usname" placeholder="Your Name" required/></br></br>
      <label for="mail">email:</label>
      <input type="email" id="mail" name="email" placeholder="Your Email" required/></br></br>
      <input type="submit" class="button1" value="login"/>
    </form></div></center>
  </body>
</html>
