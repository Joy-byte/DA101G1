package com.productreport.model;

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

public class ProductreportJNDIDAO implements ProductreportDAO_interface {

	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			pstmt.setString(2, productreportVO.getReportcon_no());
			pstmt.setString(3, productreportVO.getReportcon_status());

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
	public void update(ProductreportVO productreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			pstmt.setString(2, productreportVO.getReportcon_no());
			pstmt.setString(3, productreportVO.getReportcon_status());
			
			pstmt.setString(4, productreportVO.getProductreport_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, productreport_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ONE_PRODUCTREPORT_STATUS);
			
			pstmt.setString(1, productreportVO.getProduct_no());
			
			pstmt.setString(2, productreportVO.getReportcon_status());
			
			pstmt.setString(3, productreportVO.getProductreport_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	
	
	
	
}
