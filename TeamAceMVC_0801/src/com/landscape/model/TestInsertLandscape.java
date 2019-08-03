package com.landscape.model;

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

public class TestInsertLandscape {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEAMACE";
	private static final String PASSWORD = "teamace";
	private static final String SQL = "INSERT INTO LANDSCAPE(LANDSCAPE_NO, LANDSCAPE_LNG, LANDSCAPE_LAT, LANDSCAPE_NAME, LANDSCAPE_STATUS, LANDSCAPE_ADD)"
			+ "VALUES('LS'||LPAD(to_char(LANDSCAPE_seq.NEXTVAL),5,0), ?, ?, ?, ?, ?)";


	public static void main(String[] args) throws IOException, JSONException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");

			StringBuilder sb = new StringBuilder();
			FileInputStream fis = new FileInputStream("d:\\data7.json");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String str;
			while ((str = br.readLine()) != null) {
				
					sb.append(str);
							
			}
				

			br.close();
			isr.close();
			fis.close();

			pstmt = con.prepareStatement(SQL);
//			pstmt.setInt(1, 100001);
			int rowCount = 0;

			JSONObject jObj = new JSONObject(sb.toString()).getJSONObject("XML_Head").getJSONObject("Infos");
			JSONArray infos = jObj.getJSONArray("Info");
			for (int i = 0; i < infos.length(); i++) {
				JSONObject jObj2 = infos.getJSONObject(i);
				String name = jObj2.getString("Name");
				String add = jObj2.getString("Add");
				double lat = jObj2.getDouble("Py"); // 緯度
				double lng = jObj2.getDouble("Px"); // 經度
				if(add!=null && add.length()!=0 && !name.contains("書")) {
					pstmt.setDouble(1, lng);
					pstmt.setDouble(2, lat);
					pstmt.setString(3, name);
					pstmt.setString(4, "L1");
					pstmt.setString(5, add);
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
