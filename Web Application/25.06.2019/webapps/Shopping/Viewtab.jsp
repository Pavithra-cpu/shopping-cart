<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="servletclass.Items"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Items</title>
  </head>
  <style>
  #container ul
    {
      list-style:none;
    }
  #container ul li:hover
    {
      background-color:#ADD8E6;
    }
  #container ul
  {
    background-color: #9bf442;
  }
  #container ul li
    {
      background-color:solid #9bf442;
      width:130px;
      height:40px;
      border-radius:10px;
      line-height:40px;
      text-align:center;
      color:black;
      font-size:20px;
      float:left;
  }
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
      <center><b>Items</b></center>
    </div></br>
<%
   List<Items> item = (ArrayList<Items>) request.getAttribute("items");
   int slnumber=1;
   String choice=(String) session.getAttribute("choi");
if(!(choice.equals("User"))){
%>
<div id="container"><center>
<ul>
<form action="Additem.jsp">
  <li><input type="submit" value="Add Item" /></li>
</form>
<form action="Dispreq" method = "post">
  <li><input type="submit" value="View Requests" /></li>
</form>
<form action="Disppur" method = "get">
  <li><input type="submit" value="View Purchases" /></li>
</form>
</ul></center>
</div>
</br></br></br>
<%}%>
<center><table>
  <tr>
  <th>Slot No.</th><th>Item name</th><th>Price</th>
  <%
  if(choice.equals("User")){
  %>
  <th>Buy</th>
  <%}
  else
  {
  %>
  <th>Delete</th>
  <%}%>
</tr>
<%
    for(Items items : item)
    {
      %>
      <tr>
        <td>
          <%out.println(slnumber);%>
        </td>
      <td>
       <%
       out.println(items.getIname());%>
     </td>
       <td>
       <%
       out.println(items.getPrice());
       %>
     </td>
     <td>
       <%
       if(choice.equals("User")){
       %>
       <form action="Writeitem" method="get">
         <input type="hidden" name="it" value="<%=items.getIname()%>"/>
         <input type="submit" value="Buy"/>
       </form>
      <%}
      else
      {
      %>
      <form action="Deleteitem" method="post">
        <input type="hidden" name="it" value="<%=items.getIname()%>"/>
        <input type="submit" value="Delete"/>
      </form>
      <%}%>
     </td>
   </tr>
     <%
     slnumber++;
    }
    %>


</table></center></br>
<center><div class="row">
  <%
  if(choice.equals("Admin")){
  %>
  <a href = "Home.jsp" style="color:black;"><b>Home</b></a>
  <%}
  else
  {
  %>
  <a href = "Login.jsp" style="color:black;"><b>Logout</b></a>
  <%}%>
</div></center></br>
</body>
</html>
