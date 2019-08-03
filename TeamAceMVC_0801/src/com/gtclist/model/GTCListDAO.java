package com.gtclist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GTCListDAO implements GTCListDAO_interface{
	
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
	
	private static final String INSERT_STMT = "INSERT INTO GTCLIST (GUIDETOUR_NO,Category) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT GUIDETOUR_NO,Category FROM GTCLIST order by GUIDETOUR_NO";
	private static final String GET_CATEGORY_STMT = "SELECT GUIDETOUR_NO,Category FROM GTCLIST where GUIDETOUR_NO = ?";
	private static final String GET_GTOUR_STMT = "SELECT GUIDETOUR_NO,Category FROM GTCLIST where Category = ?";
	private static final String DELETE = "DELETE FROM GTCLIST where GUIDETOUR_NO = ? and Category = ?";

	@Override
	public void insert(GTCListVO gtcListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gtcListVO.getGuideTour_no());
			pstmt.setString(2, gtcListVO.getCategory());
			
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
	public void delete(String guideTour_no, String Category) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, guideTour_no);
			pstmt.setString(2, Category);

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
	public List<GTCListVO> getAll() {
		List<GTCListVO> list = new ArrayList<GTCListVO>();
		GTCListVO gtcListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtcListVO = new GTCListVO();
				gtcListVO.setGuideTour_no(rs.getString("guideTour_no"));
				gtcListVO.setCategory(rs.getString("Category"));
				list.add(gtcListVO);
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
	public List<GTCListVO> getCategoryByGTno(String guideTour_no) {
		List<GTCListVO> list = new ArrayList<GTCListVO>();
		GTCListVO gtcListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CATEGORY_STMT);
			pstmt.setString(1, guideTour_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtcListVO = new GTCListVO();
				gtcListVO.setGuideTour_no(rs.getString("guidetour_no"));
				gtcListVO.setCategory(rs.getString("Category"));
				list.add(gtcListVO);
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
	public List<GTCListVO> getGuideTourByCno(String Category) {
		List<GTCListVO> list = new ArrayList<GTCListVO>();
		GTCListVO gtcListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GTOUR_STMT);
			pstmt.setString(1, Category);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtcListVO = new GTCListVO();
				gtcListVO.setGuideTour_no(rs.getString("guideTour_no"));
				gtcListVO.setCategory(rs.getString("Category"));
				list.add(gtcListVO);
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
