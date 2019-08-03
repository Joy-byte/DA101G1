//package com.itinerary_track.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.itinerary_report.model.Itinerary_reportVO;
//
//public class Itinerary_trackJDBCDAO implements Itinerary_trackDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
//
//	private static final String GET_ALL_STMT = "SELECT * FROM itinerary_track order by itinerary_no";
//	private static final String INSERT_STMT = "INSERT INTO itinerary_track (itinerary_no,mem_no)" + " VALUES ( ?,?)";
//	private static final String GET_ONE_STMT = "SELECT * FROM itinerary_track where mem_no = ? ";
//	private static final String DELETE = "DELETE FROM itinerary_track where itinerary_no = ? AND mem_no=?";
//	@Override
//	public void insert(Itinerary_trackVO itinerary_trackVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, itinerary_trackVO.getItinerary_no());
//			pstmt.setString(2, itinerary_trackVO.getMem_no());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//
//	@Override
//	public List<Itinerary_trackVO> getAll() {
//		List<Itinerary_trackVO> list = new ArrayList<Itinerary_trackVO>();
//		Itinerary_trackVO itinerary_trackVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// itineraryVO Domain objects
//				itinerary_trackVO = new Itinerary_trackVO();
//				itinerary_trackVO.setItinerary_no(rs.getString("itinerary_no"));
//				itinerary_trackVO.setMem_no(rs.getString("mem_no"));
//				list.add(itinerary_trackVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}
//
//
//	@Override
//	public Itinerary_trackVO findMemberItinerary_track(String mem_no) {
//
//		Itinerary_trackVO itinerary_trackVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				itinerary_trackVO = new Itinerary_trackVO();
//				itinerary_trackVO.setMem_no(rs.getString("mem_no"));
//				itinerary_trackVO.setItinerary_no(rs.getString("itinerary_no"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		return itinerary_trackVO;
//	}
//	
//	
//	
//	@Override
//	public void delete(String mem_no, String itinerary_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			try {
//				Class.forName(driver);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, itinerary_no);
//			pstmt.setString(2, mem_no);
//			pstmt.executeUpdate();
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	}
//	
//	public static void main(String[] args) {
//		Itinerary_trackJDBCDAO dao = new Itinerary_trackJDBCDAO();
////		List<Itinerary_trackVO> list = dao.getAll();
////		for (Itinerary_trackVO itinerary_trackVO : list) {
////			System.out.print(itinerary_trackVO.getItinerary_no() + ",");
////			System.out.print(itinerary_trackVO.getMem_no() + ",");
////			System.out.println();
////		}
//		
//		dao.delete("MB00001","IT00020");
//	}
//}