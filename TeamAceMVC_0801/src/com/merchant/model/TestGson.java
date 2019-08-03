package com.merchant.model;

import java.util.Random;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestGson {
	
	
	public void sendMail(String to, String subject, String messageText) {
		
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "BBB45678";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	
	
	

	public static void main(String[] args) {
		
		
//		MerchantVO merchantVO1 = new MerchantVO();
//		merchantVO1.setMerchant_id("Kadokawa");
//		merchantVO1.setMerchant_pass("Kadokawa");
//		merchantVO1.setMerchant_name("台灣角川");
//		merchantVO1.setMerchant_pm("藍鳳凰");
//		merchantVO1.setMerchant_add("105台北市松山區光復北路11巷44號");
//		merchantVO1.setMerchant_tel(" (02)27472433");
//		merchantVO1.setMerchant_email("service@kadokawa.com.tw");
//		merchantVO1.setMerchant_status("A1");
//		merchantVO1.setMerchant_ps("簡稱為台角，是日本角川集團在台灣設立的出版公司");
//		
//		Gson gson = new Gson();
//		// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
//		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		
//		String jsonStr = "";
//		
//		// Object to JSON
//		jsonStr = gson.toJson(merchantVO1);
//		System.out.println("Object to JSON: " + jsonStr);
////		// JSON to Object
////		System.out.println("JSON to Object: ");
////		MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);
//		
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.auth("123456");
//		
//		StringBuilder sb = new StringBuilder("merchant:").append(merchantVO1.getMerchant_id());
//		jedis.set(sb.toString(), jsonStr);
//		
//		String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
//		
//		Random random = new Random();
//		StringBuffer stringBuffer = new StringBuffer();
//		
//		for(int i=0;i<=10;i++) {
//			int index = random.nextInt(str.length());
//			char c = str.charAt(index);
//			stringBuffer.append(c);  
//		}
//		
//		String string = stringBuffer.toString();
//
//		jedis.set("codekey", string);
//		System.out.println(jedis.get(sb.toString()));
		
		MerchantVO merchantVO1 = new MerchantVO();
		merchantVO1.setMerchant_id("Kadokawa");
		merchantVO1.setMerchant_pass("Kadokawa");
		merchantVO1.setMerchant_name("台灣角川");
		merchantVO1.setMerchant_pm("藍鳳凰");
		merchantVO1.setMerchant_add("105台北市松山區光復北路11巷44號");
		merchantVO1.setMerchant_tel(" (02)27472433");
		merchantVO1.setMerchant_email("service@kadokawa.com.tw");
		merchantVO1.setMerchant_status("A1");
		merchantVO1.setMerchant_ps("簡稱為台角，是日本角川集團在台灣設立的出版公司");
		
		Gson gson = new Gson();
		// 遇到日期格式資料，在創建gson物件同時也指定日期格式 (Client - Server需一致)
		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		//連線
		String jsonStr = "";
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		
		String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
		
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i=0;i<=10;i++) {
			int index = random.nextInt(str.length());
			char c = str.charAt(index);
			stringBuffer.append(c);  
		}
		
		String string = stringBuffer.toString();
		//暫存密碼
		jedis.set("codekey", string);
		
		
		//寄信
		String to = "singularityqaz@gmail.com";
	      
	    String subject = "密碼通知";
	      
	    String ch_name = merchantVO1.getMerchant_id();
	    String passRandom = string;
	    String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
	       
	    TestGson mailService = new TestGson();
	    mailService.sendMail(to, subject, messageText);
	      
	      
	    //將廠商密碼改成暫時密碼
	    merchantVO1.setMerchant_pass(string);
	    // Object to JSON
	    jsonStr = gson.toJson(merchantVO1);
	    System.out.println("Object to JSON: " + jsonStr);
//		// JSON to Object
//		System.out.println("JSON to Object: ");
//		MerchantVO myMerchant = gson.fromJson(jsonStr, MerchantVO.class);

		StringBuilder sb = new StringBuilder("merchant:").append(merchantVO1.getMerchant_id());
		jedis.set(sb.toString(), jsonStr);
		System.out.println(jedis.get(sb.toString()));
		
		
		
		
		
		jedis.close();

	}

}
