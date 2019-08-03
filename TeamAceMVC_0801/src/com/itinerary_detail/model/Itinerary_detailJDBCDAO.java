//package com.itinerary_detail.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.itinerary.model.ItineraryVO;
//import com.landscape.model.LandscapeVO;
//import com.picture.model.PictureVO;
//
//public class Itinerary_detailJDBCDAO implements Itinerary_detailDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
//
//	private static final String GET_ALL_STMT = 
//		"SELECT * FROM Itinerary_detail order by itinerary_no";
//	private static final String INSERT_STMT = 
//		"INSERT INTO Itinerary_detail (itinerary_no,landscape_no,itinerary_content) VALUES"
//			+ "(  ?, ?, ?)";
//	private static final String LANDS = 
//			"select * from itinerary_detail where itinerary_no=?";
//	
//	@Override
//	public void insert(Itinerary_detailVO itinerary_detailVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, itinerary_detailVO.getItinerary_no());
//			pstmt.setString(2, itinerary_detailVO.getLandscape_no());
//			pstmt.setString(3, itinerary_detailVO.getItinerary_content());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
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
//	
//	@Override
//	public List<Itinerary_detailVO> getAll() {
//		List<Itinerary_detailVO> list = new ArrayList<Itinerary_detailVO>();
//		Itinerary_detailVO itinerary_detailVO = null;
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
//				// itineraryVO  Domain objects
//				itinerary_detailVO = new Itinerary_detailVO();
//				itinerary_detailVO.setItinerary_no(rs.getString("itinerary_no"));
//				itinerary_detailVO.setLandscape_no(rs.getString("landscape_no"));
//				itinerary_detailVO.setItinerary_content(rs.getString("itinerary_content"));
//				list.add(itinerary_detailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//	@Override
//	public List<Itinerary_detailVO> getLas(String itinerary_no) {
//		List<Itinerary_detailVO> list = new ArrayList<Itinerary_detailVO>();
//		Itinerary_detailVO itinerary_detailVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(LANDS);
//
//			pstmt.setString(1, itinerary_no);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {				
//				// itineraryVO  Domain objects
//				itinerary_detailVO = new Itinerary_detailVO();
//				itinerary_detailVO.setItinerary_no(rs.getString("itinerary_no"));
//				itinerary_detailVO.setLandscape_no(rs.getString("landscape_no"));
//				itinerary_detailVO.setItinerary_content(rs.getString("itinerary_content"));
//				list.add(itinerary_detailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//	public static void main(String[] args) {
//		Itinerary_detailJDBCDAO dao = new Itinerary_detailJDBCDAO();
//		List<Itinerary_detailVO> list = dao.getAll();
//		for (Itinerary_detailVO itinerary_detailVO : list) {
//			System.out.print(itinerary_detailVO.getItinerary_no() + ",");
//			System.out.print(itinerary_detailVO.getLandscape_no() + ",");
//			System.out.print(itinerary_detailVO.getItinerary_content() + ",");
//			System.out.println();
//			List<Itinerary_detailVO> list2 = dao.getLas("IT00004");
//			for (Itinerary_detailVO itinerary_detailVO2 : list2) {
////				System.out.print(itinerary_detailVO2.getItinerary_no() + ",");
//				System.out.print(itinerary_detailVO2.getLandscape_no() + ",");
//				System.out.print(itinerary_detailVO2.getItinerary_content() + ",");
//				System.out.println();
//			}
//		}
//		
//		
//		
////		Itinerary_detailVO itinerary_detailVO = new Itinerary_detailVO();
////		itinerary_detailVO.setItinerary_no("IT00001");
////		itinerary_detailVO.setLandscape_no("LS00003");
////		itinerary_detailVO.setItinerary_content("Good");
////		dao.insert(itinerary_detailVO);
//		
//		
//	}
//}
