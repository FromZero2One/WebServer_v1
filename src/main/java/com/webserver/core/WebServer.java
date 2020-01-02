package com.webserver.core;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer主类
 * @author ta
 *
 */
public class WebServer {
	private ServerSocket server;
	private ExecutorService threadPool;
	/**
	 * 构造方法，用来初始化服务端
	 */
	public WebServer() {
		try {
			System.out.println("正在启动服务端...");
			server = new ServerSocket(8088);
			//线程池
			threadPool = Executors.newFixedThreadPool(50);
			System.out.println("服务端启动完毕!");
			System.err.println("访问路径为====http://localhost:"+server.getLocalPort()+"/myweb/index.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 服务端开始工作的方法
	 */
	public void start() {
		try {
			while(true) {
				System.out.println("等待客户端连接...");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了！");
				//启动一个线程处理该客户端请求
				ClientHandler handler  = new ClientHandler(socket);
				threadPool.execute(handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}










