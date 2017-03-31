/*레코드 결과를 배열로 받을때의 단점
 * 레코드의 총 갯수를 알수가 없다.
 * 우물에 깊이를 읽어낼 수 있는 녀석을 빠뜨리자
 *  
 * */

package com.ss.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest {
	String driver="oracle.jdbc.driver.OracleDriver"; //.class는 붙이면 안된다.
	String url="jdbc:oracle:thin:@sist108:1521:XE";//jdbc(Java Data Base Connectivity):자바의 데이터베이스 연동기술
	//한컴퓨터에 오라클이 여러대 설치 되어있을 수 있으므로 맨마지막에 해당 오라클 이름을 넣어준다. 여기서는 XE 
	String user="batman";
	String password="1234";
	
	Connection con; //ms에서는 접속시도, 여기서는 접속한 이후 그 결과를 담는 객체
	PreparedStatement pstmt;
	ResultSet rs;	
	//위의 삼총사는 모두 인터페이스이다.
	//미래에 나올 dbms를 위해 몸체를 제거한 기능만을 구현한 인터페이스로 만들어두었다.
	//dbms제조사에서 이 인터페이스들을 이용해서 driver를 만들면 다운받아 누구나 이용할 수 있다.
	//사용하려면 java 인터페이스를 이용한다.
	
	//레코드셋 객체를 이용하여 총 레코드 수 알아맞추기
	public ResultSetTest() {
		try {
			//1.드라이버 로드
			Class.forName(driver);
			System.out.println("로드성공");
			
			//2.접속
			con=DriverManager.getConnection(url, user, password);			
			if (con!=null) {
				System.out.println("접속성공");
				
				//3. sql문실행
				String sql="select * from company";
				
				//rs의 커서를 전방향, 후방향 등으로 자유롭게 움직이거나, 한꺼번에 건너뛰게 하려면
				//스크롤 가능한 상수 옵션을 부여해야 한다.
				//select문의 결과 집합을 대상으로 단지 보기만 할꺼면 CONCUR_READ_ONLY로,
				//수정을 가할거면 CONCUR_UPDATABLE
				//하지만 대부분 SELECT문에 의한 레코드는 읽기위함이다.
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );				
				rs=pstmt.executeQuery();
				
				//제일 마지막 레코드로 보내기
				rs.last();
				int num=rs.getRow();//현재 커서가 위치한 레코드 번호!! 즉 레코드의 위치
				System.out.println(num);
				
			} else {
				System.out.println("접속실패");				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//4.삼총사닫기
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
