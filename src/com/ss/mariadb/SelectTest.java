/*������db�� �����Ͽ� ���ڵ带 �ֿܼ� ����
 * 
 * ����) dbms�����簡 �����ϴ� ����̹��� �̸� �غ�����
 * */

package com.ss.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331"; //�̺κ��� �ܿ��� �Ѵ�. �˻��ص� �ȴ�.
	String user="root";
	String password="";
	
	Connection con; //���������� ���� �������̽�
	
	PreparedStatement pstmt; //���� ���� ��ü
	ResultSet rs;
	/*�������� select���� ��� �������� ���������̽��� ���̺�� 	������ ��������� ��Ƴ��� ��ü(=ǥ�� ����.)*/
	
	public SelectTest() {
		//1.����̹��� �ε�
		//2. ����
		//3. ���ϴ� ������ ����
		//4. db ���õ� �ڿ� �ݱ�
		
		try {
			//1.����̹� �ε�
			Class.forName(driver);
			System.out.println("�ε� ����");
			
			//2.����
			con=DriverManager.getConnection(url, user, password);
			if (con!=null) {
				System.out.println("���� ����");
				
				//3.���ϴ� ������ ����
				String sql="select * from member"; //�ܼ�â�� �ƴϹǷ� sql�������� ��������;���� �ʴ´�.
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery(); //���� ������ ��ȯ�Ǵ� ��������� ����. ��?? select���̴ϱ�
				
				//Ŀ�� ��ĭ ����
	/*			rs.next();
				int member_id=rs.getInt("member_id");
				String name=rs.getString("name"); //�÷��� �ش��ϴ� ���� ��ȯ
				int age=rs.getInt("age"); //���� ��ȯ
				System.out.println(member_id+", "+name+", "+age);
				
				rs.next();
				rs.next();*/
				
				while (rs.next()) {
					int member_id=rs.getInt("member_id");
					String name=rs.getString("name"); //�÷��� �ش��ϴ� ���� ��ȯ
					int age=rs.getInt("age"); //���� ��ȯ
					System.out.println(member_id+", "+name+", "+age);					
				}
				
			}else {
				System.out.println("���� ����");				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		//������ �ֱ������� �ٿ�Ǹ� ���� ���δٴ� ���̴�. ��κ� db�ȴݾ��ذ��̴�.
		
	}
	
	
	public static void main(String[] args) {
		new SelectTest();
	}
}
