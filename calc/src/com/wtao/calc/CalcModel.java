package com.wtao.calc;

import java.util.Arrays;
import java.util.HashMap;
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

	private String input;
	
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
		
		//通知观察者
		setChanged();
		notifyObservers("");
	}
	
	public void setInput(String input){
		this.input = input;
		
		//通知视图更新
		setChanged();
		notifyObservers(input);
	
		//callback.showResult(input);
	}

	public void todo(String input) {
		this.input = input;
		// 运算

		// 获取操作数
		String[] n = input.split("[\\+\\-\\*/=]");
		System.out.println(Arrays.toString(n));

		// 获取操作符
		String[] m = input.split("\\d+");
		System.out.println(Arrays.toString(m));

		Stack<String> numStack = new Stack<String>();
		Stack<String> opeStack = new Stack<String>();

		int i = 0, j = 1;
		numStack.push(n[i]);
		i++;
		while (j < m.length) {
			numStack.push(n[i]);
			i++;
			// 优先级后者比前者高
			String str = opeStack.isEmpty() ? null : opeStack.peek();
			if (judge(str, m[j])) {
				opeStack.push(m[j]);
				j++;
			} else {
				// 优先级后者比前者低或者等于
				// 计算
				numStack.pop();
				i--;
				numStack.push(Calculate(numStack, opeStack));
				System.out.println(numStack);
			}
		}
		if (!opeStack.isEmpty()) {
			numStack.push(Calculate(numStack, opeStack));
		}
		
		// 通知视图更新
		setChanged();
		notifyObservers(numStack.toString());
	}
	
	boolean judge(String former, String latter) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(null, 0);
		map.put("+", 1);
		map.put("-", 1);
		map.put("*", 2);
		map.put("/", 2);
		// 优先级后者比前者高
		if (map.get(latter) > map.get(former)) {
			return true;
		}
		return false;
	}
	
	String Calculate(Stack<String> numStack, Stack<String> opeStack) {
		double numberOne = Double.parseDouble(numStack.pop());
		
		while (!opeStack.isEmpty()) {
			System.out.println(numberOne);
			double numberTwe = Double.parseDouble(numStack.pop());
			System.out.println(numberTwe);
			switch (opeStack.pop()) {
			
			case "+":
				System.out.print(numberOne );
				numberOne += numberTwe;
				System.out.print( "+" + numberTwe + "=");
				System.out.println(numberOne);
				break;
				
			case "-":
				System.out.print(numberTwe+"-"+numberOne+"=");
				numberOne = numberTwe - numberOne;
				System.out.println(numberOne);
				break;
				
			case "*":
				System.out.print(numberOne );
				System.out.print("*" + numberTwe + "=");
				numberOne *= numberTwe;
				System.out.println(numberOne);
				break;

			case "/":
				numberOne /= numberTwe;
				break;
			}
		}
		return String.valueOf(numberOne);
	}

}
