package com.video.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class VideoDAO implements VideoDAO_interface {

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
		"SELECT * FROM VIDEO order by vdo_no";
	private static final String INSERT_STMT = 
			"INSERT INTO VIDEO (vdo_no,itinerary_no,mem_no,landscape_no,vdo) VALUES ('VD'||LPAD(to_char(VIDEO_seq.NEXTVAL),5,0),?,?,?,?)";
	private static final String GET_ONE_STMT = 
			"SELECT vdo_no,itinerary_no,mem_no,landscape_no,vdo from VIDEO where itinerary_no = ?";
	private static final String GET_ALL_BYMEM = 
			"SELECT vdo_no,itinerary_no,mem_no,landscape_no,vdo from VIDEO where mem_no = ?";
	
	@Override
	public void insert(VideoVO videoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, videoVO.getItinerary_no());
			pstmt.setString(2, videoVO.getMem_no());
			pstmt.setString(3, videoVO.getLandscape_no());
			pstmt.setBytes(4, videoVO.getVdo());
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
	public List<VideoVO> getAll() {
		List<VideoVO> list = new ArrayList<VideoVO>();
		VideoVO videoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				videoVO = new VideoVO();
				videoVO.setVdo_no(rs.getString("vdo_no"));
				videoVO.setItinerary_no(rs.getString("itinerary_no"));
				videoVO.setMem_no(rs.getString("mem_no"));
				videoVO.setLandscape_no(rs.getString("landscape_no"));
				videoVO.setVdo(rs.getBytes("vdo"));
				list.add(videoVO); // Store the row in the list
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
	public VideoVO findByPrimaryKey(String itinerary_no) {

		VideoVO videoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itinerary_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				videoVO = new VideoVO();
				videoVO.setVdo_no(rs.getString("vdo_no"));
				videoVO.setItinerary_no(rs.getString("itinerary_no"));
				videoVO.setMem_no(rs.getString("mem_no"));
				videoVO.setLandscape_no(rs.getString("landscape_no"));
				videoVO.setVdo(rs.getBytes("vdo"));
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
		return videoVO;
	}
	
	
	@Override
	public List<VideoVO> findAllByMem(String mem_no) {
		List<VideoVO> list = new ArrayList<VideoVO>();
		VideoVO videoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYMEM);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				videoVO = new VideoVO();
				videoVO.setVdo_no(rs.getString("vdo_no"));
				videoVO.setItinerary_no(rs.getString("itinerary_no"));
				videoVO.setMem_no(rs.getString("mem_no"));
				videoVO.setLandscape_no(rs.getString("landscape_no"));
				videoVO.setVdo(rs.getBytes("vdo"));
				list.add(videoVO);
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
		return list;
	}
}