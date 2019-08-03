package com.friends.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.friends.model.FriendsVO;

public class FriendsDAO implements Friends_interface{
	
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, friendsVO.getMem_no());
				
				pstmt.executeUpdate();
				
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, friendsVO.getMem_no());
				pstmt.setString(2, friendsVO.getFriends_no());
				
				pstmt.executeUpdate();
				
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
				
				con = ds.getConnection();
				con.setAutoCommit(false);
				
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, friends_no);

				pstmt.executeUpdate();
				
				con.commit();
				con.setAutoCommit(true);
				
			
			} catch (SQLException se) {
				if(con != null) {
					try {
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, friends_no);

				rs = pstmt.executeQuery();
				while (rs.next()) {
			
					friendsVO = new FriendsVO();
					friendsVO.setFriends_no(rs.getString("friends_no"));
					friendsVO.setMem_no(rs.getString("mem_no"));
					
				}
			
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
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					friendsVO = new FriendsVO();
					friendsVO.setFriends_no(rs.getString("friends_no"));
					friendsVO.setMem_no(rs.getString("mem_no"));	
					list.add(friendsVO);
				}
				
			
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

}
