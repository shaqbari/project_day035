/*�̰�ü�� JTable�� �����ڿ��� �䱸�ϴ� ��Ʈ�ѷ���ü�̴�
 * �̰�ü�� ������ �����ΰ� ������ �и������ִ� �߰��� �����̴�.
 * 
 * */

package com.ss.model;

import javax.swing.table.AbstractTableModel;

public class OracleModel extends AbstractTableModel{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@sist108:1521:XE";
	String user="batman";
	String password="1234";
	
	String[][] data=new String[3][2];
	
	public OracleModel() {
		data[0][0]="¥���";
		data[0][1]="�ϰ����";
		data[1][0]="������ġ";
		data[1][1]="�������";
		data[2][0]="��ũ������ġŲ";
		data[2][1]="KFC";
	}
	
	//�÷��� ������ ��ȯ
	public int getColumnCount() {
		return 2;
	}

	//���ڵ��� ������ ��ȯ
	public int getRowCount() {
		return 3;
	}

	//Ư����ġ�� ���� ��ȯ jtable�� ȣ���ϴ� ���̴�.
	public Object getValueAt(int row, int col) {//������ �μ��� jtable�� ���Ѱ��̴�.
		//System.out.println("row="+row+", col="+col+"�� �� �־�� �ؿ�?");
		return data[row][col];
	}

}
