package com.wtao.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 按钮的点击事件监听器
 * 
 * @author wtao
 *
 */
public class Controller implements ActionListener {

	/**
	 * 计算器的核心
	 */
	private CalcModel calc;

	private StringBuilder input = new StringBuilder();

	/**
	 * 构造方法
	 */
	public Controller() {
	}

	/**
	 * 设置控制器依赖的模式
	 * 
	 * @param model
	 */
	public void setModel(CalcModel model) {
		calc = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);

		switch (command) {
		case "+":
		case "-":
		case "*":
		case "/":
		case "=":
			calc.push(input.toString());
			input.delete(0, input.length());
			calc.push(command);
			break;

		case "C":
			calc.clear();
			break;

		// case "=":
		// break;

		default:
			input.append(command);
		}

	}

}
