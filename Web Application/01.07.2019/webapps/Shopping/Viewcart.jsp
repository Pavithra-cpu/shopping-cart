<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Cart</title>
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
   List<Cart> cart = (ArrayList<Cart>) request.getAttribute("cart");
%>
<a href = "DispitemU" style="color:black;"><b>Master List</b></a>
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
            <input type = "submit" value="Remove"/>
          </form>
        </td>
   </tr>
    <%
    }
    %>


</table></br>
<form action="Checkout" method="get">
    <input type = "submit" value = "Proceed to checkout"/>
</form>
</center>
</body>
</html>
