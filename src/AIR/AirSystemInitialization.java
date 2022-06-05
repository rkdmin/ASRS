package AIR;

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
               " id varchar(20) NOT NULL, " + 
               " password varchar(200) NOT NULL, " +
               " name varchar(20) NOT NULL, " +
               " gender varchar(10) NOT NULL, " +
               " number varchar(14) NOT NULL, " +
               " age int(11) NOT NULL, " +
               " passportNo varchar(20) NOT NULL, " +
               " address varchar(20) NOT NULL, " +
               " PRIMARY KEY (id) " +
           " );",
           " insert into air.Customer values('0', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4','���','����','1012345678',20,'M123123','������'); ",
           
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
                        " routeName varchar(20) NOT NULL, " +
                        " sAirNo int(11) NOT NULL, " +
                        " aAirNo int(11) NOT NULL, " +
                        " sAirName varchar(20) NOT NULL, " +
                        " sTime varchar(20) NOT NULL, " +
                        " aAirName varchar(20) NOT NULL, " +
                        " aTime varchar(20) NOT NULL, " +
                        " date varchar(20) NOT NULL, " +
                        " price int(11) NOT NULL, " +
                        " PRIMARY KEY (routeName,sAirNo,aAirNo) " +
                    " );",
                    
                  "insert Route values('��õ-����ī',15772600,570073200,'��õ����','12:30','�����̰���','13:40','2022-07-02',519000);" ,
                  "insert Route values('����ī-��õ',570073200,15772600,'�����̰���','14:00','��õ����','15:10','2022-07-03',449000);" ,
                  "insert Route values('��õ-����',15772600,647972526,'��õ����','12:00','���ְ���','12:55','2022-07-02',171000);" ,
                  "insert Route values('����-��õ',647972526,15772600,'���ְ���','15:00','��õ����','15:55','2022-07-03',124000);" ,
                  "insert Route values('��õ-����',15772600,718244444,'��õ����','08:30','���ɳ׵����','23:30','2022-07-03',4225000);" ,
                  "insert Route values('����-��õ',718244444,15772600,'���ɳ׵����','07:00','��õ����','22:00','2022-07-04',4127000);" ,
                  "insert Route values('��õ-��',15772600,671642321,'��õ����','10:20','����Ͽ�B.���� ����','19:40','2022-07-04',686100);" ,
                  "insert Route values('��-��õ',671642321,15772600,'����Ͽ�B.���� ����','11:00','��õ����','20:20','2022-07-05',575400);" ,
                  "insert Route values('��õ-�ĸ�',15772600,3950,'��õ����','16:00','����������','08:30','2022-07-03',3430000);" ,
                  "insert Route values('�ĸ�-��õ',3950,15772600,'����������','08:00','��õ����','00:30','2022-07-07',3152000);" };

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
   
   
}