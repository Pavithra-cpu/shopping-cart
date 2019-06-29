<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
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
   List<String> maspur = (ArrayList<String>) request.getAttribute("maspur");
   List<Integer> count = (ArrayList<Integer>) request.getAttribute("count");
   int slnumber=1;
   int i;
%>
<center><table>
  <tr>
  <th>Slot No.</th><th>User Name</th><th>Count</th><th>View Details</th>
</tr>
<%
    for(i=0;i<maspur.size();i++)
    {
      %>
      <tr>
        <td>
          <%out.println(slnumber);%>
        </td>
      <td>
       <%
       out.println(maspur.get(i));
       %>
     </td>
     <td>
       <%
       out.println(count.get(i));
       %>
     </td>
       <td>
         <form action="Dispuser" method="post">
           <input type="hidden" name="us" value="<%=maspur.get(i)%>"/>
           <input type="submit" value="View"/>
         </form>
     </td></tr>
     <%
     slnumber++;
    }
    %>


</table></center></br>
<center><div class="row">
  <a href = "DispitemU" style="color:black;"><b>Admin Home</b></a>
</div></center></br>
</body>
</html>
