package AIR;

import java.sql.*;

import AIR.AIRDB;


public class AirSystemInitialization {
	
	static String[]  sqls = {
			" drop database if exists air;", 
			" create database air;",
			" use air;",
			
			/***********************************************
			 TABLE NAME   : air.Customer
			 DATABASE     : air
			 **********************************************/

			" DROP TABLE IF EXISTS  air.Customer;",
			" CREATE TABLE  Customer( " +
					" ID varchar(20) NOT NULL, " + 
					" password varchar(20) NOT NULL, " +
					"  name varchar(20) NOT NULL, " +
					"  gender varchar(10) NOT NULL, " +
					" number int(11) NOT NULL, " +
					" age int(11) NOT NULL, " +
					"  PassportNo varchar(20) NOT NULL, " +
					"  address varchar(20) NOT NULL, " +
					" PRIMARY KEY (ID) " +
			  " );",
			  " insert into air.Customer values('a001', 'a001','���ѽ�','����',1012345678,20,'M123123','������'); ",
			  
		  /****************************************
				 TABLE NAME   : air.AirPlane
			 DATABASE     : air
				***************************************/
			  " DROP TABLE IF EXISTS  air.AirPlane;",
				" CREATE TABLE AirPlane( " +
						" UniqueNo varchar(20) NOT NULL, " + 
						" price int(11) NOT NULL, " +
						"  seat int(200) NOT NULL, " +
						" PRIMARY KEY (UniqueNo) " +
				  " );",
				  "insert into air.AirPlane values('H1001',70000,150); ",
				  "insert into air.AirPlane values('H1002',65000,130); ",
				  "insert into air.AirPlane values('H1003',70000,150); ",
				  "insert into air.AirPlane values('H1004',80000,180); ",
				  "insert into air.AirPlane values('H1005',100000,220); ",
				  /****************************************
					 TABLE NAME   : air.Tour
					 DATABASE     : air
					***************************************/
				  " DROP TABLE IF EXISTS  air.Tour ;",
					" CREATE TABLE Tour( " +
							" UniqueNo varchar(20) NOT NULL, " + 
							" TourDay int(11) NOT NULL, " +
							" EmptySeat int(200) NOT NULL, " +
							" FOREIGN KEY (UniqueNo) REFERENCES AirPlane(UniqueNo)" +
					  " );", 
					  " insert into air.Tour values('H1001',20220502,150); ",
					  " insert into air.Tour values('H1001',20220503,150); ",
					  " insert into air.Tour values('H1002',20220502,130); ",
					  " insert into air.Tour values('H1002',20220503,130); ",
					  " insert into air.Tour values('H1003',20220503,150); ",
					  " insert into air.Tour values('H1003',20220504,150); ",
					  " insert into air.Tour values('H1004',20220504,180); ",
					  " insert into air.Tour values('H1004',20220505,180); ",
					  " insert into air.Tour values('H1005',20220503,220); ",
					  " insert into air.Tour values('H1005',20220507,220); ",
					  
					  /****************************************
						 TABLE NAME   : air.Route
						 DATABASE     : air
						***************************************/
					  
					  " DROP TABLE IF EXISTS  air.Route ;",
						" CREATE TABLE Route( " +
								" UniqueNo varchar(20) NOT NULL, " + 
								" RouteName varchar(20) NOT NULL, " +
								" SAirNo int(11) NOT NULL, " +
								" AAirNo int(11) NOT NULL, " +
								" SAirName varchar(20) NOT NULL, " +
								" STime varchar(20) NOT NULL, " +
								" AAirName varchar(20) NOT NULL, " +
								" ATime varchar(20) NOT NULL, " +
								" TourDay int NOT NULL, " +
								" PRIMARY KEY (RouteName,SAirNo,AAirNo), " +
								" FOREIGN KEY (UniqueNo) REFERENCES AirPlane(UniqueNo) " +
						  " );",
						  
						"insert Route values('H1001','��õ-����ī',15772600,570073200,'��õ����','12:30','�����̰���','13:40',20220502);" ,
						"insert Route values('H1001','����ī-��õ',570073200,15772600,'�����̰���','14:00','��õ����','15:10',20220503);" ,
						"insert Route values('H1002','��õ-����',15772600,647972526,'��õ����','12:00','���ְ���','12:55',20220502);" ,
						"insert Route values('H1002','����-��õ',647972526,15772600,'���ְ���','15:00','��õ����','15:55',20220503);" ,
						"insert Route values('H1003','��õ-����',15772600,718244444,'��õ����','08:30','���ɳ׵����','23:30',20220503);" ,
						"insert Route values('H1003','����-��õ',718244444,15772600,'���ɳ׵����','07:00','��õ����','22:00',20220504);" ,
						"insert Route values('H1004','��õ-��',15772600,671642321,'��õ����','10:20','����Ͽ�B.���� ����','19:40',20220504);" ,
						"insert Route values('H1004','��-��õ',671642321,15772600,'����Ͽ�B.���� ����','11:00','��õ����','20:20',20220505);" ,
						"insert Route values('H1005','��õ-�ĸ�',15772600,3950,'��õ����','16:00','����������','08:30',20220503);" ,
						"insert Route values('H1005','�ĸ�-��õ',3950,15772600,'����������','08:00','��õ����','00:30',20220507);" ,};

	public static void main(String[] args) {
		createInitialAirDatabase();
        
	}
	static void createInitialAirDatabase() {
		AIRDB.loadConnect("mysql");
		
		for (String sql : sqls) {
			AIRDB.executeAnyQuery(sql);
			
	         System.out.println("  << for debug >> in createInitialAirDatabase(), sql = " + sql); 
		}
		
	}
	
	static void insertRootBanker() {
		
		Manager manager = new Manager("A001","A001");
		
		AIRDB.insertManager(manager);
		
		ResultSet rs = AIRDB.getAllBankersRS();
		
		AIRDB.outputResultSet("\t\t** �ʱ�ȭ�� ��ϵ� ��Ʈ ������ **", rs);
	}
}
