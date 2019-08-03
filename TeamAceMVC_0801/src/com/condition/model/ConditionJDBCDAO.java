package com.condition.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.game_detail.model.Game_detailVO;

public class ConditionJDBCDAO implements ConditionDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM condition order by condition_no";
	private static final String INSERT_STMT = 
			"INSERT INTO condition (condition_no,itinerary_no,landscape_no,condition_content,condition_day)"
				+ " VALUES ( 'CD'||LPAD(to_char(condition_seq.NEXTVAL),5,0),?,?,?)";
	
	@Override
	public void insert(ConditionVO conditionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, conditionVO.getItinerary_no());
			pstmt.setString(2, conditionVO.getLandscape_no());
			pstmt.setString(3, conditionVO.getCondition_content());
			pstmt.setInt(4, conditionVO.getCondition_day());
			pstmt.executeUpdate();

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

	}

	@Override
	public List<ConditionVO> getAll() {
		List<ConditionVO> list = new ArrayList<ConditionVO>();
		ConditionVO conditionVO = null;

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
				conditionVO = new ConditionVO();
				conditionVO.setCondition_no(rs.getString("condition_no"));
				conditionVO.setItinerary_no(rs.getString("itinerary_no"));
				conditionVO.setLandscape_no(rs.getString("landscape_no"));
				conditionVO.setCondition_content(rs.getString("condition_content"));
				conditionVO.setCondition_day(rs.getInt("condition_day"));
				list.add(conditionVO); // Store the row in the list
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
		ConditionJDBCDAO dao = new ConditionJDBCDAO();
		List<ConditionVO> list = dao.getAll();
		for (ConditionVO conditionVO : list) {
			System.out.print(conditionVO.getCondition_no() + ",");
			System.out.print(conditionVO.getItinerary_no() + ",");
			System.out.print(conditionVO.getLandscape_no() + ",");
			System.out.print(conditionVO.getCondition_content() + ",");
			System.out.print(conditionVO.getCondition_day() + ",");
			System.out.println();
		}
	}
}
