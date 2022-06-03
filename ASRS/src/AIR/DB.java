package AIR;

import java.sql.*;
import java.io.IOException;
import java.util.Vector;

/**
 * DB 클래스
 *  o MySQL DBMS와 연결하고 DB 테이블의 저장 및 검색을 위한 메소드 갖는 클래스
 *    
 *  o DBMS 연결, 테이블에 대한 처리 요청은 모두 public static 메소드로 구현되므로 
 *    다른 클래스에서 DB 클래스의 메소드를 호출할 때 수신자는 DB
 *    
 */

public class DB {
	static  Connection con         = null;

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
		database = "HSbankJSP";
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

		loadConnectHSbankJSP();
	}

	// JDBC 드라이버 로드 및 연결, 연경 성공이면 true, 실패면 false 반환하는 메소드
	public static boolean loadConnectHSbankJSP()  {
		return loadConnect("HSbankJSP");
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
			// 연결하기 - HSbankJSP 데이터베이스와 연결
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

	// Banker 객체를 은행원 테이블 banker의 투플로 삽입하는 메소드
	public static int insertBanker(Banker banker) {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into banker values (?, ?, ?, ?, ?, ?, ?);" ;
			outputForDebug("In insertBanker() : " + sql);
			
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, banker.getBankerType());
			prStmt.setInt(2, banker.getBno());
			prStmt.setString(3, banker.getBname());
			prStmt.setString(4, banker.getID());
			prStmt.setString(5, banker.getPassword());
			prStmt.setDate(6, banker.getDateHired());
			prStmt.setString(7, banker.getBranch());

			updateCnt = prStmt.executeUpdate();  		
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertBanker(): " + ex.getMessage() );
		}

		return updateCnt;
	}

	// 주어진 아이디와 패스워드의 은행원 탐색하여 성공하면 해당 은행원 객체를 반환하는 메소드
	// 탐색 실패시 null 반환
	public static Banker getBanker(String ID, String password) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from banker where ID=? and password=?;" ;
			outputForDebug("In getBanker() SQL : " + sql);
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, ID);
			prStmt.setString(2, password);

			ResultSet rs = prStmt.executeQuery();  
			if (rs.next())  { 
				Banker banker = getBankerFromRS(rs);
				return banker;
			}			
		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getBanker(): " + ex.getMessage() );
		}

		return null;
	}
		  
	public static Vector<Banker> getAllBankers()  {
		Vector<Banker> bankers = new Vector<Banker>();

		Banker banker;

		ResultSet rs = getAllBankersRS();

		try {
			while (rs.next())  {
				banker = getBankerFromRS(rs);
				bankers.addElement(banker);
			}
		} catch( SQLException ex ) 	    {
			System.err.println("** SQL exec error in getAllAccounts() : " + ex.getMessage() );
		}	
		
		return bankers;	
	}

	// 모든 은행원 탐색하여 ResultSet 객체로 반환하는 메소드
	public static ResultSet getAllBankersRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from banker" ;
			outputForDebug("In getAllBankersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;
			
		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllBankerRS(): " + ex.getMessage() );
		}

		return null;
	}

	// 주어진 아이디의 은행원 탐색하여 성공하면 해당 은행원 객체를 반환하는 메소드
	// 탐색 실패시  null 반환
	public static Banker getBanker(String ID) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from banker where ID = ?;" ;
			outputForDebug("In getBanker() SQL : " + sql);
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, ID);

			ResultSet rs = prStmt.executeQuery();  
			if (rs.next())  {
				Banker banker = getBankerFromRS(rs);
				return banker;
			}			
		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getBanker(): " + ex.getMessage() );
		}

		return null;
	}

	// 은행원 테이블의 투플 개수를 반환하는 메소드
	public static int getNumBankers() {
		int num = 0;
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "select count(*) as num from banker";
			outputForDebug("In getNumBankers() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
			if (rs.next())
				num = rs.getInt("num");

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNumBankers() : " + ex.getMessage() );
		}

		return num;
	}

	/*
	 * 	  ResultSet 객체에 banker 테이블의 투플이 저장되어 있을 때, 이를 Banker 객체로 변환하는 메소드
	 *    은행원은 root 은행원과 일반 은행원이 있으며, root 은행원의 ID는 "root"임
	 *    그러므로 투플의 ID 애트리뷰트 값이 "root"이면  RootBanker 객체로, 그 외는 NormalBanker 객체로 반환
	 */	      
	public static Banker getBankerFromRS(ResultSet rs) {  
		Banker banker = null;

		try {
			if (rs.getRow() ==  0)
				return null;
			
			String ID = rs.getString("ID");  // ID 애트리뷰트 값을 저장

			if (ID.equals("root"))           // root 은행원이면
				banker = new RootBanker();   // RootBanker 객체를 생성하여 banker 변수가 참조하게 함
			else
				banker = new NormalBanker(); // 아니면 NormalBanker객체를 생성하여  banker 변수가 참조하게 함

			banker.setBankerType( rs.getString("bankerType") );   // ResultSet의 애트리뷰트 값을 get하여 필드의 값으로 저장 
			banker.setBno( rs.getInt("bno") );
			banker.setBname( rs.getString("bname") );
			banker.setID( rs.getString("ID") );
			banker.setPassword( rs.getString("password") );
			banker.setDateHired( rs.getDate("dateHired") );
			banker.setBranch( rs.getString("branch") );
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getBankerFromRS(): " + ex.getMessage() );
		}

		return banker;
	}
	
	/*
	 * 	  ResultSet 객체에 banker 테이블의 투플들 저장되어 있을 때, 이를 Banker 객체들로 변환하고 Vector<Banker>로 반환하는 메소드
	 *    
	 *    
	 */	      
	public static Vector<Banker> getBankersFromRS(ResultSet rs) {  
		Vector<Banker> bankers = new Vector<Banker>();
		
		Banker banker = null;

		try {
			while(rs.next()) {
				banker = getBankerFromRS(rs);
			
				if (banker != null)
					bankers.add(banker);
			}	
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getBankerFromRS(): " + ex.getMessage() );
		}

		return bankers;
	}
	
	public static boolean isValidBankerInfo(Banker banker) {
		
		return false;
	}

	// 정규직, 비정규직 또는 보통예금, 저축예금에 대한 순서번호를 할당하는 메소드
	public static int getNextSerial(String serialType) {
		int next = 0;
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			// 주어진 계좌유형 accType에 대한 nextSerial 검색 및 1 증가
			sql = "select serial from nextSerial where serialType = '" + serialType + "';";
			outputForDebug("In getNextSerial() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
			if (rs.next())
				next = rs.getInt("serial");

			stmt = con.createStatement();

			sql = "update nextSerial set serial = serial + 1 where serialType = '" + serialType + "';";
			outputForDebug("In getNextSerial() SQL : " + sql);

			stmt.executeUpdate(sql);

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNextSerial(): " + ex.getMessage() );
		}
		
		if (serialType.equals("정규직") )  // 10000 + next
			next = next + 10000;
		else if (serialType.equals("비정규직") ) 
			next = next + 20000;		
		else if (serialType.equals("보통예금"))  // 10000 + next
			next = next + 100000;
		else if (serialType.equals("저축예금")) 
			next = next + 200000;	
		return next;
	}

	// account 테미블의 투플 개수를 반환하는 메소드
	public static int getNumAccounts() {
		int num = 0;
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "select count(accno) as num from account";
			outputForDebug("In getNumAccounts() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
			if (rs.next())
				num = rs.getInt("num");


		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNumAccounts() : " + ex.getMessage() );
		}

		return num;
	}

	public static ResultSet getAccountRS(int accno)  {	
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "select * from account where accno = "+ accno;
			outputForDebug("In getAccountRS() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  
			
			return rs;

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getAccount() : " + ex.getMessage() );
		}

		return null;		
	}
	
	public static Account getAccount(int accno)  {	
		String sql;

		try {
			ResultSet rs = getAccountRS(accno);  
			
			if (rs.next()) 
				return getAccountFromRS(rs);

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getAccount() : " + ex.getMessage() );
		}

		return null;		
	}


	//  ResultSet객체에 계좌 투플이 저장되어 있을 때 이를 Account 객체로 변환하는 메소드
	public static Account getAccountFromRS(ResultSet rs) {  
		Account acc = new Account();

		try {
			acc.accType = rs.getString("accType");
			acc.accno = rs.getInt("accno");
			acc.owner = rs.getString("owner");
			acc.dateOpend = rs.getDate("dateOpend");
			acc.balance = rs.getInt("balance");
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getAccountFromRS() : " + ex.getMessage() );
		}

		return acc;
	}

	// Account 객체를 account 테이블의 투플로 삽입하는 메소드
	public static void insertAccount(Account acc)  {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into account (accType, accno, owner, dateOpend, balance) values (?, ?, ?, ?, ?);" ;
			outputForDebug("In insertAccount() SQL : " + sql);
			
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, acc.accType);
			prStmt.setInt(2, acc.accno);
			prStmt.setString(3, acc.owner);
			prStmt.setDate(4, acc.dateOpend);
			prStmt.setInt(5, acc.balance);

			updateCnt = prStmt.executeUpdate();  		
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertAccount(): " + ex.getMessage() );
		}

		if (updateCnt > 0)
			outputForDebug("In insertAccount(): " + updateCnt + " new acount was inserted into account table");
		else
			System.out.println("  >> Error in Inserting New Account\n\n"); 

		outputForDebug("In insertAccount(): total "+getNumAccounts()+" accounts in account table");
	}

	public static Vector getAllAccounts()  {
		Vector accounts = new Vector();

		Account acc;

		ResultSet rs = getAllAccountsRS();

		try {
			while (rs.next())  {
				acc = getAccountFromRS(rs);
				accounts.addElement(acc);
			}
		} catch( SQLException ex ) 	    {
			System.err.println("** SQL exec error in getAllAccounts() : " + ex.getMessage() );
		}	
		
		return accounts;	
	}

    public static ResultSet getAllAccountsRS()  {
		Vector accounts = new Vector();
		
		Account acc;
		String sql;
		
		ResultSet rs = null;
		
		try {
		   // Statement 생성 
			Statement stmt = con.createStatement();
		    
		   sql = "select * from account";
		   System.out.println("   >> SQL : " + sql + "\n");
		
		   rs = stmt.executeQuery(sql);  		   
		} catch( SQLException ex ) 	    {
		    System.err.println("** SQL exec error in getAllAccountsRS() : " + ex.getMessage() );
		}
	
		return rs;	
	}
    
	public static void updateBalance(Account acc) {
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "update account set balance = "+acc.balance+" where accno = "+acc.accno;
			outputForDebug("In updateBalance() SQL : " + sql);

			stmt.executeUpdate(sql);

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in updateBalance() : " + ex.getMessage() );
		}
	}

	public static ResultSet getAccountInfoRS(String name)  {
		String sql;
		ResultSet  rs = null;
		
		try {
		   // Statement 생성 
			Statement stmt = con.createStatement();
		    		 
		   if (name.equals("*"))
		       sql = "select count(*) as 'Account number', sum(balance) as 'Total Balance', avg(balance) as 'Average Balance' from account";
			else
			   sql = "select count(*) as 'Account number', sum(balance) as 'Total Balance', avg(balance) as 'Average Balance' from account where owner ='"
		            + name + "'";
		   
		   System.out.println("   >> SQL : " + sql + "\n");
		
		   rs = stmt.executeQuery(sql); 
		} catch( SQLException ex ) 	    {
		    System.err.println("** SQL exec error in getAccountInfoRS() : " + ex.getMessage() );
		}
	
		return rs;	
	}
	
	public static ResultSet getAllAccountInfoRS() {
		return getAccountInfoRS("*");
	}
	
	public static AccountInfo getAccountInfo(String name)  {

		AccountInfo accInfo = null;

		Account acc;
		
		try {
			ResultSet rs = getAccountInfoRS(name);

			accInfo = new AccountInfo();
			accInfo.name = name;  

			if (rs.next())  {
				accInfo.numAccounts = rs.getInt("numAccounts");
				accInfo.totalBalance = rs.getInt("totalBalance");
				accInfo.avgBalance = rs.getInt("avgBalance");
			}

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getAccountInfo() : " + ex.getMessage() );
		}

		return accInfo;	
	}
	
	// transaction 테미블의 투플 개수를 반환
	public static int getNumTransactions() {
		int num = 0;
		String sql;

		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "select count(accno) as num from transaction";
			outputForDebug("In getNumTransactions() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // 하나의 투플만 검색되므로 while 문 사용하지 않음
			if (rs.next())
				num = rs.getInt("num");


		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNumTransactions() : " + ex.getMessage() );
		}

		return num;
	}

	public static Vector getTransactions(int accno)  {
		Vector trs = new Vector();

		Transaction tr;
		String sql;
		
		ResultSet rs = null;
		
		try {
			// Statement 생성 
			Statement stmt = con.createStatement();

			sql = "select * from transaction where accno = "+accno;
			outputForDebug("In getTransactions() SQL : " + sql + "\n");

			rs = stmt.executeQuery(sql);  
			while (rs.next())  {
				tr = getTransactionFromRS(rs);
				trs.addElement(tr);
			}

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getTransactions() : " + ex.getMessage() );
		}

		return trs;	
	}

	public static void insertTransaction(Transaction tr)  {
		int updateCnt = 0;

		try {
			// SQL 질의문 수행
			String sql = "insert into transaction values (?, ?, ?, ?, ?, ?, ?, ?, ?);" ;
			outputForDebug("In insertTransaction() SQL : " + sql + "\n");
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, tr.transType);
			prStmt.setInt(2, tr.accno);
			prStmt.setString(3, tr.customer);
			prStmt.setDate(4, tr.dateTrans);
			prStmt.setTime(5, tr.timeTrans);
			prStmt.setInt(6, tr.accnoTrans);		   
			prStmt.setInt(7, tr.bno);
			prStmt.setInt(8, tr.amount);
			prStmt.setInt(9, tr.balance);

			updateCnt = prStmt.executeUpdate();
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in insertTransaction(): " + ex.getMessage() );
		}

		if (updateCnt > 0)
			outputForDebug("In insertTransaction(): " + updateCnt+" new transaction was inserted into account table");
		else
			System.out.println("  >> Error in Inserting New Transaction\n\n"); 

		outputForDebug("n insertTransaction(): total " + getNumTransactions()+" transactions in transaction table");
	}

    public static ResultSet getAllTransactionsRS()  {
		Vector transactions = new Vector();
		
		Transaction tr;
		String sql;
		
		ResultSet rs = null;
		
		try {
		   // Statement 생성 
			Statement stmt = con.createStatement();
		    
		   sql = "select * from transaction";
		   System.out.println("   >> SQL : " + sql + "\n");
		
		   rs = stmt.executeQuery(sql);  
		 
		} catch( SQLException ex ) 	    {
		    System.err.println("** SQL exec error in getAllTransactionsRS() : " + ex.getMessage() );
		}
	
		return rs;	
	}

	public static Transaction getTransactionFromRS(ResultSet rs) {
		Transaction tr = new Transaction();

		try {
			tr.transType = rs.getString("transType");
			tr.accno = rs.getInt("accno");
			tr.customer = rs.getString("customer");
			tr.dateTrans = rs.getDate("dateTrans");
			tr.timeTrans = rs.getTime("timeTrans");
			tr.accnoTrans = rs.getInt("accnoTrans");
			tr.bno = rs.getInt("bno");
			tr.amount = rs.getInt("amount");
			tr.balance = rs.getInt("balance");
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getAccountFromRS() : " + ex.getMessage() );
		}

		return tr;
	}

	public static  void setAutoLoan(AutoLoan autoLoan) {
		
	}
	
	public static int getNoTuplesRS(ResultSet rs) {
		int cnt = 0;
		try {
			rs.last();

			cnt = rs.getRow();

			rs.beforeFirst();

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in ggetNoTuplesRS : " + ex.getMessage() );
		}
		return cnt;
	}
		
	// 타이틀과 ResultSet 객체가 주어지면 ResultSetMetaData 객체를 이용하여 주어진 결과를 출력
	// 이 메소드는 select 문의 모든 수행결과를 출력할 수 있는 메소드임
	static void outputResultSet(String title, ResultSet rs) {
		try {		
			System.out.println("\n" + title + "\n");

			ResultSetMetaData  rsm = rs.getMetaData();   // 메타데이터 객체를 가져옴

			int cnt = rsm.getColumnCount() ;    // 메타데이터 객체에서 ResultSet 객체의 애트리뷰트 개수를 가져옴

			while(rs.next()) {  //  ResultSet 객체의 모든 투플에 대하여
				for (int i=1; i<=cnt; i++) {
					String attrTitle = rsm.getColumnLabel(i);        // 애트리뷰트의 타이틀을 가져옴
					Object obj = rs.getObject(i);                    // getObject(): 타입을 고려하지 않고 애트리뷰트 값을 가져옴
					System.out.print("\t" + attrTitle + ": " + obj); // Object 객체 obj의 overriding 메소드인 
					//     toString()을 통하여 문자열로 출력됨
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			System.err.println("\n  ?? SQL문 결과출력 에러:" + e.getMessage() );
		}

		System.out.println("\n");
	}
}

