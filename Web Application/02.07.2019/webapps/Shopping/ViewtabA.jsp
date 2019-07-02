<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Purchases"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Purchase Menu</title>
  </head>
  <style>
  table {
      font-family: arial, sans-serif;
      border-collapse: collapse;
      width: 30%;
    }
  th{
    border: 1px solid #dddddd;
    text-align:center;
    padding:20px;
  }
  td {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
  }

  tr:nth-child(odd) {
      background-color: #dddddd;
  }
  </style>
  <body>
    <div>
      <center><b>Purchases</b></center>
    </div></br>
<%
   List<Purchases> purchase = (ArrayList<Purchases>) request.getAttribute("purchase");
%>
<center><table>
  <tr>
  <th>Purchase ID</th><th>User ID</th><th>Username</th><th>Count</th><th>View Details</th>
</tr>
<%
    for(Purchases P : purchase)
    {
      %>
      <tr>
      <td>
        <%
        out.println(P.getPid());
        %>
      </td>
      <td>
       <%
       out.println(P.getUid());
       %>
     </td>
     <td>
       <%
       out.println(P.getUname());
       %>
     </td>
     <td>
       <%
       out.println(P.getCount());
       %>
     </td>
       <td>
         <form action="Dispuser" method="get">
           <input type="hidden" name="uid" value="<%=P.getUid()%>"/>
           <input type="submit" value="View"/>
         </form>
     </td></tr>
    <%
    }
    %>


</table></center></br>
<center><div class="row">
  <a href = "DispitemU" style="color:black;"><b>Admin Home</b></a>
</div></center></br>
</body>
</html>
