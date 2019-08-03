package com.guidereport.model;

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

public class GuideReportDAO implements GuideReportDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO GUIDEREPORT (GUIDEREP_NO,GUIDETOUR_NO,GUIDEREP_REASON,GUIDEREP_STATUS,MEM_NO) VALUES ('GR'||LPAD(to_char(GUIDEREPORT_seq.NEXTVAL),5,0),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GUIDEREPORT order by GUIDEREP_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM GUIDEREPORT where GUIDEREP_NO = ?";
	private static final String DELETE = "DELETE FROM GUIDEREPORT where GUIDEREP_NO = ?";
	private static final String UPDATE = "UPDATE GUIDEREPORT set GUIDETOUR_NO=?, GUIDEREP_REASON=?, GUIDEREP_STATUS=?,MEM_NO=? where GUIDEREP_NO = ?";

	@Override
	public void insert(GuideReportVO guideReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, guideReportVO.getGuideTour_no());
			pstmt.setString(2, guideReportVO.getGuideRep_reason());
			pstmt.setString(3, guideReportVO.getGuideRep_status());
			pstmt.setString(4, guideReportVO.getMem_no());

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
	public void update(GuideReportVO guideReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, guideReportVO.getGuideTour_no());
			pstmt.setString(2, guideReportVO.getGuideRep_reason());
			pstmt.setString(3, guideReportVO.getGuideRep_status());
			pstmt.setString(4, guideReportVO.getMem_no());
			pstmt.setString(5, guideReportVO.getGuideRep_no());

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
	public void delete(String guideRep_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, guideRep_no);

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
	public GuideReportVO findByPrimaryKey(String guideRep_no) {

		GuideReportVO guideReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, guideRep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				guideReportVO = new GuideReportVO();
				guideReportVO.setGuideRep_no(rs.getString("guideRep_no"));
				guideReportVO.setGuideTour_no(rs.getString("guideTour_no"));
				guideReportVO.setGuideRep_reason(rs.getString("guideRep_reason"));
				guideReportVO.setGuideRep_status(rs.getString("guideRep_status"));
				guideReportVO.setMem_no(rs.getString("Mem_no"));
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
		return guideReportVO;
	}

	@Override
	public List<GuideReportVO> getAll() {
		List<GuideReportVO> list = new ArrayList<GuideReportVO>();
		GuideReportVO guideReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				guideReportVO = new GuideReportVO();
				guideReportVO.setGuideRep_no(rs.getString("guideRep_no"));
				guideReportVO.setGuideTour_no(rs.getString("guideTour_no"));
				guideReportVO.setGuideRep_reason(rs.getString("guideRep_reason"));
				guideReportVO.setGuideRep_status(rs.getString("guideRep_status"));
				guideReportVO.setMem_no(rs.getString("Mem_no"));
				list.add(guideReportVO); 
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
