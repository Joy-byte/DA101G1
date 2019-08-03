package com.topickind.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.topic.model.TopicVO;

public class TopicKindJDBCDAO implements TopicKindDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
	String userid = "david";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO topickind (topicKind_no,topicKind_name) VALUES ('TK'||LPAD(to_char(TOPICKIND_seq.NEXTVAL),5,0),?)";
	private static final String GET_ALL_STMT = "SELECT * FROM topicKind order by topicKind_no";
	private static final String GET_ONE_STMT = "SELECT * FROM topicKind where topicKind_no = ?";
	private static final String DELETE = "DELETE FROM topickind where topicKind_no = ?";
	private static final String UPDATE = "UPDATE topickind set topicKind_name= ? where topicKind_no = ?";

	public void insert(TopicKindVO topicKindVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, topicKindVO.getTopicKind_name());

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

	public void update(TopicKindVO topicKindVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, topicKindVO.getTopicKind_name());
			pstmt.setString(2, topicKindVO.getTopicKind_no());

			pstmt.executeUpdate();

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

	public void delete(String topicKind_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, topicKind_no);

			pstmt.executeUpdate();

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

	public TopicKindVO findByPrimaryKey(String topicKind_No) {

		TopicKindVO topicKindVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, topicKind_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				topicKindVO = new TopicKindVO();
				topicKindVO.setTopicKind_no(rs.getString("topicKind_no"));
				topicKindVO.setTopicKind_name(rs.getString("topicKind_name"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return topicKindVO;
	}

	@Override
	public List<TopicKindVO> getAll() {
		List<TopicKindVO> list = new ArrayList<TopicKindVO>();
		TopicKindVO conditionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// conditionVO Domain objects
				conditionVO = new TopicKindVO();
				conditionVO.setTopicKind_no(rs.getString("topicKind_no"));
				conditionVO.setTopicKind_name(rs.getString("topicKind_name"));
				list.add(conditionVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		TopicKindJDBCDAO dao = new TopicKindJDBCDAO();
		
		
		// 新增
//		TopicKindVO topicKindVO1 = new TopicKindVO();
//		topicKindVO1.setTopicKind_name("動物");
//		dao.insert(topicKindVO1);
//		System.out.println("新增成功");
		
		// 修改
//		TopicKindVO topicKindVO2 = new TopicKindVO();
//		topicKindVO2.setTopicKind_no("TK00002");
//		topicKindVO2.setTopicKind_name("動物");		
//		dao.update(topicKindVO2);
//		System.out.println("修改成功");		
		
		// 刪除
//		dao.delete("TK00003");
//		System.out.println("刪除成功");
		
		// 查詢
//		TopicKindVO topicKindVO3 = dao.findByPrimaryKey("TK00001");
//		System.out.print(topicKindVO3.getTopicKind_no() + ",");
//		System.out.print(topicKindVO3.getTopicKind_name());
		

		
//		List<TopicKindVO> list = dao.getAll();
//		for (TopicKindVO conditionVO : list) {
//			System.out.print(conditionVO.getTopicKind_no() + ",");
//			System.out.print(conditionVO.getTopicKind_name() + ",");
//			System.out.println();
//		}
	}
}
