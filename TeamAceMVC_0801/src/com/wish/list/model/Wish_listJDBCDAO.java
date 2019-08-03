package com.wish.list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Wish_listJDBCDAO implements Wish_listDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO wish_list (mem_no,product_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_no,product_no FROM wish_list order by mem_no";
	private static final String GET_ONE_STMT = "SELECT mem_no,product_no FROM wish_list where mem_no = ?";
	private static final String IsProductExist = "SELECT mem_no,product_no FROM wish_list where mem_no = ? AND product_no = ?";
	private static final String DELETE = "DELETE FROM wish_list where mem_no = ? AND product_no = ?";

	public void insert(Wish_listVO wish_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wish_listVO.getMem_no());
			pstmt.setString(2, wish_listVO.getProduct_no());

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

	public void delete(String mem_no, String product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);
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

	public List<Wish_listVO> findByMemberNo(String mem_no) {
		List<Wish_listVO> list = new ArrayList<Wish_listVO>();
		Wish_listVO wish_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(IsProductExist);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				wish_listVO = new Wish_listVO();
				wish_listVO.setMem_no(rs.getString("mem_no"));
				wish_listVO.setProduct_no(rs.getString("product_no"));
				list.add(wish_listVO);
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
	}
	
	public List<Wish_listVO> findByPrimaryKey(String mem_no, String product_no) {
		List<Wish_listVO> list = new ArrayList<Wish_listVO>();
		Wish_listVO wish_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(IsProductExist);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, product_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wish_listVO = new Wish_listVO();
				wish_listVO.setMem_no(rs.getString("mem_no"));
				wish_listVO.setProduct_no(rs.getString("product_no"));
				list.add(wish_listVO);
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
	}

	public List<Wish_listVO> getAll() {
		List<Wish_listVO> list = new ArrayList<Wish_listVO>();
		Wish_listVO wish_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				wish_listVO = new Wish_listVO();
				wish_listVO.setMem_no(rs.getString("mem_no"));
				wish_listVO.setProduct_no(rs.getString("product_no"));
				list.add(wish_listVO);
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

		Wish_listJDBCDAO dao = new Wish_listJDBCDAO();

		// 新增
//		Wish_listVO wish_listVO1 = new Wish_listVO();
//		wish_listVO1.setMem_no("MB00002");
//		wish_listVO1.setProduct_no("PR00005");
//		dao.insert(wish_listVO1);
//		System.out.println("新增成功");

		// 刪除
//		dao.delete("MB00002","PR00005");
//		System.out.println("刪除成功");

		 //查詢(get one)
		List<Wish_listVO> list = dao.findByMemberNo("MB00001");
		for (Wish_listVO wlist : list) {
		System.out.print(wlist.getMem_no() + ",");
		System.out.print(wlist.getProduct_no());
		}

		// 查詢(get all)
//		List<Wish_listVO> list = dao.getAll();
//		for (Wish_listVO wlist : list) {
//			System.out.print(wlist.getMem_no() + ",");
//			System.out.print(wlist.getProduct_no()+'\n');
//		}
	}

}
