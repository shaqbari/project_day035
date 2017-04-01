package com.ss.homework;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;

public class TableSwitch extends JFrame implements ItemListener{
	JPanel p_west, p_center;
	Choice choice;	
	JTextField txt;
	JTable table;	
	JScrollPane scroll;	
	MyModel model;
	String[] empCol={"ename",	 "job","mgr","hiredate", "sal", "comm", "deptno"};	
	String[] deptCol={"deptno", "dname", "loc"};	
	
	public TableSwitch() {
		p_west=new JPanel();
		p_center=new JPanel();
		choice=new Choice();
		
		choice.add("▼목록선택");
		choice.add("사원목록");
		choice.add("부서목록");	
		choice.addItemListener(this);
		
		p_west.add(choice);		
		add(p_west, BorderLayout.WEST);
		add(p_center);			
		
		p_west.setPreferredSize(new Dimension(100, 500));
		p_center.setPreferredSize(new Dimension(600, 500));
		
		setSize(700, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void setTable(String tableName, String[] columnName){
		p_center.removeAll();
		model=new MyModel(tableName, columnName);
		table=new JTable(model);		
		scroll=new JScrollPane(table);
		p_center.add(scroll);
		p_center.updateUI();		
	}
	
	public void itemStateChanged(ItemEvent e) {
		int index=choice.getSelectedIndex();
		if(index==0){
			System.out.println("목록선택?");
			p_center.removeAll();
			p_center.updateUI();
		}else if	(index==1) {
			System.out.println("사원선택?");
			setTable("emp", empCol);
		} else if(index==2){
			System.out.println("부서선택?");
			setTable("dept", deptCol);
		}		
	}

	public static void main(String[] args) {
		new TableSwitch();
		
	}


}
