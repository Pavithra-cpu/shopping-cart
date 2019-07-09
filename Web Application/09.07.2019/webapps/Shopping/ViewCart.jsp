<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="queryclass.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Cart</title>
    <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="styles.css">
      <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>-->
  <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
  <script>
    $(document).ready(function () {
      var multl = 0;
      var totitl = 0;
      $("tr.roww").each(function () {
          var $vall1 = $('.val1', this).text();
          var $vall2 = $('.itno', this).text();
          var $totall = ($vall1) * ($vall2);
          var $totl = 1 * ($vall2);
          $('.ittotal',this).text($totall);
          multl += $totall;
          totitl += $totl;
      });
      $("#grandtotal").text(multl);
      $("#items").text(totitl);
      $(".roww input").click(calculate);
      $(".roww input").keyup(calculate);
      $(".roww input").keyup(update);
      $(".roww input").click(update);

      function calculate() {
          var mult = 0;
          var totit = 0;
          $("tr.roww").each(function () {
            var $aquan  = parseInt($('.val0',this).text());
            if(($('.val2',this).val()<=0)||($('.val2',this).val()>$aquan)){
              alert("Please enter valid number or check the available quantity:(")
            }
            else{
              var $val1 = $('.val1',this).text();
              var $val2 = $('.val2',this).val();
              var $total = ($val1) * ($val2);
              var $tot = 1 * ($val2);
              $('.ittotal',this).text($total);
              $('.itno',this).text($tot);
              mult += $total;
              totit += $tot;
            }
          });
          $("#grandtotal").text(mult);
          $("#items").text(totit);
      }
      function update(){
        $("tr.roww").each(function () {
          var $aquanu  = parseInt($('.val0',this).text());
          if(($('.val2',this).val()>0)&&($('.val2',this).val()<=$aquanu)){
            //var $id = $(this).closest('tr').find('td').eq(0).html();
            var $id = $('.id',this).text();
            var $quan = $('.val2',this).val();
            var $price = $('.ittotal',this).text();
            $.ajax({
              url : 'UpdateCart',
              type : 'POST',
              data : {
                  iid : $id,
                  cquan : $quan,
                  cprice : $price
              },
              success : function(responseText){
              },
              error : function(xhr, status, error){
                  alert(xhr+" "+status+" "+error);
              }
            });
          }
        });
      }
    });
  </script>
  </head>
  <body>
  <%
     List<Cart> cart = (ArrayList<Cart>) request.getAttribute("cart");
  %>
  <a href = "ViewItems" class="button1" style="color:black;"><b>Back</b></a>
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
          <td><span class="id"><%out.print(C.getIid());%></span></td>
          <td><%out.print(C.getIname());%></td>
          <td><span class="val0"><%out.print(C.getAquantity());%></span></td>
          <td><span class="val1"><%out.print(C.getPrice());%></span></td>
          <td><input type = "number" class= "val2" value="<%=C.getCquantity()%>"/></td>
          <td><span class="itno"><%out.println(C.getCquantity());%></span></td>
          <td><span class="ittotal"><%out.println(C.getCprice());%></span></td>
          <td>
            <form action="RemoveFromCart" method="get">
              <input type = "hidden" value="<%=C.getIid()%>" name="id"/>
              <input type = "submit" class="button3" value="Remove"/>
            </form>
          </td>
     </tr>
      <%
      }
      %>
  <tr><td colspan="4" align="right">Total items=
    <span id = "items"></span>
  </td>
  <td colspan="3" align="right">Cart total=
    <span id = "grandtotal">0</span>
  </td></tr>
  </table></br>
  <form action="AddCheckout" method="get" id = "arrr">
      <input type = "hidden" id = "arr" name = "arr"/>
      <input type = "submit" class="button1" value = "Proceed to checkout"/>
  </form>
  </center>
  </body>
</html>
