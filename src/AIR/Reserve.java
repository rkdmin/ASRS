package AIR;

public class Reserve {
   private int reserveId;// �⺻Ű
   private int uniqueNo; // �뼱��ȣ
   private String id;// ����� ���̵�
   private int num;// �ο���
   private int totalPrice;// �ѱ��Ű���
   public Reserve() {}
   public Reserve(int reserveId, int uniqueNo, String id, int num, int totalPrice) {
      super();
      this.reserveId = reserveId;
      this.uniqueNo = uniqueNo;
      this.id = id;
      this.num = num;
      this.totalPrice = totalPrice;
   }
   public int getReserveId() {
      return reserveId;
   }
   public void setReserveId(int reserveId) {
      this.reserveId = reserveId;
   }
   public int getUniqueNo() {
      return uniqueNo;
   }
   public void setUniqueNo(int uniqueNo) {
      this.uniqueNo = uniqueNo;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public int getTotalPrice() {
      return totalPrice;
   }
   public void setTotalPrice(int totalPrice) {
      this.totalPrice = totalPrice;
   }
   
// ������ ���
      public void output() {
         System.out.print("  * �����ȣ: " + reserveId +",  ");
         System.out.print("������ȣ: " + uniqueNo +",  ");
         System.out.print("���̵�: " + id+",  ");
         System.out.print("�ο���: " + num+",   ");
         System.out.print("�ѱ��Ű���: " + totalPrice +",");
         
      }   
 
}