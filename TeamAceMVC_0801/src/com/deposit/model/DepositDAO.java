package com.deposit.model;

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



public class DepositDAO implements DepositDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = 
			"INSERT INTO deposit ( deposit_no,mem_no,deposit_time,deposit_amo) VALUES ('DE'||LPAD(to_char(deposit_seq.NEXTVAL),5,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT deposit_no,mem_no,to_char(Deposit_time,'yyyy-mm-dd') Deposit_time,deposit_amo FROM deposit order by deposit_no";
	private static final String GET_ONE_STMT = 
			"SELECT deposit_no,mem_no,to_char(Deposit_time,'yyyy-mm-dd') Deposit_time,deposit_amo FROM deposit where mem_no = ?";
	private static final String DELETE = 
			"DELETE FROM deposit where deposit_no = ?";
	private static final String UPDATE = 
			"UPDATE deposit set mem_no=?, deposit_time=?, deposit_amo=? where deposit_no = ?";
	
	
	private static final String GET_ONE_STMT_BY_DEPOSIT = 
			"SELECT deposit_no,mem_no,to_char(Deposit_time,'yyyy-mm-dd') Deposit_time,deposit_amo FROM deposit where deposit_no = ?";
	
	@Override
	public void insert(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setDate(2, depositVO.getDeposit_time());
			pstmt.setInt(3, depositVO.getDeposit_amo());

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
	public void update(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setDate(2, depositVO.getDeposit_time());
			pstmt.setInt(3, depositVO.getDeposit_amo());
			
			pstmt.setString(4, depositVO.getDeposit_no());
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
	public void delete(String deposit_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);


			pstmt.setString(1, deposit_no);

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
	
	//儲值用
	@Override
	public DepositVO findByPrimaryKey(String deposit_no) {
		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement( GET_ONE_STMT_BY_DEPOSIT);

			pstmt.setString(1, deposit_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("deposit_no"));
				depositVO.setMem_no(rs.getString("mem_no"));
				depositVO.setDeposit_time(rs.getDate("deposit_time"));
				depositVO.setDeposit_amo(rs.getInt("deposit_amo"));
				
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
		return depositVO;
	}
	
	
	
	@Override
	public DepositVO findByPrimaryKey(String deposit_no, String mem_no) {
		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, deposit_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("deposit_no"));
				depositVO.setMem_no(rs.getString("mem_no"));
				depositVO.setDeposit_time(rs.getDate("deposit_time"));
				depositVO.setDeposit_amo(rs.getInt("deposit_amo"));
				
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
		return depositVO;
	}
	
	@Override
	public List<DepositVO> getAll() {
		List<DepositVO> list = new ArrayList<DepositVO>();
		DepositVO depositVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				depositVO = new DepositVO();
				
				depositVO.setDeposit_no(rs.getString("deposit_no"));
				depositVO.setMem_no(rs.getString("mem_no"));
				depositVO.setDeposit_time(rs.getDate("deposit_time"));
				depositVO.setDeposit_amo(rs.getInt("deposit_amo"));
				list.add(depositVO); // Store the row in the list
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

	@Override
	public List<DepositVO> findByMemNo(String mem_no) {
		List<DepositVO> list = new ArrayList<DepositVO>();
		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("deposit_no"));
				depositVO.setMem_no(rs.getString("mem_no"));
				depositVO.setDeposit_time(rs.getDate("deposit_time"));
				depositVO.setDeposit_amo(rs.getInt("deposit_amo"));
				list.add(depositVO);
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
}
