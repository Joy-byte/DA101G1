package com.itinerary_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Itinerary_reportJDBCDAO implements Itinerary_reportDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM Itinerary_report order by Irep_no";
	private static final String INSERT_STMT = 
			"INSERT INTO Itinerary_report (irep_no,itinerary_no,irep_detail,irep_status)"
				+ " VALUES ( 'IR'||LPAD(to_char(ITINERARY_REPORT_seq.NEXTVAL),5,0),?,?,?)";
	
	@Override
	public void insert(Itinerary_reportVO itinerary_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itinerary_reportVO.getItinerary_no());
			pstmt.setString(2, itinerary_reportVO.getIrep_detail());
			pstmt.setString(3, itinerary_reportVO.getIrep_status());

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
	public List<Itinerary_reportVO> getAll() {
		List<Itinerary_reportVO> list = new ArrayList<Itinerary_reportVO>();
		Itinerary_reportVO itinerary_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				// itineraryVO  Domain objects
				itinerary_reportVO = new Itinerary_reportVO();
				itinerary_reportVO.setIrep_no(rs.getString("irep_no"));
				itinerary_reportVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_reportVO.setIrep_detail(rs.getString("irep_detail"));
				itinerary_reportVO.setIrep_status(rs.getString("irep_status"));
				list.add(itinerary_reportVO); // Store the row in the list
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
		Itinerary_reportJDBCDAO dao = new Itinerary_reportJDBCDAO();
		List<Itinerary_reportVO> list = dao.getAll();
		for (Itinerary_reportVO itinerary_reportVO : list) {
			System.out.print(itinerary_reportVO.getIrep_no() + ",");
			System.out.print(itinerary_reportVO.getItinerary_no() + ",");
			System.out.print(itinerary_reportVO.getIrep_detail() + ",");
			System.out.print(itinerary_reportVO.getIrep_status() + ",");
			System.out.println();
		}
	}
}
