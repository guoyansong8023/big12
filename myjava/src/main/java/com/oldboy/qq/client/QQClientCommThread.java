package com.oldboy.qq.client;

import com.oldboy.java.qq.common.BaseMessage;
import com.oldboy.java.qq.common.ClientChatsMessage;
import com.oldboy.java.qq.common.ClientRefreshFriendsMessage;
import com.oldboy.qq.util.IConstants;
import com.oldboy.qq.util.MessageFactory;

import java.net.Socket;

/**
 * client通信线程
 */
public class QQClientCommThread extends Thread {
	//
	public QQClientChatsUI ui ;

	public void run() {
		try {
			Socket s = new Socket(IConstants.QQ_CLIENT_SERVER_IP,IConstants.QQ_CLIENT_SERVER_PORT) ;
			for(;;){
				BaseMessage msg = MessageFactory.parseServerMesageFromSocket(s) ;
				if(msg != null){
					System.out.println("收到服务器消息");
					switch (msg.getMessageType()){
						//群聊
						case BaseMessage.CLIENT_TO_SERVER_CHATS:
						{
							ClientChatsMessage msg0 = (ClientChatsMessage) msg;
							//更新历史区
							ui.updateHistory(msg0.getSenderAddr(),msg0.getMessage());
							break ;
						}
						case BaseMessage.CLIENT_TO_SERVER_CHAT:
						{

							break ;
						}
						case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
						{
							ClientRefreshFriendsMessage msg0 = (ClientRefreshFriendsMessage) msg;
							ui.refreshFriendList(msg0.getFriendsList());
							break ;
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
