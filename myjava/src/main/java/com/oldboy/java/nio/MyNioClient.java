package com.oldboy.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import static com.oldboy.java.nio.MyNioServer.getRemoteAddr;
import static com.oldboy.java.nio.MyNioServer.readStringFromChannel;

/**
 * 客户端
 */
public class MyNioClient {
	public static void main(String[] args) throws Exception {
		//
		SocketChannel sc = SocketChannel.open();
		//
		sc.configureBlocking(false) ;

		//连接到远程服务器
		InetSocketAddress srvAddr = new InetSocketAddress("192.168.13.5" , 8888) ;
		sc.connect(srvAddr) ;

		//挑选器
		Selector sel = Selector.open();

		//注册
		SelectionKey key = sc.register(sel , SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT | SelectionKey.OP_READ) ;

		int index = 1 ;
		while(true){
			sel.select() ;
			if(key.isConnectable()){
				System.out.println("可连接!");
				sc.finishConnect();
			}

			if(key.isWritable()){
				System.out.println("可写");
				String msg = "小黄澳" + index ;
				ByteBuffer buf = ByteBuffer.wrap(msg.getBytes()) ;
				//发送消息给服务器
				sc.write(buf) ;
				index ++ ;
			}
			//是否可读
			if(key.isReadable()){
				SocketChannel sc0 = (SocketChannel) key.channel();
				System.out.println(getRemoteAddr(sc0) + " 可读了");
				String msg = readStringFromChannel(sc0) ;
				System.out.println(getRemoteAddr(sc0) + " 发来消息: " + msg);
			}
			//清空挑选集合
			sel.selectedKeys().clear();
			Thread.sleep(1000);
		}
	}
}
