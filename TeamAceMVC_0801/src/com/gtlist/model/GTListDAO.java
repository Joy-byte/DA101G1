package com.gtlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GTListDAO implements GTListDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx;
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO GTLIST (GUIDETOUR_NO,LANDSCAPE_NO,LANDSCAPE_PIC,LANDSCAPE_TRANS,LANDSCAPE_HR,LANDSCAPE_MIN,landscape_order) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GTLIST ORDER BY GUIDETOUR_NO,landscape_order";
	private static final String GET_LIST_BY_GT = "SELECT * FROM GTLIST where GUIDETOUR_NO=? ORDER BY GUIDETOUR_NO,landscape_order";
	private static final String GET_ONE_STMT = "SELECT * FROM GTLIST WHERE GUIDETOUR_NO = ? AND LANDSCAPE_NO= ?";
	private static final String DELETE = "DELETE FROM GTLIST WHERE GUIDETOUR_NO=? AND LANDSCAPE_NO=?";
	private static final String UPDATE = "UPDATE GTLIST SET LANDSCAPE_PIC= ? ,LANDSCAPE_TRANS= ? ,LANDSCAPE_HR= ? ,LANDSCAPE_MIN= ?, landscape_order=? WHERE GUIDETOUR_NO= ? AND LANDSCAPE_NO= ?";

	@Override
	public void insert(GTListVO gtListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gtListVO.getGuideTour_no());
			pstmt.setString(2, gtListVO.getLandscape_no());
			pstmt.setBytes(3, gtListVO.getLandscape_pic());
			pstmt.setString(4, gtListVO.getLandscape_trans());
			pstmt.setInt(5, gtListVO.getLandscape_hr());
			pstmt.setInt(6, gtListVO.getLandscape_min());
			pstmt.setInt(7, gtListVO.getLandscape_order());

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
	public void update(GTListVO gtListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, gtListVO.getLandscape_pic());
			pstmt.setString(2, gtListVO.getLandscape_trans());
			pstmt.setInt(3, gtListVO.getLandscape_hr());
			pstmt.setInt(4, gtListVO.getLandscape_min());
			pstmt.setInt(5, gtListVO.getLandscape_order());
			pstmt.setString(6, gtListVO.getGuideTour_no());
			pstmt.setString(7, gtListVO.getLandscape_no());

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
	public void delete(String guideTour_no, String landscape_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, guideTour_no);
			pstmt.setString(2, landscape_no);

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
	public GTListVO findByPrimaryKey(String guideTour_no, String landscape_no) {

		GTListVO gtListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, guideTour_no);
			pstmt.setString(2, landscape_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtListVO = new GTListVO();
				gtListVO.setGuideTour_no(rs.getString("guideTour_no"));
				gtListVO.setLandscape_no(rs.getString("landscape_no"));
				gtListVO.setLandscape_pic(rs.getBytes("landscape_pic"));
				gtListVO.setLandscape_trans(rs.getString("landscape_trans"));
				gtListVO.setLandscape_hr(rs.getInt("landscape_hr"));
				gtListVO.setLandscape_min(rs.getInt("landscape_min"));
				gtListVO.setLandscape_order(rs.getInt("landscape_order"));
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
		return gtListVO;
	}

	@Override
	public List<GTListVO> findByGuideTourNo(String guideTour_no) {
	//TODO
		List<GTListVO> list = new ArrayList<GTListVO>();
		GTListVO gtListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_BY_GT);

			pstmt.setString(1, guideTour_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtListVO = new GTListVO();
				gtListVO.setGuideTour_no(rs.getString("guideTour_no"));
				gtListVO.setLandscape_no(rs.getString("landscape_no"));
				gtListVO.setLandscape_pic(rs.getBytes("landscape_pic"));
				gtListVO.setLandscape_trans(rs.getString("landscape_trans"));
				gtListVO.setLandscape_hr(rs.getInt("landscape_hr"));
				gtListVO.setLandscape_min(rs.getInt("landscape_min"));
				gtListVO.setLandscape_order(rs.getInt("landscape_order"));
				list.add(gtListVO);
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
	
	@Override
	public List<GTListVO> getAll() {
		List<GTListVO> list = new ArrayList<GTListVO>();
		GTListVO gtListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtListVO = new GTListVO();
				gtListVO.setGuideTour_no(rs.getString("guideTour_no"));
				gtListVO.setLandscape_no(rs.getString("landscape_no"));
				gtListVO.setLandscape_pic(rs.getBytes("landscape_pic"));
				gtListVO.setLandscape_trans(rs.getString("landscape_trans"));
				gtListVO.setLandscape_hr(rs.getInt("landscape_hr"));
				gtListVO.setLandscape_min(rs.getInt("landscape_min"));
				gtListVO.setLandscape_order(rs.getInt("landscape_order"));
				list.add(gtListVO);
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
