package AIR;


import java.sql.*;

import hsbank.Banker;

public class AIRDB {
	static  Connection con         = null;
    //static Statement stmt         = null;
	
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

		loadConnectair();
	}
 // JDBC ����̹� �ε� �� ����, ���� �����̸� true, ���и� false ��ȯ�ϴ� �޼ҵ�
 	public static boolean loadConnectair()  {
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
 	
 // Customer ��ü�� ����� ���̺� customer�� ���÷� �����ϴ� �޼ҵ�
 	public static int insertCustomer(Customer customer) {
 		int updateCnt = 0;

 		try {                      
 			// SQL ���ǹ��� �����Ѵ�.
 			String sql = "insert into customer values (?, ?, ?, ?, ?, ?, ?, ?);" ;
 			outputForDebug("In insertCustomer() : " + sql);
 			
 			PreparedStatement prStmt = con.prepareStatement(sql);

 			prStmt.setString(1, customer.getID());
 			prStmt.setString(2, customer.getPassword());
 			prStmt.setString(3, customer.getName());
 			prStmt.setString(4, customer.getGender());
 			prStmt.setInt(5, customer.getNumber());
 			prStmt.setInt(6, customer.getAge());
 			prStmt.setString(7, customer.getPassportNo());
 			prStmt.setString(8, customer.getAddress());

 			updateCnt = prStmt.executeUpdate();  		
 		} catch( SQLException ex ) {

 			System.err.println("\n  ??? SQL exec error in insertCustomer(): " + ex.getMessage() );
 		}

 		return updateCnt;
 	}
 // Banker ��ü�� ����� ���̺� banker�� ���÷� �����ϴ� �޼ҵ�
 	public static int insertManager(Manager manager) {
 		int updateCnt = 0;

 		try {                      
 			// SQL ���ǹ��� �����Ѵ�.
 			String sql = "insert into manager values (?, ?);" ;
 			outputForDebug("In insertManager() : " + sql);
 			
 			PreparedStatement prStmt = con.prepareStatement(sql);

 			prStmt.setString(1, manager.getMgrID());
 			prStmt.setInt(2, manager.getPassword());
 			

 			updateCnt = prStmt.executeUpdate();  		
 		} catch( SQLException ex ) {

 			System.err.println("\n  ??? SQL exec error in insertManager(): " + ex.getMessage() );
 		}

 		return updateCnt;
 	}
}
    
    