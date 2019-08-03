package com.order_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_listJDBCDAO implements Order_listDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO order_list (order_no,product_no,order_product_pr,order_quan) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_no,product_no,order_product_pr,order_quan FROM order_list order by order_no";
	private static final String GET_ONE_STMT = "SELECT order_no,product_no,order_product_pr,order_quan FROM order_list where order_no = ? AND product_no= ?";
	private static final String DELETE = "DELETE FROM order_list where order_no = ? AND product_no= ?";
	private static final String UPDATE = "UPDATE order_list set order_product_pr= ?, order_quan= ? where order_no = ? AND product_no= ?";

	public void insert(Order_listVO order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_listVO.getOrder_no());
			pstmt.setString(2, order_listVO.getProduct_no());
			pstmt.setInt(3, order_listVO.getOrder_product_pr());
			pstmt.setInt(4, order_listVO.getOrder_quan());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_listVO.getOrder_product_pr());
			pstmt.setInt(2, order_listVO.getOrder_quan());
			pstmt.setString(3, order_listVO.getOrder_no());
			pstmt.setString(4, order_listVO.getProduct_no());

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
	};

	public void delete(String order_no, String product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);
			pstmt.setString(2, product_no);

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

	};

	public Order_listVO findByPrimaryKey(String order_no, String product_no) {

		Order_listVO order_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

	public static void main(String[] args) {

		Order_listJDBCDAO dao = new Order_listJDBCDAO();

		// 新增
//		Order_listVO order_listVO1 = new Order_listVO();
//		order_listVO1.setOrder_no("OR00005");
//		order_listVO1.setProduct_no("PR00002");
//		order_listVO1.setOrder_product_pr(100);
//		order_listVO1.setOrder_quan(10);
//		dao.insert(order_listVO1);
//		System.out.println("新增成功");

		// 修改
//		Order_listVO order_listVO2= new Order_listVO();
//		order_listVO2.setOrder_product_pr(90);
//		order_listVO2.setOrder_quan(9);
//		order_listVO2.setOrder_no("OR00005");
//		order_listVO2.setProduct_no("PR00003");
//		dao.update(order_listVO2);
//		System.out.println("修改成功");

		// 刪除
//		dao.delete("OR00005","PR00002");
//		System.out.println("刪除成功");

//		 查詢(get one)
//		Order_listVO order_listVO03 = dao.findByPrimaryKey("OR00003", "PR00004");
//		System.out.print(order_listVO03.getOrder_no() + ",");
//		System.out.print(order_listVO03.getProduct_no() + ",");
//		System.out.print(order_listVO03.getOrder_product_pr() + ",");
//		System.out.print(order_listVO03.getOrder_quan());

		// 查詢(get all)
//		List<Order_listVO> list = dao.getAll();
//		for (Order_listVO ollist : list) {
//			System.out.print(ollist.getOrder_no() + ",");
//			System.out.print(ollist.getProduct_no() + ",");
//			System.out.print(ollist.getOrder_product_pr() + ",");
//			System.out.print(ollist.getOrder_quan());
//
//		}
	}

	@Override
	public List<Order_listVO> findByOrder_no(String order_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_listVO> findOneOrder_listByOrder_no(String order_no, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order_listVO getOneMonthOfMerchant(String month, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

}