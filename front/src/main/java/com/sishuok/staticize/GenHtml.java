package com.sishuok.staticize;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GenHtml {
	public static Map<String,byte[]> mapHtml = new HashMap<String,byte[]>();
	/**
	 * 生成静态的html
	 */
	public void genStaticHtml(){
		try {
			//1:直接使用url去获取内容
//			URL url = new URL("http://localhost:9080/toIndex?gen=true");
			
			URL url = new URL("http://192.168.1.106:8080/front/toIndex?gen=true");
			
			BufferedInputStream bin = new BufferedInputStream(url.openStream());

			byte[] bs = new byte[10000];

			bin.read(bs);
			//2：
			if(bs!=null && bs.length>1){
				mapHtml.put("toIndex", bs);
			}else{
				System.out.println("no dataaaaaaaaaaaaaaaaa");
			}
			
			bin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 启动生成静态Html的线程
	 */
	public void startGenStaticHtml(){
		Thread t = new GenHtmlThread();
		t.start();
	}
	private class GenHtmlThread extends Thread{
		public void run(){
			while(true){
				try{
					//1：休息
					Thread.sleep(10000L);
				}catch(Exception err){
					err.printStackTrace();
				}
				//2：调用生成
				genStaticHtml();
			}
		}
	}
}
