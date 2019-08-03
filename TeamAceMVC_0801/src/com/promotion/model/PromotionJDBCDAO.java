package com.promotion.model;

import java.util.*;

import com.merchant.model.MerchantVO;

import java.sql.*;

public class PromotionJDBCDAO implements PromotionDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G1MER";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO promotion (promotion_no,merchant_no,product_no,promotion_name,promotion_start,promotion_end,promotion_pr,promotion_ps,promotion_status) VALUES ('PR'||LPAD(to_char(promotion_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion order by promotion_no";
		private static final String GET_ONE_STMT = 
			"SELECT promotion_no,merchant_no,product_no,promotion_name,to_char(promotion_start,'yyyy-mm-dd') promotion_start,to_char(promotion_end,'yyyy-mm-dd') promotion_end,promotion_pr,promotion_ps,promotion_status,promotion_img FROM promotion where promotion_no = ?";
		private static final String DELETE = 
			"DELETE FROM promotion where promotion_no = ?";
		private static final String UPDATE = 
			"UPDATE promotion set merchant_no=?, product_no=?, promotion_name=?, promotion_start=?, promotion_end=?, promotion_pr=?, promotion_ps=?, promotion_status=? where promotion_no = ?";
		
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, promotionVO.getMerchant_no());
				pstmt.setString(2, promotionVO.getProduct_no());
				pstmt.setString(3, promotionVO.getPromotion_name());
				pstmt.setDate(4, promotionVO.getPromotion_start());
				pstmt.setDate(5, promotionVO.getPromotion_end());
				pstmt.setDouble(6, promotionVO.getPromotion_pr());
				pstmt.setString(7, promotionVO.getPromotion_ps());
				pstmt.setString(8, promotionVO.getPromotion_status());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, promotionVO.getMerchant_no());
				pstmt.setString(2, promotionVO.getProduct_no());
				pstmt.setString(3, promotionVO.getPromotion_name());
				pstmt.setDate(4, promotionVO.getPromotion_start());
				pstmt.setDate(5, promotionVO.getPromotion_end());
				pstmt.setDouble(6, promotionVO.getPromotion_pr());
				pstmt.setString(7, promotionVO.getPromotion_ps());
				pstmt.setString(8, promotionVO.getPromotion_status());
				
				pstmt.setString(9, promotionVO.getPromotion_no());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(String productreport_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, productreport_no);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public PromotionVO findByPrimaryKey(String promotion_no) {
			PromotionVO promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		
		
		// 查詢單一廠商有關廣告
		@Override
		public List<PromotionVO> findByMerchantNo(String merchant_no) {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		
		
		//更改單一廣告上下架狀態
		@Override
		public void updatePromotionStatus(PromotionVO promotionVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_ONE_PROMOTION_STATUS);

				pstmt.setString(1, promotionVO.getPromotion_status());

				pstmt.setString(2, promotionVO.getPromotion_no());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		
		
		//查詢單一上下架狀態傳回廣告
		@Override
		public List<PromotionVO> getOneStatusOfAll(String promotion_status) {
			List<PromotionVO> list = new ArrayList<PromotionVO>();
			PromotionVO promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		
		
		
		//用商品編號找出廣告VO(廣告折扣用)
		@Override
		public PromotionVO findByProductNo(String product_no) {
			PromotionVO promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
			return promotionVO;
		}
		
		public static void main(String[] args) {
			
			PromotionJDBCDAO dao = new PromotionJDBCDAO();

			// 新增
//			PromotionVO promotionVO1 = new PromotionVO();
//			promotionVO1.setMerchant_no("ME00001");
//			promotionVO1.setProduct_no("PR00001");
//			promotionVO1.setPromotion_name("2百年好棋體驗活動");
//			promotionVO1.setPromotion_start(java.sql.Date.valueOf("2019-06-19"));
//			promotionVO1.setPromotion_end(java.sql.Date.valueOf("2019-06-20"));
//			promotionVO1.setPromotion_pr(new Double(0.9));
//			promotionVO1.setPromotion_ps("2製作榮獲高雄市十大伴手禮百年棋餅");
//			promotionVO1.setPromotion_status("B2");
//			
//			dao.insert(promotionVO1);
			
			// 修改
//			PromotionVO promotionVO2 = new PromotionVO();
//			promotionVO2.setPromotion_no("PR00007");
//			promotionVO2.setMerchant_no("ME00001");
//			promotionVO2.setProduct_no("PR00001");
//			promotionVO2.setPromotion_name("3百年好棋體驗活動");
//			promotionVO2.setPromotion_start(java.sql.Date.valueOf("2019-06-19"));
//			promotionVO2.setPromotion_end(java.sql.Date.valueOf("2019-06-20"));
//			promotionVO2.setPromotion_pr(new Double(0.9));
//			promotionVO2.setPromotion_ps("3製作榮獲高雄市十大伴手禮百年棋餅");
//			promotionVO2.setPromotion_status("B2");
//			dao.update(promotionVO2);
			
			// 刪除
//			dao.delete("PR00007");
			
			
			
			// 查詢
//			PromotionVO promotionVO3 = dao.findByPrimaryKey("PR00001");
//			System.out.print(promotionVO3.getPromotion_no() + ",");
//			System.out.print(promotionVO3.getMerchant_no() + ",");
//			System.out.print(promotionVO3.getProduct_no() + ",");
//			System.out.print(promotionVO3.getPromotion_name() + ",");
//			System.out.print(promotionVO3.getPromotion_start() + ",");
//			System.out.print(promotionVO3.getPromotion_end() + ",");
//			System.out.print(promotionVO3.getPromotion_pr() + ",");
//			System.out.print(promotionVO3.getPromotion_ps() + ",");
//			System.out.print(promotionVO3.getPromotion_status() + ",");
//			System.out.print(promotionVO3.getPromotion_img() + ",");
//			
//			
//			System.out.println("---------------------");
			
			// 查詢
//			List<PromotionVO> list = dao.getAll();
//			for (PromotionVO aPromotion : list) {
//				System.out.print(aPromotion.getPromotion_no() + ",");
//				System.out.print(aPromotion.getMerchant_no() + ",");
//				System.out.print(aPromotion.getProduct_no() + ",");
//				System.out.print(aPromotion.getPromotion_name() + ",");
//				System.out.print(aPromotion.getPromotion_start() + ",");
//				System.out.print(aPromotion.getPromotion_end() + ",");
//				System.out.print(aPromotion.getPromotion_pr() + ",");
//				System.out.print(aPromotion.getPromotion_ps() + ",");
//				System.out.print(aPromotion.getPromotion_status() + ",");
//				System.out.print(aPromotion.getPromotion_img() + ",");
//				System.out.println();
//			}
			
			
//			// 查詢單一廠商有關廣告
//			List<PromotionVO> list = dao.findByMerchantNo("ME00002");
//			for (PromotionVO aPromotion : list) {
//				System.out.print(aPromotion.getPromotion_no() + ",");
//				System.out.print(aPromotion.getMerchant_no() + ",");
//				System.out.print(aPromotion.getProduct_no() + ",");
//				System.out.print(aPromotion.getPromotion_name() + ",");
//				System.out.print(aPromotion.getPromotion_start() + ",");
//				System.out.print(aPromotion.getPromotion_end() + ",");
//				System.out.print(aPromotion.getPromotion_pr() + ",");
//				System.out.print(aPromotion.getPromotion_ps() + ",");
//				System.out.print(aPromotion.getPromotion_status() + ",");
//				System.out.print(aPromotion.getPromotion_img() + ",");
//				System.out.println();
//			}
				
			// 修改單一廣告上下架狀態
//			PromotionVO promotionVO5 = new PromotionVO();
//			promotionVO5.setPromotion_no("PR00002");
//			promotionVO5.setPromotion_status("B1");
//			dao.updatePromotionStatus(promotionVO5);
			
			
			// 查詢單一狀態有關廠商
//			List<PromotionVO> list = dao.getOneStatusOfAll("B3");
//			for (PromotionVO aPromotion : list) {
//				System.out.print(aPromotion.getPromotion_no() + ",");
//				System.out.print(aPromotion.getMerchant_no() + ",");
//				System.out.print(aPromotion.getProduct_no() + ",");
//				System.out.print(aPromotion.getPromotion_name() + ",");
//				System.out.print(aPromotion.getPromotion_start() + ",");
//				System.out.print(aPromotion.getPromotion_end() + ",");
//				System.out.print(aPromotion.getPromotion_pr() + ",");
//				System.out.print(aPromotion.getPromotion_ps() + ",");
//				System.out.print(aPromotion.getPromotion_status() + ",");
//				System.out.print(aPromotion.getPromotion_img() + ",");
//				System.out.println();
//			}
			
			
			//用商品編號找出廣告VO(廣告折扣用)
//			PromotionVO promotionVO3 = dao.findByProductNo("PR00001");
//			System.out.print(promotionVO3.getPromotion_no() + ",");
//			System.out.print(promotionVO3.getMerchant_no() + ",");
//			System.out.print(promotionVO3.getProduct_no() + ",");
//			System.out.print(promotionVO3.getPromotion_name() + ",");
//			System.out.print(promotionVO3.getPromotion_start() + ",");
//			System.out.print(promotionVO3.getPromotion_end() + ",");
//			System.out.print(promotionVO3.getPromotion_pr() + ",");
//			System.out.print(promotionVO3.getPromotion_ps() + ",");
//			System.out.print(promotionVO3.getPromotion_status() + ",");
//			System.out.print(promotionVO3.getPromotion_img() + ",");
//			
//			
//			System.out.println("---------------------");
			
			
			
		}

}
