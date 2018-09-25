package com.oldboy.qq.server;

import com.oldboy.java.qq.common.BaseMessage;
import com.oldboy.java.qq.common.ServerChatMessage;
import com.oldboy.qq.util.MessageFactory;
import com.oldboy.qq.util.QQUtil;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 处理消息
 */
public class ProcessMessageTask implements  Runnable{

	private SelectionKey key  ; ;

	public ProcessMessageTask(SelectionKey key){
		this.key = key;
	}
	public void run() {
		QQServer server = QQServer.getInstance();
		SocketChannel sc = (SocketChannel) key.channel();
		ReentrantLock lock = (ReentrantLock) key.attachment();
		boolean b = lock.tryLock() ;
		if(b){
			try {
				BaseMessage msg = MessageFactory.parseClientMessageFromChannel(sc) ;
				if(msg != null){
					switch (msg.getMessageType()){
						case BaseMessage.SERVER_TO_CLIENT_CHATS:
							server.broadcastMessage(msg) ;
							break ;
						case BaseMessage.SERVER_TO_CLIENT_CHAT:
							server.forwardMessage(msg , new String(((ServerChatMessage)msg).getRecvAddrBytes()));
							break ;
						case BaseMessage.SERVER_TO_CLIENT_REFRESH_FRIENDS:
							server.forwardMessage(msg , QQUtil.getRemoteAddr(sc.socket()));
							break ;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			lock.unlock();
		}
	}
}
