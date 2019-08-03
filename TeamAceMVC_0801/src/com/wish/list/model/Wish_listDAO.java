package com.wish.list.model;

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

public class Wish_listDAO implements Wish_listDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO wish_list (mem_no,product_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_no,product_no FROM wish_list order by mem_no";
	private static final String GET_ONE_STMT = "SELECT mem_no,product_no FROM wish_list where mem_no = ?";
	private static final String IsProductExist = "SELECT mem_no,product_no FROM wish_list where mem_no = ? and product_no = ?";
	private static final String DELETE = "DELETE FROM wish_list where mem_no = ? AND product_no = ?";

	public void insert(Wish_listVO wish_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wish_listVO.getMem_no());
			pstmt.setString(2, wish_listVO.getProduct_no());

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

	public void delete(String mem_no, String product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);
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

	public List<Wish_listVO> findByMemberNo(String mem_no) {
        
		List<Wish_listVO> list = new ArrayList<Wish_listVO>();
		Wish_listVO wish_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				wish_listVO = new Wish_listVO();
				wish_listVO.setMem_no(rs.getString("mem_no"));
				wish_listVO.setProduct_no(rs.getString("product_no"));
				list.add(wish_listVO);
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
	
public List<Wish_listVO> findByPrimaryKey(String mem_no, String product_no) {
        
		List<Wish_listVO> list = new ArrayList<Wish_listVO>();
		Wish_listVO wish_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				wish_listVO = new Wish_listVO();
				wish_listVO.setMem_no(rs.getString("mem_no"));
				wish_listVO.setProduct_no(rs.getString("product_no"));
				list.add(wish_listVO);
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
}
