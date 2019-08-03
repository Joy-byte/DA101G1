package com.resorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ResOrderDAO implements ResOrderDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO RESORDER (RESORDER_NO,MEM_NO,GUIDE_NO,GUIDETOUR_NO,RESORDER_DATE,RESORDER_PEO,RESORDER_MSG,RESORDER_STATUS,RESORDER_JOINPEO,RESORDER_RATE,RESORDER_CANCEL,RESORDER_REVIEW,RESORDER_TIME) VALUES ('RO'||LPAD(to_char(RESORDER_seq.NEXTVAL),5,0),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RESORDER order by RESORDER_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM RESORDER where RESORDER_NO = ?";
	private static final String DELETE = "DELETE FROM RESORDER where RESORDER_NO = ?";
	private static final String UPDATE = "UPDATE RESORDER set MEM_NO=?, GUIDE_NO=?, GUIDETOUR_NO=?, RESORDER_DATE=?, RESORDER_PEO=? ,RESORDER_MSG=?,RESORDER_STATUS=?,RESORDER_JOINPEO=?,RESORDER_RATE=?,RESORDER_CANCEL=?,RESORDER_REVIEW=?,RESORDER_TIME=? where RESORDER_NO = ?";

	@Override
	public void insert(ResOrderVO resOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resOrderVO.getMem_no());
			pstmt.setString(2, resOrderVO.getGuide_no());
			pstmt.setString(3, resOrderVO.getGuideTour_no());
			pstmt.setDate(4, resOrderVO.getResOrder_date());
			pstmt.setInt(5, resOrderVO.getResOrder_peo());
			pstmt.setString(6, resOrderVO.getResOrder_msg());
			pstmt.setString(7, resOrderVO.getResOrder_status());
			pstmt.setString(8, resOrderVO.getResOrder_joinpeo());
			pstmt.setDouble(9, resOrderVO.getResOrder_rate());
			pstmt.setString(10, resOrderVO.getResOrder_cancel());
			pstmt.setString(11, resOrderVO.getResOrder_review());
			pstmt.setDate(12, resOrderVO.getResOrder_time());

			pstmt.executeUpdate();

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
	public void update(ResOrderVO resOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resOrderVO.getMem_no());
			pstmt.setString(2, resOrderVO.getGuide_no());
			pstmt.setString(3, resOrderVO.getGuideTour_no());
			pstmt.setDate(4, resOrderVO.getResOrder_date());
			pstmt.setInt(5, resOrderVO.getResOrder_peo());
			pstmt.setString(6, resOrderVO.getResOrder_msg());
			pstmt.setString(7, resOrderVO.getResOrder_status());
			pstmt.setString(8, resOrderVO.getResOrder_joinpeo());
			pstmt.setDouble(9, resOrderVO.getResOrder_rate());
			pstmt.setString(10, resOrderVO.getResOrder_cancel());
			pstmt.setString(11, resOrderVO.getResOrder_review());
			pstmt.setDate(12, resOrderVO.getResOrder_time());
			pstmt.setString(13, resOrderVO.getResOrder_no());

			pstmt.executeUpdate();

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
	public void delete(String resOrder_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, resOrder_no);

			pstmt.executeUpdate();

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
	public ResOrderVO findByPrimaryKey(String resOrder_no) {

		ResOrderVO resOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, resOrder_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resOrderVO = new ResOrderVO();
				resOrderVO.setResOrder_no(rs.getString("resOrder_no"));
				resOrderVO.setMem_no(rs.getString("mem_no"));
				resOrderVO.setGuide_no(rs.getString("guide_no"));
				resOrderVO.setGuideTour_no(rs.getString("guideTour_no"));
				resOrderVO.setResOrder_date(rs.getDate("resOrder_date"));
				resOrderVO.setResOrder_peo(rs.getInt("resOrder_peo"));
				resOrderVO.setResOrder_msg(rs.getString("resOrder_msg"));
				resOrderVO.setResOrder_status(rs.getString("resOrder_status"));
				resOrderVO.setResOrder_joinpeo(rs.getString("resOrder_joinpeo"));
				resOrderVO.setResOrder_rate(rs.getDouble("resOrder_rate"));
				resOrderVO.setResOrder_cancel(rs.getString("resOrder_cancel"));
				resOrderVO.setResOrder_review(rs.getString("resOrder_review"));
				resOrderVO.setResOrder_time(rs.getDate("resOrder_time"));
			}

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
		return resOrderVO;
	}

	@Override
	public List<ResOrderVO> getAll() {
		List<ResOrderVO> list = new ArrayList<ResOrderVO>();
		ResOrderVO resOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resOrderVO = new ResOrderVO();
				resOrderVO.setResOrder_no(rs.getString("resOrder_no"));
				resOrderVO.setMem_no(rs.getString("mem_no"));
				resOrderVO.setGuide_no(rs.getString("guide_no"));
				resOrderVO.setGuideTour_no(rs.getString("guideTour_no"));
				resOrderVO.setResOrder_date(rs.getDate("resOrder_date"));
				resOrderVO.setResOrder_peo(rs.getInt("resOrder_peo"));
				resOrderVO.setResOrder_msg(rs.getString("resOrder_msg"));
				resOrderVO.setResOrder_status(rs.getString("resOrder_status"));
				resOrderVO.setResOrder_joinpeo(rs.getString("resOrder_joinpeo"));
				resOrderVO.setResOrder_rate(rs.getDouble("resOrder_rate"));
				resOrderVO.setResOrder_cancel(rs.getString("resOrder_cancel"));
				resOrderVO.setResOrder_review(rs.getString("resOrder_review"));
				resOrderVO.setResOrder_time(rs.getDate("resOrder_time"));
				list.add(resOrderVO); // Store the row in the list
			}

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
