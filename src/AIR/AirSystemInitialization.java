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
           " insert into air.Customer values('0', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4','운영자','남자','1012345678',20,'M123123','수원시'); ",
           
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
                    
                  "insert Route values('인천-오사카',15772600,570073200,'인천공항','12:30','간사이공항','13:40','2022-07-02',519000);" ,
                  "insert Route values('오사카-인천',570073200,15772600,'간사이공항','14:00','인천공항','15:10','2022-07-03',449000);" ,
                  "insert Route values('인천-제주',15772600,647972526,'인천공항','12:00','제주공항','12:55','2022-07-02',171000);" ,
                  "insert Route values('제주-인천',647972526,15772600,'제주공항','15:00','인천공항','15:55','2022-07-03',124000);" ,
                  "insert Route values('인천-뉴욕',15772600,718244444,'인천공항','08:30','존케네디공항','23:30','2022-07-03',4225000);" ,
                  "insert Route values('뉴욕-인천',718244444,15772600,'존케네디공항','07:00','인천공항','22:00','2022-07-04',4127000);" ,
                  "insert Route values('인천-괌',15772600,671642321,'인천공항','10:20','앤토니오B.원팻 공항','19:40','2022-07-04',686100);" ,
                  "insert Route values('괌-인천',671642321,15772600,'앤토니오B.원팻 공항','11:00','인천공항','20:20','2022-07-05',575400);" ,
                  "insert Route values('인천-파리',15772600,3950,'인천공항','16:00','샤를드골공항','08:30','2022-07-03',3430000);" ,
                  "insert Route values('파리-인천',3950,15772600,'샤를드골공항','08:00','인천공항','00:30','2022-07-07',3152000);" };

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