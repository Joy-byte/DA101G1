package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityJDBCDAO implements AuthorityDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String GET_ALL_STMT = "SELECT * FROM Authority order by features_no";
	private static final String GET_ONE_STMT = "SELECT * FROM Authority where features_no = ?";

	@Override
	public AuthorityVO findByPrimaryKey(String features_no) {

		AuthorityVO AuthorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, features_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				AuthorityVO = new AuthorityVO();
				AuthorityVO.setFeatures_no(rs.getString("features_no"));
				AuthorityVO.setAdministrator_no(rs.getString("Administrator_no"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return AuthorityVO;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO AuthorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				AuthorityVO = new AuthorityVO();
				AuthorityVO.setFeatures_no(rs.getString("features_no"));
				AuthorityVO.setAdministrator_no(rs.getString("Administrator_no"));

				list.add(AuthorityVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();

//		// PK查詢
//		AuthorityVO AuthorityVO1 = dao.findByPrimaryKey("FT00001");
//		System.out.print(AuthorityVO1.getFeatures_no() + ",");
//		System.out.println(AuthorityVO1.getAdministrator_no());
//		System.out.println("---------------------");

		// 全部查詢
		List<AuthorityVO> list = dao.getAll();
		for (AuthorityVO aAuthority : list) {
			System.out.print(aAuthority.getFeatures_no() + ",");
			System.out.println(aAuthority.getFeatures_no());
			System.out.println("---------------------");
		}
	}
}