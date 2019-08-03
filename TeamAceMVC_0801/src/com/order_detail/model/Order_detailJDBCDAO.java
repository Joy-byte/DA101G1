package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.order_list.model.Order_listJDBCDAO;
import com.order_list.model.Order_listVO;

public class Order_detailJDBCDAO implements Order_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO order_detail (order_no,mem_no,merchant_no,order_status,order_amosum,order_time,order_cusadr,order_cusname,order_cusphone) VALUES ('OR'||LPAD(to_char(order_detail_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_no,mem_no,merchant_no,order_status,order_amosum,order_time,order_cusadr,order_cusname,order_cusphone FROM order_detail order by order_no";
	private static final String GET_ONE_STMT = "SELECT * FROM order_detail where order_no = ?";
	private static final String DELETE = "DELETE FROM order_detail where order_no = ?";
	private static final String UPDATE = "UPDATE order_detail set mem_no=?, merchant_no=?, order_status=?, order_amosum=?, order_cusadr=?, order_cusname=?, order_cusphone=? where order_no = ?";

	public void insert(Order_detailVO order_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detailVO.getMem_no());
			pstmt.setString(2, order_detailVO.getMerchant_no());
			pstmt.setString(3, order_detailVO.getOrder_status());
			pstmt.setInt(4, order_detailVO.getOrder_amosum());
			pstmt.setString(5, order_detailVO.getOrder_cusadr());
			pstmt.setString(6, order_detailVO.getOrder_cusname());
			pstmt.setString(7, order_detailVO.getOrder_cusphone());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

	public void insertWithOrder_list(Order_detailVO order_detailVO, List<Order_listVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			String cols[] = { "ORDER_NO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, order_detailVO.getMem_no());
			pstmt.setString(2, order_detailVO.getMerchant_no());
			pstmt.setString(3, order_detailVO.getOrder_status());
			pstmt.setInt(4, order_detailVO.getOrder_amosum());
			pstmt.setString(5, order_detailVO.getOrder_cusadr());
			pstmt.setString(6, order_detailVO.getOrder_cusname());
			pstmt.setString(7, order_detailVO.getOrder_cusphone());
			pstmt.executeUpdate();

			// 掘取對應的自增主鍵值
			String next_order_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_no + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			Order_listJDBCDAO dao = new Order_listJDBCDAO();
			System.out.println("list.size()-A=" + list.size());
			for (Order_listVO aOrderProduct : list) {
				aOrderProduct.setOrder_no(next_order_no);
				dao.insertWithOrderDetail(aOrderProduct, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增訂單編號" + next_order_no + "時,共有訂單明細" + list.size() + "個同時被新增");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void update(Order_detailVO order_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, order_detailVO.getMem_no());
			pstmt.setString(2, order_detailVO.getMerchant_no());
			pstmt.setString(3, order_detailVO.getOrder_status());
			pstmt.setInt(4, order_detailVO.getOrder_amosum());
			pstmt.setString(5, order_detailVO.getOrder_cusadr());
			pstmt.setString(6, order_detailVO.getOrder_cusname());
			pstmt.setString(7, order_detailVO.getOrder_cusphone());
			pstmt.setString(8, order_detailVO.getOrder_no());

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

	public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

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

	public Order_detailVO findByPrimaryKey(String order_no) {

		Order_detailVO order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailVO = new Order_detailVO();
				order_detailVO.setOrder_no(rs.getString("order_no"));
				order_detailVO.setMem_no(rs.getString("mem_no"));
				order_detailVO.setMerchant_no(rs.getString("merchant_no"));
				order_detailVO.setOrder_status(rs.getString("order_status"));
				order_detailVO.setOrder_amosum(rs.getInt("order_amosum"));
				order_detailVO.setOrder_time(rs.getTimestamp("order_time"));
				order_detailVO.setOrder_cusadr(rs.getString("order_cusadr"));
				order_detailVO.setOrder_cusname(rs.getString("order_cusname"));
				order_detailVO.setOrder_cusphone(rs.getString("order_cusphone"));
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
		return order_detailVO;
	}

	public List<Order_detailVO> getAll() {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		Order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailVO = new Order_detailVO();
				order_detailVO.setOrder_no(rs.getString("order_no"));
				order_detailVO.setMem_no(rs.getString("mem_no"));
				order_detailVO.setMerchant_no(rs.getString("merchant_no"));
				order_detailVO.setOrder_status(rs.getString("order_status"));
				order_detailVO.setOrder_amosum(rs.getInt("order_amosum"));
				order_detailVO.setOrder_time(rs.getTimestamp("order_time"));
				order_detailVO.setOrder_cusadr(rs.getString("order_cusadr"));
				order_detailVO.setOrder_cusname(rs.getString("order_cusname"));
				order_detailVO.setOrder_cusphone(rs.getString("order_cusphone"));
				list.add(order_detailVO);
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

		Order_detailJDBCDAO dao = new Order_detailJDBCDAO();

		Order_detailVO order_detailVO = new Order_detailVO();
		order_detailVO.setMem_no("MB00005");
		order_detailVO.setMerchant_no("ME00001");
		order_detailVO.setOrder_status("O4");
		order_detailVO.setOrder_amosum(800);
		order_detailVO.setOrder_cusadr("中央大學");
		order_detailVO.setOrder_cusname("游凱宇");
		order_detailVO.setOrder_cusphone("0900000000");

		List<Order_listVO> testList = new ArrayList<Order_listVO>();
		Order_listVO orderlistxx = new Order_listVO();
		orderlistxx.setProduct_no("PR00001");
		orderlistxx.setOrder_product_pr(60);
		orderlistxx.setOrder_quan(3);

		Order_listVO orderlistyy = new Order_listVO();
		orderlistyy.setProduct_no("PR00002");
		orderlistyy.setOrder_product_pr(60);
		orderlistyy.setOrder_quan(3);

		testList.add(orderlistxx);
		testList.add(orderlistyy);

		dao.insertWithOrder_list(order_detailVO, testList);

		// 新增
//		Order_detailVO order_detailVO1 = new Order_detailVO();
//		order_detailVO1.setMem_no("MB00005");
//		order_detailVO1.setMerchant_no("ME00001");
//		order_detailVO1.setOrder_status("O4");
//		order_detailVO1.setOrder_amosum(800);
//		order_detailVO1.setOrder_cusadr("中央大學");
//		order_detailVO1.setOrder_cusname("游凱宇");
//		order_detailVO1.setOrder_cusphone("0900000000");
//		dao.insert(order_detailVO1);
//		System.out.println("新增成功");

//		 修改
//		Order_detailVO order_detailVO2 = new Order_detailVO();
//		order_detailVO2.setMem_no("MB00005");
//		order_detailVO2.setMerchant_no("ME00001");
//		order_detailVO2.setOrder_status("O1");
//		order_detailVO2.setOrder_amosum(1000);
//		order_detailVO2.setOrder_cusadr("中大湖");
//		order_detailVO2.setOrder_cusname("游凱宇1");
//		order_detailVO2.setOrder_cusphone("0911111111");
//		order_detailVO2.setOrder_no("OR00005");
//		dao.update(order_detailVO2);
//		System.out.println("修改成功");

//		 刪除
//		dao.delete("6");
//		System.out.println("刪除成功");

//		 查詢(get one)
//		Order_detailVO order_detailVO3 = dao.findByPrimaryKey("OR00005");
//		System.out.print(order_detailVO3.getMem_no() + ",");
//		System.out.print(order_detailVO3.getMerchant_no() + ",");
//		System.out.print(order_detailVO3.getOrder_status() + ",");
//		System.out.print(order_detailVO3.getOrder_amosum() + ",");
//		System.out.print(order_detailVO3.getOrder_time() + ",");
//		System.out.print(order_detailVO3.getOrder_cusadr() + ",");
//		System.out.print(order_detailVO3.getOrder_cusname() + ",");
//		System.out.print(order_detailVO3.getOrder_cusphone());

		// 查詢(get all)
//		List<Order_detailVO> list = dao.getAll();
//		for (Order_detailVO odlist : list) {
//			System.out.print(odlist.getOrder_no() + ",");
//			System.out.print(odlist.getMem_no() + ",");
//			System.out.print(odlist.getMerchant_no() + ",");
//			System.out.print(odlist.getOrder_status() + ",");
//			System.out.print(odlist.getOrder_amosum() + ",");
//			System.out.print(odlist.getOrder_time() + ",");
//			System.out.print(odlist.getOrder_cusadr() + ",");
//			System.out.print(odlist.getOrder_cusname() + ",");
//			System.out.print(odlist.getOrder_cusphone());
//
//		}
	}

	@Override
	public List<Order_detailVO> getByMemNo(String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> findOrder_detailByMerchantNo(String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrder_detailStatus(Order_detailVO order_detailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order_detailVO> getOneStatusOrder_detailByMerchantNo(String order_status, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getOneMonthOfOneMerchantNo(String month, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order_detailVO getOneMonthOfMerchant(String month, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getAllOneDayOfMerchantNo(String order_time_start, String order_time_end,
			String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_detailVO> getAll(Map<String, String[]> map, String merchant_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
