package com.friends.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FriendsJDBCDAO implements Friends_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";
	
	private static final String INSERT_STMT = 
		"INSERT INTO FRIENDS (FRIENDS_NO,MEM_NO) VALUES ('FR'||LPAD(to_char(friends_seq.NEXTVAL), 5, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT FRIENDS_NO,MEM_NO FROM FRIENDS order by FRIENDS_NO";
	private static final String GET_ONE_STMT = 
		"SELECT FRIENDS_NO,MEM_NO FROM FRIENDS where FRIENDS_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FRIENDS where FRIENDS_NO = ?";
	private static final String UPDATE = 
		"UPDATE FRIENDS set MEM_NO= ? WHERE FRIENDS_NO = ? ";

	
	@Override
	public void insert(FriendsVO friendsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, friendsVO.getMem_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(FriendsVO friendsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, friendsVO.getMem_no());
			pstmt.setString(2, friendsVO.getFriends_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void delete(String friends_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, friends_no);

			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public FriendsVO findByPrimaryKey(String friends_no) {
		FriendsVO friendsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, friends_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
		
				friendsVO = new FriendsVO();
				friendsVO.setFriends_no(rs.getString("friends_no"));
				friendsVO.setMem_no(rs.getString("mem_no"));
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
		
		return friendsVO;
		
		
		
	}
	@Override
	public List<FriendsVO> getAll() {
		List<FriendsVO> list = new ArrayList<FriendsVO>();
		FriendsVO friendsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				friendsVO = new FriendsVO();
				friendsVO.setFriends_no(rs.getString("friends_no"));
				friendsVO.setMem_no(rs.getString("mem_no"));	
				list.add(friendsVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
		FriendsJDBCDAO dao = new FriendsJDBCDAO();
		//�d��
//		FriendsVO friendsVO = dao.findByPrimaryKey("FR00001");
//		System.out.print(friendsVO.getFriends_no() + ",");
//		System.out.print(friendsVO.getMem_no() );
		
		//�s�W
//		FriendsVO friendsVO1 = new FriendsVO();
//		friendsVO1.setMem_no("MB00003");
//		dao.insert(friendsVO1);
		
		//�R��
//		FriendsVO friendsVO2 = new FriendsVO();
//		dao.delete("FR00003");
//		dao.delete("FR00004");
		
		//�ק�
//		FriendsVO friendsVO3 = new FriendsVO();
//		friendsVO3.setFriends_no("FR00005");
//		friendsVO3.setMem_no("MB00004");
//		dao.update(friendsVO3);
		
		//�d��
		List<FriendsVO> list = dao.getAll();
		for(FriendsVO friendsVO4 : list) {
			System.out.print(friendsVO4.getFriends_no() + ",");
			System.out.print(friendsVO4.getMem_no());
			System.out.println();
		}
	}
}
