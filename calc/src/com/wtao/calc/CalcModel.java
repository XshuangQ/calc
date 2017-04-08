package com.wtao.calc;

import java.util.Observable;
import java.util.Stack;

/**
 * 计算器的模型（Model）
 * 
 * 模型中的数据发布出去（成为被订阅的主题、被观察的对象）
 * 
 * @author wtao
 *
 */
public class CalcModel extends Observable {

	// 逆波兰表达式（后序表达式）

	// 操作数和运算符
	private Stack<String> stack;
	
	private CalcCallback callback;
	
	public void setCallback(CalcCallback callback) {
		this.callback = callback;
	}

	/**
	 * 计算器
	 */
	public CalcModel() {
		stack = new Stack<>();
	}
	
	public String getResult() {
		return stack.toString();
	}

	/**
	 * 
	 * @param e
	 */
	public void push(String e) {
		stack.push(e);
		System.out.println(stack);
		
		// 数据改变了，通知观察者
		setChanged();
		notifyObservers(stack.toString());
		
		callback.showResult(stack.toString());
	}

	/**
	 * 清空
	 */
	public void clear() {
		stack.clear();
	}

	public void todo() {
		// 运算
		
		// 通知视图更新
		notifyObservers("数据");
	}

}
