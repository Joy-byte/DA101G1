//package com.administrator.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdministratorJDBCDAO implements AdministratorDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G1";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO Administrator (Administrator_no,Administrator_id,Administrator_psw,Administrator_name) VALUES ('AM'||LPAD(to_char(Administrator_seq.NEXTVAL),5 ,'0'), ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT * FROM Administrator order by Administrator_no";
//	private static final String GET_ONE_STMT = 
//			"SELECT * FROM Administrator where Administrator_no = ?";
//	private static final String GET_ID = 
//			"SELECT * FROM Administrator where Administrator_id = ?";
//	private static final String DELETE = 
//			"DELETE FROM Administrator where Administrator_no = ?";
//	private static final String UPDATE = 
//			"UPDATE Administrator set Administrator_id=?, Administrator_psw=?, Administrator_name=? where Administrator_no = ?";
//
//	@Override
//	public void insert(AdministratorVO AdministratorVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, AdministratorVO.getAdministrator_id());
//			pstmt.setString(2, AdministratorVO.getAdministrator_psw());
//			pstmt.setString(3, AdministratorVO.getAdministrator_name());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
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
//	}
//
//	public void update(AdministratorVO AdministratorVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, AdministratorVO.getAdministrator_id());
//			pstmt.setString(2, AdministratorVO.getAdministrator_psw());
//			pstmt.setString(3, AdministratorVO.getAdministrator_name());
//			pstmt.setString(4, AdministratorVO.getAdministrator_no());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
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
//	}
//
//	public void delete(String administrator_no) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, administrator_no);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
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
//	}
//
//	public AdministratorVO findID(String administrator_id) {
//
//		AdministratorVO AdministratorVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ID);
//
//			pstmt.setString(1, administrator_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// MemberVO
//				AdministratorVO = new AdministratorVO();
//				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
//				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
//				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
//				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		return AdministratorVO;
//	}
//
//	public AdministratorVO findByPrimaryKey(String administrator_no) {
//
//		AdministratorVO AdministratorVO = null;
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
//			pstmt.setString(1, administrator_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// MemberVO Domain objects
//				AdministratorVO = new AdministratorVO();
//				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
//				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
//				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
//				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		return AdministratorVO;
//	}
//
//	public List<AdministratorVO> getAll() {
//		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
//		AdministratorVO AdministratorVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// MemberVO Domain objects
//				AdministratorVO = new AdministratorVO();
//				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
//				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
//				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
//				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));
//
//				list.add(AdministratorVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		return list;
//	}
//
//	public static void main(String[] args) {
//		AdministratorJDBCDAO dao = new AdministratorJDBCDAO();
//
////		// 新增
////		AdministratorVO AdministratorVO1 = new AdministratorVO();
////		AdministratorVO1.setAdministrator_id("MANAGER");
////		AdministratorVO1.setAdministrator_psw("MANAGER");
////		AdministratorVO1.setAdministrator_name("巨神兵");
////		dao.insert(AdministratorVO1);
////
////		// 修改
////		AdministratorVO AdministratorVO2 = new AdministratorVO();
////		AdministratorVO2.setAdministrator_no("");
////		AdministratorVO2.setAdministrator_name("");
////		dao.update(AdministratorVO2);
////
////		// 刪除
////		dao.delete("AM00005");
////
////		// PK查詢
////		AdministratorVO AdministratorVO3 = dao.findByPrimaryKey("AM00001");
////		System.out.print(AdministratorVO3.getAdministrator_no() + ",");
////		System.out.print(AdministratorVO3.getAdministrator_id() + ",");
////		System.out.print(AdministratorVO3.getAdministrator_psw() + ",");
////		System.out.println(AdministratorVO3.getAdministrator_name());
////		System.out.println("---------------------");
////
////		// id查詢
////		AdministratorVO AdministratorVO4 = dao.findID("666666");
////		System.out.print(AdministratorVO4.getAdministrator_no() + ",");
////		System.out.print(AdministratorVO4.getAdministrator_id() + ",");
////		System.out.print(AdministratorVO4.getAdministrator_psw() + ",");
////		System.out.println(AdministratorVO4.getAdministrator_name());
////		System.out.println("---------------------");
////
//		// 全部查詢
//		List<AdministratorVO> list = dao.getAll();
//		for (AdministratorVO aAdministrator : list) {
//			System.out.print(aAdministrator.getAdministrator_no() + ",");
//			System.out.print(aAdministrator.getAdministrator_id() + ",");
//			System.out.print(aAdministrator.getAdministrator_psw() + ",");
//			System.out.println(aAdministrator.getAdministrator_name());
//			System.out.println("---------------------");
//		}
//	}
//}
