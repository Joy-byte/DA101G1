package com.itinerary_track.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Itinerary_trackDAO implements Itinerary_trackDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "SELECT * FROM itinerary_track order by itinerary_no";
	private static final String GET_ONE_STMT = "SELECT * FROM itinerary_track where itinerary_no = ? ";
	private static final String GET_ONEMEM_STMT = "SELECT * FROM itinerary_track where mem_no = ? ";
	private static final String INSERT_STMT = "INSERT INTO itinerary_track (itinerary_no, mem_no) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM itinerary_track where itinerary_no = ? AND mem_no=?";

	@Override
	public void insert(Itinerary_trackVO itinerary_trackVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itinerary_trackVO.getItinerary_no());
			pstmt.setString(2, itinerary_trackVO.getMem_no());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Itinerary_trackVO> getAll() {
		List<Itinerary_trackVO> list = new ArrayList<Itinerary_trackVO>();
		Itinerary_trackVO itinerary_trackVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// itinerary_trackVO Domain objects
				itinerary_trackVO = new Itinerary_trackVO();
				itinerary_trackVO.setItinerary_no(rs.getString("itinerary_no"));
				itinerary_trackVO.setMem_no(rs.getString("mem_no"));
				list.add(itinerary_trackVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Itinerary_trackVO> findMemberItinerary_track(String itinerary_no, String mem_no) {

		List<Itinerary_trackVO> list = new ArrayList<Itinerary_trackVO>();
		Itinerary_trackVO itinerary_trackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itinerary_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itinerary_trackVO = new Itinerary_trackVO();
				itinerary_trackVO.setMem_no(rs.getString("mem_no"));
				itinerary_trackVO.setItinerary_no(rs.getString("itinerary_no"));
				list.add(itinerary_trackVO);
			}

			// Handle any driver errors
		} catch (

		SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Itinerary_trackVO> findItinerary_trackFormem(String mem_no) {
		List<Itinerary_trackVO> list = new ArrayList<Itinerary_trackVO>();
		Itinerary_trackVO itinerary_trackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEMEM_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itinerary_trackVO = new Itinerary_trackVO();
				itinerary_trackVO.setMem_no(rs.getString("mem_no"));
				itinerary_trackVO.setItinerary_no(rs.getString("itinerary_no"));
				list.add(itinerary_trackVO);
			}

			// Handle any driver errors
		} catch (

		SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String mem_no, String itinerary_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itinerary_no);
			pstmt.setString(2, mem_no);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}