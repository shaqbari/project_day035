package com.ss.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class DeptModel extends AbstractTableModel{	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/ss_edu";
	String user="root";
	String password="";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String[][] data;
	String[] column={"deptno", "dname", "loc"};
	
	public DeptModel() {			
		//1.드라이버 로드
		try {
			Class.forName(driver);
			System.out.println("로드성공");
			
			//2. 접속
			con=DriverManager.getConnection(url, user, password);
			if (con!=null) {
				System.out.println("접속성공");
				
				//3. sql수행
				String sql="Select * from dept";				
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
				
				rs.last();
				int total=rs.getRow();
				rs.beforeFirst();
				
				data=new String[total+1][column.length];
				
				for (int i = 0; i < column.length; i++) {
					data[0][i]=column[i];
				}				
				
				int index=1;				
				while (rs.next()) {
					int deptno=rs.getInt(column[0]);
					String dname=rs.getString(column[1]);
					String loc=rs.getString(column[2]);
					
					data[index][0]=Integer.toString(deptno);
					data[index][1]=dname;
					data[index][2]=loc;
					
					index++;					
				}				
			} else {
				System.out.println("접속실패");
			}			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			//4.닫기
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

	public int getColumnCount() {
		return data[0].length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public static void main(String[] args) {
		new DeptModel();
	}
}
