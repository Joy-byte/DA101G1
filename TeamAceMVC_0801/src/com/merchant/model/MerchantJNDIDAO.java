package com.merchant.model;

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

public class MerchantJNDIDAO implements MerchantDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		private static final String INSERT_STMT = 
				"INSERT INTO merchant (merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps, merchant_img) VALUES ('ME'||LPAD(to_char(merchant_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant order by merchant_no";
			private static final String GET_ONE_STMT = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant where merchant_no = ?";
			private static final String DELETE = 
				"DELETE FROM merchant where merchant_no = ?";
			private static final String UPDATE = 
				"UPDATE merchant set  merchant_id=?, merchant_pass=?, merchant_name=?, merchant_pm=?, merchant_add=?, merchant_tel=?, merchant_email=?, merchant_status=?, merchant_ps=?, merchant_img=? where merchant_no = ?";
			
			//輸入帳號登入用,傳回將session加入廠商編號
			private static final String GET_TWO_IDandPASS = 
					"SELECT merchant_no,merchant_id,merchant_pass,merchant_name FROM merchant where merchant_id = ?";	
			
			 // 查詢單一廠商用編號並MerchantVO傳回list
			private static final String GET_ONE_ALLVO = 
					"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant order by merchant_no";
		
			//更改單一廠商審核狀態
			private static final String UPDATE_ONE_STATUS = 
				"UPDATE merchant set  merchant_status=? where merchant_no = ?";
			
			//查詢單一審核狀態傳回廠商
			private static final String GET_ONE_STATUS = 
				"SELECT merchant_no,merchant_id,merchant_pass,merchant_name,merchant_pm,merchant_add,merchant_tel,merchant_email,merchant_status,merchant_ps,merchant_img FROM merchant where merchant_status = ?";
			

		

		@Override
		public void insert(MerchantVO merchantVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, merchantVO.getMerchant_id());
				pstmt.setString(2, merchantVO.getMerchant_pass());
				pstmt.setString(3, merchantVO.getMerchant_name());
				pstmt.setString(4, merchantVO.getMerchant_pm());
				pstmt.setString(5, merchantVO.getMerchant_add());
				pstmt.setString(6, merchantVO.getMerchant_tel());
				pstmt.setString(7, merchantVO.getMerchant_email());
				pstmt.setString(8, merchantVO.getMerchant_status());
				pstmt.setString(9, merchantVO.getMerchant_ps());
				pstmt.setBytes(10, merchantVO.getMerchant_img());

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
		public void update(MerchantVO merchantVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, merchantVO.getMerchant_id());
				pstmt.setString(2, merchantVO.getMerchant_pass());
				pstmt.setString(3, merchantVO.getMerchant_name());
				pstmt.setString(4, merchantVO.getMerchant_pm());
				pstmt.setString(5, merchantVO.getMerchant_add());
				pstmt.setString(6, merchantVO.getMerchant_tel());
				pstmt.setString(7, merchantVO.getMerchant_email());
				pstmt.setString(8, merchantVO.getMerchant_status());
				pstmt.setString(9, merchantVO.getMerchant_ps());
				pstmt.setBytes(10, merchantVO.getMerchant_img());
				
				pstmt.setString(11, merchantVO.getMerchant_no());

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
		public void delete(String merchant_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, merchant_no);

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
		public MerchantVO findByPrimaryKey(String merchant_no) {
			
			MerchantVO merchantVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, merchant_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects

					merchantVO = new MerchantVO();
					merchantVO.setMerchant_no(rs.getString("merchant_no"));
					merchantVO.setMerchant_id(rs.getString("merchant_id"));
					merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
					merchantVO.setMerchant_name(rs.getString("merchant_name"));
					merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
					merchantVO.setMerchant_add(rs.getString("merchant_add"));
					merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
					merchantVO.setMerchant_email(rs.getString("merchant_email"));
					merchantVO.setMerchant_status(rs.getString("merchant_status"));
					merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
					merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
					
				}

				// Handle any driver errors
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
			return merchantVO;
		}

		@Override
		public List<MerchantVO> getAll() {
			List<MerchantVO> list = new ArrayList<MerchantVO>();
			MerchantVO merchantVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					merchantVO = new MerchantVO();
					merchantVO.setMerchant_no(rs.getString("merchant_no"));
					merchantVO.setMerchant_id(rs.getString("merchant_id"));
					merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
					merchantVO.setMerchant_name(rs.getString("merchant_name"));
					merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
					merchantVO.setMerchant_add(rs.getString("merchant_add"));
					merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
					merchantVO.setMerchant_email(rs.getString("merchant_email"));
					merchantVO.setMerchant_status(rs.getString("merchant_status"));
					merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
					merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
					list.add(merchantVO); // Store the row in the list
				}

				// Handle any driver errors
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
		
		@Override
		public MerchantVO findByMerchantId(String merchant_id) {
			
			MerchantVO merchantVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_TWO_IDandPASS);

				pstmt.setString(1, merchant_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects

					merchantVO = new MerchantVO();
					merchantVO.setMerchant_no(rs.getString("merchant_no"));
					merchantVO.setMerchant_id(rs.getString("merchant_id"));
					merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
					merchantVO.setMerchant_name(rs.getString("merchant_name"));
					
				}

				// Handle any driver errors
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
			return merchantVO;
		}
		
		
		
		@Override   
		public List<MerchantVO> getAllOneMerchantVO(String merchant_no) {
			List<MerchantVO> list = new ArrayList<MerchantVO>();
			MerchantVO merchantVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_ALLVO);
				
				pstmt.setString(1, merchant_no);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects

					merchantVO = new MerchantVO();
					merchantVO.setMerchant_no(rs.getString("merchant_no"));
					merchantVO.setMerchant_id(rs.getString("merchant_id"));
					merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
					merchantVO.setMerchant_name(rs.getString("merchant_name"));
					merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
					merchantVO.setMerchant_add(rs.getString("merchant_add"));
					merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
					merchantVO.setMerchant_email(rs.getString("merchant_email"));
					merchantVO.setMerchant_status(rs.getString("merchant_status"));
					merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
					merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
					list.add(merchantVO); // Store the row in the list
				}

				// Handle any driver errors
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
		
		
		//更改單一廠商狀態
		@Override
		public void updateMerchantStatus(MerchantVO merchantVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_ONE_STATUS);
				
				pstmt.setString(1, merchantVO.getMerchant_status());

				
				pstmt.setString(2, merchantVO.getMerchant_no());

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
		
		//查詢單一狀態有關廠商
		@Override
		public List<MerchantVO> getOneStatusOfAll(String merchant_status) {
			List<MerchantVO> list = new ArrayList<MerchantVO>();
			MerchantVO merchantVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STATUS);
				
				pstmt.setString(1, merchant_status);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					merchantVO = new MerchantVO();
					merchantVO.setMerchant_no(rs.getString("merchant_no"));
					merchantVO.setMerchant_id(rs.getString("merchant_id"));
					merchantVO.setMerchant_pass(rs.getString("merchant_pass"));
					merchantVO.setMerchant_name(rs.getString("merchant_name"));
					merchantVO.setMerchant_pm(rs.getString("merchant_pm"));
					merchantVO.setMerchant_add(rs.getString("merchant_add"));
					merchantVO.setMerchant_tel(rs.getString("merchant_tel"));
					merchantVO.setMerchant_email(rs.getString("merchant_email"));
					merchantVO.setMerchant_status(rs.getString("merchant_status"));
					merchantVO.setMerchant_ps(rs.getString("merchant_ps"));
					merchantVO.setMerchant_img(rs.getBytes("merchant_img"));
					list.add(merchantVO); // Store the row in the list
				}

				// Handle any driver errors
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

