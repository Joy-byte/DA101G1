package com.order_list.model;

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

public class Order_listDAO implements Order_listDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO order_list (order_no,product_no,order_product_pr,order_quan) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_no,product_no,order_product_pr,order_quan FROM order_list order by order_no";
	private static final String GET_ONE_STMT = "SELECT order_no,product_no,order_product_pr,order_quan FROM order_list where order_no = ? AND product_no= ?";
	private static final String GET_BY_ORDER_NO = "SELECT order_no,product_no,order_product_pr,order_quan FROM order_list where order_no = ?";
	private static final String DELETE = "DELETE FROM order_list where order_no = ? AND product_no= ?";
	private static final String UPDATE = "UPDATE order_list set order_product_pr= ?, order_quan= ? where order_no = ? AND product_no= ?";

	//用訂單編號查詢單一訂單明細
	private static final String GET_ONE_ORDER_LIST = 
		"SELECT order_no,product_no,order_product_pr,order_quan FROM order_list where order_no= ? order by product_no";
		
	//用訂單編號查詢單一訂單明細
	private static final String GET_ONE_ORDER_LIST_ONE_MERCHANT = 
		"SELECT order_no,product_no,order_product_pr,order_quan FROM order_list where order_no= ? AND product_no IN (SELECT product_no FROM product WHERE merchant_no=? ) order by product_no";
	// 管理員查詢一個月單一廠商已出貨全部訂單總金額
		private static final String GET_ONE_MONTH_MANEY = 
			"SELECT SUM(order_product_pr) FROM order_list WHERE order_no IN (SELECT order_no FROM order_detail WHERE (order_status='O2' AND to_char(order_time,'mm')=? ))  AND product_no IN (SELECT product_no FROM product WHERE merchant_no=? )";		
	
	
	
	public void insert(Order_listVO order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_listVO.getOrder_no());
			pstmt.setString(2, order_listVO.getProduct_no());
			pstmt.setInt(3, order_listVO.getOrder_product_pr());
			pstmt.setInt(4, order_listVO.getOrder_quan());

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

	public void insertWithOrderDetail(Order_listVO order_listVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, order_listVO.getOrder_no());
			pstmt.setString(2, order_listVO.getProduct_no());
			pstmt.setInt(3, order_listVO.getOrder_product_pr());
			pstmt.setInt(4, order_listVO.getOrder_quan());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Order_list");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

	public void update(Order_listVO order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_listVO.getOrder_product_pr());
			pstmt.setInt(2, order_listVO.getOrder_quan());
			pstmt.setString(3, order_listVO.getOrder_no());
			pstmt.setString(4, order_listVO.getProduct_no());

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

	public void delete(String order_no, String product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);
			pstmt.setString(2, product_no);

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

	public Order_listVO findByPrimaryKey(String order_no, String product_no) {

		Order_listVO order_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);
			pstmt.setString(2, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_listVO = new Order_listVO();
				order_listVO.setOrder_no(rs.getString("order_no"));
				order_listVO.setProduct_no(rs.getString("product_no"));
				order_listVO.setOrder_product_pr(rs.getInt("order_product_pr"));
				order_listVO.setOrder_quan(rs.getInt("order_quan"));

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
		return order_listVO;
	}

	public List<Order_listVO> getAll() {
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		Order_listVO order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_listVO = new Order_listVO();
				order_listVO.setOrder_no(rs.getString("order_no"));
				order_listVO.setProduct_no(rs.getString("product_no"));
				order_listVO.setOrder_product_pr(rs.getInt("order_product_pr"));
				order_listVO.setOrder_quan(rs.getInt("order_quan"));
				list.add(order_listVO);
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
	}

	@Override
	public List<Order_listVO> findByOrder_no(String order_no) {
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		Order_listVO order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ORDER_NO);
			
			pstmt.setString(1, order_no);
			
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {

				order_listVO = new Order_listVO();
				order_listVO.setOrder_no(rs.getString("order_no"));
				order_listVO.setProduct_no(rs.getString("product_no"));
				order_listVO.setOrder_product_pr(rs.getInt("order_product_pr"));
				order_listVO.setOrder_quan(rs.getInt("order_quan"));
				list.add(order_listVO);
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
	}
	
	
	//查詢單一訂單明細
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no) {
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		Order_listVO order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_ORDER_LIST);
					
				pstmt.setString(1, order_no);
					
				rs = pstmt.executeQuery();

				while (rs.next()) {

					order_listVO = new Order_listVO();
					order_listVO.setOrder_no(rs.getString("order_no"));
					order_listVO.setProduct_no(rs.getString("product_no"));
					order_listVO.setOrder_product_pr(rs.getInt("order_product_pr"));
					order_listVO.setOrder_quan(rs.getInt("order_quan"));
					list.add(order_listVO);
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
			
			
			
	//查詢單一訂單明細限單一廠商
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no,String merchant_no) {
		List<Order_listVO> list = new ArrayList<Order_listVO>();
		Order_listVO order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ORDER_LIST_ONE_MERCHANT);
					
			pstmt.setString(1, order_no);
			pstmt.setString(2, merchant_no);
					
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_listVO = new Order_listVO();
				order_listVO.setOrder_no(rs.getString("order_no"));
				order_listVO.setProduct_no(rs.getString("product_no"));
				order_listVO.setOrder_product_pr(rs.getInt("order_product_pr"));
				order_listVO.setOrder_quan(rs.getInt("order_quan"));
				list.add(order_listVO);
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
	
	// 管理員查詢一個月單一廠商已出貨全部訂單總金額
		public Order_listVO getOneMonthOfMerchant(String month,String merchant_no) {
			Order_listVO order_listVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MONTH_MANEY);

				pstmt.setString(1, month);
				pstmt.setString(2, merchant_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					order_listVO = new Order_listVO();
					order_listVO.setOrder_product_pr(rs.getInt("SUM(order_product_pr)"));

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
			return order_listVO;
		}


}