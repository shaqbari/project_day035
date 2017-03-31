
/*�츮�� ������� ������ ���̽� ��ǰ�� ���DBMS�̴�
 * DB(�����)MS(�����ý���) - ��Ʈ��ũ ���
 * �̶� ���������� �����ϴ�.
 * 
 * �����ͺ��̽� ���� 
 * Ŭ���̾�Ʈ: sqlplus, Toad ��
 * 
 * ����Ŭ: ����
 * 
 * ���� ������� ��Ʈ��ũ ���������� tcp/ip����̹Ƿ�,
 * �������� ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� �ּҸ� �˾ƾ� �ϴµ�,
 * ����� tcp/ip������ ip�ּҸ� �˾��Ѵ�.
 * 
 * user������������ �˾ƾ� �Ѵ�!
 * */
package com.ss.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {

	public TestMain() {

	}
	
	public static void main(String[] args) {
		//1�ܰ� ����Ŭ�� �ڹٰ� ������ �� �ִ� �ڵ尡 ����ִ� jar������ �޸𸮿� �ε��ؾ� �Ѵ�.
		//�̷� �����ͺ��̽� ���� jar������ �ڹٿ����� ����̹��� �Ѵ�.
		//����̹��� db�����翡�� �����Ѵ�.
		//oracle--> ����Ŭ�� mysql--> ����Ŭ�� mssql-->ms		
				
		//2�ܰ� ����Ŭ�� ��������
		
		Connection con=null;
		PreparedStatement pstmt=null;//���⼭ �����ؾ� finally���� ������ �ִ�.
		
		try {
			//����̹� Ŭ���� �ε�!! //�����Ѱ� ��Ʈ�������� ������ �ȴ�.
			Class.forName("oracle.jdbc.driver.OracleDriver"); //��Ű������ ��θ� �����ϰ� ctrl+c, ctrl+v�ϸ� �����ؼ� �Է°���  .classȮ���ڴ� ����.
			System.out.println("����̹� �ε� ����");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@sist108:1521", "batman", "1234"); //url�κ��� �ܿ��� �Ѵ�. �Ǹ������� ��Ʈ��ȣ
			if (con!=null) {
				System.out.println("���Ӽ���");
				
				//���� ������ ������ ���̺� insert
				String sql="insert into company(compay_id, name) values(seq_company.nextval, '����Ű')";
				//������ ������ ���ؼ��� �������� �����ϴ� ��ü�� �̿��ؾ� �ϴµ�, 
				//�� ��ü�� PreparedStatement �������̽��̴�.
				pstmt=con.prepareStatement(sql); //��ȯ���� ���������� ��Ŭ�������� �˾Ƽ� ã���ش�.
				int result=pstmt.executeUpdate(); //������ ���� �޼ҵ�
				//�� ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�� �ش�. insert�����ϸ� 1���� �ݿ��Ǿ� 1�� ��ȯ�ȴ�.
				
				con.getAutoCommit(); //�� �⺻���̶� �ڵ����� commit���ش�.
				
				if(result==1){
					System.out.println("�Է¼���");
				}else {
					System.out.println("�Է½���");
				}								
			}else {
				System.out.println("���ӽ���");				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			//��Ʈ���� db�����۾� �Ŀ� �ݵ�� �ݴ� ó���� �ؾ��Ѵ�.			
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
}
