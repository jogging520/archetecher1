package com.sishuok.staticize;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GenHtml {
	public static Map<String,byte[]> mapHtml = new HashMap<String,byte[]>();
	/**
	 * 生成静态的html
	 */
	public void genStaticHtml(){
//		URL url = null;
		InputStream bin = null;
		CloseableHttpResponse response = null;
		try {
			//1:直接使用url去获取内容
//			url = new URL("http://localhost:8080/front/toIndex?gen=true");
			
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet  method = new HttpGet("http://192.168.1.106:8080/front/toIndex?gen=true");
			response = client.execute(method);

			HttpEntity entity = response.getEntity();  
			
			System.out.println("entity==="+entity.getContentLength());
			
			bin = entity.getContent();
			
			System.out.println("now begin url===="+bin.available()+" , "+entity.isStreaming());
			byte[] bs = new byte[(int)entity.getContentLength()];

			bin.read(bs);
			//2：
			if(bs!=null && bs.length>1){
				mapHtml.put("toIndex", bs);
			}else{
				System.out.println("no dddddddddddddddddddddddddd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
