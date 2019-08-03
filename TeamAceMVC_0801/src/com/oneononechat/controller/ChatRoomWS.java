package com.oneononechat.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

import com.oneononechat.controller.JedisMessageAction;

@ServerEndpoint("/ChatRoomWS/{memNo}")
public class ChatRoomWS {
	
private static final Map<Session,String> allSessions = Collections.synchronizedMap(new HashMap<Session,String>());
	
	@OnOpen
	public void onOpen(@PathParam("memNo") String memNo, Session userSession) throws IOException {
		allSessions.put(userSession, memNo);
		
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(memNo + ": 已連線");
		String message = "{\"action\":\"online\",\"memNo\":\""+memNo+"\"}";
		for (Session session : allSessions.keySet()) {
			if (session.isOpen() && !session.getId().equals(userSession.getId()))
//				synchronized (session){
					session.getAsyncRemote().sendText(message);
//				};	
		}
		System.out.println(message);
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		try {
			JSONObject obj = new JSONObject(message);
			String action = (String)obj.get("action");
			if("getOnline".equals(action)) {
				JSONObject obj2 = new JSONObject();
				JSONArray array2 = new JSONArray();
				for(Session session : allSessions.keySet()) {
					String memNo = allSessions.get(session);
					JSONObject obj3 = new JSONObject();
					obj3.put("memNo", memNo);
					array2.put(obj3.toString());
				}
				obj2.put("action", "getOnline");
				obj2.put("onlineArray", array2);
				if (userSession.isOpen())
//					synchronized (userSession){
						userSession.getAsyncRemote().sendText(obj2.toString());
//					};
				
				System.out.println(obj2.toString());
				System.out.println("Message received: " + message);
				
			}
			if("sendMsg".equals(action)) {
				String receiver = (String)obj.get("receiver");
				String sender = (String)obj.get("sender");
				for (Session session : allSessions.keySet()) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(message);
				}
				JedisMessageAction.saveChatMessage(sender, receiver, message);
				
				System.out.println("Message received: " + message);
			}
			if("changeRoom".equals(action)) {
				String receiver = (String)obj.get("receiver");
				String sender = (String)obj.get("sender");
				List<String> historyMsg = JedisMessageAction.getHistoryMsg(sender, receiver);
				JSONObject historyObj = new JSONObject();
				JSONArray array =  new JSONArray(historyMsg);
				historyObj.put("action", action);
				historyObj.put("historyMsg", array);
				
				if (userSession.isOpen())
					userSession.getAsyncRemote().sendText(historyObj.toString());	
				
				System.out.println("Message received: " + message);
				System.out.println(historyObj.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String memNo = allSessions.get(userSession);
		String message = "{\"action\":\"offline\",\"memNo\":\""+memNo+"\"}";
		for (Session session : allSessions.keySet()) {
			if (session.isOpen()&& !session.getId().equals(userSession.getId()))
				session.getAsyncRemote().sendText(message);
		}
		System.out.println(message);
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
