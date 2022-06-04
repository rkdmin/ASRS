package AIR;

import java.sql.*;

public class AIRDB {
	
	static  Connection con         = null;
    static Statement stmt         = null;
    static PreparedStatement  prStmt = null;
    static  ResultSet rs           = null ;
    
	static String driver;
	public static String dbms;
	static String URL;
	static String database;
    
	static String driverMySQL = "com.mysql.jdbc.Driver";
	static String URLLocalMySQL = "jdbc:mysql://localhost:3306/" ;
	static String URLRemoteMySQL = "jdbc:mysql://203.252.21.54:3306/";
    
    static {
		driver = driverMySQL;
		dbms = "MySQL";
		URL = URLLocalMySQL;
		database = "air";
	}
    
 // DEBUG ���� ���� true�̸� debug�� ���� ������ ��µ�
 	static boolean DEBUG = false;
 	
 	static void outputForDebug(String msg) {
 		if (DEBUG)
 			System.out.println("  << for debug >> " + msg);		
 	}
 	
 	public static void setDBMS(String dbmsTo) {
		outputForDebug("in setSBMS(): DBMS = " + dbmsTo); 
		
		if (dbmsTo.equals("MySQL")){
			driver = driverMySQL;
			dbms = "MySQL";
			URL = URLLocalMySQL;
		}
		else if (dbmsTo.equals("Remote MySQL")){
			driver = driverMySQL;
			dbms = "Remote MySQL";
			URL = URLRemoteMySQL;
		}

		loadConnectAir();
	}
 // JDBC ����̹� �ε� �� ����, ���� �����̸� true, ���и� false ��ȯ�ϴ� �޼ҵ�
 	public static boolean loadConnectAir()  {
 		return loadConnect("air");
 	}
 	
 	
 // ����̺� �ε� �� �����ϴ� �޼ҵ�
 	public static boolean loadConnect(String database)  {
 		try {
 			// ����̹� �ε�
 			Class.forName(driverMySQL);
 		} catch ( java.lang.ClassNotFoundException e ) {
 			System.err.println("\n  ??? Driver load error in loadConnect(): " + e.getMessage() );
 			e.printStackTrace();
 		}

 		try {
 			// �����ϱ� - air �����ͺ��̽��� ����
 			con = DriverManager.getConnection(URL + database, "root", "onlyroot");
 			outputForDebug("���� ����: " + URL + database + "�� �����");
 			
 			return true;
 		} catch( SQLException ex ) {
 			System.err.println("\n  ??? Connection error in loadConnect(): " + ex.getMessage() );
 			ex.printStackTrace();
 		}	   		
 	
 		return false;
 	}
 // �־��� SQL ���� �����ϴ� �޼ҵ�
  	public static void executeAnyQuery(String sql) {
  		try {
  			Statement stmt = con.createStatement();
  			stmt.execute(sql);
  		}
  		catch(SQLException ex ) {
  			System.err.println("\n  ??? SQL exec error in executeAnyQuery(): " + ex.getMessage() );
  			ex.printStackTrace();
  		}	   
  	}
  	public static ResultSet selectQuery(String sql) { 
   	   try {
   		   // Statement ���� 
   		   stmt = con.createStatement();
   		   rs = stmt.executeQuery(sql);  

   	   } catch( SQLException ex ) 	    {
   		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
   	   }
   			
   	   return rs;
   		
      }
 	
 // Customer ��ü�� ����� ���̺� customer�� ���÷� �����ϴ� �޼ҵ�
 	public static boolean insertCustomer(Customer customer) {
		try {
			customer.output();
			String sql = "insert into customer values (?, ?, ?, ?, ?, ?, ?, ?);" ;
			prStmt= con.prepareStatement(sql);  
			
			prStmt.setString(1, customer.getId());
 			prStmt.setString(2, customer.getPassword());
 			prStmt.setString(3, customer.getName());
 			prStmt.setString(4, customer.getGender());
 			prStmt.setString(5, customer.getNumber());
 			prStmt.setInt(6, customer.getAge());
 			prStmt.setString(7, customer.getPassportNo());
 			prStmt.setString(8, customer.getAddress());
			prStmt.executeUpdate();
			return true;
		}
		catch(SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in executeAnyQuery(): " + ex.getMessage() );
			ex.printStackTrace();
			return false;
		}	 
 	}
 	
 	// ���̵��ߺ��� Ȯ���ϴ� �޼ҵ� �ߺ��� ������ true
 	public static boolean idDuplication(String id) {
 		return true;
 	}
 	
 	// ���̵� ������� �α��� �Һ��� ��ü ��ȯ(��ġ�ϴ� ȸ������������ null�� ��ȯ)
 	public static Customer loginProcess(String id,  String password) {
 		return null;
 	}

}
    
    