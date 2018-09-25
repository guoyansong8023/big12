package com.oldboy.java.proxy;

/**
 * 接口实现类
 */
public class WelcomeServiceImpl implements WelcomeService ,Dog{
	public void sayHello(String msg) {
		System.out.println("hello : " + msg);
	}

	@Override
	public void eat() {
		System.out.println("eat");
	}
}
