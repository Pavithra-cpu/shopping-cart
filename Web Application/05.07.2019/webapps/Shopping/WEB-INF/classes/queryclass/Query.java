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
  public static int purchaseno = 0;

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
    ResultSet rs = null;
    PreparedStatement ptstmt = null;
    String tname = null;
    String uuid = null;
    try{
      String  query0 = "SELECT username FROM users WHERE uid = ?";
      ptstmt = con.prepareStatement(query0);
      ptstmt.setInt(1,uid);
      ResultSet rss = ptstmt.executeQuery();
      rss.next();
      uuid = Integer.toString(uid);
      tname = rss.getString("username");
      DatabaseMetaData meta = con.getMetaData();
      rs = meta.getTables(null,null,tname+uuid,null);
      if(rs.next()){
        PreparedStatement pstmt2 = null;
        try{
          int quantity = 1;
          String query1 = "INSERT INTO "+tname+""+uuid+" (item_id, nquantity) VALUES (?,?)";
          pstmt2 = con.prepareStatement(query1);
          //pstmt2.setString(1,tname);
          pstmt2.setInt(1,itid);
          pstmt2.setInt(2,quantity);
          pstmt2.executeUpdate();
        }
        finally{
          if(pstmt2!=null){
            pstmt2.close();
          }
        }
      }
      else{
        Statement stmt1 = null;
        PreparedStatement pstmt3 = null;
        try{
          String qt = "CREATE TABLE "+tname+""+uuid+" (id BIGSERIAL NOT NULL PRIMARY KEY,item_id BIGINT REFERENCES items (id) NOT NULL,nquantity INT NOT NULL)";
          stmt1 = con.createStatement();
          stmt1.executeUpdate(qt);
          int quantity = 1;
          String query1 = "INSERT INTO "+tname+""+uuid+" (item_id, nquantity) VALUES (?,?)";
          pstmt3 = con.prepareStatement(query1);
          pstmt3.setInt(1,itid);
          pstmt3.setInt(2,quantity);
          pstmt3.executeUpdate();
        }
        finally{
          if(stmt1!=null){
            stmt1.close();
          }
          if(pstmt3!=null){
            pstmt3.close();
          }
        }
      }
    }
    finally{
      if(rs!=null){
        rs.close();
      }
    }
  }

  public List<Cart> viewcart(int uid)throws SQLException{
    List<Cart> cart = new ArrayList<Cart>();
    Statement stmt = null;
    PreparedStatement pstmt = null;
    String tabname = null;
    try{
      String query = "SELECT username FROM users WHERE uid = ?";
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1,uid);
      ResultSet rsu = pstmt.executeQuery();
      rsu.next();
      String tname = rsu.getString("username");
      String uuid = Integer.toString(uid);
      tabname = tname+uuid;
      stmt = con.createStatement();
      String query1 = "SELECT items.*,"+tabname+".nquantity FROM "+tabname+" JOIN items ON "+tabname+".item_id = items.id";
      //String query2 = que.replace("$tabname",tabname);
      ResultSet rs = stmt.executeQuery(query1);
      while(rs.next()){
        Cart C = new Cart();
        C.setIid(rs.getInt("id"));
        C.setIname(rs.getString("itemname"));
        C.setAquantity(rs.getInt("quantity"));
        C.setPrice(rs.getInt("price"));
        C.setQuantity(rs.getInt("nquantity"));
        cart.add(C);
      }
    }
    finally{
      if(stmt!=null){
        stmt.close();
      }
      if(pstmt!=null){
        pstmt.close();
      }
    }
    return cart;
  }

  public void removefromcart(int uid,int id)throws SQLException{
    PreparedStatement ptstmt = null;
    PreparedStatement pstmt = null;
    String tname = null;
    String uuid = null;
    try{
      String  query0 = "SELECT username FROM users WHERE uid = ?";
      ptstmt = con.prepareStatement(query0);
      ptstmt.setInt(1,uid);
      ResultSet rss = ptstmt.executeQuery();
      rss.next();
      uuid = Integer.toString(uid);
      tname = rss.getString("username");
      String tabname = tname+uuid;
      String query1 = "DELETE FROM "+tabname+" WHERE item_id=?";
      pstmt=con.prepareStatement(query1);
      pstmt.setInt(1,id);
      pstmt.executeUpdate();
    }
    finally{
      if(pstmt!=null){
        pstmt.close();
      }
      if(ptstmt!=null){
        ptstmt.close();
      }
    }
  }

  public void addCheckout(int uid,String[] quaa)throws SQLException{
    purchaseno = purchaseno + 1;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    Statement stmt1 = null;
    Statement stmt3 = null;
    String usname = null;
    int counti = 0;
    //int n = quaa.length;
    String ss = quaa[0];
    String[] quaan=ss.split(",");
    Integer[] quanlist = new Integer[quaan.length];
    String uuid = null;
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
      //to retrieve tablename
      String query1 = "SELECT username FROM users WHERE uid = ?";
      pstmt1 = con.prepareStatement(query1);
      pstmt1.setInt(1,uid);
      ResultSet rs = pstmt1.executeQuery();
      rs.next();
      usname = rs.getString("username");
      uuid = Integer.toString(uid);
      String tabname = usname+uuid;
      //updating quantity in items relation
      stmt1 = con.createStatement();
      String query2 = "SELECT item_id FROM "+tabname;
      ResultSet rss = stmt1.executeQuery(query2);
      while(rss.next()){
        String query3 = "UPDATE items SET quantity = quantity-"+quanlist[j]+" WHERE id = ?";
        pstmt2 = con.prepareStatement(query3);
        pstmt2.setInt(1,rss.getInt("item_id"));
        pstmt2.executeUpdate();
        pstmt2.close();
        //add the purchase details into the master list
        String query = "INSERT INTO purchases (pid,user_id,item_id,pquantity) VALUES (?,?,?,?)";
        pstmt3 = con.prepareStatement(query);
        pstmt3.setInt(1,purchaseno);
        pstmt3.setInt(2,uid);
        pstmt3.setInt(3,rss.getInt("item_id"));
        pstmt3.setInt(4,quanlist[j]);
        pstmt3.executeUpdate();
        pstmt3.close();
        j++;
      }
      //clear the Cart
      String query6 = "DELETE FROM "+tabname;
      stmt3 = con.createStatement();
      stmt3.executeUpdate(query6);
    }
    finally{
      if(pstmt1!=null){
        pstmt1.close();
      }
      if(stmt3!=null){
        stmt3.close();
      }
      if(stmt1!=null){
        stmt1.close();
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
        P.setPid(rs.getInt("pid"));
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

  public List<Order> vieworder(int uid,int pid)throws SQLException{
    List<Order> order = new ArrayList<Order>();
    PreparedStatement pstmt = null;
    try{
      String query1 = "SELECT items.id,items.itemname,purchases.pquantity FROM purchases JOIN items ON purchases.item_id = items.id WHERE pid = ? AND user_id = ?";
      pstmt = con.prepareStatement(query1);
      pstmt.setInt(1,pid);
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
