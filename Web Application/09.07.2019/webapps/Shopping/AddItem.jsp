<html>
  <head>
    <title>Shopping:Admin:Additem</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
    <center><%
    String msg = (String) request.getAttribute("msg");
    if(msg!=null){
      out.println(msg);
    }
    %></center>
    <div>
      <center><b>Shopping</b></center>
    </div></br>
    <center><div class="row">
      <a href = "ViewItems" class="button1" style="color:black;"><b>Home</b></a>
    </div></center></br>
    <div>
      <center><b>Add Items</b></center>
    </div></br>
    <center><form action="AddItem" method="get">
      <table><tr><td>Item Name:</td><td><input type="text" name="iname" align="left"></td></tr>
      <tr><td>Quantity:</td><td><input type="number" name="quantity" align="left"></td></tr>
      <tr><td>Price:</td><td><input type="number" name="price" align="left"></td></tr></table></br>
      <input type="submit" class="button1" value="add">
    </form></center>
  </body>
</html>
