package com.oldboy.qq.client;

/**
 * Created by Administrator on 2018/9/10.
 */
public class QQClientMain {
	public static void main(String[] args) {
		QQClientChatsUI ui = new QQClientChatsUI();
		QQClientCommThread t = new QQClientCommThread();
		t.start();
		t.ui = ui ;

	}
}
