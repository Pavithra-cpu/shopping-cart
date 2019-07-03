<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Cart</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
  </head>
<body>
<%
   List<Cart> cart = (ArrayList<Cart>) request.getAttribute("cart");
%>
<a href = "DispitemU" class="button1" style="color:black;"><b>Back</b></a>
<center>
  <table>
  <tr>
  <th>Item ID</th><th>Item Name</th><th>Available Quantity</th><th>Price</th><th>In cart</th>
</tr>
<%
    for(Cart C : cart)
    {
      %>
      <tr>
        <td>
          <%
          out.println(C.getIid());
          %>
        </td>
        <td>
          <%
          out.println(C.getIname());
          %>
        </td>
        <td>
          <%
          out.println(C.getAquantity());
          %>
        </td>
        <td>
          <%
          out.println(C.getPrice());
          %>
        </td>
        <td>
          <form action="Removefromcart" method="get">
            <input type = "hidden" value="<%=C.getIid()%>" name="id"/>
            <input type = "number" value="<%=C.getQuantity()%>" name="need"/>
            <input type = "submit" class="button3" value="Remove"/>
          </form>
        </td>
   </tr>
    <%
    }
    %>


</table></br>
<form action="Checkout" method="get">
    <input type = "submit" class="button1" value = "Proceed to checkout"/>
</form>
</center>
</body>
</html>
