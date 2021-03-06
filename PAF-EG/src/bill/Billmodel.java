package bill;
import java.sql.*;

public class Billmodel {
 //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/power_grid", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }


// @POST
public String insertItem(String Customer_Name, String Customer_Account, String Date, String Units_Used,
    String Amount) {
  String output = "";
  try {
    Connection con = connect();
    if (con == null) {
      return "Error while connecting to the database for inserting.";
    }
    
    // create a prepared statement
    String query = " insert into bill (`Customer_Name`,`Customer_Account`,`Date`,`Units_Used`,`Amount`)" + " values (?, ?, ?, ?, ?)";
    
    PreparedStatement preparedStmt = con.prepareStatement(query);
    // binding values
    preparedStmt.setString(1, Customer_Name);
    preparedStmt.setString(2, Customer_Account);
    preparedStmt.setString(3, Date);
    preparedStmt.setString(4, Units_Used);
    preparedStmt.setString(5, Amount);
    
    
    // execute the statement
    preparedStmt.execute();
    
    con.close();
    
    // output
    String newUse = readItems();
    output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
    
  } 
  catch (Exception e) {
    
    output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
    System.err.println(e.getMessage());
  }
  return output;
}


// @GET
public String readItems() {
String output = "";
try {
  Connection con = connect();
  if (con == null) {
    return "Error while connecting to the database for reading.";
  }
  
  
  // Prepare the html table to be displayed
  output = "<table border='1'><tr><th>Bill ID</th><th>Customer Name</th>" + "<th>Customer Account</th>" + "<th>Date</th>" + "<th>Units Used</th>" + "<th>Amount</th>" + "<th>Update</th> <th>Remove</th></tr>";

  String query = "select * from bill";
  Statement stmt = con.createStatement();
  ResultSet rs = stmt.executeQuery(query);
  
  
  // iterate through the rows in the result set
  while (rs.next()) {
	  String Bill_ID = Integer.toString(rs.getInt("Bill_ID"));
	  String Customer_Name = rs.getString("Customer_Name");
	  String Customer_Account = rs.getString("Customer_Account");
	  String Date = rs.getString("Date");
	  String Units_Used = rs.getString("Units_Used");
	  String Amount = rs.getString("Amount");
    
    
    // Add into the html table
    output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='' value='" + Bill_ID + "'> </td>";
    output += "<td>" + Customer_Name + "</td>";
    output += "<td>" + Customer_Account + "</td>";
    output += "<td>" + Date + "</td>";
    output += "<td>" + Units_Used + "</td>";
    output += "<td>" + Amount + "</td>";
    
    
    // buttons
    output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"
    + Bill_ID + "'>" + "</td>"
        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
        + Bill_ID + "'>" + "</td></tr>";
  }
  con.close();
  
  
  // Complete the html table
  output += "</table>";
  
} 
catch (Exception e) {
  
  output = "Error while reading the Bills.";
  System.err.println(e.getMessage());
}
return output;
}


public String updateItem(String Bill_ID, String Customer_Name, String Customer_Account, String Date, String Units_Used, String Amount)

 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE bill SET Customer_Name=?,Customer_Account=?,Date=?,Units_Used=?,Amount=? WHERE Bill_ID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, Customer_Name);
 preparedStmt.setString(2, Customer_Account);
 preparedStmt.setString(3, Date);
 preparedStmt.setString(4, Units_Used);
 preparedStmt.setString(5, Amount);
 preparedStmt.setInt(6, Integer.parseInt(Bill_ID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 
 String newItems = readItems();
 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 

 }
 
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the bill.\"}"; 
 System.err.println(e.getMessage());
 }
 return output;
 }

public String deleteItem(String Bill_ID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from bill where bill_ID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(Bill_ID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newItems = readItems();
 output = "{\"status\":\"success\",\"data\":\""+ newItems +"\"}"; 
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\",\"data\":\"Error while deleting the bill.\"}"; 
 System.err.println(e.getMessage());
 }
 return output;
 }
}