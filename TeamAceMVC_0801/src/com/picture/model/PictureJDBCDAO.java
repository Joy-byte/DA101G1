//package com.picture.model;
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
//
//public class PictureJDBCDAO implements PictureDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
//
//	private static final String GET_ALL_STMT = 
//		"SELECT * FROM Picture order by pic_no";
//	private static final String INSERT_STMT = 
//			"INSERT INTO PICTURE (pic_no,itinerary_no,mem_no,landscape_no) VALUES ('PI'||LPAD(to_char(PICTURE_seq.NEXTVAL),5,0),?,?,?)";
//	private static final String GET_ONE_STMT = 
//			"SELECT pic_no,itinerary_no,mem_no,landscape_no,pic from picture where itinerary_no = ? ";
//	
//	@Override
//	public void insert(PictureVO pictureVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, pictureVO.getItinerary_no());
//			pstmt.setString(2, pictureVO.getMem_no());
//			pstmt.setString(3, pictureVO.getLandscape_no());
////			pstmt.setBytes(4, pictureVO.getPic());
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
//	public List<PictureVO> getAll() {
//		List<PictureVO> list = new ArrayList<PictureVO>();
//		PictureVO pictureVO = null;
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
//				// pictureVO  Domain objects
//				pictureVO = new PictureVO();
//				pictureVO.setPic_no(rs.getString("pic_no"));
//				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
//				pictureVO.setMem_no(rs.getString("mem_no"));
//				pictureVO.setLandscape_no(rs.getString("landscape_no"));
//				list.add(pictureVO); // Store the row in the list
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
//	public PictureVO findByPrimaryKey(String itinerary_no) {
//
//		PictureVO pictureVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, itinerary_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				pictureVO = new PictureVO();
//				pictureVO.setPic_no(rs.getString("pic_no"));
//				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
//				pictureVO.setMem_no(rs.getString("mem_no"));
//				pictureVO.setLandscape_no(rs.getString("landscape_no"));
//				pictureVO.setPic(rs.getBytes("pic"));
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
//		return pictureVO;
//	}
//
//	public static void main(String[] args) {
//		PictureJDBCDAO dao = new PictureJDBCDAO();
////		List<PictureVO> list = dao.getAll();
////		for (PictureVO pictureVO : list) {
////			System.out.print(pictureVO.getPic_no() + ",");
////			System.out.print(pictureVO.getItinerary_no() + ",");
////			System.out.print(pictureVO.getMem_no() + ",");
////			System.out.print(pictureVO.getLandscape_no() + ",");
////			System.out.println();
////		}
//		
////		PictureVO pictureVO = new PictureVO();
////		pictureVO.setItinerary_no("IT00002");
////		pictureVO.setMem_no("MB00002");
////		pictureVO.setLandscape_no("LS00002");
////		dao.insert(pictureVO);
//		
//		PictureVO pictureVO = dao.findByPrimaryKey("IT00024");
//		System.out.print(pictureVO.getPic_no() + ",");
//		System.out.print(pictureVO.getItinerary_no() + ",");
//		System.out.print(pictureVO.getMem_no() + ",");
//		System.out.print(pictureVO.getLandscape_no() + ",");
//		System.out.println("---------------------");
//	}
//}
