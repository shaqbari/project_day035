package com.ss.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/ss_edu";
	String user="root";
	String password="";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String[][] data;
	
	public MyModel(String tableName, String[] columnName) {
		try {
			Class.forName(driver);
			System.out.println("�ε强��");
			
			con=DriverManager.getConnection(url, user, password);
			if (con!=null) {
				System.out.println("���Ӽ���");
				
				String sql="select * from "+tableName;
				pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();				
				
				rs.last();
				int total=rs.getRow();
				rs.beforeFirst();
				
				data=new String[total+1][columnName.length];				
				for (int i = 0; i < columnName.length; i++) {
					data[0][i]=columnName[i];
				}
				
				int index=1;
				while (rs.next()) {
					for (int i = 0; i < columnName.length; i++) {
						data[index][i] = rs.getString(columnName[i]);						
					}
					index++;
				}
				
			} else {
				System.out.println("���ӽ���");
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
		
}
