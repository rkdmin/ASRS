package AIR;

import java.sql.*;
import java.io.IOException;
import java.util.Vector;

/**
 * DB Ŭ����
 *  o MySQL DBMS�� �����ϰ� DB ���̺��� ���� �� �˻��� ���� �޼ҵ� ���� Ŭ����
 *    
 *  o DBMS ����, ���̺� ���� ó�� ��û�� ��� public static �޼ҵ�� �����ǹǷ� 
 *    �ٸ� Ŭ�������� DB Ŭ������ �޼ҵ带 ȣ���� �� �����ڴ� DB
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

		loadConnectHSbankJSP();
	}

	// JDBC ����̹� �ε� �� ����, ���� �����̸� true, ���и� false ��ȯ�ϴ� �޼ҵ�
	public static boolean loadConnectHSbankJSP()  {
		return loadConnect("HSbankJSP");
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
			// �����ϱ� - HSbankJSP �����ͺ��̽��� ����
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

	// Banker ��ü�� ����� ���̺� banker�� ���÷� �����ϴ� �޼ҵ�
	public static int insertBanker(Banker banker) {
		int updateCnt = 0;

		try {                      
			// SQL ���ǹ��� �����Ѵ�.
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

	// �־��� ���̵�� �н������� ����� Ž���Ͽ� �����ϸ� �ش� ����� ��ü�� ��ȯ�ϴ� �޼ҵ�
	// Ž�� ���н� null ��ȯ
	public static Banker getBanker(String ID, String password) {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
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

	// ��� ����� Ž���Ͽ� ResultSet ��ü�� ��ȯ�ϴ� �޼ҵ�
	public static ResultSet getAllBankersRS() {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
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

	// �־��� ���̵��� ����� Ž���Ͽ� �����ϸ� �ش� ����� ��ü�� ��ȯ�ϴ� �޼ҵ�
	// Ž�� ���н�  null ��ȯ
	public static Banker getBanker(String ID) {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
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

	// ����� ���̺��� ���� ������ ��ȯ�ϴ� �޼ҵ�
	public static int getNumBankers() {
		int num = 0;
		String sql;

		try {
			// Statement ���� 
			Statement stmt = con.createStatement();

			sql = "select count(*) as num from banker";
			outputForDebug("In getNumBankers() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // �ϳ��� ���ø� �˻��ǹǷ� while �� ������� ����
			if (rs.next())
				num = rs.getInt("num");

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNumBankers() : " + ex.getMessage() );
		}

		return num;
	}

	/*
	 * 	  ResultSet ��ü�� banker ���̺��� ������ ����Ǿ� ���� ��, �̸� Banker ��ü�� ��ȯ�ϴ� �޼ҵ�
	 *    ������� root ������� �Ϲ� ������� ������, root ������� ID�� "root"��
	 *    �׷��Ƿ� ������ ID ��Ʈ����Ʈ ���� "root"�̸�  RootBanker ��ü��, �� �ܴ� NormalBanker ��ü�� ��ȯ
	 */	      
	public static Banker getBankerFromRS(ResultSet rs) {  
		Banker banker = null;

		try {
			if (rs.getRow() ==  0)
				return null;
			
			String ID = rs.getString("ID");  // ID ��Ʈ����Ʈ ���� ����

			if (ID.equals("root"))           // root ������̸�
				banker = new RootBanker();   // RootBanker ��ü�� �����Ͽ� banker ������ �����ϰ� ��
			else
				banker = new NormalBanker(); // �ƴϸ� NormalBanker��ü�� �����Ͽ�  banker ������ �����ϰ� ��

			banker.setBankerType( rs.getString("bankerType") );   // ResultSet�� ��Ʈ����Ʈ ���� get�Ͽ� �ʵ��� ������ ���� 
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
	 * 	  ResultSet ��ü�� banker ���̺��� ���õ� ����Ǿ� ���� ��, �̸� Banker ��ü��� ��ȯ�ϰ� Vector<Banker>�� ��ȯ�ϴ� �޼ҵ�
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

	// ������, �������� �Ǵ� ���뿹��, ���࿹�ݿ� ���� ������ȣ�� �Ҵ��ϴ� �޼ҵ�
	public static int getNextSerial(String serialType) {
		int next = 0;
		String sql;

		try {
			// Statement ���� 
			Statement stmt = con.createStatement();

			// �־��� �������� accType�� ���� nextSerial �˻� �� 1 ����
			sql = "select serial from nextSerial where serialType = '" + serialType + "';";
			outputForDebug("In getNextSerial() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // �ϳ��� ���ø� �˻��ǹǷ� while �� ������� ����
			if (rs.next())
				next = rs.getInt("serial");

			stmt = con.createStatement();

			sql = "update nextSerial set serial = serial + 1 where serialType = '" + serialType + "';";
			outputForDebug("In getNextSerial() SQL : " + sql);

			stmt.executeUpdate(sql);

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNextSerial(): " + ex.getMessage() );
		}
		
		if (serialType.equals("������") )  // 10000 + next
			next = next + 10000;
		else if (serialType.equals("��������") ) 
			next = next + 20000;		
		else if (serialType.equals("���뿹��"))  // 10000 + next
			next = next + 100000;
		else if (serialType.equals("���࿹��")) 
			next = next + 200000;	
		return next;
	}

	// account �׹̺��� ���� ������ ��ȯ�ϴ� �޼ҵ�
	public static int getNumAccounts() {
		int num = 0;
		String sql;

		try {
			// Statement ���� 
			Statement stmt = con.createStatement();

			sql = "select count(accno) as num from account";
			outputForDebug("In getNumAccounts() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // �ϳ��� ���ø� �˻��ǹǷ� while �� ������� ����
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
			// Statement ���� 
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


	//  ResultSet��ü�� ���� ������ ����Ǿ� ���� �� �̸� Account ��ü�� ��ȯ�ϴ� �޼ҵ�
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

	// Account ��ü�� account ���̺��� ���÷� �����ϴ� �޼ҵ�
	public static void insertAccount(Account acc)  {
		int updateCnt = 0;

		try {                      
			// SQL ���ǹ��� �����Ѵ�.
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
		   // Statement ���� 
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
			// Statement ���� 
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
		   // Statement ���� 
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
	
	// transaction �׹̺��� ���� ������ ��ȯ
	public static int getNumTransactions() {
		int num = 0;
		String sql;

		try {
			// Statement ���� 
			Statement stmt = con.createStatement();

			sql = "select count(accno) as num from transaction";
			outputForDebug("In getNumTransactions() SQL : " + sql);

			ResultSet rs = stmt.executeQuery(sql);  // �ϳ��� ���ø� �˻��ǹǷ� while �� ������� ����
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
			// Statement ���� 
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
			// SQL ���ǹ� ����
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
		   // Statement ���� 
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
		
	// Ÿ��Ʋ�� ResultSet ��ü�� �־����� ResultSetMetaData ��ü�� �̿��Ͽ� �־��� ����� ���
	// �� �޼ҵ�� select ���� ��� �������� ����� �� �ִ� �޼ҵ���
	static void outputResultSet(String title, ResultSet rs) {
		try {		
			System.out.println("\n" + title + "\n");

			ResultSetMetaData  rsm = rs.getMetaData();   // ��Ÿ������ ��ü�� ������

			int cnt = rsm.getColumnCount() ;    // ��Ÿ������ ��ü���� ResultSet ��ü�� ��Ʈ����Ʈ ������ ������

			while(rs.next()) {  //  ResultSet ��ü�� ��� ���ÿ� ���Ͽ�
				for (int i=1; i<=cnt; i++) {
					String attrTitle = rsm.getColumnLabel(i);        // ��Ʈ����Ʈ�� Ÿ��Ʋ�� ������
					Object obj = rs.getObject(i);                    // getObject(): Ÿ���� ������� �ʰ� ��Ʈ����Ʈ ���� ������
					System.out.print("\t" + attrTitle + ": " + obj); // Object ��ü obj�� overriding �޼ҵ��� 
					//     toString()�� ���Ͽ� ���ڿ��� ��µ�
				}
				System.out.println();
			}
		}
		catch (SQLException e) {
			System.err.println("\n  ?? SQL�� ������ ����:" + e.getMessage() );
		}

		System.out.println("\n");
	}
}

