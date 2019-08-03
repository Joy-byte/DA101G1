package com.product.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InsertIntoDB {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEAMACE";
	private static final String PASSWORD = "teamace";
	private static final String SQL = "INSERT INTO HOTEL(HOTEL_NO, HOTEL_LNG, HOTEL_LAT, HOTEL_NAME, HOTEL_ADD) VALUES ('HO'||LPAD(to_char(HOTEL_SEQ.NEXTVAL),5,0), ?, ?, ?, ?)";

//	-----------------------------
//	--HOTEL 
//	-----------------------------
// DROP TABLE HOTEL;
// DROP SEQUENCE HOTEL_SEQ;
	
//	CREATE SEQUENCE HOTEL_SEQ
//	MINVALUE 1 
//	INCREMENT BY 1 
//	START WITH 1 
//	NOCACHE  NOORDER  NOCYCLE NOMAXVALUE ;
//
//	CREATE TABLE HOTEL (
//	HOTEL_NO             VARCHAR2(7) NOT NULL,
//	HOTEL_LNG            NUMBER(11,8),
//	HOTEL_LAT            NUMBER(11,8),
//	HOTEL_NAME           VARCHAR2(90),
//	HOTEL_ADD            VARCHAR2(150), 
//	CONSTRAINT "HOTEL_PK" PRIMARY KEY (HOTEL_NO)
//	);

	public static void main(String[] args) throws IOException, JSONException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");

			StringBuilder sb = new StringBuilder();
			FileInputStream fis = new FileInputStream("C:/Users/USER/Desktop/hotel.json");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);

			br.close();
			isr.close();
			fis.close();
            
			String result = sb.toString();
			int j = result.indexOf("{");
			
			pstmt = con.prepareStatement(SQL);
			int rowCount = 0;

			JSONObject jObj = new JSONObject(result.substring(j)).getJSONObject("XML_Head").getJSONObject("Infos");
			JSONArray infos = jObj.getJSONArray("Info");
			for (int i = 0; i < infos.length(); i++) {
				JSONObject jObj2 = infos.getJSONObject(i);
				String name = jObj2.getString("Name");
				String add = jObj2.getString("Add");
				double lat = jObj2.getDouble("Py"); // 緯度
				double lng = jObj2.getDouble("Px"); // 經度
				
				String cityname = add.substring(0,2);
				if(cityname.equals("臺北")) {
				
				pstmt.setDouble(1, lng);
				pstmt.setDouble(2, lat);
				pstmt.setString(3, name);
				pstmt.setString(4, add);
				rowCount = rowCount + pstmt.executeUpdate();
				}
			}

			System.out.println("新增 " + rowCount + " 筆資料");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}
}
