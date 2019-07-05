<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Cart</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script>
      $(document).ready(function () {
      $(".roww input").click(multiply);
      $(".roww input").keyup(multiply);
      $(".roww input").keyup(add);
      $(".roww input").click(add);
      $(".button1").click(passvalues);

      function multiply() {
          var mult = 0;
          $("tr.roww").each(function () {
              var $val1 = $('.val1', this).text();
              var $val2 = $('.val2', this).val();
              var $total = ($val1) * ($val2);
              $('.ittotal',this).text($total);
              mult += $total;
          });
          $("#grandtotal").text(mult);
      }
      function add(){
        var totit = 0;
        $("tr.roww").each(function () {
          var $val3 = $('.val2', this).val();
          var $tot = 1 * ($val3);
          $('.itno',this).text($tot);
          totit += $tot;
        });
        $("#items").text(totit);
      }
      function passvalues(){
        var eait = new Array();
        $("tr.roww").each(function () {
          var $val4 = $('.itno',this).text();
          var $ind = 1 * ($val4);
          eait.push($ind);
        });
        document.getElementById("arr").value = eait;
      //  document.getElementById("arrr").submit();
      }
 });
</script>
  </head>
<body>
<%
   List<Cart> cart = (ArrayList<Cart>) request.getAttribute("cart");
   List<Integer> quantity = new ArrayList<Integer>();
   int total = 0;
%>
<a href = "DispitemU" class="button1" style="color:black;"><b>Back</b></a>
<center>
  <table>
  <tr>
  <th>Item ID</th><th>Item Name</th><th>Available Quantity</th><th>Price</th><th>Needed</th><th>In cart</th><th>Item total</th><th>Remove</th>
</tr>
<%
    for(Cart C : cart)
    {
      %>
      <tr class="roww">
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
        <td><span class="val1">
          <%
          out.println(C.getPrice());
          %>
        </span>
        </td>
        <td>
            <input type = "number" class= "val2"/>
        </td>
        <td>
          <span class="itno"><%int itemq = 0;%>
          </span>
        </td>
        <td>
          <span class="ittotal">0</span>
        </td>
        <td>
          <form action="Removefromcart" method="get">
            <input type = "hidden" value="<%=C.getIid()%>" name="id"/>
            <input type = "submit" class="button3" value="Remove"/>
          </form>
        </td>
   </tr>
    <%
    quantity.add(itemq);
    }
    %>
<tr><td colspan="4" align="right">Total items=
  <span id = "items"></span>
</td>
<td colspan="3" align="right">Cart total=
  <span id = "grandtotal">0</span>
</td></tr>
<%out.println(quantity);%>
</table></br>
<form action="Checkout" method="get" id = "arrr">
    <%
    session.setAttribute("quantity",quantity);
    %>
    <input type = "hidden" id = "arr" name = "arr"/>
    <input type = "submit" class="button1" onclick = "passvalues()" value = "Proceed to checkout"/>
</form>
</center>
</body>
</html>
