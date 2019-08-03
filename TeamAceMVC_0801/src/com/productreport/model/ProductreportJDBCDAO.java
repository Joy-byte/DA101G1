package com.productreport.model;

import java.util.*;
import java.sql.*;

public class ProductreportJDBCDAO implements ProductreportDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G1MER";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO productreport (productreport_no,product_no,reportcon_no,reportcon_status) VALUES ('PR'||LPAD(to_char(productreport_seq.NEXTVAL),5,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT productreport_no,product_no,reportcon_no,reportcon_status FROM productreport order by productreport_no";
	private static final String GET_ONE_STMT = 
			"SELECT productreport_no,product_no,reportcon_no,reportcon_status FROM productreport where productreport_no = ?";
	private static final String DELETE = 
			"DELETE FROM productreport where productreport_no = ?";
	private static final String UPDATE = 
			"UPDATE productreport set product_no=?, reportcon_no=?, reportcon_status=? where productreport_no = ?";
	
	// 更改用單一檢舉編號更改審核狀態並ProductreportVO傳回
	private static final String UPDATE_ONE_PRODUCTREPORT_STATUS = 
			"UPDATE productreport set product_no=?, reportcon_status=? where productreport_no = ?";

	@Override
	public void insert(ProductreportVO productreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			pstmt.setString(2, productreportVO.getReportcon_no());
			pstmt.setString(3, productreportVO.getReportcon_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(ProductreportVO productreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			pstmt.setString(2, productreportVO.getReportcon_no());
			pstmt.setString(3, productreportVO.getReportcon_status());
			
			pstmt.setString(4, productreportVO.getProductreport_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String productreport_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, productreport_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public ProductreportVO findByPrimaryKey(String productreport_no) {
		ProductreportVO productreportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, productreport_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productreportVO = new ProductreportVO();
				productreportVO.setProductreport_no(rs.getString("productreport_no"));
				productreportVO.setProduct_no(rs.getString("product_no"));
				productreportVO.setReportcon_no(rs.getString("reportcon_no"));
				productreportVO.setReportcon_status(rs.getString("reportcon_status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return productreportVO;
	}

	@Override
	public List<ProductreportVO> getAll() {
		List<ProductreportVO> list = new ArrayList<ProductreportVO>();
		ProductreportVO productreportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productreportVO = new ProductreportVO();
				
				productreportVO.setProductreport_no(rs.getString("productreport_no"));
				productreportVO.setProduct_no(rs.getString("product_no"));
				productreportVO.setReportcon_no(rs.getString("reportcon_no"));
				productreportVO.setReportcon_status(rs.getString("reportcon_status"));
				list.add(productreportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	
	// 更改用單一檢舉編號更改審核狀態並ProductreportVO傳回
	@Override
	public void updateProductreportStatus(ProductreportVO productreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ONE_PRODUCTREPORT_STATUS);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			
			pstmt.setString(2, productreportVO.getReportcon_status());
			
			pstmt.setString(3, productreportVO.getProductreport_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {

		ProductreportJDBCDAO dao = new ProductreportJDBCDAO();

		// 新增
//		ProductreportVO productreportVO1 = new ProductreportVO();
//		productreportVO1.setProduct_no("PR00004");
//		productreportVO1.setReportcon_no("4檢舉說明敘述在這裡");
//		productreportVO1.setReportcon_status("PR2");
//		dao.insert(productreportVO1);
		
		// 修改
//		ProductreportVO productreportVO2 = new ProductreportVO();		
//		productreportVO2.setProductreport_no("PR00004");
//		productreportVO2.setProduct_no("PR00004");
//		productreportVO2.setReportcon_no("5檢舉說明敘述在這裡");
//		productreportVO2.setReportcon_status("PR2");
//		dao.update(productreportVO2);
		
		// 刪除
//		dao.delete("PR00004");
		
		
		
		// 查詢
//		ProductreportVO productreportVO3 = dao.findByPrimaryKey("PR00003");
//		System.out.print(productreportVO3.getProductreport_no() + ",");
//		System.out.print(productreportVO3.getProduct_no() + ",");
//		System.out.print(productreportVO3.getReportcon_no() + ",");
//		System.out.print(productreportVO3.getReportcon_status() + ",");
//		System.out.println("---------------------");
		
		// 查詢
//		List<ProductreportVO> list = dao.getAll();
//		for (ProductreportVO aProductreport : list) {
//			System.out.print(aProductreport.getProductreport_no() + ",");
//			System.out.print(aProductreport.getProduct_no() + ",");
//			System.out.print(aProductreport.getReportcon_no() + ",");
//			System.out.print(aProductreport.getReportcon_status() + ",");
//			System.out.println();
//		}
		
		
		// 修改單一檢舉狀態
		ProductreportVO productreportVO2 = new ProductreportVO();		
		productreportVO2.setProductreport_no("PR00005");
		productreportVO2.setProduct_no("PR00007");
		productreportVO2.setReportcon_status("PR1");
		dao.updateProductreportStatus(productreportVO2);
	}

}
