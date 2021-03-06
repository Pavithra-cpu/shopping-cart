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
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script type="text/javascript" src="jquery.chained.js" charset="utf-8"></script>
    <script>
    $(document).ready(function (){
      $(document).on('click','.delbtn', function(){
          $(this).closest('tr').remove();
          $('#tableID tbody>tr:last #delete').attr("disabled",false);
        });
      $('.button3').click(passvalues);
      function passvalues(){
      var attr = new Array();
      var cons = new Array();
      var val = new Array();
      var cond = new Array();
      $("tr.roww").each(function () {
        var $att = $('.attr',this).val();
        var $con = $('.const',this).val();
        var $va = $('.val',this).val();
        var $co = $('.cond',this).val();
        attr.push($att);
        cons.push($con);
        val.push($va);
        cond.push($co);
      });
      document.getElementById("ro1").value = attr;
      document.getElementById("ro2").value = cons;
      document.getElementById("ro3").value = val;
      document.getElementById("ro4").value = cond;
    }
    });
    </script>
  </head>
  <body>
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
      <table id="tableID" border="1">
    <tbody>
        <tr class="roww">
            <td>
      <select id="attribute" name="attribute" class="attr">
        <option value="">select</option>
        <option value="ITEMNAME">Item Name</option>
        <option value="QUANTITY">Quantity</option>
        <option value="PRICE">Price</option>
      </select></td>
	<td>
      <select id="constraint" name="constraint" class="const">
        <option value="">select</option>
        <option value="=" class="ITEMNAME">Equals</option>
        <option value="!=" class="ITEMNAME">Not Equals</option>
        <option value="LIKE_%" class="ITEMNAME">Starts with</option>
        <option value="LIKE%_" class="ITEMNAME">Ends with</option>
        <option value="LIKE%%" class="ITEMNAME">Contains</option>
        <option value=">" class="QUANTITY">Greater than</option>
        <option value=">=" class="QUANTITY">Greater than equal</option>
        <option value="<" class="QUANTITY">Less than</option>
        <option value="<=" class="QUANTITY">Less than equal</option>
        <option value=">" class="PRICE">Greater than</option>
        <option value=">=" class="PRICE">Greater than equal</option>
        <option value="<" class="PRICE">Less than</option>
        <option value="<=" class="PRICE">Less than equal</option>
      </select></td>
      <td>
      <input type = "text" name="data" class="val"/></td>
      <td>
      <select name="condition" class="cond">
        <option value=" ">None</option>
        <option value="AND">and</option>
        <option value="OR">or</option>
      </select></td>
      <td>
      <button type="button" class="delbtn" id="delete">delete</button></td>
        </tr>
    </tbody>
    </table>
    <a href="#" onClick="addRow(tableID)" class="button3">add</a>
    <script>
     var row = $('#tableID tbody:first').html();
     $("#constraint").chained("#attribute");

     function addRow(tableID) {
        $('#tableID tbody>tr:last #delete').attr("disabled",true);
         $(row).appendTo('#tableID');
         var rowCnt = $('#tableID tbody>tr').length;

         $('#tableID tbody>tr:last select:first').attr('id', 'attribute' + rowCnt);
         $('#tableID tbody>tr:last #constraint').attr('id', 'constraint' + rowCnt);
         $('#constraint' + rowCnt).chained("#attribute" + rowCnt);
     }
    </script>
      <input type = "hidden" id = "ro1" name = "ro1"/>
      <input type = "hidden" id = "ro2" name = "ro2"/>
      <input type = "hidden" id = "ro3" name = "ro3"/>
      <input type = "hidden" id = "ro4" name = "ro4"/>
      <input type = "submit" value = "search" class="button3" onclick="passvalues()"/>
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
