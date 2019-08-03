package com.product.model;

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

public class ProductDAO implements ProductDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO product (product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan) VALUES ('PR'||LPAD(to_char(product_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan FROM product order by product_no";
	private static final String GET_ONE_STMT = "SELECT product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan FROM product where product_no = ?";
	private static final String DELETE = "DELETE FROM product where product_no = ?";
	private static final String UPDATE = "UPDATE product set merchant_no=?, product_name=?, product_status=?, product_pr=?, product_typ=?, product_pn=?, product_ps=?, product_img=?, product_quan=? where product_no = ?";
	
	// 查詢單一廠商有關商品
	private static final String GET_ONE_MERCHANT = 
		"SELECT product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan FROM product where merchant_no = ?";
	
	//更改單一商品上下架狀態
	private static final String UPDATE_ONE_PRODUCT_STATUS = 
		"UPDATE product set  product_status=? where product_no = ?";
		
	//查詢單一上下架狀態傳回商品list
	private static final String GET_ONE_STATUS_PRODUCT = 
		"SELECT product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan FROM product where product_status = ?";
						
	
	//查詢上下架狀態和廣告狀態傳回商品list
	private static final String GET_TWO_STATUS_PRODUCT = 
		"SELECT product_no,merchant_no,product_name,product_status,product_pr,product_typ,product_pn,product_ps,product_img,product_quan FROM product where product_status = ? AND product_pn=? ";
	
	
	// 更改用單一商品編號更改廣告上下架狀態並ProductVO傳回
	private static final String UPDATE_ONE_PRODUCT_PN = 
		"UPDATE product set  product_pn=? where product_no = ?";
	
	
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getMerchant_no());
			pstmt.setString(2, productVO.getProduct_name());
			pstmt.setString(3, productVO.getProduct_status());
			pstmt.setInt(4, productVO.getProduct_pr());
			pstmt.setString(5, productVO.getProduct_typ());
			pstmt.setString(6, productVO.getProduct_pn());
			pstmt.setString(7, productVO.getProduct_ps());
			pstmt.setBytes(8, productVO.getProduct_img());
			pstmt.setInt(9, productVO.getProduct_quan());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

	};

	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getMerchant_no());
			pstmt.setString(2, productVO.getProduct_name());
			pstmt.setString(3, productVO.getProduct_status());
			pstmt.setInt(4, productVO.getProduct_pr());
			pstmt.setString(5, productVO.getProduct_typ());
			pstmt.setString(6, productVO.getProduct_pn());
			pstmt.setString(7, productVO.getProduct_ps());
			pstmt.setBytes(8, productVO.getProduct_img());	
			pstmt.setInt(9, productVO.getProduct_quan());
			pstmt.setString(10, productVO.getProduct_no());

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

	};

	public void delete(String product_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, product_no);

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

	};

	public ProductVO findByPrimaryKey(String product_No) {
        
	
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setMerchant_no(rs.getString("merchant_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_status(rs.getString("product_status"));
				productVO.setProduct_pr(rs.getInt("product_pr"));
				productVO.setProduct_typ(rs.getString("product_typ"));
				productVO.setProduct_pn(rs.getString("product_pn"));
				productVO.setProduct_ps(rs.getString("product_ps"));
				productVO.setProduct_img(rs.getBytes("product_img"));
				productVO.setProduct_quan(rs.getInt("product_quan"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return productVO;
	}

	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setMerchant_no(rs.getString("merchant_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_status(rs.getString("product_status"));
				productVO.setProduct_pr(rs.getInt("product_pr"));
				productVO.setProduct_typ(rs.getString("product_typ"));
				productVO.setProduct_pn(rs.getString("product_pn"));
				productVO.setProduct_ps(rs.getString("product_ps"));
				productVO.setProduct_img(rs.getBytes("product_img"));
				productVO.setProduct_quan(rs.getInt("product_quan"));
				list.add(productVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	};
	// 查詢單一廠商有關商品
	@Override
	public List<ProductVO> findByMerchantNo(String merchant_no) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MERCHANT);
			
			pstmt.setString(1, merchant_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setMerchant_no(rs.getString("merchant_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_status(rs.getString("product_status"));
				productVO.setProduct_pr(rs.getInt("product_pr"));
				productVO.setProduct_typ(rs.getString("product_typ"));
				productVO.setProduct_pn(rs.getString("product_pn"));
				productVO.setProduct_ps(rs.getString("product_ps"));
				productVO.setProduct_img(rs.getBytes("product_img"));
				productVO.setProduct_img(rs.getBytes("product_quan"));
				list.add(productVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	};
	
	
	// 更改用單一商品編號更改上下架狀態並ProductVO傳回
	@Override
	public void updateProductStatus(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ONE_PRODUCT_STATUS);

			pstmt.setString(1, productVO.getProduct_status());
	
			pstmt.setString(2, productVO.getProduct_no());

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

	};
	
	
	//查詢單一上下架狀態傳回商品
	@Override
	public List<ProductVO> getOneStatusOfAll(String product_status) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STATUS_PRODUCT);
			
			pstmt.setString(1, product_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setMerchant_no(rs.getString("merchant_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_status(rs.getString("product_status"));
				productVO.setProduct_pr(rs.getInt("product_pr"));
				productVO.setProduct_typ(rs.getString("product_typ"));
				productVO.setProduct_pn(rs.getString("product_pn"));
				productVO.setProduct_ps(rs.getString("product_ps"));
				productVO.setProduct_img(rs.getBytes("product_img"));
				productVO.setProduct_img(rs.getBytes("product_quan"));
				list.add(productVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	};
	
	
	
	//查詢上下架狀態和廣告狀態傳回商品list
	@Override
	public List<ProductVO> getOneStatusOfAll(String product_status,String product_pn) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TWO_STATUS_PRODUCT);
			
			pstmt.setString(1, product_status);
			pstmt.setString(2, product_pn);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setMerchant_no(rs.getString("merchant_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_status(rs.getString("product_status"));
				productVO.setProduct_pr(rs.getInt("product_pr"));
				productVO.setProduct_typ(rs.getString("product_typ"));
				productVO.setProduct_pn(rs.getString("product_pn"));
				productVO.setProduct_ps(rs.getString("product_ps"));
				productVO.setProduct_img(rs.getBytes("product_img"));
				productVO.setProduct_img(rs.getBytes("product_quan"));
				list.add(productVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	};
	
	
	// 更改用單一商品編號更改上下架狀態並ProductVO傳回
		@Override
		public void updateProductOfPN(ProductVO productVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_ONE_PRODUCT_PN);

				pstmt.setString(1, productVO.getProduct_pn());

				pstmt.setString(2, productVO.getProduct_no());

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
}