package com.ss.homework;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

public class TableSwitch extends JFrame implements ItemListener{
	JPanel p;
	Choice choice;	
	JTable table;
	JScrollPane scroll;
	
	public TableSwitch() {
		p=new JPanel();
		choice=new Choice();

		table=new JTable(new DeptModel());
		//table=new JTable(3, 4);
		scroll=new JScrollPane(table);
		
		choice.add("▼목록선택");
		choice.add("사원목록");
		choice.add("부서목록");	
		choice.addItemListener(this);
		
		p.add(choice);		
		add(p, BorderLayout.WEST);
		add(scroll);		
		
		setSize(700, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void setEmpTable(){
		System.out.println("사원선택?");
		
	}
	
	public void setDeptTable(){
		System.out.println("부서선택?");
	
	}
	
	public void itemStateChanged(ItemEvent e) {
		int index=choice.getSelectedIndex();
		if (index==1) {			
			setEmpTable();
		} else if(index==2){
			setDeptTable();
		}		
	}

	public static void main(String[] args) {
		new TableSwitch();
		
	}


}
