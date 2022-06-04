package AIR;

public class Route {

	String routeName; //�뼱��
	int sAirNo; //��߰��׹�ȣ StartAirNumber
	int aAirNo; //�������׹�ȣ ArriveAirNumber
	String sAirName; //��߰����̸� StartAirName
	String aAirName; //���������̸� ArriveAirName
	String sTime; //StartTime
	String aTime; //�����ð� ArriveTime
	String date;// ���೯¥
	public Route() {}
	public Route(String routeName, int sAirNo, int aAirNo, String sAirName, String aAirName, String sTime, String aTime,
			String date) {
		super();
		this.routeName = routeName;
		this.sAirNo = sAirNo;
		this.aAirNo = aAirNo;
		this.sAirName = sAirName;
		this.aAirName = aAirName;
		this.sTime = sTime;
		this.aTime = aTime;
		this.date = date;
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
	public String getTime() {
		return date;
	}
	public void setTime(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Route [routeName=" + routeName + ", sAirNo=" + sAirNo + ", aAirNo=" + aAirNo + ", sAirName=" + sAirName
				+ ", aAirName=" + aAirName + ", sTime=" + sTime + ", aTime=" + aTime + ", date=" + date + "]";
	}
	
	
	
	
}