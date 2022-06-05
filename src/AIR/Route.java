	package AIR;

public class Route {

   int uniqueNo; //������ȣ
   String routeName; //�뼱��
   int sAirNo; //��߰��׹�ȣ StartAirNumber
   int aAirNo; //�������׹�ȣ ArriveAirNumber
   String sAirName; //��߰����̸� StartAirName
   String aAirName; //���������̸� ArriveAirName
   String sTime; //StartTime
   String aTime; //�����ð� ArriveTime
   String date;// ���೯¥
   int price;// ����
   public Route() {}
   public Route(int uniqueNo,String routeName, int sAirNo, int aAirNo, String sAirName, String aAirName, String sTime, String aTime,
         String date, int price) {
      super();
      this.uniqueNo = uniqueNo;
      this.routeName = routeName;
      this.sAirNo = sAirNo;
      this.aAirNo = aAirNo;
      this.sAirName = sAirName;
      this.aAirName = aAirName;
      this.sTime = sTime;
      this.aTime = aTime;
      this.date = date;
      this.price = price;
   }
   public int getUniqueNo() {
      return uniqueNo;
   }
   public void setUniqueNo(int uniqueNo) {
      this.uniqueNo = uniqueNo;
   }
   public String getRouteName() {
      return routeName;
   }
   public void setRouteName(String routeName) {
      this.routeName = routeName;
   }
   public int getsAirNo() {
      return sAirNo;
   }
   public void setsAirNo(int sAirNo) {
      this.sAirNo = sAirNo;
   }
   public int getaAirNo() {
      return aAirNo;
   }
   public void setaAirNo(int aAirNo) {
      this.aAirNo = aAirNo;
   }
   public String getsAirName() {
      return sAirName;
   }
   public void setsAirName(String sAirName) {
      this.sAirName = sAirName;
   }
   public String getaAirName() {
      return aAirName;
   }
   public void setaAirName(String aAirName) {
      this.aAirName = aAirName;
   }
   public String getsTime() {
      return sTime;
   }
   public void setsTime(String sTime) {
      this.sTime = sTime;
   }
   public String getaTime() {
      return aTime;
   }
   public void setaTime(String aTime) {
      this.aTime = aTime;
   }
   public String getDate() {
      return date;
   }
   public void setDate(String date) {
      this.date = date;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   @Override
   public String toString() {
      return "Route [uniqueNo=" + uniqueNo + ",routeName=" + routeName + ", sAirNo=" + sAirNo + ", aAirNo=" + aAirNo + ", sAirName=" + sAirName
            + ", aAirName=" + aAirName + ", sTime=" + sTime + ", aTime=" + aTime + ", date=" + date + ", price="
            + price + "]";
   }
   
}