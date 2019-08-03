package com.picture.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PictureDAO implements PictureDAO_interface {

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
		"SELECT * FROM PICTURE order by Itinerary_no";
	private static final String INSERT_STMT = 
			"INSERT INTO PICTURE (pic_no,itinerary_no,mem_no,landscape_no,pic) VALUES ('PI'||LPAD(to_char(PICTURE_seq.NEXTVAL),5,0),?,?,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT pic_no,itinerary_no,mem_no,landscape_no,pic from picture where itinerary_no = ?";
	private static final String GET_THEONE_STMT = 
			"SELECT pic_no,itinerary_no,mem_no,landscape_no,pic from picture where itinerary_no=? And  landscape_no= ?";
	private static final String GET_BY_MEMID = "SELECT pic_no,itinerary_no,mem_no,landscape_no,pic from picture where mem_no = ?";
	
	@Override
	public void insert(PictureVO pictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pictureVO.getItinerary_no());
			pstmt.setString(2, pictureVO.getMem_no());
			pstmt.setString(3, pictureVO.getLandscape_no());
			pstmt.setBytes(4, pictureVO.getPic());
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
	public List<PictureVO> getAll() {
		List<PictureVO> list = new ArrayList<PictureVO>();
		PictureVO pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pictureVO = new PictureVO();
				pictureVO.setPic_no(rs.getString("pic_no"));
				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
				pictureVO.setMem_no(rs.getString("mem_no"));
				pictureVO.setLandscape_no(rs.getString("landscape_no"));
				list.add(pictureVO); // Store the row in the list
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
	public PictureVO findByPrimaryKey(String itinerary_no) {
		PictureVO pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itinerary_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pictureVO = new PictureVO();
				pictureVO.setPic_no(rs.getString("pic_no"));
				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
				pictureVO.setMem_no(rs.getString("mem_no"));
				pictureVO.setLandscape_no(rs.getString("landscape_no"));
				pictureVO.setPic(rs.getBytes("pic"));
			}

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
		return pictureVO;
	}
	
	
	@Override
	public PictureVO findByItiAndLs(String itinerary_no,String landscape_no) {
		PictureVO pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_THEONE_STMT);

			pstmt.setString(1, itinerary_no);
			pstmt.setString(2, landscape_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pictureVO = new PictureVO();
				pictureVO.setPic_no(rs.getString("pic_no"));
				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
				pictureVO.setMem_no(rs.getString("mem_no"));
				pictureVO.setLandscape_no(rs.getString("landscape_no"));
				pictureVO.setPic(rs.getBytes("pic"));
			}

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
		return pictureVO;
	}
	
	
	@Override
	public List<PictureVO> findByMemId(String mem_no) {
		List<PictureVO> list = new ArrayList<PictureVO>();
		PictureVO pictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMID);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pictureVO = new PictureVO();
				pictureVO.setPic_no(rs.getString("pic_no"));
				pictureVO.setItinerary_no(rs.getString("itinerary_no"));
				pictureVO.setMem_no(rs.getString("mem_no"));
				pictureVO.setLandscape_no(rs.getString("landscape_no"));
				pictureVO.setPic(rs.getBytes("pic"));
				list.add(pictureVO); 
			}

			// Handle any SQL errors
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
	
}