package com.wtao.calc;

public class App {

	public static void main(String[] args) {

		// 不改变程序的功能，只调整结构【重构】
		// 模型
		CalcModel model = new CalcModel();

		// 控制器
		Controller controller = new Controller();
		controller.setModel(model);

		// 视图
		CalcFrame view = new CalcFrame(controller);
		
		// 模式设置回调接口（视图实现了回调接口）
		model.setCallback(view);
		
		// 订阅（添加观察者）
		model.addObserver(view);

		// 装配（依赖关系）
		// 解决组件之间的依赖问题：
		// 1）构造方法注入
		// 2）setter 方法注入依赖的组件
		// controller.setModel(model);
		// view.setController(controller);
	}

}
