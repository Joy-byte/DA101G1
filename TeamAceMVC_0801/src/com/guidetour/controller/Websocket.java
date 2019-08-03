package com.guidetour.controller;

import java.io.IOException;
import java.util.*;
import org.json.*;

import com.guidetour.model.GuideTourService;
import com.resorder.model.*;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/Websocket/{userName}")
public class Websocket {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("My Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException {
		String event="";
		System.out.println("Message received: " + message);
		for (Session session : connectedSessions) {
			JSONObject obj = new JSONObject(message);
			String guideTour_no = (String)obj.get("guideTour_no");
			String guide_no = (String)obj.get("guide_no");
			String resOrder_date = (String)obj.get("resOrder_date");
			Integer resOrder_peo = Integer.parseInt((String)obj.get("resOrder_peo"));
			String resOrder_joinpeo = (String)obj.get("resOrder_joinpeo");
			//目前不可選的日期Array
			JSONArray dateArray = new JSONArray(obj.get("dateArray").toString());
			//目前是綠色底的日期object(日期：預約人數)
			JSONObject mapGreen = new JSONObject(obj.get("mapGreen").toString());
//			JSONObject msg = new JSONObject();
			//不接受共遊
			if(resOrder_joinpeo == null || "".equals(resOrder_joinpeo)) {
				dateArray.put(resOrder_date);
				//events,dateArray,greenToGray,otherEvents,otherDateArray
				event = "{\"guide_no\":\""+guide_no+"\",\"guideTour_no\":\""+guideTour_no+"\",\"events\":[{\"start\":\""+resOrder_date+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},"
						+"{\"start\":\""+resOrder_date+"\",\"title\":\"已預約  "+resOrder_peo+"  人\",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"}],"
						+"\"dateArray\":"+dateArray+",\"greenToGray\":\"false\","
						+"\"otherEvents\":[{\"start\":\""+resOrder_date+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},"
						+"{\"start\":\""+resOrder_date+"\",\"title\":\"其他行程預約  \",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"}],"
						+"\"otherDateArray\":"+dateArray+"}";
			}else {
//				ResOrderService resOrderSvc = new ResOrderService();
				GuideTourService svc = new GuideTourService();
				Integer maxPeo = svc.getOneGuideTour(guideTour_no).getGuideTour_peo();
				Integer num = resOrder_peo; //計算同天的已預約人數總和
				Iterator greenDate = mapGreen.keys();
				while(greenDate.hasNext()){
					String date = (String)greenDate.next();
					if(date.equals(resOrder_date)) {
						num = num+(Integer)mapGreen.get(resOrder_date);
						mapGreen.put(date, num);
					}
				}
				//已達人數上限
				if(num>=maxPeo) {
					//預約日期加到不可選日期Array
					dateArray.put(resOrder_date);
					//events,dateArray,greenToGray,resOrder_date,otherEvents,otherDateArray
					event = "{\"guide_no\":\""+guide_no+"\",\"guideTour_no\":\""+guideTour_no+"\",\"events\":[{\"start\":\""+resOrder_date+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},"
							+"{\"start\":\""+resOrder_date+"\",\"title\":\"已預約  "+num+"  人\",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"}],"
							+"\"dateArray\":"+dateArray+",\"greenToGray\":\"true\",\"resOrder_date\":\""+resOrder_date+"\",\"mapGreen\":\"null\","
							+"\"otherEvents\":[{\"start\":\""+resOrder_date+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},"
							+"{\"start\":\""+resOrder_date+"\",\"title\":\"其他行程預約  \",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"}],"
							+"\"otherDateArray\":"+dateArray+"}";
				//未達人數上限
				}else {	
					// events,dateArray,greenToGray,resOrder_date,mapGreen,otherEvents,otherDateArray
					mapGreen.put(resOrder_date, num);
					JSONArray otherDateArray = new JSONArray();
					for(int i=0;i<dateArray.length();i++) {
						otherDateArray.put((String)dateArray.get(i));
					}
					otherDateArray.put(resOrder_date); //給同Guide但不同行程的頁面
					event = "{\"guide_no\":\""+guide_no+"\",\"guideTour_no\":\""+guideTour_no+"\",\"events\":[{\"id\":\""+resOrder_date+"bg\",\"start\":\""+resOrder_date+"\",\"rendering\": \"background\"},"
							+"{\"id\":\""+resOrder_date+"event\",\"start\":\""+resOrder_date+"\",\"title\":\"已預約  "+num+"  人\",\"backgroundColor\":\"#b6e2db\",\"borderColor\":\"#b6e2db\"}],"
							+"\"dateArray\":"+dateArray+",\"greenToGray\":\"true\",\"resOrder_date\":\""+resOrder_date+"\",\"mapGreen\":"+mapGreen+","
							+"\"otherEvents\":[{\"start\":\""+resOrder_date+"\",\"rendering\": \"background\",\"color\": \"#bac4ce\"},"
							+"{\"start\":\""+resOrder_date+"\",\"title\":\"其他行程預約  \",\"backgroundColor\":\"#bac4ce\",\"borderColor\":\"#bac4ce\"}],"
							+"\"otherDateArray\":"+otherDateArray+"}";
				}
			}
			
			if (session.isOpen())
				session.getAsyncRemote().sendText(event);
		}
		System.out.println(event);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}

