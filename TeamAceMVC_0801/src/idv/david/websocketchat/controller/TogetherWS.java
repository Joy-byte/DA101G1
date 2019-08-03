package idv.david.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import idv.david.websocketchatadvweb.model.State;


@ServerEndpoint("/TogetherWS/{userName}")


public class TogetherWS {
//	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private static Map<String, Session> sessionsMap = new LinkedHashMap<>();
	Gson gson = new Gson();
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new LinkedHashSet<>());
//	Map<String,Integer>	votes = new HashMap<>();
//	static int count;
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			session.getAsyncRemote().sendText(stateMessageJson);
		}
		
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);	
		
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		try {
			JSONObject jObj = new JSONObject(message.toString());
//			System.out.println(message);
			if(jObj.has("isStart")) {
				int n = jObj.getString("topic").indexOf(",");
				String goodBoy= jObj.getString("topic").substring(0,n);
				String badBoy= jObj.getString("topic").substring(n+1);
				String message1=message.replace(jObj.getString("topic"), goodBoy);
				String message2=message.replace(jObj.getString("topic"), badBoy);
				message2=message2.replace(jObj.getString("isGood"), "臥底");
//				System.out.println(message1);
				System.out.println(message2);
				Set<String> userNames = sessionsMap.keySet();
				int usersLength = userNames.size();
				int random = (int)(Math.random()*usersLength)+1;
//				System.out.println(random);
				for (Session session : connectedSessions) {
					if (session.isOpen() && usersLength==random) {
						session.getAsyncRemote().sendText(message2);					
					}else {
						session.getAsyncRemote().sendText(message1);
					}
					usersLength--;
				}
			}else if(message.contains("votes")){				
//					jObj.getString("votes");
//					System.out.println(message);
					synchronized(TogetherWS.class) {
//					count++;
//					System.out.println(count);
//					if(count==2) {
						for (Session session : connectedSessions) {
							if (session.isOpen()) {
								session.getAsyncRemote().sendText(message);
							}
						}
					}
//					}
//					count=0;
			}else {
					for (Session session : connectedSessions) {
						if (session.isOpen()) {
								session.getAsyncRemote().sendText(message);
						}
					}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
				
//		System.out.println("Message received: " + message);	
	}
	

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
//		String userNameClose = null;
//		Set<String> userNames = sessionsMap.keySet();
//		for (String userName : userNames) {
//			if (sessionsMap.get(userName).equals(userSession)) {
//				userNameClose = userName;
//				sessionsMap.remove(userName);
//				break;
//			}
//		}
//
//		if (userNameClose != null) {
//			State stateMessage = new State("close", userNameClose, userNames);
//			String stateMessageJson = gson.toJson(stateMessage);
//			Collection<Session> sessions = sessionsMap.values();
//			for (Session session : sessions) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}
//
//		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
}
