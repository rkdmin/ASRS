package AIR;

import java.util.*;

public class Customer {

	int id; // ������ȣ
	String userId;// ���̵�
	String password; //��й�ȣ
	String name;
	String gender;
	String number;
	int age;
	String PassportNo; // ���ǹ�ȣ
	String address; //�ּ�	
	
	public	Customer() {
	}

	public Customer(int id, String userId, String password, String name, String gender, String number, int age,
			String passportNo, String address) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.age = age;
		PassportNo = passportNo;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassportNo() {
		return PassportNo;
	}

	public void setPassportNo(String passportNo) {
		PassportNo = passportNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
	// ������ ���
//		public void output() {
//			System.out.print("  * ���̵�: " + ID +",  ");
//			System.out.print("��й�ȣ: " + password +",  ");
//			System.out.print("�̸�: " + name+",  ");
//			System.out.print("����: " + gender+",   ");
//			System.out.print("��ȭ��ȣ: " + number +",");
//			System.out.print("����: " + age +",   ");
//			System.out.print("���ǹ�ȣ: " + PassportNo+",   ");
//			System.out.print("�ּ�: " + address +"\n");
//		}	
	
}