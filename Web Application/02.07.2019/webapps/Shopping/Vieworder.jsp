<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>User Purchase</title>
  </head>
  <style>
table
  {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 30%;
  }
th
  {
    border: 1px solid #dddddd;
    text-align:center;
    padding:20px;
  }
td
  {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
  }

tr:nth-child(odd) {
    background-color: #dddddd;
}
</style>
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
<a href = "Disppur" style="color:black;"><b>Master List</b></a>
</center>
</body>
</html>
