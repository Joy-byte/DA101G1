package com.gtcategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GTCategoryDAO implements GTCategoryDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO GTCATEGORY (CATEGORY_NO,CATEGORY_NAME) VALUES ('GC'||LPAD(to_char(GTCATEGORY_seq.NEXTVAL),5,0), ?)";
	private static final String GET_ALL_STMT = "SELECT CATEGORY_NO,CATEGORY_NAME FROM GTCATEGORY order by CATEGORY_NO";
	private static final String GET_ONE_STMT = "SELECT CATEGORY_NO,CATEGORY_NAME FROM GTCATEGORY where CATEGORY_NO = ?";
	private static final String DELETE = "DELETE FROM GTCATEGORY where CATEGORY_NO = ?";
	private static final String UPDATE = "UPDATE GTCATEGORY set CATEGORY_NAME= ? where CATEGORY_NO = ?";

	@Override
	public void insert(GTCategoryVO gtCategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gtCategoryVO.getCategory_name());

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

	@Override
	public void update(GTCategoryVO gtCategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gtCategoryVO.getCategory_name());
			pstmt.setString(2, gtCategoryVO.getCategory_no());

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

	@Override
	public void delete(String category_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, category_no);

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

	@Override
	public GTCategoryVO findByPrimaryKey(String category_no) {

		GTCategoryVO gtCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtCategoryVO = new GTCategoryVO();
				gtCategoryVO.setCategory_no(rs.getString("category_no"));
				gtCategoryVO.setCategory_name(rs.getString("category_name"));
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
		return gtCategoryVO;
	}

	@Override
	public List<GTCategoryVO> getAll() {
		List<GTCategoryVO> list = new ArrayList<GTCategoryVO>();
		GTCategoryVO gtCategoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gtCategoryVO = new GTCategoryVO();
				gtCategoryVO.setCategory_no(rs.getString("category_no"));
				gtCategoryVO.setCategory_name(rs.getString("category_name"));
				list.add(gtCategoryVO); // Store the row in the list
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

	// test
	public static void main(String[] args) {
		GTCategoryDAO dao = new GTCategoryDAO();
		
		// 新增
//		GTCategoryVO gtcVO = new GTCategoryVO();
//		gtcVO.setCategory_name("健行");
//		dao.insert(gtcVO);

		// 修改
//		GTCategoryVO gtcVO2 = new GTCategoryVO();
//		gtcVO2.setCategory_no("GC00004");
//		gtcVO2.setCategory_name("娛樂");
//		dao.update(gtcVO2);
		
		// 刪除
//		dao.delete("GC00006");
			
		// 查詢
//		GTCategoryVO gtcVO3 = dao.findByPrimaryKey("GC00002");
//		System.out.print(gtcVO3.getCategory_no() + ",");
//		System.out.print(gtcVO3.getCategory_name());
//		System.out.println();

		 //查詢
		List<GTCategoryVO> list = dao.getAll();
		for (GTCategoryVO gtcVO4 : list) {
			System.out.print(gtcVO4.getCategory_no() + ",");
			System.out.print(gtcVO4.getCategory_name());
			System.out.println();
		}
	}
}
