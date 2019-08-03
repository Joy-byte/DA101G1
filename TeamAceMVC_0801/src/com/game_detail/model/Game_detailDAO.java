package com.game_detail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Game_detailDAO implements Game_detailDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = 
			"SELECT * FROM Game_detail order by Gameroom_no";
		private static final String INSERT_STMT = 
			"INSERT INTO Game_detail (gameroom_no,mem_no,lens_status,topic_no,room_num)"
				+ " VALUES ( ?, ?,?,?,?)";
		private static final String GET_ONE_STMT = 
			"SELECT * from Game_detail where Gameroom_no = ? And mem_no=?";
		private static final String UPDATE = 
			"UPDATE Game_detail set  lens_status=?, topic_no=?,room_num=? where gameroom_no = ? And mem_no=?";
		private static final String DELETE = 
			"DELETE FROM Game_detail where gameroom_no = ? And mem_no=? ";

	@Override
	public void insert(Game_detailVO game_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, game_detailVO.getGameroom_no());
			pstmt.setString(2, game_detailVO.getMem_no());
			pstmt.setString(3, game_detailVO.getLens_status());
			pstmt.setString(4, game_detailVO.getTopic_no());
			pstmt.setInt(5, game_detailVO.getRoom_num());
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
	public List<Game_detailVO> getAll() {
		List<Game_detailVO> list = new ArrayList<Game_detailVO>();
		Game_detailVO game_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {				
				// game_detailVO  Domain objects
				game_detailVO = new Game_detailVO();
				game_detailVO.setGameroom_no(rs.getString("gameroom_no"));
				game_detailVO.setMem_no(rs.getString("mem_no"));
				game_detailVO.setLens_status(rs.getString("lens_status"));
				game_detailVO.setTopic_no(rs.getString("topic_no"));
				game_detailVO.setRoom_num(rs.getInt("room_num"));
				list.add(game_detailVO); // Store the row in the list
			}

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
	
	public Game_detailVO findByPrimaryKey(String gameroom_no ,String  mem_no) {

		Game_detailVO game_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gameroom_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				game_detailVO = new Game_detailVO();
				game_detailVO.setGameroom_no(gameroom_no);
				game_detailVO.setMem_no(mem_no);
				game_detailVO.setLens_status(rs.getString("lens_status"));
				game_detailVO.setTopic_no(rs.getString("topic_no"));
				game_detailVO.setRoom_num(rs.getInt("room_num"));
			}

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
		return game_detailVO;
	}
	
	@Override
	public void update(Game_detailVO game_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, game_detailVO.getLens_status());
			pstmt.setString(2, game_detailVO.getTopic_no());
			pstmt.setInt(3, game_detailVO.getRoom_num());
			pstmt.setString(4, game_detailVO.getGameroom_no());
			pstmt.setString(5, game_detailVO.getMem_no());
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
	public void delete(String gameroom_no,String  mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gameroom_no);
			pstmt.setString(2, mem_no);

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
}