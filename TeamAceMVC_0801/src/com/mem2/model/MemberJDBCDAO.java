package com.mem2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCDAO implements MemberDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = 
		"INSERT INTO Member (Member_NO,Member_NAME,Member_ID,Member_PSW,Member_PIC) VALUES (Member_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT Member_NO,Member_NAME,Member_ID,Member_PSW FROM Member order by Member_NO";
	private static final String GET_ONE_STMT = 
		"SELECT Member_NO,Member_NAME,Member_ID,Member_PSW FROM Member where Member_NO = ?";
	private static final String GET_ID = 
		"SELECT Member_NAME,Member_ID,Member_PSW FROM Member where Member_ID = ?";
//	private static final String DELETE = 
//		"DELETE FROM Member2 where Member_NO = ?";
//	private static final String UPDATE = 
//		"UPDATE Member2 set Member_NAME=?, Member_ID=?, Member_PSW=?, sal=?, comm=?, deptno=? where Member_NO = ?";

	@Override
	public void insert(MemberVO MemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, MemberVO.getMember_NAME());
			pstmt.setString(2, MemberVO.getMember_ID());
			pstmt.setString(3, MemberVO.getMember_PSW());
			pstmt.setBytes(4, MemberVO.getMember_PIC());
//			pstmt.setBinaryStream(4, MemberVO.getMember_PIC());
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

//	@Override
//	public void update(MemberVO MemberVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, MemberVO.getMember_NAME());
//			pstmt.setString(2, MemberVO.getMember_ID());
//			pstmt.setString(3, MemberVO.getMember_PSW());
//			pstmt.setInt(7, MemberVO.getMember_NO());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//
//	}

//	@Override
//	public void delete(Integer Member_NO) {
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
//			pstmt.setInt(1, Member_NO);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//
//	}
	
	@Override  
	public MemberVO findID(String Member_ID) {

		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ID);

			pstmt.setString(1, Member_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO 
				MemberVO = new MemberVO();
				MemberVO.setMember_NAME(rs.getString("Member_NAME"));
				MemberVO.setMember_ID(rs.getString("Member_ID"));
				MemberVO.setMember_PSW(rs.getString("Member_PSW"));
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
		return MemberVO;
	}
//	

	@Override
	public MemberVO findByPrimaryKey(Integer Member_NO) {

		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, Member_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO  Domain objects
				MemberVO = new MemberVO();
				MemberVO.setMember_NO(rs.getInt("Member_NO"));
				MemberVO.setMember_NAME(rs.getString("Member_NAME"));
				MemberVO.setMember_ID(rs.getString("Member_ID"));
				MemberVO.setMember_PSW(rs.getString("Member_PSW"));
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
		return MemberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO  Domain objects
				MemberVO = new MemberVO();
				MemberVO.setMember_NO(rs.getInt("Member_NO"));
				MemberVO.setMember_NAME(rs.getString("Member_NAME"));
				MemberVO.setMember_ID(rs.getString("Member_ID"));
				MemberVO.setMember_PSW(rs.getString("Member_PSW"));
				list.add(MemberVO); // Store the row in the list
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

		MemberJDBCDAO dao = new MemberJDBCDAO();
//		MemberVO MemberVO4 = dao.findID("qazwsx");
//		System.out.print(MemberVO4.getMember_NAME() + ",");
//		System.out.print(MemberVO4.getMember_ID() + ",");
//		System.out.println(MemberVO4.getMember_PSW() + ",");
//		System.out.println("---------------------");
		
		MemberVO MemberVO1 = new MemberVO();
		MemberVO1.setMember_NAME("Misjj");
		MemberVO1.setMember_ID("MANAGER");
		MemberVO1.setMember_PSW("MANAGER");
		dao.insert(MemberVO1);
//
//
//		MemberVO MemberVO2 = new MemberVO();
//		MemberVO2.setMember_NO(80001);
//		MemberVO2.setMember_NAME("MisJ2");
//		dao.update(MemberVO2);
//
//		
//		dao.delete(7014);

		// 
//		MemberVO MemberVO3 = dao.findByPrimaryKey(80001);
//		System.out.print(MemberVO3.getMember_NO() + ",");
//		System.out.print(MemberVO3.getMember_NAME() + ",");
//		System.out.print(MemberVO3.getMember_ID() + ",");
//		System.out.println(MemberVO3.getMember_PSW() + ",");
//		System.out.println("---------------------");
		
		
		

		// 
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aMember : list) {
//			System.out.print(aMember.getMember_NO() + ",");
//			System.out.print(aMember.getMember_NAME() + ",");
//			System.out.print(aMember.getMember_ID() + ",");
//			System.out.print(aMember.getMember_PSW() + ",");
//			System.out.println();
//		}
	}
}
