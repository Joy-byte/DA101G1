package com.guide.model;

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

public class GuideDAO implements GuideDAO_interface{
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
			"INSERT INTO GUIDE (GUIDE_NO,MEM_NO,GUIDE_INTRO,GUIDE_PIC,GUIDE_COVER,GUIDE_HOBBY,GUIDE_STATUS,GUIDE_LNG,GUIDE_LAT,GUIDE_AREA,GUIDE_RATE,GUIDE_STLANG,GUIDE_NDLANG) VALUES ('GU'||LPAD(to_char(GUIDE_seq.NEXTVAL),5,0),?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM GUIDE order by GUIDE_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM GUIDE where GUIDE_NO = ?";
		private static final String GET_ONE_STMT_BY_MEM_NO = 
			"SELECT * FROM GUIDE where MEM_NO = ?";
		private static final String DELETE = 
			"DELETE FROM GUIDE where GUIDE_NO = ?";
		private static final String UPDATE = 
			"UPDATE GUIDE set MEM_NO=?, GUIDE_INTRO=?, GUIDE_PIC=?, GUIDE_COVER=?, GUIDE_HOBBY=? ,GUIDE_STATUS=?,GUIDE_LNG=?,GUIDE_LAT=?,GUIDE_AREA=?,GUIDE_RATE=?,GUIDE_STLANG=?,GUIDE_NDLANG=? where GUIDE_NO = ?";
		private static final String UPDATE_STATUS = "UPDATE GUIDE set GUIDE_STATUS = ? where GUIDE_NO = ?";
		
		@Override
		public void insert(GuideVO guideVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, guideVO.getMem_no());
				pstmt.setString(2, guideVO.getGuide_intro());
				pstmt.setBytes(3, guideVO.getGuide_pic());
				pstmt.setBytes(4, guideVO.getGuide_cover());
				pstmt.setString(5, guideVO.getGuide_hobby());
				pstmt.setString(6, guideVO.getGuide_status());
				pstmt.setDouble(7, guideVO.getGuide_lng());
				pstmt.setDouble(8, guideVO.getGuide_lat());
				pstmt.setString(9, guideVO.getGuide_area());
				pstmt.setDouble(10, guideVO.getGuide_rate());
				pstmt.setString(11, guideVO.getGuide_stLang());
				pstmt.setString(12, guideVO.getGuide_ndLang());
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
		public void update(GuideVO guideVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, guideVO.getMem_no());
				pstmt.setString(2, guideVO.getGuide_intro());
				pstmt.setBytes(3, guideVO.getGuide_pic());
				pstmt.setBytes(4, guideVO.getGuide_cover());
				pstmt.setString(5, guideVO.getGuide_hobby());
				pstmt.setString(6, guideVO.getGuide_status());
				pstmt.setDouble(7, guideVO.getGuide_lng());
				pstmt.setDouble(8, guideVO.getGuide_lat());
				pstmt.setString(9, guideVO.getGuide_area());
				pstmt.setDouble(10, guideVO.getGuide_rate());
				pstmt.setString(11, guideVO.getGuide_stLang());
				pstmt.setString(12, guideVO.getGuide_ndLang());
				pstmt.setString(13, guideVO.getGuide_no());

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
		public void updateGuideStatus(String guide_no, String guide_status) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STATUS);

				pstmt.setString(1, guide_status);
				pstmt.setString(2, guide_no);

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
		public void delete(String guide_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, guide_no);

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
		public GuideVO findByPrimaryKey(String guide_no) {

			GuideVO guideVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, guide_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					guideVO = new GuideVO();
					guideVO.setGuide_no(rs.getString("guide_no"));
					guideVO.setMem_no(rs.getString("mem_no"));
					guideVO.setGuide_intro(rs.getString("guide_intro"));
					guideVO.setGuide_pic(rs.getBytes("guide_pic"));
					guideVO.setGuide_cover(rs.getBytes("guide_cover"));
					guideVO.setGuide_hobby(rs.getString("guide_hobby"));
					guideVO.setGuide_status(rs.getString("guide_status"));
					guideVO.setGuide_lng(rs.getDouble("guide_lng"));
					guideVO.setGuide_lat(rs.getDouble("guide_lat"));
					guideVO.setGuide_area(rs.getString("guide_area"));
					guideVO.setGuide_rate(rs.getDouble("guide_rate"));
					guideVO.setGuide_stLang(rs.getString("guide_stlang"));
					guideVO.setGuide_ndLang(rs.getString("guide_ndlang"));
				}

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
			return guideVO;
		}
		
		public GuideVO findByMemNo(String mem_no) {
			GuideVO guideVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEM_NO);

				pstmt.setString(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					guideVO = new GuideVO();
					guideVO.setGuide_no(rs.getString("guide_no"));
					guideVO.setMem_no(rs.getString("mem_no"));
					guideVO.setGuide_intro(rs.getString("guide_intro"));
					guideVO.setGuide_pic(rs.getBytes("guide_pic"));
					guideVO.setGuide_cover(rs.getBytes("guide_cover"));
					guideVO.setGuide_hobby(rs.getString("guide_hobby"));
					guideVO.setGuide_status(rs.getString("guide_status"));
					guideVO.setGuide_lng(rs.getDouble("guide_lng"));
					guideVO.setGuide_lat(rs.getDouble("guide_lat"));
					guideVO.setGuide_area(rs.getString("guide_area"));
					guideVO.setGuide_rate(rs.getDouble("guide_rate"));
					guideVO.setGuide_stLang(rs.getString("guide_stlang"));
					guideVO.setGuide_ndLang(rs.getString("guide_ndlang"));
				}

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
			return guideVO;
		}


		@Override
		public List<GuideVO> getAll() {
			List<GuideVO> list = new ArrayList<GuideVO>();
			GuideVO guideVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					guideVO = new GuideVO();
					guideVO.setGuide_no(rs.getString("guide_no"));
					guideVO.setMem_no(rs.getString("mem_no"));
					guideVO.setGuide_intro(rs.getString("guide_intro"));
					guideVO.setGuide_pic(rs.getBytes("guide_pic"));
					guideVO.setGuide_cover(rs.getBytes("guide_cover"));
					guideVO.setGuide_hobby(rs.getString("guide_hobby"));
					guideVO.setGuide_status(rs.getString("guide_status"));
					guideVO.setGuide_lng(rs.getDouble("guide_lng"));
					guideVO.setGuide_lat(rs.getDouble("guide_lat"));
					guideVO.setGuide_area(rs.getString("guide_area"));
					guideVO.setGuide_rate(rs.getDouble("guide_rate"));
					guideVO.setGuide_stLang(rs.getString("guide_stlang"));
					guideVO.setGuide_ndLang(rs.getString("guide_ndlang"));
					list.add(guideVO); 
				}

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
}
