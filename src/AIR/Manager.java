package AIR;

public class Manager {

	String UniqueNo; //������ȣ
	String RouteName; //�뼱��
	int SAirNo; //��߰��׹�ȣ StartAirNumber
	int AAirNo; //�������׹�ȣ ArriveAirNumber
	String MgrID; //������ ���̵� ManagerID
	String password;
	
	public Manager(String MgrID, String password) {
		
		this.MgrID = MgrID;
		this.password = password;
	
		
	}
	// ���������� ���
			public void output() {
				
				System.out.print("�����ھ��̵�: " + MgrID +",");
				System.out.print("��й�ȣ: " + password +"\n ");
				
			}	
			/*
			 * getter �޼ҵ�� setter �޼ҵ� ����
			 */
			public String getMgrID() {
				return MgrID;
			}
			public void setMgrID(String MgrID) {
				this.MgrID = MgrID;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
}
