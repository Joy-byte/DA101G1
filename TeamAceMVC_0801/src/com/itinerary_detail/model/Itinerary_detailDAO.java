package com.itinerary_detail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Itinerary_detailDAO implements Itinerary_detailDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO Itinerary_detail (itinerary_no,landscape_no,itinerary_content) VALUES"
				+ "(  ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Itinerary_detail order by Itinerary_no";
	private static final String LANDS = 
			"select * from itinerary_detail where itinerary_no=?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM Itinerary_detail where Itinerary_no = ?";
	@Override
	public void insert(Itinerary_detailVO itinerary_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itinerary_detailVO.getItinerary_no());
			pstmt.setString(2, itinerary_detailVO.getLandscape_no());
			pstmt.setString(3, itinerary_detailVO.getItinerary_content());
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
	public List<Itinerary_detailVO> getAll() {
		List<Itinerary_detailVO> list = new ArrayList<Itinerary_detailVO>();
		Itinerary_detailVO itinerary_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itinerary_detailVO = new Itinerary_detailVO();
				itinerary_detailVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_detailVO.setLandscape_no(rs.getString("landscape_no"));
				itinerary_detailVO.setItinerary_content(rs.getString("itinerary_content"));
				list.add(itinerary_detailVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	@Override
	public List<Itinerary_detailVO> getLas(String itinerary_no) {
		List<Itinerary_detailVO> list = new ArrayList<Itinerary_detailVO>();
		Itinerary_detailVO itinerary_detailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LANDS);
			pstmt.setString(1, itinerary_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				// itineraryVO  Domain objects
				itinerary_detailVO = new Itinerary_detailVO();
				itinerary_detailVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_detailVO.setLandscape_no(rs.getString("landscape_no"));
				itinerary_detailVO.setItinerary_content(rs.getString("itinerary_content"));
				list.add(itinerary_detailVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	@Override
	public Itinerary_detailVO findByPrimaryKey(String itinerary_no) {
		
		Itinerary_detailVO itinerary_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, itinerary_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				itinerary_detailVO = new Itinerary_detailVO();
				itinerary_detailVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_detailVO.setLandscape_no(rs.getString("landscape_no"));
				itinerary_detailVO.setItinerary_content(rs.getString("itinerary_content"));
			}
			
			// Handle any driver errors
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
		return itinerary_detailVO;
	}
}