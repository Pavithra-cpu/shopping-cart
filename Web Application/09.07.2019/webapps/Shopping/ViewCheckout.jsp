<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>User Purchase</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
<body>
  <div>
    <center><b>Order details</b></center>
  </div></br>
<%
   List<Order> order = (ArrayList<Order>) request.getAttribute("order");
%>
<center>
  <table>
  <tr>
  <th>Item ID</th><th>Item Name</th><th>Quantity</th>
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
   </tr>
    <%
    }
    %>
</table></br>
<a href = "ViewCheckouts" class="button1" style="color:black;"><b>All Checkouts</b></a>
</center>
</body>
</html>
