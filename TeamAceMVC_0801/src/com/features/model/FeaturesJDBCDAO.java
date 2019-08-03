package com.features.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeaturesJDBCDAO implements FeaturesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO features (features_no,features_name) VALUES ('FT'||LPAD(to_char(features_seq.NEXTVAL),5 ,'0'), ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM features order by features_no";
	private static final String GET_ONE_STMT = "SELECT * FROM features where features_no = ?";
	private static final String DELETE = "DELETE FROM features where features_no = ?";
	private static final String UPDATE = "UPDATE features set features_name=? where features_no = ?";

	public void insert(FeaturesVO FeaturesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, FeaturesVO.getFeatures_name());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void update(FeaturesVO FeaturesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, FeaturesVO.getFeatures_no());
			pstmt.setString(2, FeaturesVO.getFeatures_name());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void delete(String features_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, features_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public FeaturesVO findByPrimaryKey(String features_no) {

		FeaturesVO FeaturesVO = null;
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
				FeaturesVO = new FeaturesVO();
				FeaturesVO.setFeatures_no(rs.getString("features_no"));
				FeaturesVO.setFeatures_name(rs.getString("features_name"));
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
		return FeaturesVO;
	}

	public List<FeaturesVO> getAll() {
		List<FeaturesVO> list = new ArrayList<FeaturesVO>();
		FeaturesVO FeaturesVO = null;

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
				FeaturesVO = new FeaturesVO();
				FeaturesVO.setFeatures_no(rs.getString("features_no"));
				FeaturesVO.setFeatures_name(rs.getString("features_name"));

				list.add(FeaturesVO); // Store the row in the list
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

		FeaturesJDBCDAO dao = new FeaturesJDBCDAO();

//		// 新增
//		FeaturesVO FeaturesVO1 = new FeaturesVO();
//		FeaturesVO1.setFeatures_name("");
//		dao.insert(FeaturesVO1);
//
//		// 修改
//		FeaturesVO FeaturesVO2 = new FeaturesVO();
//		FeaturesVO2.setFeatures_no("");
//		FeaturesVO2.setFeatures_name("");
//		dao.update(FeaturesVO2);
//
//		// 刪除
//		dao.delete("");
//
//		// PK查詢
//		FeaturesVO FeaturesVO3 = dao.findByPrimaryKey("");
//		System.out.print(FeaturesVO3.getFeatures_no() + ",");
//		System.out.println(FeaturesVO3.getFeatures_name());
//		System.out.println("---------------------");

		// 全部查詢
		List<FeaturesVO> list = dao.getAll();
		for (FeaturesVO aFeatures : list) {
			System.out.print(aFeatures.getFeatures_no() + ",");
			System.out.println(aFeatures.getFeatures_name());
			System.out.println("---------------------");
		}
	}
}
