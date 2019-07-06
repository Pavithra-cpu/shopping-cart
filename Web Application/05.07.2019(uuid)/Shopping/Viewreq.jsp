<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Requests List</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
<body>
<%
   List<String> re = (ArrayList<String>) request.getAttribute("re");
   int slnumber=1;
   int i=0;
%>
<a href = "DispitemU" class="button1" style="color:black;"><b>Admin menu</b></a>
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
