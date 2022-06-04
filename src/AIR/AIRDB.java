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
    
 // DEBUG 변수 값이 true이면 debug을 위한 정보가 출력됨
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
 // JDBC 드라이버 로드 및 연결, 연경 성공이면 true, 실패면 false 반환하는 메소드
 	public static boolean loadConnectAir()  {
 		return loadConnect("air");
 	}
 	
 	
 // 드라이브 로드 및 연결하는 메소드
 	public static boolean loadConnect(String database)  {
 		try {
 			// 드라이버 로딩
 			Class.forName(driverMySQL);
 		} catch ( java.lang.ClassNotFoundException e ) {
 			System.err.println("\n  ??? Driver load error in loadConnect(): " + e.getMessage() );
 			e.printStackTrace();
 		}

 		try {
 			// 연결하기 - air 데이터베이스와 연결
 			con = DriverManager.getConnection(URL + database, "root", "onlyroot");
 			outputForDebug("연결 성공: " + URL + database + "에 연결됨");
 			
 			return true;
 		} catch( SQLException ex ) {
 			System.err.println("\n  ??? Connection error in loadConnect(): " + ex.getMessage() );
 			ex.printStackTrace();
 		}	   		
 	
 		return false;
 	}
 // 주어진 SQL 문을 실행하는 메소드
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
   		   // Statement 생성 
   		   stmt = con.createStatement();
   		   rs = stmt.executeQuery(sql);  

   	   } catch( SQLException ex ) 	    {
   		   System.err.println("** SQL exec error in selectQuery() : " + ex.getMessage() );
   	   }
   			
   	   return rs;
   		
      }
 	
 // Customer 객체를 은행원 테이블 customer의 투플로 삽입하는 메소드
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
 	
 	// 아이디중복을 확인하는 메소드 중복이 없으면 true
 	public static boolean idDuplication(String id) {
 		return true;
 	}
 	
 	// 아이디 비번으로 로그인 소비자 객체 반환(일치하는 회원정보없으면 null값 반환)
 	public static Customer loginProcess(String id,  String password) {
 		return null;
 	}

}
    
    