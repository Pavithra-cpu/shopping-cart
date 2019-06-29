import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/sample",
            "user", "deep");
         System.out.println("Opened database successfully");
	DatabaseMetaData meta = c.getMetaData();
	ResultSet rs = meta.getTables(null,null,"samplic",null);
	if(rs.next()){
	System.out.println("table exist");
	}
	else{
	
         stmt = c.createStatement();
         String sql = "CREATE TABLE samplic " +
            "(ID             BIGSERIAL   NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        CHAR(50), " +
            " SALARY         REAL)";
	System.out.println("Table created successfully");
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } 
}catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      
   }
}