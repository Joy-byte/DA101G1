package com.mem.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface {

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
			"INSERT INTO Mem (Mem_no, Mem_email, Mem_psw, Mem_name, Mem_nickname, mem_birthday, Mem_adrs, Mem_tel, mem_status, Mem_pic) VALUES ('MB'||LPAD(to_char(MEM_seq.NEXTVAL),5 ,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE Mem set Mem_email=?, Mem_psw=?, Mem_name=?, Mem_nickname=?, Mem_birthday=?, Mem_adrs=?, Mem_tel=?, Mem_status=?, Mem_amo=?, Mem_pic=? where Mem_no = ?";
	private static final String UPDATE2 = 
			"UPDATE Mem set Mem_email=?, Mem_psw=?, Mem_name=?, Mem_nickname=?, Mem_birthday=?, Mem_adrs=?, Mem_tel=?, Mem_status=?, Mem_amo=? where Mem_no = ?";
	private static final String DELETE = 
			"DELETE FROM Mem where Mem_no = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM Mem where Mem_no = ?";
	private static final String GET_EMAIL = 
			"SELECT * FROM Mem where Mem_email = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Mem order by Mem_no";
//	private static final String GET_ID = 
//			"SELECT * FROM Mem where Mem_id = ?";
	
	
	//查詢一個會員點數
		private static final String GET_ONE_MEM_AMO = 
			"SELECT mem_no,mem_amo FROM Mem where mem_no = ?";
			
	//修改會員點數
	private static final String UPDATE_MEM_AMO = 
			"UPDATE mem set mem_amo= ? where mem_no = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVO.getMem_email());
			pstmt.setString(2, memberVO.getMem_psw());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setString(4, memberVO.getMem_nickname());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_adrs());
			pstmt.setString(7, memberVO.getMem_tel());
			pstmt.setString(8, memberVO.getMem_status());
//			pstmt.setInt(9, memberVO.getMem_amo());
			pstmt.setBytes(9, memberVO.getMem_pic());
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMem_email());
			pstmt.setString(2, memberVO.getMem_psw());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setString(4, memberVO.getMem_nickname());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_adrs());
			pstmt.setString(7, memberVO.getMem_tel());
			pstmt.setString(8, memberVO.getMem_status());
			pstmt.setInt(9, memberVO.getMem_amo());
			pstmt.setBytes(10, memberVO.getMem_pic());
			pstmt.setString(11, memberVO.getMem_no());
			System.out.println("memberVO.getMem_pic()="+memberVO.getMem_pic().length);
			System.out.println("pstmt.executeUpdate()="+pstmt.executeUpdate());

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
	public void update2(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
			pstmt.setString(1, memberVO.getMem_email());
			pstmt.setString(2, memberVO.getMem_psw());
			pstmt.setString(3, memberVO.getMem_name());
			pstmt.setString(4, memberVO.getMem_nickname());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_adrs());
			pstmt.setString(7, memberVO.getMem_tel());
			pstmt.setString(8, memberVO.getMem_status());
			pstmt.setInt(9, memberVO.getMem_amo());
//			pstmt.setBytes(9, memberVO.getMem_pic());
			pstmt.setString(10, memberVO.getMem_no());
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
	public void delete(String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

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
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_adrs(rs.getString("mem_adrs"));
				memberVO.setMem_tel(rs.getString("mem_tel"));
				memberVO.setMem_status(rs.getString("mem_status"));
				memberVO.setMem_amo(rs.getInt("mem_amo"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
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
		return memberVO;
	}

	@Override
	public MemberVO findMem_email(String mem_email) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMAIL);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_adrs(rs.getString("mem_adrs"));
				memberVO.setMem_tel(rs.getString("mem_tel"));
				memberVO.setMem_status(rs.getString("mem_status"));
				memberVO.setMem_amo(rs.getInt("mem_amo"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
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
		return memberVO;
	}
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_adrs(rs.getString("mem_adrs"));
				memberVO.setMem_tel(rs.getString("mem_tel"));
				memberVO.setMem_status(rs.getString("mem_status"));
				memberVO.setMem_amo(rs.getInt("mem_amo"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));

				list.add(memberVO); // Store the row in the list
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
	
	
	
	//查詢單一會員點數
		@Override
		public MemberVO findMenAmoByMenNo(String mem_no) {
			
			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MEM_AMO);

				pstmt.setString(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// MemberVO Domain objects
					memberVO = new MemberVO();
					memberVO.setMem_no(rs.getString("mem_no"));
					memberVO.setMem_amo(rs.getInt("mem_amo"));
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
			return memberVO;
		}
		
		
		
		//更改會員點數
		@Override
		public void updateAboutMenAmo(MemberVO memberVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_MEM_AMO);
				
				pstmt.setInt(1, memberVO.getMem_amo());
				pstmt.setString(2, memberVO.getMem_no());

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
}