<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Purchases"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Purchase Menu</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
    <div>
      <center><b>Purchases</b></center>
    </div></br>
<%
   List<Purchases> purchase = (ArrayList<Purchases>) request.getAttribute("purchase");
%>
<center><table>
  <tr>
  <th>Purchase ID</th><th>User ID</th><th>Username</th><th>Email</th><th>Count</th><th>View Details</th>
</tr>
<%
    for(Purchases P : purchase)
    {
      %>
      <tr>
      <td>
        <%
        Object uuid = P.getPid();
        out.println(uuid);
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
       out.println(P.getEmail());
       %>
     </td>
     <td>
       <%
       out.println(P.getCount());
       %>
     </td>
       <td>
         <form action="ViewCheckout" method="get">
           <input type="hidden" name="uid" value="<%=P.getUid()%>"/>
           <input type="hidden" name="pid" value="<%=P.getPid()%>"/>
           <input type="submit" class="button3" value="View"/>
         </form>
     </td></tr>
    <%
    }
    %>


</table></center></br>
<center><div class="row">
  <a href = "ViewItems" class="button1" style="color:black;"><b>Back</b></a>
</div></center></br>
</body>
</html>
