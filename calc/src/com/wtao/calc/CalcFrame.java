package com.wtao.calc;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcFrame extends JFrame {

	private JLabel labelResult;

	private String[] titles = { "7", "8", "9", "Del", "4", "5", "6", "/", "1",
			"2", "3", "-", "0", "=", "+", "." };

	private JButton[] buttons =  new JButton[titles.length];

	/**
	 * 构造方法
	 */
	public CalcFrame() {
		initUi();
		setVisible(true);
	}

	private void initUi() {
		setTitle("计算器 - xx 作品");
		setSize(320, 480);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//添加结果标签
		labelResult = new JLabel("0.0");
		add(labelResult,BorderLayout.NORTH);
		
		//添加按钮
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.CENTER);
		
		//设置布局
		buttonPanel.setLayout(new GridLayout(5,4,5,5));
		
		for (int i = 0; i < titles.length; i++) {
			if(titles[i].length()==0){
				//不显示内容的标签
				buttonPanel.add(new JLabel(""));
			}else{
				//按钮
				buttons[i] = new JButton(titles[i]);
				buttonPanel.add(buttons[i]);
			}
		}
		
		
	}

}
