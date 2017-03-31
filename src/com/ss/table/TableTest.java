/*swing�� ������Ʈ �� �����ͺ��̽��� ��������� �ð�ȭ�ϱ⿡ ����ȭ��
 * ������Ʈ�� �ִµ� jtable�̴�.
 * */

package com.ss.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String pw="";	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select���� ��츸 �ʿ��� //��? ����� ��ƾ� �ϹǷ�
	
	/*String[][] data={
		{"���	", "����", "�ٳ���"},
		{"����", "�ƿ��", "bmw"},
		{"�౸", "��", "�߱�"}
	};
	String[] column={
		"�÷�1", "�÷�2", "�÷�3"
	};*/
	
	String[][] data;
	String[] column={"member_id", "name", "age"};
	
	public TableTest() {
		//setLayout(new FlowLayout());
		
		loadData();
		
		//table=new JTable();
		//table=new JTable(4, 3);
		table=new JTable(data, column);
		scroll=new JScrollPane(table); //��ũ���� �ٿ��� column�� ���´�.
				
		add(scroll);
		
		setSize(500, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//���ڵ� ä���ֱ�
	//���̺��� �����ϱ� ����, mariadb�� �����Ͽ� member���̺��� ���ڵ带
	//�������迭�� ��Ƴ���! ��? JTable�� �������� �μ��� �������迭�� ���Ǵϱ�!
	public void loadData(){
		/*1.����̹� �ε�
		 *2. ����
		 *3. ���ϴ� ��������
		 *4. �����ͺ��̽� �ݱ�
		 * */
		
		try {
			//1.�ε�
			Class.forName(driver);
			System.out.println("�ε强��");
			
			//2.����
			con=DriverManager.getConnection(url, user, pw);
			if (con!=null) {
				System.out.println("���Ӽ���");
				
				//3.������ ����
				String sql="Select * from member";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				//data�� ���� �����Ǿ�� ������ �ִ�.***
				data=new String[3][3];
				
				int count=0;
				while (rs.next()) {					
					int member_id=rs.getInt("member_id");//primary key������
					String name=rs.getString("name");//�̸� �޾ƿ���		
					int age=rs.getInt("age"); //���� ������
					
					System.out.println(member_id+", "+name+", "+age);
					
					data[count][0]=Integer.toString(member_id);	
					data[count][1]=name;
					data[count][2]=Integer.toString(age);
					
					count++;
				
				}					
			} else {
				System.out.println("���ӽ���");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	//4.�ݱ�
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
		new TableTest();
	}
}
