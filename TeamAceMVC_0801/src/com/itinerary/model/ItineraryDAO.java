package com.itinerary.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.landscape.model.LandscapeVO;

public class ItineraryDAO implements ItineraryDAO_interface {

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
		"SELECT * FROM Itinerary order by Itinerary_no";
	private static final String INSERT_STMT = 
			"INSERT INTO Itinerary (itinerary_no,mem_no,itinerary_status)"
			+ " VALUES ( 'IT'||LPAD(to_char(ITINERARY_seq.NEXTVAL),5,0),?, ?)";
	private static final String KEY = 
			"select rownum,itinerary_no from (select rownum no,itinerary_no from itinerary) where no=(select count(*)from itinerary)";
	private static final String LASTFOUR = 
			"select * from (select rownum no,itinerary_no,mem_no,itinerary.itinerary_status from itinerary) where no>=(select count(*)from itinerary)-5 order by Itinerary_no DESC";
	
	private static final String GET_BY_MEMID = "SELECT itinerary_no,mem_no,itinerary_status,itinerary_start,itinerary_end from Itinerary where mem_no = ?";
	
	private static final String GET_ONE_STMT = 
			"SELECT * from Itinerary where Itinerary_no = ?";

	
	@Override
	public String insert(ItineraryVO itineraryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		String[] cols = { "itinerary_no" };
		ResultSet rs2=null;
		String key=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itineraryVO.getMem_no());
			pstmt.setString(2, itineraryVO.getItinerary_status());
			pstmt.executeUpdate();
			pstmt.close();
			
			//取得自增值主鍵
//			ResultSet rs = pstmt.getGeneratedKeys();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int columnCount = rsmd.getColumnCount();
//			if (rs.next()) {
//				do {
//					for (int i = 1; i <= columnCount; i++) {
//						key = rs.getString(i);
//					}
//				} while (rs.next());
//			}
			
			pstmt = con.prepareStatement(KEY);
			rs2 = pstmt.executeQuery();
			rs2.next();
			key=rs2.getString("itinerary_no");
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
		return key;
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
	public List<ItineraryVO> getAll() {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryVO.setMem_no(rs.getString("mem_no"));
				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
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
	public List<ItineraryVO> getLastFour() {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LASTFOUR);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryVO.setMem_no(rs.getString("mem_no"));
				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
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
	public List<ItineraryVO> getByMemNo(String mem_no) {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMID);
			
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryVO.setMem_no(rs.getString("mem_no"));
				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getString("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getString("itinerary_end"));
				list.add(itineraryVO);
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
	
	
	public ItineraryVO findByPrimaryKey(String itinerary_no) {

		ItineraryVO itineraryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, itinerary_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
				itineraryVO.setMem_no(rs.getString("mem_no"));
				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
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
		return itineraryVO;
	}
}