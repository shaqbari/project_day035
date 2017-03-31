
/*우리가 사용중인 데이터 베이스 제품은 모두DBMS이다
 * DB(저장소)MS(관리시스템) - 네트워크 기반
 * 이라서 원격접속이 가능하다.
 * 
 * 데이터베이스 접속 
 * 클라이언트: sqlplus, Toad 등
 * 
 * 오라클: 서버
 * 
 * 현재 사용중인 네트워크 프로토콜은 tcp/ip기반이므로,
 * 원격지의 호스트를 접속하려면 그 호스트의 주소를 알아야 하는데,
 * 기반이 tcp/ip인지라 ip주소를 알아한다.
 * 
 * user계정정보까지 알아야 한다!
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
		//1단계 오라클을 자바가 제어할 수 있는 코드가 들어있는 jar파일을 메모리에 로드해야 한다.
		//이런 데이터베이스 제어 jar파일을 자바에서는 드라이버라 한다.
		//드라이버는 db제조사에서 제공한다.
		//oracle--> 오라클사 mysql--> 오라클사 mssql-->ms		
				
		//2단계 오라클에 접속하자
		
		Connection con=null;
		PreparedStatement pstmt=null;//여기서 선언해야 finally에서 닫을수 있다.
		
		try {
			//드라이버 클래스 로드!! //희한한게 스트링형으로 넣으면 된다.
			Class.forName("oracle.jdbc.driver.OracleDriver"); //패키지에서 경로명 선택하고 ctrl+c, ctrl+v하면 복사해서 입력가능  .class확장자는 뺀다.
			System.out.println("드라이버 로드 성공");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@sist108:1521", "batman", "1234"); //url부분은 외워야 한다. 맨마지막은 포트번호
			if (con!=null) {
				System.out.println("접속성공");
				
				//현재 유저가 보유한 테이블에 insert
				String sql="insert into company(compay_id, name) values(seq_company.nextval, '나이키')";
				//쿼리문 수행을 위해서는 쿼리문을 전담하는 객체를 이용해야 하는데, 
				//이 객체가 PreparedStatement 인터페이스이다.
				pstmt=con.prepareStatement(sql); //반환형을 먼저적으면 이클립스에서 알아서 찾아준다.
				int result=pstmt.executeUpdate(); //쿼리문 실행 메소드
				//이 쿼리문 수행에 의해 반영된 레코드의 수를 반환해 준다. insert성공하면 1개가 반영되어 1이 반환된다.
				
				con.getAutoCommit(); //가 기본값이라 자동으로 commit해준다.
				
				if(result==1){
					System.out.println("입력성공");
				}else {
					System.out.println("입력실패");
				}								
			}else {
				System.out.println("접속실패");				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			//스트림과 db연동작업 후엔 반드시 닫는 처리를 해야한다.			
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
