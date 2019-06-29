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
   List<String> uspur = (ArrayList<String>) request.getAttribute("uspur");
   int slnumber=1;
   int i;
%>
<a href = "Disppur" style="color:black;"><b>Master List</b></a>
<center>
  <table>
  <tr>
  <th>Slot No.</th><th>Item name</th>
</tr>
<%
    for(i=0;i<uspur.size();i++)
    {
      %>
      <tr>
        <td>
          <%out.println(slnumber);%>
        </td>
      <td>
       <%
       out.println(uspur.get(i));%></td>
   </tr>
     <%
     slnumber++;
    }
    %>


</table></center>
</body>
</html>
