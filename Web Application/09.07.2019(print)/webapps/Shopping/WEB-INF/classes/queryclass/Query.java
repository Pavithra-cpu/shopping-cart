package queryclass;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.postgresql.Driver;
import queryclass.Items;
import queryclass.Purchases;
import queryclass.Order;

public class Query{
  Connection con=null;

  public Query()throws SQLException,ClassNotFoundException{
    Class.forName("org.postgresql.Driver");
    con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdata","user","deep");
  }

  public void addItem(Items I)throws SQLException{
    PreparedStatement pstmt = null;
    try{
      String query = "INSERT INTO items(itemname,quantity,price) VALUES(?,?,?)";
      pstmt=con.prepareStatement(query);
      pstmt.setString(1,I.getIname());
      pstmt.setInt(2,I.getQuantity());
      pstmt.setInt(3,I.getPrice());
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
  }

  public List<Items> viewItems()throws SQLException{
    List<Items> items = new ArrayList<Items>();
    Statement stmt = null;

    try{
      stmt = con.createStatement();
      String query = "SELECT * FROM items";
      ResultSet rs = stmt.executeQuery(query);
      while(rs.next()){
        Items I = new Items();
        I.setId(rs.getInt("id"));
        I.setIname(rs.getString("itemname"));
        I.setQuantity(rs.getInt("quantity"));
        I.setPrice(rs.getInt("price"));
        items.add(I);
      }
    }
    finally{
      if(stmt!=null){
        stmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return items;
  }

  public void deleteItem(int id)throws SQLException{
    PreparedStatement pstmt = null;
    try{
      String query = "DELETE FROM items WHERE id=?";
      pstmt=con.prepareStatement(query);
      pstmt.setInt(1,id);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
  }

  public int addUser(String uname,String email)throws SQLException{
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    int uid = 0;
    try{
      String query = "INSERT INTO users(username,email) VALUES (?,?) ON CONFLICT (email) DO NOTHING";
      pstmt1 = con.prepareStatement(query);
      pstmt1.setString(1,uname);
      pstmt1.setString(2,email);
      pstmt1.executeUpdate();
      String query1 = "SELECT uid FROM users WHERE email = ?";
      pstmt2  = con.prepareStatement(query1);
      pstmt2.setString(1,email);
      ResultSet rs = pstmt2.executeQuery();
      rs.next();
      uid = rs.getInt("uid");
    }
    finally{
      if(pstmt1!=null){
        pstmt1.close();
      }
      if(pstmt2!=null){
        pstmt2.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return uid;
  }

  public void addToCart(int uid,int itid)throws SQLException{
    PreparedStatement pstmt = null;
    try{
      String query = "INSERT INTO cart (uid,item_id,cquantity) VALUES (?,?,?)";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,uid);
      pstmt.setInt(2,itid);
      pstmt.setInt(3,1);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
  }

  public void updateCart(int uid,String iid,String cquan)throws SQLException{
    PreparedStatement pstmt = null;
    int iiid = Integer.parseInt(iid);
    int icquan = Integer.parseInt(cquan);
    //int icprice = Integer.parseInt(cprice);
    try{
      String query = "UPDATE cart SET cquantity = ? WHERE uid = ? AND item_id = ?";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,icquan);
      pstmt.setInt(2,uid);
      pstmt.setInt(3,iiid);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
  }

  public List<Cart> viewCart(int uid)throws SQLException{
    List<Cart> cart = new ArrayList<Cart>();
    PreparedStatement pstmt = null;
    try{
      String query = "SELECT items.*,cart.cquantity FROM cart JOIN items ON cart.item_id = items.id WHERE uid = ?";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,uid);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Cart C = new Cart();
        C.setIid(rs.getInt("id"));
        C.setIname(rs.getString("itemname"));
        C.setAquantity(rs.getInt("quantity"));
        C.setPrice(rs.getInt("price"));
        C.setCquantity(rs.getInt("cquantity"));
        //C.setCprice(rs.getInt("cprice"));
        cart.add(C);
      }
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return cart;
  }

  public void removeFromCart(int uid,int id)throws SQLException{
    PreparedStatement pstmt = null;
    try{
      String query = "DELETE FROM cart WHERE uid = ? AND item_id= ? ";
      pstmt=con.prepareStatement(query);
      pstmt.setInt(1,uid);
      pstmt.setInt(2,id);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
  }

  public List<Object> addCheckout(int uid)throws SQLException{
  //  UUID uuid = UUID.randomUUID();
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    PreparedStatement pstmt4 = null;
    PreparedStatement pstmt5 = null;
    PreparedStatement pstmt6 = null;
    Statement stmt = null;
    int count = 0;
    int price = 0;
    String email = null;
    Object pid = null;
    try{
      int j=0;
      stmt = con.createStatement();
      String query = "SELECT uuid_generate_v4()";
      ResultSet rsid = stmt.executeQuery(query);
      rsid.next();
      pid = rsid.getObject("uuid_generate_v4");
      //updating quantity in items relation
      String query1 = "SELECT item_id,cquantity FROM cart WHERE uid = ?";
      pstmt1 = con.prepareStatement(query1);
      pstmt1.setInt(1,uid);
      ResultSet rs = pstmt1.executeQuery();
      while(rs.next()){
        String query2 = "UPDATE items SET quantity = quantity-? WHERE id = ?";
        pstmt2 = con.prepareStatement(query2);
        pstmt2.setInt(1,rs.getInt("cquantity"));
        pstmt2.setInt(2,rs.getInt("item_id"));
        pstmt2.executeUpdate();
        pstmt2.close();
        //add the purchase details into the master list
        String query3 = "INSERT INTO purchases (pid,user_id,item_id,pquantity) VALUES (CAST(? AS UUID),?,?,?)";
        pstmt3 = con.prepareStatement(query3);
        pstmt3.setObject(1,pid);
        pstmt3.setInt(2,uid);
        pstmt3.setInt(3,rs.getInt("item_id"));
        pstmt3.setInt(4,rs.getInt("cquantity"));
        pstmt3.executeUpdate();
        pstmt3.close();
        j++;
      }
      //clear the Cart
      String query4 = "DELETE FROM cart WHERE uid = ?";
      pstmt4 = con.prepareStatement(query4);
      pstmt4.setInt(1,uid);
      pstmt4.executeUpdate();
      //select user email to send the email
      String query5 = "SELECT email FROM users WHERE uid = ?";
      pstmt5 = con.prepareStatement(query5);
      pstmt5.setInt(1,uid);
      ResultSet rsmail = pstmt5.executeQuery();
      rsmail.next();
      email = rsmail.getString("email");
      //select count and total price for a purchases
      String query6 = "SELECT SUM(pquantity) as tquantity,SUM(items.price * purchases.pquantity) as tprice FROM purchases JOIN items ON purchases.item_id = items.id WHERE pid = ?";
      pstmt6 = con.prepareStatement(query6);
      pstmt6.setObject(1,pid);
      ResultSet rscp = pstmt6.executeQuery();
      rscp.next();
      count = rscp.getInt("tquantity");
      price = rscp.getInt("tprice");
    }
    finally{
      if(pstmt1!=null){
        pstmt1.close();
      }
      if(pstmt4!=null){
        pstmt4.close();
      }
      if(pstmt5!=null){
        pstmt5.close();
      }
      if(pstmt6!=null){
        pstmt6.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return Arrays.asList(email,pid,count,price);
  }

  public List<Purchases> viewCheckouts()throws SQLException{
    List<Purchases> purchase = new ArrayList<Purchases>();
    Statement stmt = null;

    try{
      stmt = con.createStatement();
      String query = "SELECT purchases.pid,users.uid,users.username,users.email,COUNT(purchases.pid) FROM purchases JOIN users ON purchases.user_id=users.uid GROUP BY pid,users.uid";
      ResultSet rs = stmt.executeQuery(query);
      while(rs.next()){
        Purchases P = new Purchases();
        P.setPid(rs.getObject("pid"));
        P.setUid(rs.getInt("uid"));
        P.setUname(rs.getString("username"));
        P.setEmail(rs.getString("email"));
        P.setCount(rs.getInt("count"));
        purchase.add(P);
      }
    }
    finally{
      if(stmt!=null){
        stmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return purchase;
  }

  public List<Order> viewCheckout(Object pid)throws SQLException{
    List<Order> order = new ArrayList<Order>();
    PreparedStatement pstmt = null;
    try{
      String query1 = "SELECT items.id,items.itemname,purchases.pquantity,items.price * purchases.pquantity as price FROM purchases JOIN items ON purchases.item_id = items.id WHERE pid = CAST(? AS UUID)";
      pstmt = con.prepareStatement(query1);
      pstmt.setObject(1,pid);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Order O = new Order();
        O.setIid(rs.getInt("id"));
        O.setIname(rs.getString("itemname"));
        O.setPquantity(rs.getInt("pquantity"));
        O.setPprice(rs.getInt("price"));
        order.add(O);
      }
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(con!=null){
        con.close();
      }
    }
    return order;
  }

}
