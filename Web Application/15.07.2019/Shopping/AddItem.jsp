<html>
  <head>
    <title>Shopping:Admin:Additem</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
    input[type=text],[type=number]{
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
    <center><div>
    <form action="AddItem" method="get">
      <label for="iname">Item Name:</label>
      <input type="text" id="iname" name="iname" placeholder="Item name"/></br></br>
      <label for="iquantity">Quantity:</label>
      <input type="number" id="iquantity" name="quantity" placeholder="Quantity"/></br></br>
      <label for="iprice">Price:</label>
      <input type="number" id="iprice" name="price" placeholder="Price"/></br></br>
      <input type="submit" class="button1" value="add">
    </form></div></center>
  </body>
</html>
