package com.merchant.model;

import java.util.*;


import java.sql.*;

public class MerchantJDBCDAO implements MerchantDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G1MER";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO merchant (merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps) VALUES ('ME'||LPAD(to_char(merchant_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant order by merchant_no";
		private static final String GET_ONE_STMT = 
			"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant where merchant_no = ?";
		private static final String DELETE = 
			"DELETE FROM merchant where merchant_no = ?";
		private static final String UPDATE = 
			"UPDATE merchant set  merchant_id=?, merchant_pass=?, merchant_name=?, merchant_pm=?, merchant_add=?, merchant_tel=?, merchant_email=?, merchant_status=?, merchant_ps=? where merchant_no = ?";
		
		
		//輸入帳號登入用,傳回將session加入廠商編號
		private static final String GET_TWO_IDandPASS = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name FROM merchant where merchant_id = ?";
		
		 // 查詢單一廠商用編號並MerchantVO傳回VO
		private static final String GET_ONE_ALLVO = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant where merchant_no = ?";
		
		
		//更改單一廠商審核狀態
		private static final String UPDATE_ONE_STATUS = 
				"UPDATE merchant set  merchant_status=? where merchant_no = ?";
		
		//查詢單一審核狀態傳回廠商
		private static final String GET_ONE_STATUS = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant where merchant_status = ?";
			

	@Override
	public void insert(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, merchantVO.getMerchant_id());
			pstmt.setString(2, merchantVO.getMerchant_pass());
			pstmt.setString(3, merchantVO.getMerchant_name());
			pstmt.setString(4, merchantVO.getMerchant_pm());
			pstmt.setString(5, merchantVO.getMerchant_add());
			pstmt.setString(6, merchantVO.getMerchant_tel());
			pstmt.setString(7, merchantVO.getMerchant_email());
			pstmt.setString(8, merchantVO.getMerchant_status());
			pstmt.setString(9, merchantVO.getMerchant_ps());

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
	public void update(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, merchantVO.getMerchant_id());
			pstmt.setString(2, merchantVO.getMerchant_pass());
			pstmt.setString(3, merchantVO.getMerchant_name());
			pstmt.setString(4, merchantVO.getMerchant_pm());
			pstmt.setString(5, merchantVO.getMerchant_add());
			pstmt.setString(6, merchantVO.getMerchant_tel());
			pstmt.setString(7, merchantVO.getMerchant_email());
			pstmt.setString(8, merchantVO.getMerchant_status());
			pstmt.setString(9, merchantVO.getMerchant_ps());
			
			pstmt.setString(10, merchantVO.getMerchant_no());

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
	public void delete(String merchant_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, merchant_no);

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
	public MerchantVO findByPrimaryKey(String merchant_no) {
		
		MerchantVO merchantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, merchant_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(rs.getString("merchant_no"));
				merchantVO.setMerchant_id(rs.getString("merchant_id"));
				merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
				merchantVO.setMerchant_name(rs.getString("merchant_name"));
				merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
				merchantVO.setMerchant_add(rs.getString("merchant_add"));
				merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
				merchantVO.setMerchant_email(rs.getString("merchant_email"));
				merchantVO.setMerchant_status(rs.getString("merchant_status"));
				merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
				merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
				
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
		return merchantVO;
	}

	@Override
	public List<MerchantVO> getAll() {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO merchantVO = null;

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
				merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(rs.getString("merchant_no"));
				merchantVO.setMerchant_id(rs.getString("merchant_id"));
				merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
				merchantVO.setMerchant_name(rs.getString("merchant_name"));
				merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
				merchantVO.setMerchant_add(rs.getString("merchant_add"));
				merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
				merchantVO.setMerchant_email(rs.getString("merchant_email"));
				merchantVO.setMerchant_status(rs.getString("merchant_status"));
				merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
				merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
				list.add(merchantVO); // Store the row in the list
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
	
	
	@Override
	public MerchantVO findByMerchantId(String merchant_id) {
		
		MerchantVO merchantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_TWO_IDandPASS);

			pstmt.setString(1, merchant_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(rs.getString("merchant_no"));
				merchantVO.setMerchant_id(rs.getString("merchant_id"));
				merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
				merchantVO.setMerchant_name(rs.getString("merchant_name"));
	
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
		return merchantVO;
	}
	
	
	@Override
	public List<MerchantVO> getAllOneMerchantVO(String merchant_no) {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO merchantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ALLVO);

			pstmt.setString(1, merchant_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(rs.getString("merchant_no"));
				merchantVO.setMerchant_id(rs.getString("merchant_id"));
				merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
				merchantVO.setMerchant_name(rs.getString("merchant_name"));
				merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
				merchantVO.setMerchant_add(rs.getString("merchant_add"));
				merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
				merchantVO.setMerchant_email(rs.getString("merchant_email"));
				merchantVO.setMerchant_status(rs.getString("merchant_status"));
				merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
				merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
				list.add(merchantVO); // Store the row in the list
				
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
	
	
	//更改單一廠商狀態
	@Override
	public void updateMerchantStatus(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ONE_STATUS);

			pstmt.setString(1, merchantVO.getMerchant_status());

			pstmt.setString(2, merchantVO.getMerchant_no());

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
	
	//查詢單一審核狀態傳回廠商
	@Override
	public List<MerchantVO> getOneStatusOfAll(String merchant_status) {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO merchantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STATUS);
			
			pstmt.setString(1, merchant_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				merchantVO = new MerchantVO();
				merchantVO.setMerchant_no(rs.getString("merchant_no"));
				merchantVO.setMerchant_id(rs.getString("merchant_id"));
				merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
				merchantVO.setMerchant_name(rs.getString("merchant_name"));
				merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
				merchantVO.setMerchant_add(rs.getString("merchant_add"));
				merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
				merchantVO.setMerchant_email(rs.getString("merchant_email"));
				merchantVO.setMerchant_status(rs.getString("merchant_status"));
				merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
				merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
				list.add(merchantVO); // Store the row in the list
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

	
	
	
	
	public static void main(String[] args) {

		MerchantJDBCDAO dao = new MerchantJDBCDAO();

		// 新增
//		MerchantVO merchantVO1 = new MerchantVO();
//		merchantVO1.setMerchant_id("Kaohsiung2");
//		merchantVO1.setMerchant_pass("Kaohsiung2");
//		merchantVO1.setMerchant_name("2中外餅舖棋餅文創館");
//		merchantVO1.setMerchant_pm("2陳建村");
//		merchantVO1.setMerchant_add("2高雄市左營區蓮潭路60-1號");
//		merchantVO1.setMerchant_tel("2(07)588-6366");
//		merchantVO1.setMerchant_email("2www@gmail.com");
//		merchantVO1.setMerchant_status("A2");
//		merchantVO1.setMerchant_ps("1");
//		
//		dao.insert(merchantVO1);
		
		// 修改
//		MerchantVO merchantVO2 = new MerchantVO();
//	
//		merchantVO2.setMerchant_no("ME00006");
//		merchantVO2.setMerchant_id("3Kaohsiung");
//		merchantVO2.setMerchant_pass("3Kaohsiung");
//		merchantVO2.setMerchant_name("3中外餅舖棋餅文創館");
//		merchantVO2.setMerchant_pm("3陳建村");
//		merchantVO2.setMerchant_add("3高雄市左營區蓮潭路60-1號");
//		merchantVO2.setMerchant_tel("3(07)588-6366");
//		merchantVO2.setMerchant_email("3www@gmail.com");
//		merchantVO2.setMerchant_status("A2");
//		merchantVO2.setMerchant_ps("位在高雄左營蓮池潭附近，日治時代原是全左營地區的鹽");
//		dao.update(merchantVO2);
		
		// 刪除
//		dao.delete("ME00006");
		
		
		
		// 查詢
//		MerchantVO merchantVO3 = dao.findByMerchantId("ME00001");
//		System.out.print(merchantVO3.getMerchant_no() + ",");
//		System.out.print(merchantVO3.getMerchant_id() + ",");
//		System.out.print(merchantVO3.getMerchant_pass() + ",");
//		System.out.print(merchantVO3.getMerchant_name() + ",");
//		System.out.print(merchantVO3.getMerchant_pm() + ",");
//		System.out.print(merchantVO3.getMerchant_add() + ",");
//		System.out.print(merchantVO3.getMerchant_tel() + ",");
//		System.out.print(merchantVO3.getMerchant_email() + ",");
//		System.out.print(merchantVO3.getMerchant_status() + ",");
//		System.out.print(merchantVO3.getMerchant_img() + ",");
		
//		System.out.println("---------------------");
//		
//		MerchantVO merchantVO4 = dao.findByMerchantId("David");
//		System.out.print(merchantVO4.getMerchant_no() + ",");
//		System.out.print(merchantVO4.getMerchant_id() + ",");
//		System.out.print(merchantVO4.getMerchant_pass() + ",");
//		
//		
//		System.out.println("---------------------");
		
		// 查詢
//		List<MerchantVO> list = dao.getAll();
//		for (MerchantVO aMerchant : list) {
//			System.out.print(aMerchant.getMerchant_no() + ",");
//			System.out.print(aMerchant.getMerchant_id() + ",");
//			System.out.print(aMerchant.getMerchant_pass() + ",");
//			System.out.print(aMerchant.getMerchant_name() + ",");
//			System.out.print(aMerchant.getMerchant_pm() + ",");
//			System.out.print(aMerchant.getMerchant_add() + ",");
//			System.out.print(aMerchant.getMerchant_tel() + ",");
//			System.out.print(aMerchant.getMerchant_email() + ",");
//			System.out.print(aMerchant.getMerchant_status() + ",");
//			System.out.print(aMerchant.getMerchant_img() + ",");
//			System.out.println();
//		}
		
		//查編號找全部回傳VO
//		List<MerchantVO> list = dao.getAllOneMerchantVO("ME00003");
//		for (MerchantVO aMerchant : list) {
//			System.out.print(aMerchant.getMerchant_no() + ",");
//			System.out.print(aMerchant.getMerchant_id() + ",");
//			System.out.print(aMerchant.getMerchant_pass() + ",");
//			System.out.print(aMerchant.getMerchant_name() + ",");
//			System.out.print(aMerchant.getMerchant_pm() + ",");
//			System.out.print(aMerchant.getMerchant_add() + ",");
//			System.out.print(aMerchant.getMerchant_tel() + ",");
//			System.out.print(aMerchant.getMerchant_email() + ",");
//			System.out.print(aMerchant.getMerchant_status() + ",");
//			System.out.print(aMerchant.getMerchant_img() + ",");
//			System.out.println();
//		}
		
		
		// 修改單一廠商狀態
//		MerchantVO merchantVO5 = new MerchantVO();
//	
//		merchantVO5.setMerchant_no("ME00011");
//		merchantVO5.setMerchant_status("A2");
//		dao.updateMerchantStatus(merchantVO5);
		
		
		// 查詢單一狀態有關廠商
//		List<MerchantVO> list = dao.getOneStatusOfAll("A2");
//		for (MerchantVO aMerchant : list) {
//			System.out.print(aMerchant.getMerchant_no() + ",");
//			System.out.print(aMerchant.getMerchant_id() + ",");
//			System.out.print(aMerchant.getMerchant_pass() + ",");
//			System.out.print(aMerchant.getMerchant_name() + ",");
//			System.out.print(aMerchant.getMerchant_pm() + ",");
//			System.out.print(aMerchant.getMerchant_add() + ",");
//			System.out.print(aMerchant.getMerchant_tel() + ",");
//			System.out.print(aMerchant.getMerchant_email() + ",");
//			System.out.print(aMerchant.getMerchant_status() + ",");
//			System.out.print(aMerchant.getMerchant_img() + ",");
//			System.out.println();
//		}
		
		
		
	}

}
