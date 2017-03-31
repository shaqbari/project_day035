/* * � ���ø����̼��� �����ϴ� ������������ ���ƾ� ����� �����ǰ�,
 * ���ø����̼��� ǰ���� �ö󰣴�.
 *
 * Ư�� �����ΰ� ������ ���� �ִ� GUI�� �ִ� ���ø����̼��� ���,
 *  �������� ���� ������ �ʴ� ���� ������,
 *  ������Ʈ�� ���� ������ ������ ���� ���� ���� ���
 *  ���� ���� ������ ����Ǿ��� �� �����ϱⰡ ���������, 
 *  ������������ ��������.
 *  
 *  �̷��� ������ ������ ������ ��������, ���ߵ��� �̹� �����ߴ� �������̾���.
 *  ����(��)�� ������(��)�� �и����� �����ߴ���
 *   ������������ ���� �ö󰬴ٴ� ������ ������ MVC�����̶� �Ѵ�.
 *   
 *   jtable�� swing ������Ʈ �� mvc������ �����ִ� ������Ʈ�̸�,
 *   �����ο� �ش��ϴ� JTable�� ������ �ش��ϴ� DB�����͸� �и���Ű�� ����
 *   TableModel�̶�� �߰� ��Ʈ�ѷ��� ������ �ش�.
  *
  * jtable ������ �� �����ص��� https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
  * */



package com.ss.model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	public ModelTest() {
		table=new JTable(new MariaModel());
		scroll=new JScrollPane(table);
				
		add(scroll);
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	//���� ������(emp), �μ����(dept) ���̺� �ٲ㼭 �����ִ°� �Ͽ��� ���ѽñ���.

	public static void main(String[] args) {
		new ModelTest();
	}

}
