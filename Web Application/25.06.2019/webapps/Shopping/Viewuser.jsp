<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="servletclass.Userpurchase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Adminmenu</title>
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
   List<Userpurchase> uspur = (ArrayList<Userpurchase>) request.getAttribute("uspur");
   int slnumber=1;
%>
<a href = "Disppur" style="color:black;"><b>Master List</b></a>
<center>
  <table>
  <tr>
  <th>Slot No.</th><th>Item name</th>
</tr>
<%
    for(Userpurchase uspurs : uspur)
    {
      %>
      <tr>
        <td>
          <%out.println(slnumber);%>
        </td>
      <td name="it">
       <%
       out.println(uspurs.getItemname());%></td>
   </tr>
     <%
     slnumber++;
    }
    %>


</table></center>
</body>
</html>
