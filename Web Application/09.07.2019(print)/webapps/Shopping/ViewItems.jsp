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
<form action="AddItem.jsp">
  <li><input type="submit" class="button2" value="Add Item" /></li>
</form>
<form action="ViewRequests" method = "get">
  <li><input type="submit" class="button2" value="View Requests" /></li>
</form>
<form action="ViewCheckouts" method = "get">
  <li><input type="submit" class="button2" value="View Purchases" /></li>
</form>
</ul></center>
</div>
</br></br></br>
<%}%>
</br>
<div>
  <center><b>Items</b></br>
  <%
  String msg = (String) request.getAttribute("msg");
  if(msg!=null){
    out.println(msg);
  }
  %>
</center>
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
        <form action="AddToCart" method="get">
          <input type="hidden" name="it" value="<%=item.getId()%>"/>
          <input type="hidden" name="qu" value="<%=item.getQuantity()%>"/>
          <input type="submit" class="button3" value="Add to cart"/>
        </form>
        <%}
        else
        {
        %>
        <form action="DeleteItem" method="get">
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
<form action = "ViewCart" method = "get">
  <input type="submit" class="button1" value="View cart"/>
</form>
<form action = "Logout" method = "get">
  <input type="submit" class="button1" value="Logout"/>
</form>
  <%}%>
</div></center></br>
</body>
</html>
