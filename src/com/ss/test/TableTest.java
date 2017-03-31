/*swing의 컴포넌트 중 데이터베이스의 결과집합을 시각화하기에 최적화된
 * 컴포넌트가 있는데 jtable이다.
 * 
 * 레코드의 갯수에 따라 배열의 크기를 지정해서 개발해보자
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
	ResultSet rs; //select문일 경우만 필요함 //왜? 결과를 담아야 하므로
	
	/*String[][] data={
		{"사과	", "딸기", "바나나"},
		{"벤츠", "아우디", "bmw"},
		{"축구", "농구", "야구"}
	};
	String[] column={
		"컬럼1", "컬럼2", "컬럼3"
	};*/
	
	String[][] data;
	String[] column={"empno", "ename", "job", "mgr", "hiredate", "sal", "deptno", "depno"};
	
	public TableTest() {
		//setLayout(new FlowLayout());
		
		loadData();
		
		//table=new JTable();
		//table=new JTable(4, 3);
		table=new JTable(data, column);
		scroll=new JScrollPane(table); //스크롤을 붙여야 column이 나온다.
				
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//레코드 채워넣기
	//테이블을 생성하기 전에, mariadb와 연동하여 member테이블의 레코드를
	//이차원배열에 담아놓자! 왜? JTable의 생성자의 인수로 이차원배열이 사용되니깐!
	public void loadData(){
		/*1.드라이버 로드
		 *2. 접속
		 *3. 원하는 쿼리실행
		 *4. 데이터베이스 닫기
		 * */
		
		try {
			//1.로드
			Class.forName(driver);
			System.out.println("로드성공");
			
			//2.접속
			con=DriverManager.getConnection(url, user, pw);
			if (con!=null) {
				System.out.println("접속성공");
				
				//3.쿼리문 실행
				String sql="Select * from emp";
				
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				//rs를 last()로 보내고, 위치를 묻자
				rs.last();
				int total=rs.getRow();
				
				//rs원상복구
				rs.beforeFirst(); //아무것도 가리키지 않는 상태
				//rs.first();쓰면 첫번째 레코드가 있는곳으로 커서가 위치하기 때문에 처음 next()하면 두번쨰부터 읽게된다. 
				
				//data가 먼저 생성되어야 넣을수 있다.***
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
					//null이 있어서 위험, null은 왠만하면 쓰지 않게 설계하는 것이 좋다.
					//숫자 취급하면 null은 0이되어버린다
					//String취급하면 null이 여기서는 살아난다.
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
				System.out.println("접속실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	//4.닫기
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
