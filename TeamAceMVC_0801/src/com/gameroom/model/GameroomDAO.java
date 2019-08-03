package com.gameroom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.friends.model.FriendsVO;

public class GameroomDAO implements GameroomDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Gameroom order by gameroom_no";


		@Override
		public List<GameroomVO> getAll() {
			List<GameroomVO> list = new ArrayList<GameroomVO>();
			GameroomVO gameroomVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {				
					// conditionVO  Domain objects
					gameroomVO = new GameroomVO();
					gameroomVO.setGameroom_no(rs.getString("gameroom_no"));
					gameroomVO.setRoom_psw(rs.getString("room_psw"));
					gameroomVO.setPsw_status(rs.getString("psw_status"));
					gameroomVO.setBlank_status(rs.getString("blank_status"));
					list.add(gameroomVO); // Store the row in the list
				}
		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

}
