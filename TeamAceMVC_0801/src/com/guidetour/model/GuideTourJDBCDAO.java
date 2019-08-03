package com.guidetour.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuideTourJDBCDAO implements GuideTourDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO GUIDETOUR (GUIDETOUR_NO,GUIDE_NO,GUIDETOUR_AREA,GUIDETOUR_DETAIL,GUIDETOUR_NAME,GUIDETOUR_PEO,GUIDETOUR_PIC,GUIDETOUR_STATUS,GUIDETOUR_RATE) VALUES ('GT'||LPAD(to_char(GUIDETOUR_seq.NEXTVAL),5,0),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GUIDETOUR where GUIDETOUR_STATUS='GT1' order by GUIDETOUR_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM GUIDETOUR where GUIDETOUR_NO = ?";
	private static final String DELETE = "DELETE FROM GUIDETOUR where GUIDETOUR_NO = ?";
	private static final String UPDATE = "UPDATE GUIDETOUR set GUIDE_NO=?, GUIDETOUR_AREA=?, GUIDETOUR_DETAIL=?, GUIDETOUR_NAME=?, GUIDETOUR_PEO=? ,GUIDETOUR_PIC=?,GUIDETOUR_STATUS=?,GUIDETOUR_RATE=? where GUIDETOUR_NO = ?";

	@Override
	public String insert(GuideTourVO guideTourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String nextNo = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, guideTourVO.getGuide_no());
			pstmt.setString(2, guideTourVO.getGuideTour_area());
			pstmt.setString(3, guideTourVO.getGuideTour_detail());
			pstmt.setString(4, guideTourVO.getGuideTour_name());
			pstmt.setInt(5, guideTourVO.getGuideTour_peo());
			pstmt.setBytes(6, guideTourVO.getGuideTour_pic());
			pstmt.setString(7, guideTourVO.getGuideTour_status());
			pstmt.setDouble(8, guideTourVO.getGuideTour_rate());

			pstmt.executeUpdate();
			//取得新增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextNo = rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			
			// Handle any driver errors
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
		return nextNo;
	}

	@Override
	public void update(GuideTourVO guideTourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, guideTourVO.getGuide_no());
			pstmt.setString(2, guideTourVO.getGuideTour_area());
			pstmt.setString(3, guideTourVO.getGuideTour_detail());
			pstmt.setString(4, guideTourVO.getGuideTour_name());
			pstmt.setInt(5, guideTourVO.getGuideTour_peo());
			pstmt.setBytes(6, guideTourVO.getGuideTour_pic());
			pstmt.setString(7, guideTourVO.getGuideTour_status());
			pstmt.setDouble(8, guideTourVO.getGuideTour_rate());
			pstmt.setString(9, guideTourVO.getGuideTour_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}

	@Override
	public void delete(String guideTour_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, guideTour_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}

	@Override
	public GuideTourVO findByPrimaryKey(String guideTour_no) {

		GuideTourVO guideTourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, guideTour_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				guideTourVO = new GuideTourVO();
				guideTourVO.setGuideTour_no(rs.getString("guideTour_no"));
				guideTourVO.setGuide_no(rs.getString("guide_no"));
				guideTourVO.setGuideTour_area(rs.getString("guideTour_area"));
				guideTourVO.setGuideTour_detail(rs.getString("guideTour_detail"));
				guideTourVO.setGuideTour_name(rs.getString("guideTour_name"));
				guideTourVO.setGuideTour_peo(rs.getInt("guideTour_peo"));
				guideTourVO.setGuideTour_pic(rs.getBytes("guideTour_pic"));
				guideTourVO.setGuideTour_status(rs.getString("guideTour_status"));
				guideTourVO.setGuideTour_rate(rs.getDouble("guideTour_rate"));
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
		return guideTourVO;
	}

	@Override
	public List<GuideTourVO> getAll() {
		List<GuideTourVO> list = new ArrayList<GuideTourVO>();
		GuideTourVO guideTourVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				guideTourVO = new GuideTourVO();
				guideTourVO.setGuideTour_no(rs.getString("guideTour_no"));
				guideTourVO.setGuide_no(rs.getString("guide_no"));
				guideTourVO.setGuideTour_area(rs.getString("guideTour_area"));
				guideTourVO.setGuideTour_detail(rs.getString("guideTour_detail"));
				guideTourVO.setGuideTour_name(rs.getString("guideTour_name"));
				guideTourVO.setGuideTour_peo(rs.getInt("guideTour_peo"));
				guideTourVO.setGuideTour_pic(rs.getBytes("guideTour_pic"));
				guideTourVO.setGuideTour_status(rs.getString("guideTour_status"));
				guideTourVO.setGuideTour_rate(rs.getDouble("guideTour_rate"));
				list.add(guideTourVO); // Store the row in the list
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

}