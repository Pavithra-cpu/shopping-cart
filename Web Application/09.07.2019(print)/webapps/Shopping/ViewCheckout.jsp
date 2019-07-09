<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>User Purchase</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script>
    function print()
    {
      var toPrint=document.getElementById('ToPrint');
      var newWin=window.open();
      newWin.document.open();
      newWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="styles.css"></head><body onload="window.print();window.close();"><center>'+toPrint.innerHTML+'</center></body></html>');
      newWin.document.close();
    }
    </script>
  </head>
<body><center>
  <div>
    <center><b>Order details</b></center>
  </div></br>
<%
   List<Order> order = (ArrayList<Order>) request.getAttribute("order");
   String choice=(String) session.getAttribute("choi");
   String msg = (String) request.getAttribute("msgo");
   int count = 0;
   int price = 0;
   if(choice.equals("User")){
     count = (Integer) request.getAttribute("count");
     price = (Integer) request.getAttribute("price");
   }
   if(msg!=null){
     out.println(msg);
   }
%>
<div id="ToPrint">
  <table>
  <tr>
  <th>Item ID</th><th>Item Name</th><th>Quantity</th><th>Price</th>
</tr>
<%
    for(Order O : order)
    {
      %>
      <tr>
        <td>
          <%
          out.println(O.getIid());
          %>
        </td>
        <td>
          <%
          out.println(O.getIname());
          %>
        </td>
        <td>
          <%
          out.println(O.getPquantity());
          %>
        </td>
        <td>
          <%
          out.println(O.getPprice());
          %>
        </td>
   </tr>
    <%
    }
    %>
    <%
    if(choice.equals("User")){
    %>
    <tr><td><b>TotalQuantity</b></td><td><%out.println(count);%></td><td><b>GrandTotal</b></td><td><%out.println(price);%></td></tr>
    <%}%>
</table></div></br>
<%
if(choice.equals("User")){
%>
  <a href = "ViewItems" class="button1" style="color:black;"><b>Back</b></a>
  <button onclick = "print()" class = "button1" style="color:black;"><b>Print</b></a>
<%}
else
{
%>
  <a href = "ViewCheckouts" class="button1" style="color:black;"><b>All Checkouts</b></a>
<%}%>
</center>
</body>
</html>
