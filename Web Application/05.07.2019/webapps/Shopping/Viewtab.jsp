<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Items"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Items</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
<%
   ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");
   String choice=(String) session.getAttribute("choi");
if(!(choice.equals("User"))){
%></br>
<div id="container"><center>
<ul>
<form action="Additem.jsp">
  <li><input type="submit" class="button2" value="Add Item" /></li>
</form>
<form action="Dispreq" method = "get">
  <li><input type="submit" class="button2" value="View Requests" /></li>
</form>
<form action="Disppur" method = "get">
  <li><input type="submit" class="button2" value="View Purchases" /></li>
</form>
</ul></center>
</div>
</br></br></br>
<%}%>
</br>
<div>
  <center><b>Items</b></center>
</div>
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
          <input type="submit" class="button3" value="Add to cart"/>
        </form>
        <%}
        else
        {
        %>
        <form action="Deleteitem" method="get">
          <input type="hidden" name="itt" value="<%=item.getId()%>"/>
          <input type="submit" class="button3" value="Delete"/>
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
  <a href = "Home.jsp" class="button1" style="color:black;"><b>Home</b></a>
  <%}
  else
  {
  %>
<form action = "Dispcart" method = "get">
  <input type="submit" class="button1" value="View cart"/>
</form>
  <a href = "Login.jsp" class="button1" style="color:black;"><b>Logout</b></a>
  <%}%>
</div></center></br>
</body>
</html>
