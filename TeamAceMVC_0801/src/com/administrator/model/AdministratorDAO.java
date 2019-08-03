package com.administrator.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdministratorDAO implements AdministratorDAO_interface {

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
			"INSERT INTO Administrator (Administrator_no,Administrator_id,Administrator_psw,Administrator_name) VALUES ('AM'||LPAD(to_char(Administrator_seq.NEXTVAL),5 ,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Administrator order by Administrator_no";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM Administrator where Administrator_no = ?";
	private static final String GET_ID = 
			"SELECT * FROM Administrator where Administrator_id = ?";
	private static final String DELETE = 
			"DELETE FROM Administrator where Administrator_no = ?";
	private static final String UPDATE = 
			"UPDATE Administrator set Administrator_id=?, Administrator_psw=?, Administrator_name=? where Administrator_no = ?";

	@Override
	public void insert(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, administratorVO.getAdministrator_id());
			pstmt.setString(2, administratorVO.getAdministrator_psw());
			pstmt.setString(3, administratorVO.getAdministrator_name());
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
	public void update(AdministratorVO AdministratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, AdministratorVO.getAdministrator_id());
			pstmt.setString(2, AdministratorVO.getAdministrator_psw());
			pstmt.setString(3, AdministratorVO.getAdministrator_name());
			pstmt.setString(4, AdministratorVO.getAdministrator_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String administrator_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, administrator_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public AdministratorVO findByPrimaryKey(String administrator_no) {

		AdministratorVO AdministratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, administrator_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				AdministratorVO = new AdministratorVO();
				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));
			}

			// Handle any driver errors
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
		return AdministratorVO;
	}

	@Override
	public AdministratorVO findID(String administrator_id) {

		AdministratorVO AdministratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ID);

			pstmt.setString(1, administrator_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO
				AdministratorVO = new AdministratorVO();
				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));
			}
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
		return AdministratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO AdministratorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				AdministratorVO = new AdministratorVO();
				AdministratorVO.setAdministrator_no(rs.getString("Administrator_no"));
				AdministratorVO.setAdministrator_id(rs.getString("Administrator_id"));
				AdministratorVO.setAdministrator_psw(rs.getString("Administrator_psw"));
				AdministratorVO.setAdministrator_name(rs.getString("Administrator_name"));

				list.add(AdministratorVO); // Store the row in the list
			}

			// Handle any driver errors
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
