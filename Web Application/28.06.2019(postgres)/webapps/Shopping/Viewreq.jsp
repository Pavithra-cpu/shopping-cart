<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Requests List</title>
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
   List<String> re = (ArrayList<String>) request.getAttribute("re");
   int slnumber=1;
   int i=0;
%>
<a href = "DispitemU" style="color:black;"><b>Admin menu</b></a>
<center>
  <table>
  <tr>
  <th>Slot No.</th><th>Item name</th>
</tr>
<%
    for(i=0;i<re.size();i++)
    {
      %>
      <tr>
        <td>
          <%out.println(slnumber);%>
        </td>
      <td>
       <%
       out.println(re.get(i));%></td>
   </tr>
     <%
     slnumber++;
    }
    %>


</table></center>
</body>
</html>
