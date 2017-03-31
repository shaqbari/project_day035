/*swing의 컴포넌트 중 데이터베이스의 결과집합을 시각화하기에 최적화된
 * 컴포넌트가 있는데 jtable이다.
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
	String[] column={"member_id", "name", "age"};
	
	public TableTest() {
		//setLayout(new FlowLayout());
		
		loadData();
		
		//table=new JTable();
		//table=new JTable(4, 3);
		table=new JTable(data, column);
		scroll=new JScrollPane(table); //스크롤을 붙여야 column이 나온다.
				
		add(scroll);
		
		setSize(500, 150);
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
				String sql="Select * from member";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				//data가 먼저 생성되어야 넣을수 있다.***
				data=new String[3][3];
				
				int count=0;
				while (rs.next()) {					
					int member_id=rs.getInt("member_id");//primary key얻어오기
					String name=rs.getString("name");//이름 받아오기		
					int age=rs.getInt("age"); //나이 얻어오기
					
					System.out.println(member_id+", "+name+", "+age);
					
					data[count][0]=Integer.toString(member_id);	
					data[count][1]=name;
					data[count][2]=Integer.toString(age);
					
					count++;
				
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
