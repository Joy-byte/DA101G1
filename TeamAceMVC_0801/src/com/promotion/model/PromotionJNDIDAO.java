package com.promotion.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromotionJNDIDAO implements PromotionDAO_interface{
	
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
				"INSERT INTO promotion (promotion_no,merchant_no,product_no,promotion_name,promotion_start,promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img) VALUES ('PR'||LPAD(to_char(promotion_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion order by promotion_no";
			private static final String GET_ONE_STMT = 
				"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion where promotion_no = ?";
			private static final String DELETE = 
				"DELETE FROM promotion where promotion_no = ?";
			private static final String UPDATE = 
				"UPDATE promotion set merchant_no=?, product_no=?, promotion_name=?, promotion_start=?, promotion_end=?, promotion_pr=?, promotion_ps=?, promotion_status=?, promotion_img=? where promotion_no = ?";
			
			
			// 查詢單一廠商有關廣告
			private static final String GET_ONE_MERCHANT = 
				"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion where merchant_no = ?";
			
			//更改單一廣告上下架狀態
			private static final String UPDATE_ONE_PROMOTION_STATUS = 
				"UPDATE promotion set  promotion_status=? where promotion_no = ?";
			
			//查詢單一上下架狀態傳回廣告list
			private static final String GET_ONE_STATUS_PROMOTION = 
				"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion where promotion_status = ?";
			
			
			//用商品編號找出廣告VO(廣告折扣用)
			private static final String GET_ONE_PRODUCTNO = 
				"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion where product_no = ?";
				
			
			
		@Override
		public void insert(PromotionVO promotionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, promotionVO.getMerchant_no());
				pstmt.setString(2, promotionVO.getProduct_no());
				pstmt.setString(3, promotionVO.getPromotion_name());
				pstmt.setDate(4, promotionVO.getPromotion_start());
				pstmt.setDate(5, promotionVO.getPromotion_end());
				pstmt.setDouble(6, promotionVO.getPromotion_pr());
				pstmt.setString(7, promotionVO.getPromotion_ps());
				pstmt.setString(8, promotionVO.getPromotion_status());
				pstmt.setBytes(9, promotionVO.getPromotion_img());

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
		public void update(PromotionVO promotionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, promotionVO.getMerchant_no());
				pstmt.setString(2, promotionVO.getProduct_no());
				pstmt.setString(3, promotionVO.getPromotion_name());
				pstmt.setDate(4, promotionVO.getPromotion_start());
				pstmt.setDate(5, promotionVO.getPromotion_end());
				pstmt.setDouble(6, promotionVO.getPromotion_pr());
				pstmt.setString(7, promotionVO.getPromotion_ps());
				pstmt.setString(8, promotionVO.getPromotion_status());
				pstmt.setBytes(9, promotionVO.getPromotion_img());
				
				pstmt.setString(10, promotionVO.getPromotion_no());

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
		public void delete(String productreport_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, productreport_no);

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
		public PromotionVO findByPrimaryKey(String promotion_no) {
			PromotionVO promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, promotion_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects

					promotionVO = new PromotionVO();
					promotionVO.setPromotion_no(rs.getString("promotion_no"));
					promotionVO.setMerchant_no(rs.getString("merchant_no"));
					promotionVO.setProduct_no(rs.getString("product_no"));
					promotionVO.setPromotion_name(rs.getString("promotion_name"));
					promotionVO.setPromotion_start(rs.getDate("promotion_start"));
					promotionVO.setPromotion_end(rs.getDate("promotion_end"));
					promotionVO.setPromotion_pr(rs.getDouble("promotion_pr"));
					promotionVO.setPromotion_ps(rs.getString("promotion_ps"));
					promotionVO.setPromotion_status(rs.getString("promotion_status"));
					promotionVO.setPromotion_img(rs.getBytes("promotion_img"));
					
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
			return promotionVO;
		}
		
		@Override
		public List<PromotionVO> getAll() {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					promotionVO = new PromotionVO();
					promotionVO.setPromotion_no(rs.getString("promotion_no"));
					promotionVO.setMerchant_no(rs.getString("merchant_no"));
					promotionVO.setProduct_no(rs.getString("product_no"));
					promotionVO.setPromotion_name(rs.getString("promotion_name"));
					promotionVO.setPromotion_start(rs.getDate("promotion_start"));
					promotionVO.setPromotion_end(rs.getDate("promotion_end"));
					promotionVO.setPromotion_pr(rs.getDouble("promotion_pr"));
					promotionVO.setPromotion_ps(rs.getString("promotion_ps"));
					promotionVO.setPromotion_status(rs.getString("promotion_status"));
					promotionVO.setPromotion_img(rs.getBytes("promotion_img"));
					list.add(promotionVO); // Store the row in the list
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
		
		// 查詢單一廠商有關廣告
		@Override
		public List<PromotionVO> findByMerchantNo(String merchant_no) {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MERCHANT);

				pstmt.setString(1, merchant_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				// empVo 也稱為 Domain objects

				promotionVO = new PromotionVO();
				promotionVO.setPromotion_no(rs.getString("promotion_no"));
				promotionVO.setMerchant_no(rs.getString("merchant_no"));
				promotionVO.setProduct_no(rs.getString("product_no"));
				promotionVO.setPromotion_name(rs.getString("promotion_name"));
				promotionVO.setPromotion_start(rs.getDate("promotion_start"));
				promotionVO.setPromotion_end(rs.getDate("promotion_end"));
				promotionVO.setPromotion_pr(rs.getDouble("promotion_pr"));
				promotionVO.setPromotion_ps(rs.getString("promotion_ps"));
				promotionVO.setPromotion_status(rs.getString("promotion_status"));
				promotionVO.setPromotion_img(rs.getBytes("promotion_img"));
				list.add(promotionVO); // Store the row in the list
							
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
					   }catch (Exception e) {
						e.printStackTrace(System.err);
					   }
					}
				}
					return list;
				}
		
		//更改單一廣告上下架狀態
		@Override
		public void updatePromotionStatus(PromotionVO promotionVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_ONE_PROMOTION_STATUS);
				
				pstmt.setString(1, promotionVO.getPromotion_status());

				pstmt.setString(2, promotionVO.getPromotion_no());

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
		
		//查詢單一狀態有關廣告
		@Override
		public List<PromotionVO> getOneStatusOfAll(String promotion_status) {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STATUS_PROMOTION);
				
				pstmt.setString(1, promotion_status);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					promotionVO = new PromotionVO();
					promotionVO.setPromotion_no(rs.getString("promotion_no"));
					promotionVO.setMerchant_no(rs.getString("merchant_no"));
					promotionVO.setProduct_no(rs.getString("product_no"));
					promotionVO.setPromotion_name(rs.getString("promotion_name"));
					promotionVO.setPromotion_start(rs.getDate("promotion_start"));
					promotionVO.setPromotion_end(rs.getDate("promotion_end"));
					promotionVO.setPromotion_pr(rs.getDouble("promotion_pr"));
					promotionVO.setPromotion_ps(rs.getString("promotion_ps"));
					promotionVO.setPromotion_status(rs.getString("promotion_status"));
					promotionVO.setPromotion_img(rs.getBytes("promotion_img"));
					list.add(promotionVO); // Store the row in the list
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
		
		
		
		//用商品編號找出廣告VO(廣告折扣用)
		@Override
		public PromotionVO findByProductNo(String product_no) {
			PromotionVO promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_PRODUCTNO);

				pstmt.setString(1, product_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects

					promotionVO = new PromotionVO();
					promotionVO.setPromotion_no(rs.getString("promotion_no"));
					promotionVO.setMerchant_no(rs.getString("merchant_no"));
					promotionVO.setProduct_no(rs.getString("product_no"));
					promotionVO.setPromotion_name(rs.getString("promotion_name"));
					promotionVO.setPromotion_start(rs.getDate("promotion_start"));
					promotionVO.setPromotion_end(rs.getDate("promotion_end"));
					promotionVO.setPromotion_pr(rs.getDouble("promotion_pr"));
					promotionVO.setPromotion_ps(rs.getString("promotion_ps"));
					promotionVO.setPromotion_status(rs.getString("promotion_status"));
					promotionVO.setPromotion_img(rs.getBytes("promotion_img"));
					
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
			return promotionVO;
		}
		
		
}
