package application;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopT {

	Connection conn;
	String ID_part;
	String type;
	String vendor;
	String price;
	String exist;
   ShopT(int size) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		String url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String username = "root";
		String password = "67245";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Successfully connected");
        Statement stmt = conn.createStatement();
     String strSelect = "select * from shop";
     ResultSet rset = stmt.executeQuery(strSelect);
     int i =0;
     while(rset.next()&& i<size) {
    	if(i==size-1) {
        this.ID_part=rset.getString("ID_part");
        this.price=rset.getString("price");
        this.vendor=rset.getString("vendor_code_sh");
        this.exist=rset.getString("exist");
        this.type=rset.getString("type");
    	}
        i++;
     }
    //vendor = rset.getString("vendor_code_cmp");
   }
   ShopT(String ID_part, String Type, String Vendor_code_sh, String Price, String Exist){
	   this.ID_part = ID_part;
	   this.type = Type;
	   this.vendor = Vendor_code_sh;
	   this.price = Price;
	   this.exist = Exist;
   }
   @SuppressWarnings("unused")
   public String getID_part() {
	   return this.ID_part;
   }
   public void setID_part(String ID) {
	   this.ID_part = ID;
   }
   public String getType() {
	   return this.type;
   }
   public void setType(String t) {
	   this.type = t;
   }
   public String getVendor() {
	   return this.vendor;
   }
   public void setVendor(String vend) {
	   this.vendor= vend;
   }
   public String getPrice() {
	   return this.price;
   }
   public void setPrice(String pr) {
	   this.price = pr;
   }
   public String getExist() {
	   return this.exist;
   }
   public void setExist(String ex) {
	   this.exist = ex;
   }
}