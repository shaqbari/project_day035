/*마리아db를 연동하여 레코드를 콘솔에 찍어보자
 * 
 * 주의) dbms제조사가 제공하는 드라이버를 미리 준비하자
 * */

package com.ss.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331"; //이부분은 외워야 한다. 검색해도 된다.
	String user="root";
	String password="";
	
	Connection con; //접속정보를 가진 인터페이스
	
	PreparedStatement pstmt; //쿼리 수행 객체
	ResultSet rs;
	/*쿼리문이 select문일 경우 원격지의 데이터페이스의 테이블과 	동일한 결과집합을 담아놓는 객체(=표와 같다.)*/
	
	public SelectTest() {
		//1.드라이버를 로드
		//2. 접속
		//3. 원하는 쿼리문 실행
		//4. db 관련된 자원 닫기
		
		try {
			//1.드라이버 로드
			Class.forName(driver);
			System.out.println("로드 성공");
			
			//2.접속
			con=DriverManager.getConnection(url, user, password);
			if (con!=null) {
				System.out.println("접속 성공");
				
				//3.원하는 쿼리문 실행
				String sql="select * from member"; //콘솔창이 아니므로 sql보낼때는 마지막에;찍지 않는다.
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery(); //쿼리 수행후 반환되는 결과집합을 받자. 왜?? select문이니깐
				
				//커서 한칸 전진
	/*			rs.next();
				int member_id=rs.getInt("member_id");
				String name=rs.getString("name"); //컬럼에 해당하는 값을 반환
				int age=rs.getInt("age"); //나이 반환
				System.out.println(member_id+", "+name+", "+age);
				
				rs.next();
				rs.next();*/
				
				while (rs.next()) {
					int member_id=rs.getInt("member_id");
					String name=rs.getString("name"); //컬럼에 해당하는 값을 반환
					int age=rs.getInt("age"); //나이 반환
					System.out.println(member_id+", "+name+", "+age);					
				}
				
			}else {
				System.out.println("접속 실패");				
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
		//서버가 주기적으로 다운되면 뭔가 쌓인다는 것이다. 대부분 db안닫아준것이다.
		
	}
	
	
	public static void main(String[] args) {
		new SelectTest();
	}
}
