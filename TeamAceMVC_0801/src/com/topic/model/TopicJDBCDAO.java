package com.topic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicJDBCDAO implements TopicDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEAMACE";
//	String passwd = "teamace";
	String userid = "TEAMACE";
	String passwd = "teamace";

	private static final String INSERT_STMT = "INSERT INTO topic (topic_no,topickind_no,topic_name) VALUES ('TN'||LPAD(to_char(TOPIC_seq.NEXTVAL),5,0),? , ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM topic order by topic_no";
	private static final String GET_ONE_STMT = "SELECT * FROM topic where topic_no = ?";
	private static final String DELETE = "DELETE FROM topic where topic_no = ?";
	private static final String UPDATE = "UPDATE topic set topickind_no=?, topic_name=? where topic_no = ?";

	public void insert(TopicVO topicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, topicVO.getTopicKind_no());
			pstmt.setString(2, topicVO.getTopic_name());

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

	public void update(TopicVO topicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, topicVO.getTopicKind_no());
			pstmt.setString(2, topicVO.getTopic_name());
			pstmt.setString(3, topicVO.getTopic_no());

			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
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

	public void delete(String topic_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, topic_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public TopicVO findByPrimaryKey(String topic_No) {

		TopicVO topicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, topic_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				topicVO = new TopicVO();
				topicVO.setTopic_no(rs.getString("topic_no"));
				topicVO.setTopicKind_no(rs.getString("topicKind_no"));
				topicVO.setTopic_name(rs.getString("topic_name"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
		return topicVO;
	}

	@Override
	public List<TopicVO> getAll() {
		List<TopicVO> list = new ArrayList<TopicVO>();
		TopicVO conditionVO = null;

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
				conditionVO = new TopicVO();
				conditionVO.setTopic_no(rs.getString("topic_no"));
				conditionVO.setTopicKind_no(rs.getString("topicKind_no"));
				conditionVO.setTopic_name(rs.getString("topic_name"));
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
		TopicJDBCDAO dao = new TopicJDBCDAO();
		
		// �s�W
//    	TopicVO topicVO1 = new TopicVO();
//    	topicVO1.setTopicKind_no("TK00001");
//    	topicVO1.setTopic_name("��,��");
//		dao.insert(topicVO1);
//		System.out.println("�s�W���\");
		
		// �ק�
//		TopicVO topicVO2 = new TopicVO();
//		topicVO2.setTopicKind_no("TK00001");
//		topicVO2.setTopic_name("�g��,��");
//		topicVO2.setTopic_no("TN00010");
//		
//		dao.update(topicVO02);
//		System.out.println("�ק令�\");		
		
		// �R��
//		dao.delete("TN00010");
//		System.out.println("�R�����\");
		
		// �d��
		TopicVO topicVO3 = dao.findByPrimaryKey("TN00001");
		System.out.print(topicVO3.getTopic_no() + ",");
		System.out.print(topicVO3.getTopicKind_no() + ",");
		System.out.print(topicVO3.getTopic_name() + ",");


	
//		
//		List<TopicVO> list = dao.getAll();
//		for (TopicVO tlist : list) {
//			System.out.print(tlist.getTopic_no() + ",");
//			System.out.print(tlist.getTopicKind_no() + ",");
//			System.out.print(tlist.getTopic_name() + ",");
//			System.out.println();
//		}
	}
}
