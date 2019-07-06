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
    }
  }

  public List<Items> dispItem()throws SQLException{
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
    }
    return uid;
  }

  public void writeitem(int uid,int itid)throws SQLException{
    PreparedStatement pstmt = null;
    try{
      int quantity = 1;
      String query = "INSERT INTO cart (uid,item_id) VALUES (?,?)";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,uid);
      pstmt.setInt(2,itid);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
    }
  }

  public List<Cart> viewcart(int uid)throws SQLException{
    List<Cart> cart = new ArrayList<Cart>();
    PreparedStatement pstmt = null;
    try{
      String query = "SELECT items.* FROM cart JOIN items ON cart.item_id = items.id WHERE uid = ?";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,uid);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Cart C = new Cart();
        C.setIid(rs.getInt("id"));
        C.setIname(rs.getString("itemname"));
        C.setAquantity(rs.getInt("quantity"));
        C.setPrice(rs.getInt("price"));
        cart.add(C);
      }
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
    }
    return cart;
  }

  public void removefromcart(int uid,int id)throws SQLException{
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
    }
  }

  public void addCheckout(int uid,String[] quaa)throws SQLException{
  //  UUID uuid = UUID.randomUUID();
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    PreparedStatement pstmt4 = null;
    Statement stmt = null;
    int counti = 0;
    String ss = quaa[0];
    String[] quaan=ss.split(",");
    Integer[] quanlist = new Integer[quaan.length];
    int i=0;
    for(String str:quaan){
      try{
        quanlist[i]=Integer.parseInt(str);
        i++;
      }
      catch(NumberFormatException e){
        throw new IllegalArgumentException("not a number"+str+"at index"+i,e);
      }
    }
    try{
      int j=0;
      stmt = con.createStatement();
      String query = "SELECT uuid_generate_v4()";
      ResultSet rsid = stmt.executeQuery(query);
      rsid.next();
      //updating quantity in items relation
      String query1 = "SELECT item_id FROM cart WHERE uid = ?";
      pstmt1 = con.prepareStatement(query1);
      pstmt1.setInt(1,uid);
      ResultSet rs = pstmt1.executeQuery();
      while(rs.next()){
        String query2 = "UPDATE items SET quantity = quantity-"+quanlist[j]+" WHERE id = ?";
        pstmt2 = con.prepareStatement(query2);
        pstmt2.setInt(1,rs.getInt("item_id"));
        pstmt2.executeUpdate();
        pstmt2.close();
        //add the purchase details into the master list
        String query3 = "INSERT INTO purchases (pid,user_id,item_id,pquantity) VALUES (CAST(? AS UUID),?,?,?)";
        pstmt3 = con.prepareStatement(query3);
        pstmt3.setObject(1,rsid.getObject("uuid_generate_v4"));
        pstmt3.setInt(2,uid);
        pstmt3.setInt(3,rs.getInt("item_id"));
        pstmt3.setInt(4,quanlist[j]);
        pstmt3.executeUpdate();
        pstmt3.close();
        j++;
      }
      //clear the Cart
      String query4 = "DELETE FROM cart WHERE uid = ?";
      pstmt4 = con.prepareStatement(query4);
      pstmt4.setInt(1,uid);
      pstmt4.executeUpdate();
    }
    finally{
      if(pstmt1!=null){
        pstmt1.close();
      }
      if(pstmt4!=null){
        pstmt4.close();
      }
    }
  }

  public List<Purchases> dispPurchase()throws SQLException{
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
    }
    return purchase;
  }

  public List<Order> vieworder(int uid,Object pid)throws SQLException{
    List<Order> order = new ArrayList<Order>();
    PreparedStatement pstmt = null;
    try{
      String query1 = "SELECT items.id,items.itemname,purchases.pquantity FROM purchases JOIN items ON purchases.item_id = items.id WHERE pid = CAST(? AS UUID) AND user_id = ?";
      pstmt = con.prepareStatement(query1);
      pstmt.setObject(1,pid);
      pstmt.setInt(2,uid);
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()){
        Order O = new Order();
        O.setIid(rs.getInt("id"));
        O.setIname(rs.getString("itemname"));
        O.setPquantity(rs.getInt("pquantity"));
        order.add(O);
      }
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
    }
    return order;
  }
}
