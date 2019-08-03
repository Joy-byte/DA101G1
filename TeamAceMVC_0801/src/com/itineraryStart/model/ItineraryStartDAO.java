package com.itineraryStart.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItineraryStartDAO implements ItineraryStartDAO_interface {

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
		"SELECT * FROM ItineraryStart order by mem_no";
	private static final String INSERT_STMT = 
			"INSERT INTO ItineraryStart (itinerary_no,mem_no)"
			+ " VALUES ( ?,?)";
	private static final String GET_BY_MEMID = "SELECT * from ItineraryStart where mem_no = ?";
	
	
	@Override
	public void  insert(ItineraryStartVO itineraryStartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs2=null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itineraryStartVO.getItinerary_no());
			pstmt.setString(2, itineraryStartVO.getMem_no());
			pstmt.executeUpdate();
			pstmt.close();
			

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs2 != null) {
				try {
					rs2.close();
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
	}

//	@Override
//	public void update(Itinerary_detailVO Itinerary_detailVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, Itinerary_detailVO.getEname());
//			pstmt.setString(2, Itinerary_detailVO.getJob());
//			pstmt.setDate(3, Itinerary_detailVO.getHiredate());
//			pstmt.setDouble(4, Itinerary_detailVO.getSal());
//			pstmt.setDouble(5, Itinerary_detailVO.getComm());
//			pstmt.setInt(6, Itinerary_detailVO.getDeptno());
//			pstmt.setInt(7, Itinerary_detailVO.getEmpno());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public List<ItineraryStartVO> getAll() {
		List<ItineraryStartVO> list = new ArrayList<ItineraryStartVO>();
		ItineraryStartVO itineraryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryStartVO();
				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryVO.setMem_no(rs.getString("mem_no"));
//				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
				list.add(itineraryVO); // Store the row in the list
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
	public List<ItineraryStartVO> getByMemNo(String mem_no) {
		List<ItineraryStartVO> list = new ArrayList<ItineraryStartVO>();
		ItineraryStartVO itineraryStartVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMID);
			
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				itineraryStartVO = new ItineraryStartVO();
				itineraryStartVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryStartVO.setMem_no(rs.getString("mem_no"));
//				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getString("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getString("itinerary_end"));
				list.add(itineraryStartVO);
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
}