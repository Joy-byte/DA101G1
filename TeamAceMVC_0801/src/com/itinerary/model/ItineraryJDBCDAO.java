//package com.itinerary.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ItineraryJDBCDAO implements ItineraryDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
//
//	private static final String GET_ALL_STMT = 
//		"SELECT * FROM itinerary order by itinerary_no";
//	private static final String INSERT_STMT = 
//			"INSERT INTO Itinerary (itinerary_no,mem_no,itinerary_status)"
//			+ " VALUES ( 'IT'||LPAD(to_char(ITINERARY_seq.NEXTVAL),5,0),?, ?)";
//	private static final String KEY = 
//			"select rownum,itinerary_no from (select rownum no,itinerary_no from itinerary) where no=(select count(*)from itinerary)";
//	private static final String LASTFOUR = 
//			"select itinerary_no from (select rownum no,itinerary_no from itinerary) where no>=(select count(*)from itinerary)-3;";
//	private static final String GET_ALL_STMTDESC = 
//			"SELECT * FROM Itinerary order by Itinerary_no DESC";
//	
//	@Override
//	public String insert(ItineraryVO itineraryVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
////		String[] cols = { "itinerary_no" };
//		String key=null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, itineraryVO.getMem_no());
//			pstmt.setString(2, itineraryVO.getItinerary_status());
////			pstmt.setDate(3, itineraryVO.getItinerary_start());
////			pstmt.setDate(4, itineraryVO.getItinerary_end());
//			pstmt.executeUpdate();
//			pstmt.close();
//			
//			//取得自增值主鍵
////			ResultSet rs = pstmt.getGeneratedKeys();
////			ResultSetMetaData rsmd = rs.getMetaData();
////			int columnCount = rsmd.getColumnCount();
////			if (rs.next()) {
////				do {
////					for (int i = 1; i <= columnCount; i++) {
////						key = rs.getString(i);
////					}
////				} while (rs.next());
////			} 
//			pstmt = con.prepareStatement(KEY);
//			ResultSet rs2 = pstmt.executeQuery();
//			rs2.next();
//			key=rs2.getString("itinerary_no");
//			System.out.println(key);
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
//		return key;
//	}
//	
//	@Override
//	public List<ItineraryVO> getAll() {
//		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
//		ItineraryVO itineraryVO = null;
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
//				itineraryVO = new ItineraryVO();
//				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
//				itineraryVO.setMem_no(rs.getString("mem_no"));
//				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
//				list.add(itineraryVO); // Store the row in the list
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
//	public List<ItineraryVO> getAllDesc() {
//		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
//		ItineraryVO itineraryVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			try {
//				Class.forName(driver);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMTDESC);
//			rs = pstmt.executeQuery();
//			
//
//			while (rs.next()) {
//				itineraryVO = new ItineraryVO();
//				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
//				itineraryVO.setMem_no(rs.getString("mem_no"));
//				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
////				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
////				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
//				list.add(itineraryVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
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
//	@Override
//	public List<ItineraryVO> getLastFour() {
//		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
//		ItineraryVO itineraryVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(LASTFOUR);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {				
//				// itineraryVO  Domain objects
//				itineraryVO = new ItineraryVO();
//				itineraryVO.setItinerary_no(rs.getString("itinerary_no"));
//				itineraryVO.setMem_no(rs.getString("mem_no"));
//				itineraryVO.setItinerary_status(rs.getString("itinerary_status"));
//				itineraryVO.setItinerary_start(rs.getDate("itinerary_start"));
//				itineraryVO.setItinerary_end(rs.getDate("itinerary_end"));
//				list.add(itineraryVO); // Store the row in the list
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
//	
//	public static void main(String[] args) {
//		ItineraryJDBCDAO dao = new ItineraryJDBCDAO();
//		List<ItineraryVO> list = dao.getAllDesc();
//		for (ItineraryVO itinerary : list) {
//			System.out.print(itinerary.getItinerary_no() + ",");
//			System.out.print(itinerary.getMem_no() + ",");
//			System.out.print(itinerary.getItinerary_status() + ",");
//			System.out.print(itinerary.getItinerary_start() + ",");
//			System.out.print(itinerary.getItinerary_end() + ",");
//			System.out.println();
//		}
//		
////		ItineraryVO itineraryVO = new ItineraryVO();
////		itineraryVO.setMem_no("MB00002");
////		itineraryVO.setItinerary_status("I1");
////		itineraryVO.setItinerary_start("");
////		itineraryVO.setItinerary_end("Good");
////		dao.insert(itineraryVO);
//		
//	}
//}
