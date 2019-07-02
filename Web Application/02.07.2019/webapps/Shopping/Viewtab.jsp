<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Items"%>
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
   ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");
   String choice=(String) session.getAttribute("choi");
if(!(choice.equals("User"))){
%>
<div id="container"><center>
<ul>
<form action="Additem.jsp">
  <li><input type="submit" value="Add Item" /></li>
</form>
<form action="Dispreq" method = "get">
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
  <th>Item ID</th><th>Item name</th><th>Quantity</th><th>Price</th>
  <%
  if(choice.equals("User")){
  %>
  <th>Add to cart</th>
  <%}
  else
  {
  %>
  <th>Delete</th>
  <%}%>
</tr>
<%
    for(Items item : items)
    {
%>
    <tr>
      <td>
        <%
        out.println(item.getId());
        %>
      </td>
      <td>
        <%
        out.println(item.getIname());
        %>
      </td>
      <td>
        <%
        out.println(item.getQuantity());
        %>
      </td>
      <td>
        <%
        out.println(item.getPrice());
        %>
      </td>
      <td>
        <%
        if(choice.equals("User")){
        %>
        <form action="Writeitem" method="get">
          <input type="hidden" name="it" value="<%=item.getId()%>"/>
          <input type="submit" value="Add to cart"/>
        </form>
        <%}
        else
        {
        %>
        <form action="Deleteitem" method="get">
          <input type="hidden" name="itt" value="<%=item.getId()%>"/>
          <input type="submit" value="Delete"/>
        </form>
        <%}%>
      </td>
   </tr>
    <%
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
<form action = "Dispcart" method = "get">
  <input type="submit" value="View cart"/>
</form>
  <a href = "Login.jsp" style="color:black;"><b>Logout</b></a>
  <%}%>
</div></center></br>
</body>
</html>
