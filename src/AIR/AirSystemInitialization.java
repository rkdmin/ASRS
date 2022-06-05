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
                     " TourDay varchar(20) NOT NULL, " +
                     " EmptySeat int(200) NOT NULL, " +
                     " FOREIGN KEY (UniqueNo) REFERENCES AirPlane(UniqueNo)" +
                 " );", 
                 " insert into air.Tour values('H1001',2022-05-02,150); ",
                 " insert into air.Tour values('H1001',2022-05-03,150); ",
                 " insert into air.Tour values('H1002',2022-05-02,130); ",
                 " insert into air.Tour values('H1002',2022-05-03,130); ",
                 " insert into air.Tour values('H1003',2022-05-03,150); ",
                 " insert into air.Tour values('H1003',2022-05-04,150); ",
                 " insert into air.Tour values('H1004',2022-05-04,180); ",
                 " insert into air.Tour values('H1004',2022-05-05,180); ",
                 " insert into air.Tour values('H1005',2022-05-03,220); ",
                 " insert into air.Tour values('H1005',2022-05-07,220); ",
                 
                 /****************************************
                   TABLE NAME   : air.Route
                   DATABASE     : air
                  ***************************************/
                 
                 " DROP TABLE IF EXISTS  air.Route ;",
                  " CREATE TABLE Route( " +
                      " uniqueNo int(11) NOT NULL," +
                        " routeName varchar(20) NOT NULL, " +
                        " sAirNo int(11) NOT NULL, " +
                        " aAirNo int(11) NOT NULL, " +
                        " sAirName varchar(20) NOT NULL, " +
                        " sTime varchar(20) NOT NULL, " +
                        " aAirName varchar(20) NOT NULL, " +
                        " aTime varchar(20) NOT NULL, " +
                        " date varchar(20) NOT NULL, " +
                        " price int(11) NOT NULL, " +
                        " PRIMARY KEY (uniqueNo,sAirNo,aAirNo) " +
                    " );",
                    
                  "insert Route values(1,'인천-오사카',15772600,570073200,'인천공항','12:30','간사이공항','13:40','2022-06-08',519000);" ,
                  "insert Route values(2,'오사카-인천',570073200,15772600,'간사이공항','14:00','인천공항','15:10','2022-06-09',449000);" ,
                  "insert Route values(3,'인천-제주',15772600,647972526,'인천공항','12:00','제주공항','12:55','2022-06-08',171000);" ,
                  "insert Route values(4,'제주-인천',647972526,15772600,'제주공항','15:00','인천공항','15:55','2022-06-09',124000);" ,
                  "insert Route values(5,'인천-뉴욕',15772600,718244444,'인천공항','08:30','존케네디공항','23:30','2022-06-08',4225000);" ,
                  "insert Route values(6,'뉴욕-인천',718244444,15772600,'존케네디공항','07:00','인천공항','22:00','2022-06-09',4127000);" ,
                  "insert Route values(7,'인천-괌',15772600,671642321,'인천공항','10:20','앤토니오B.원팻 공항','19:40','2022-06-08',686100);" ,
                  "insert Route values(8,'괌-인천',671642321,15772600,'앤토니오B.원팻 공항','11:00','인천공항','20:20','2022-06-09',575400);" ,
                  "insert Route values(9,'인천-파리',15772600,3950,'인천공항','16:00','샤를드골공항','08:30','2022-06-08',3430000);" ,
                  "insert Route values(10,'파리-인천',3950,15772600,'샤를드골공항','08:00','인천공항','00:30','2022-06-10',3152000);"  ,
                 "insert Route values(11,'인천-오사카',15772600,570073200,'인천공항','15:30','간사이공항','16:40','2022-06-09',519000);" ,
                 "insert Route values(12,'오사카-인천',570073200,15772600,'간사이공항','17:00','인천공항','18:10','2022-06-010',449000);" ,
                 "insert Route values(13,'인천-제주',15772600,647972526,'인천공항','15:00','제주공항','15:55','2022-06-09',171000);" ,
                 "insert Route values(14,'제주-인천',647972526,15772600,'제주공항','18:00','인천공항','18:55','2022-06-10',124000);" ,
                 "insert Route values(15,'인천-뉴욕',15772600,718244444,'인천공항','11:30','존케네디공항','02:30','2022-06-09',4225000);" ,
                 "insert Route values(16,'뉴욕-인천',718244444,15772600,'존케네디공항','10:00','인천공항','01:00','2022-06-10',4127000);" ,
                 "insert Route values(17,'인천-괌',15772600,671642321,'인천공항','13:20','앤토니오B.원팻 공항','22:40','2022-06-09',686100);" ,
                 "insert Route values(18,'괌-인천',671642321,15772600,'앤토니오B.원팻 공항','14:00','인천공항','23:20','2022-06-010',575400);" ,
                 "insert Route values(19,'인천-파리',15772600,3950,'인천공항','19:00','샤를드골공항','11:30','2022-06-09',3430000);" ,
                 "insert Route values(20,'파리-인천',3950,15772600,'샤를드골공항','11:00','인천공항','03:30','2022-06-11',3152000);" ,
                 "insert Route values(21,'인천-오사카',15772600,570073200,'인천공항','18:30','간사이공항','19:40','2022-06-09',519000);" ,
                 "insert Route values(22,'오사카-인천',570073200,15772600,'간사이공항','20:00','인천공항','21:10','2022-06-10',449000);" ,
                 "insert Route values(23,'인천-제주',15772600,647972526,'인천공항','18:00','제주공항','18:55','2022-06-11',171000);" ,
                 "insert Route values(24,'제주-인천',647972526,15772600,'제주공항','21:00','인천공항','21:55','2022-06-12',124000);" ,
                 "insert Route values(25,'인천-뉴욕',15772600,718244444,'인천공항','15:30','존케네디공항','05:30','2022-06-09',4225000);" ,
                 "insert Route values(26,'뉴욕-인천',718244444,15772600,'존케네디공항','13:00','인천공항','04:00','2022-06-10',4127000);" ,
                 "insert Route values(27,'인천-괌',15772600,671642321,'인천공항','16:20','앤토니오B.원팻 공항','01:40','2022-06-11',686100);" ,
                 "insert Route values(28,'괌-인천',671642321,15772600,'앤토니오B.원팻 공항','17:00','인천공항','02:20','2022-06-12',575400);" ,
                 "insert Route values(29,'인천-파리',15772600,3950,'인천공항','00:00','샤를드골공항','14:30','2022-06-09',3430000);" ,
                 "insert Route values(30,'파리-인천',3950,15772600,'샤를드골공항','14:00','인천공항','06:30','2022-06-11',3152000);" ,
                 "insert Route values(31,'인천-오사카',15772600,570073200,'인천공항','21:30','간사이공항','22:40','2022-06-08',519000);" ,
               "insert Route values(32,'오사카-인천',570073200,15772600,'간사이공항','23:00','인천공항','00:10','2022-06-09',449000);" ,
               "insert Route values(33,'인천-제주',15772600,647972526,'인천공항','21:00','제주공항','21:55','2022-06-08',171000);" ,
               "insert Route values(34,'제주-인천',647972526,15772600,'제주공항','00:00','인천공항','00:55','2022-06-09',124000);" ,
               "insert Route values(35,'인천-뉴욕',15772600,718244444,'인천공항','18:30','존케네디공항','08:30','2022-06-10',4225000);" ,
               "insert Route values(36,'뉴욕-인천',718244444,15772600,'존케네디공항','16:00','인천공항','07:00','2022-06-11',4127000);" ,
               "insert Route values(37,'인천-괌',15772600,671642321,'인천공항','19:20','앤토니오B.원팻 공항','04:40','2022-06-11',686100);" ,
               "insert Route values(38,'괌-인천',671642321,15772600,'앤토니오B.원팻 공항','20:00','인천공항','05:20','2022-06-13',575400);" ,
               "insert Route values(39,'인천-파리',15772600,3950,'인천공항','03:00','샤를드골공항','17:30','2022-06-09',3430000);" ,
               "insert Route values(40,'파리-인천',3950,15772600,'샤를드골공항','17:00','인천공항','09:30','2022-06-11',3152000);" ,
               "insert Route values(41,'인천-오사카',15772600,570073200,'인천공항','00:30','간사이공항','01:40','2022-06-09',519000);" ,
               "insert Route values(42,'오사카-인천',570073200,15772600,'간사이공항','02:00','인천공항','03:10','2022-06-10',449000);" ,
               "insert Route values(43,'인천-제주',15772600,647972526,'인천공항','00:00','제주공항','00:55','2022-06-09',171000);" ,
               "insert Route values(44,'제주-인천',647972526,15772600,'제주공항','03:00','인천공항','03:55','2022-06-10',124000);" ,
               "insert Route values(45,'인천-뉴욕',15772600,718244444,'인천공항','21:30','존케네디공항','11:30','2022-06-11',4225000);" ,
               "insert Route values(46,'뉴욕-인천',718244444,15772600,'존케네디공항','19:00','인천공항','10:00','2022-06-12',4127000);" ,
               "insert Route values(47,'인천-괌',15772600,671642321,'인천공항','22:20','앤토니오B.원팻 공항','07:40','2022-06-12',686100);" ,
               "insert Route values(48,'괌-인천',671642321,15772600,'앤토니오B.원팻 공항','23:00','인천공항','08:20','2022-06-14',575400);" ,
               "insert Route values(49,'인천-파리',15772600,3950,'인천공항','06:00','샤를드골공항','21:30','2022-06-13',3430000);" ,
                 "insert Route values(50,'파리-인천',3950,15772600,'샤를드골공항','20:00','인천공항','12:30','2022-06-15',3152000);"}; 

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