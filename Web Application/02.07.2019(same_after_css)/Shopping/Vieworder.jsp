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
<%
   List<Order> order = (ArrayList<Order>) request.getAttribute("order");
%>
<center>
  <table>
  <tr>
  <th>Slot No.</th><th>Item ID</th><th>Item Name</th>
</tr>
<%
    for(Order O : order)
    {
      %>
      <tr>
        <td>
          <%
          out.println(O.getPid());
          %>
        </td>
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
   </tr>
    <%
    }
    %>
</table></br>
<a href = "Disppur" class="button1" style="color:black;"><b>Master List</b></a>
</center>
</body>
</html>
