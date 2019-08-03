package com.itinerary_report.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Itinerary_reportDAO implements Itinerary_reportDAO_interface {

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
			"SELECT * FROM Itinerary_report order by Irep_no";
		private static final String INSERT_STMT = 
				"INSERT INTO Itinerary_report (irep_no,itinerary_no,irep_detail,irep_status)"
					+ " VALUES ( 'IR'||LPAD(to_char(ITINERARY_REPORT_seq.NEXTVAL),5,0),?,?,?)";
		
	@Override
	public void insert(Itinerary_reportVO itinerary_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itinerary_reportVO.getItinerary_no());
			pstmt.setString(2, itinerary_reportVO.getIrep_detail());
			pstmt.setString(3, itinerary_reportVO.getIrep_status());
			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {				
				// itinerary_reportVO  Domain objects
				itinerary_reportVO = new Itinerary_reportVO();
				itinerary_reportVO.setIrep_no(rs.getString("irep_no"));
				itinerary_reportVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_reportVO.setIrep_detail(rs.getString("irep_detail"));
				itinerary_reportVO.setIrep_status(rs.getString("irep_status"));
				list.add(itinerary_reportVO); // Store the row in the list
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