package com.deposit.model;

import java.util.*;

import java.sql.*;

public class DepositJDBCDAO implements DepositDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";
	
	private static final String INSERT_STMT = 
			"INSERT INTO deposit ( deposit_no,mem_no,deposit_time,deposit_amo) VALUES ('DE'||LPAD(to_char(deposit_seq.NEXTVAL),5,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT deposit_no,mem_no,to_char(Deposit_time,'yyyy-mm-dd') Deposit_time,deposit_amo FROM deposit order by deposit_no";
	private static final String GET_ONE_STMT = 
			"SELECT deposit_no,mem_no,to_char(Deposit_time,'yyyy-mm-dd') Deposit_time,deposit_amo FROM deposit where where mem_no = ?";
	private static final String DELETE = 
			"DELETE FROM deposit where deposit_no = ?";
	private static final String UPDATE = 
			"UPDATE deposit set mem_no=?, deposit_time=?, deposit_amo=? where deposit_no = ?";
	
	@Override
	public void insert(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setDate(2, depositVO.getDeposit_time());
			pstmt.setInt(3, depositVO.getDeposit_amo());

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
	public void update(DepositVO depositVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setDate(2, depositVO.getDeposit_time());
			pstmt.setInt(3, depositVO.getDeposit_amo());
			
			pstmt.setString(4, depositVO.getDeposit_no());
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
	public void delete(String deposit_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, deposit_no);

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
	public DepositVO findByPrimaryKey(String deposit_no, String mem_no) {
		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<DepositVO> findByMemNo(String mem_no) {
		List<DepositVO> list = new ArrayList<DepositVO>();
		
//		DepositVO depositVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, mem_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo �]�٬� Domain objects
//				depositVO = new DepositVO();
//				depositVO.setDeposit_no(rs.getString("deposit_no"));
//				depositVO.setMem_no(rs.getString("mem_no"));
//				depositVO.setDeposit_time(rs.getDate("deposit_time"));
//				depositVO.setDeposit_amo(rs.getInt("deposit_amo"));
//				
//			}
//
//			// Handle any driver errors
//		} catch (SQLException | ClassNotFoundException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
		return list;
	}
	
	
	public static void main(String[] args) {

		DepositJDBCDAO dao = new DepositJDBCDAO();

		// �s�W
//		DepositVO depositVO1 = new DepositVO();
//		depositVO1.setMem_no("MB00004");
//		depositVO1.setDeposit_time(java.sql.Date.valueOf("2019-06-19"));
//		depositVO1.setDeposit_amo(18000);
//		dao.insert(depositVO1);
		
		// �ק�
//		DepositVO depositVO2 = new DepositVO();		
//		depositVO2.setDeposit_no("DE00005");
//		depositVO2.setMem_no("MB00004");
//		depositVO2.setDeposit_time(java.sql.Date.valueOf("2019-06-21"));
//		depositVO2.setDeposit_amo(20);
//		dao.update(depositVO2);
		
		// �R��
		dao.delete("DE00005");
		
		
		
		// �d��
//		DepositVO depositVO3 = dao.findByPrimaryKey("DE00001");
//		System.out.print(depositVO3.getDeposit_no() + ",");
//		System.out.print(depositVO3.getMem_no() + ",");
//		System.out.print(depositVO3.getDeposit_time() + ",");
//		System.out.print(depositVO3.getDeposit_amo() + ",");
//		System.out.println("---------------------");
		
		// �d��
		List<DepositVO> list = dao.getAll();
		for (DepositVO aDeposit : list) {
			System.out.print(aDeposit.getDeposit_no() + ",");
			System.out.print(aDeposit.getMem_no() + ",");
			System.out.print(aDeposit.getDeposit_time() + ",");
			System.out.print(aDeposit.getDeposit_amo() + ",");
			System.out.println();
		}
	}

	@Override
	public DepositVO findByPrimaryKey(String deposit_no) {
		// TODO Auto-generated method stub
		return null;
	}


}
