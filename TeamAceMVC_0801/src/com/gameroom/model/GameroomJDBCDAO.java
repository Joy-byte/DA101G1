package com.gameroom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameroomJDBCDAO implements GameroomDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		GameroomJDBCDAO dao = new GameroomJDBCDAO();
		List<GameroomVO> list = dao.getAll();
		for (GameroomVO gameroomVO : list) {
			System.out.print(gameroomVO.getGameroom_no() + ",");
			System.out.print(gameroomVO.getRoom_psw() + ",");
			System.out.print(gameroomVO.getPsw_status() + ",");
			System.out.print(gameroomVO.getBlank_status() + ",");
			System.out.println();
		}
	}
}
