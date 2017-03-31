/*swing�� ������Ʈ �� �����ͺ��̽��� ��������� �ð�ȭ�ϱ⿡ ����ȭ��
 * ������Ʈ�� �ִµ� jtable�̴�.
 * 
 * ���ڵ��� ������ ���� �迭�� ũ�⸦ �����ؼ� �����غ���
 * */

package com.ss.test;

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
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String pw="1234";	
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
	String[] column={"empno", "ename", "job", "mgr", "hiredate", "sal", "deptno", "depno"};
	
	public TableTest() {
		//setLayout(new FlowLayout());
		
		loadData();
		
		//table=new JTable();
		//table=new JTable(4, 3);
		table=new JTable(data, column);
		scroll=new JScrollPane(table); //��ũ���� �ٿ��� column�� ���´�.
				
		add(scroll);
		
		setSize(800, 600);
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
				String sql="Select * from emp";
				
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs�� last()�� ������, ��ġ�� ����
				rs.last();
				int total=rs.getRow();
				
				//rs���󺹱�
				rs.beforeFirst(); //�ƹ��͵� ����Ű�� �ʴ� ����
				//rs.first();���� ù��° ���ڵ尡 �ִ°����� Ŀ���� ��ġ�ϱ� ������ ó�� next()�ϸ� �ι������� �аԵȴ�. 
				
				//data�� ���� �����Ǿ�� ������ �ִ�.***
				data=new String[total][column.length];
				
				int index=0;
				while (rs.next()) {					
					int empno=rs.getInt("empno");
					String ename=rs.getString("ename");
					String job=rs.getString("job");
					int mgr=rs.getInt("mgr");
					String hiredate=rs.getString("hiredate");
					int sal=rs.getInt("sal");
					String comm=rs.getString("comm");
					//null�� �־ ����, null�� �ظ��ϸ� ���� �ʰ� �����ϴ� ���� ����.
					//���� ����ϸ� null�� 0�̵Ǿ������
					//String����ϸ� null�� ���⼭�� ��Ƴ���.
					int deptno=rs.getInt("deptno");
					
					data[index][0]=Integer.toString(empno);	
					data[index][1]=ename;
					data[index][2]=job;
					data[index][3]=Integer.toString(mgr);
					data[index][4]=hiredate;
					data[index][5]=Integer.toString(sal);
					data[index][6]=comm;
					data[index][7]=Integer.toString(deptno);
					
					index++;				
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
