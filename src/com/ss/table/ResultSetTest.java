/*���ڵ� ����� �迭�� �������� ����
 * ���ڵ��� �� ������ �˼��� ����.
 * �칰�� ���̸� �о �� �ִ� �༮�� ���߸���
 *  
 * */

package com.ss.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest {
	String driver="oracle.jdbc.driver.OracleDriver"; //.class�� ���̸� �ȵȴ�.
	String url="jdbc:oracle:thin:@sist108:1521:XE";//jdbc(Java Data Base Connectivity):�ڹ��� �����ͺ��̽� �������
	//����ǻ�Ϳ� ����Ŭ�� ������ ��ġ �Ǿ����� �� �����Ƿ� �Ǹ������� �ش� ����Ŭ �̸��� �־��ش�. ���⼭�� XE 
	String user="batman";
	String password="1234";
	
	Connection con; //ms������ ���ӽõ�, ���⼭�� ������ ���� �� ����� ��� ��ü
	PreparedStatement pstmt;
	ResultSet rs;	
	//���� ���ѻ�� ��� �������̽��̴�.
	//�̷��� ���� dbms�� ���� ��ü�� ������ ��ɸ��� ������ �������̽��� �����ξ���.
	//dbms�����翡�� �� �������̽����� �̿��ؼ� driver�� ����� �ٿ�޾� ������ �̿��� �� �ִ�.
	//����Ϸ��� java �������̽��� �̿��Ѵ�.
	
	//���ڵ�� ��ü�� �̿��Ͽ� �� ���ڵ� �� �˾Ƹ��߱�
	public ResultSetTest() {
		try {
			//1.����̹� �ε�
			Class.forName(driver);
			System.out.println("�ε强��");
			
			//2.����
			con=DriverManager.getConnection(url, user, password);			
			if (con!=null) {
				System.out.println("���Ӽ���");
				
				//3. sql������
				String sql="select * from company";
				
				//rs�� Ŀ���� ������, �Ĺ��� ������ �����Ӱ� �����̰ų�, �Ѳ����� �ǳʶٰ� �Ϸ���
				//��ũ�� ������ ��� �ɼ��� �ο��ؾ� �Ѵ�.
				//select���� ��� ������ ������� ���� ���⸸ �Ҳ��� CONCUR_READ_ONLY��,
				//������ ���ҰŸ� CONCUR_UPDATABLE
				//������ ��κ� SELECT���� ���� ���ڵ�� �б������̴�.
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );				
				rs=pstmt.executeQuery();
				
				//���� ������ ���ڵ�� ������
				rs.last();
				int num=rs.getRow();//���� Ŀ���� ��ġ�� ���ڵ� ��ȣ!! �� ���ڵ��� ��ġ
				System.out.println(num);
				
			} else {
				System.out.println("���ӽ���");				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//4.���ѻ�ݱ�
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
		
	}
	
	public static void main(String[] args) {
		new ResultSetTest();
	}
	
	
}
