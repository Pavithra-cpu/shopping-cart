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
    <script type = "text/javascript" src = "search.js" ></script>
  </head>
  <body onload="select()">
    <%
    ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");
    String choice=(String) session.getAttribute("choice");
    if(choice.equals("Admin")){
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
    <%
    String msg = (String) request.getAttribute("msg");
    if(msg!=null){
      out.println(msg);
    }
    %>
    </center>
  </div><center>
    <form id="search" name="searchform" action="SearchItems" method="post">
      <label for="selectattr-1">Search by:</label>
      <select id="selectattr-1" name="attribute1">
        <option value="ITEMNAME" selected="selected">Item Name</option>
        <option value="QUANTITY">Quantity</option>
        <option value="PRICE">Price</option>
      </select>
      <select id="selectconst-1" name="constraint1">
        <option value="=">Equals</option>
        <option value="!=">Not Equals</option>
        <option value="LIKE_%">Starts with</option>
        <option value="LIKE%_">Ends with</option>
        <option value="LIKE%%">Contains</option>
        <option value=">">Greater than</option>
        <option value=">=">Greater than equal</option>
        <option value="<">Less than</option>
        <option value="<=">Less than equal</option>
      </select>
      <input type = "text" name="data1" id="value-1"/>
      <select name="condition">
        <option value="AND">and</option>
        <option value="OR">or</option>
      </select>
      <select id="selectattr-2" name="attribute2">
        <option value="ITEMNAME" selected="selected">Item Name</option>
        <option value="QUANTITY">Quantity</option>
        <option value="PRICE">Price</option>
      </select>
      <select id="selectconst-2" name="constraint2">
        <option value="=">Equals</option>
        <option value="!=">Not Equals</option>
        <option value="LIKE_%">Starts with</option>
        <option value="LIKE%_">Ends with</option>
        <option value="LIKE%%">Contains</option>
        <option value=">">Greater than</option>
        <option value=">=">Greater than equal</option>
        <option value="<">Less than</option>
        <option value="<=">Less than equal</option>
      </select>
      <input type = "text" name = "data2" id="value-2"/>
      <input type = "submit" value = "search"/>
    </form></center>
    <center><b>Items</b></br><table>
    <tr>
    <th>Item ID</th><th>Item name</th><th>Quantity</th><th>Price</th>
    <%
    if(choice.equals("User")){
    %>
    <th>Add to cart</th>
    <%}
    else if(choice.equals("Admin"))
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
        else if(choice.equals("Admin"))
        {
        %>
        <form action="DeleteItem" method="get">
          <input type="hidden" name="itt" value="<%=item.getId()%>"/>
          <input type="submit" class="button3" value="Delete"/>
        </form>
        <%}%>
      </td>
   </tr>
   <%}%>
  </table></center></br>
  <center><div class="row">
  <%
  if(choice.equals("User")){
  %>
  <form action = "ViewCart" method = "get">
    <input type="submit" class="button1" value="View cart" style="color:black"/>
  </form>
  <form action = "Logout" method = "get">
    <input type="hidden" name="logout" value="userl"/>
    <input type="submit" class="button1" value="Logout" style="color:black"/>
  </form>
  <%}
  else if(choice.equals("Admin"))
  {
  %>
  <form action = "Logout" method = "get">
    <input type="hidden" name="logout" value="adminl"/>
    <input type="submit" class="button1" value="Home" style="color:black"/>
  </form>
  <%}%>
  </div></center></br>
  </body>
</html>
