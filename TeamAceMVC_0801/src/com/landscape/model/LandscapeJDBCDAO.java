package com.landscape.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class LandscapeJDBCDAO implements LandscapeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAMACE";
	String passwd = "teamace";


	private static final String INSERT_STMT = 
		"INSERT INTO landscape (landscape_no,landscape_lng,landscape_lat,landscape_name,landscape_status,landscape_add)"
		+ " VALUES ( 'LS'||LPAD(to_char(LANDSCAPE_seq.NEXTVAL),5,0),?, ?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT landscape_no,landscape_lng,landscape_lat,landscape_name,landscape_status,"
		+"landscape_add FROM LANDSCAPE order by landScape_no";
	private static final String GET_ONE_STMT = 
		"SELECT landscape_no,landscape_lng,landscape_lat,landscape_name,landscape_status,landscape_add from landscape where landscape_no = ?";
	private static final String UPDATE = 
			"UPDATE landscape set  landscape_lng=?, landscape_lat=?, landscape_name=?, landscape_status=?,landscape_add=? where landscape_no = ?";
	private static final String DELETE = 
			"DELETE FROM landscape where landscape_no = ?";
	
	@Override
	public void insert(LandscapeVO landscapeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setDouble(1, landscapeVO.getLandscape_lng());
			pstmt.setDouble(2, landscapeVO.getLandscape_lat());
			pstmt.setString(3, landscapeVO.getLandscape_name());
			pstmt.setString(4, landscapeVO.getLandscape_status());
			pstmt.setString(5, landscapeVO.getLandscape_add());
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
	public List<LandscapeVO> getAll() {
		List<LandscapeVO> list = new ArrayList<LandscapeVO>();
		LandscapeVO landscapeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				// LandScapeVO  Domain objects
				landscapeVO = new LandscapeVO();
				landscapeVO.setLandscape_no(rs.getString("landscape_no"));
				landscapeVO.setLandscape_lng(rs.getDouble("landscape_lng"));
				landscapeVO.setLandscape_lat(rs.getDouble("landscape_lat"));
				landscapeVO.setLandscape_name(rs.getString("landscape_name"));
				landscapeVO.setLandscape_status(rs.getString("landscape_status"));
				landscapeVO.setLandscape_add(rs.getString("landscape_add"));
				list.add(landscapeVO); // Store the row in the list
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
	
	
	
	public LandscapeVO findByPrimaryKey(String landscape_no) {

		LandscapeVO landscapeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, landscape_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				landscapeVO = new LandscapeVO();
				landscapeVO.setLandscape_no(rs.getString("landscape_no"));
				landscapeVO.setLandscape_lng(rs.getDouble("landscape_lng"));
				landscapeVO.setLandscape_lat(rs.getDouble("landscape_lat"));
				landscapeVO.setLandscape_name(rs.getString("landscape_name"));
				landscapeVO.setLandscape_status(rs.getString("landscape_status"));
				landscapeVO.setLandscape_add(rs.getString("landscape_add"));
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
		return landscapeVO;
	}
	
	@Override
	public void update(LandscapeVO landscapeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDouble(1, landscapeVO.getLandscape_lng());
			pstmt.setDouble(2, landscapeVO.getLandscape_lat());
			pstmt.setString(3, landscapeVO.getLandscape_name());
			pstmt.setString(4, landscapeVO.getLandscape_status());
			pstmt.setString(5, landscapeVO.getLandscape_add());
			pstmt.setString(6, landscapeVO.getLandscape_no());
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
	public void delete(String landscape_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, landscape_no);
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
	public Set<String> getAllCity() {
		Set<String> set = new LinkedHashSet<String>();
		LandscapeVO landscapeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {				
				// LandScapeVO  Domain objects
				landscapeVO = new LandscapeVO();
//				set.add(rs.getString("landscape_add").substring(0, 3));
				String  landscape_add=rs.getString("landscape_add") ;
				if(landscape_add!=null) {
					String city = String.valueOf(landscape_add.charAt(2));			
					if(city.equals("市") || city.equals("縣")){
						set.add(landscape_add.substring(0,3));
					}
				}
				
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return set;
	}

	public static void main(String[] args) {

		LandscapeJDBCDAO dao = new LandscapeJDBCDAO();
//		List<LandscapeVO> list = dao.getAll();
//		for (LandscapeVO landscapeVO : list) {
//			System.out.print(landscapeVO.getLandscape_no() + ",");
//			System.out.print(landscapeVO.getLandscape_lng() + ",");
//			System.out.print(landscapeVO.getLandscape_lat() + ",");
//			System.out.print(landscapeVO.getLandscape_name() + ",");
//			System.out.print(landscapeVO.getLandscape_status() + ",");
//			System.out.print(landscapeVO.getLandscape_add() + ",");
//			System.out.println();
//		}
		
//		LandscapeVO landscapeVO = dao.findByPrimaryKey("LS00001");
//		System.out.print(landscapeVO.getLandscape_no() + ",");
//		System.out.print(landscapeVO.getLandscape_lng() + ",");
//		System.out.print(landscapeVO.getLandscape_lat() + ",");
//		System.out.print(landscapeVO.getLandscape_name() + ",");
//		System.out.print(landscapeVO.getLandscape_status() + ",");
//		System.out.print(landscapeVO.getLandscape_add() + ",");
		
		Set<String> set = dao.getAllCity();
		Iterator<String> itr = set.iterator();
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print(element + " ");
	      }
	}
}
