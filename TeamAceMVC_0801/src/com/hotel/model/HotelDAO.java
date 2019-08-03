package com.hotel.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class HotelDAO implements HotelDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = 
		"SELECT * FROM hotel order by hotel_no";
	private static final String GET_ONE_STMT = 
			"SELECT hotel_no,hotel_lng,hotel_lat,hotel_name,hotel_add from hotel where hotel_lng = ? AND hotel_lat = ?";
	private static final String GET_ONE_BY_HOTEL_NAME = 
			"SELECT hotel_no,hotel_lng,hotel_lat,hotel_name,hotel_add from hotel where hotel_name = ?";
	

	@Override
	public List<HotelVO> getAll() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {				
				// LandScapeVO  Domain objects
				hotelVO = new HotelVO();
				hotelVO.setHotel_no(rs.getString("hotel_no"));
				hotelVO.setHotel_lng(rs.getDouble("hotel_lng"));
				hotelVO.setHotel_lat(rs.getDouble("hotel_lat"));
				hotelVO.setHotel_name(rs.getString("hotel_name"));
				hotelVO.setHotel_add(rs.getString("hotel_add"));
				list.add(hotelVO); // Store the row in the list
			}

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
	
	public HotelVO findByLngLat(Double hotel_lng, Double hotel_lat) {

		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setDouble(1, hotel_lng);
			pstmt.setDouble(2, hotel_lat);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelVO = new HotelVO();
				hotelVO.setHotel_no(rs.getString("hotel_no"));
				hotelVO.setHotel_lng(rs.getDouble("hotel_lng"));
				hotelVO.setHotel_lat(rs.getDouble("hotel_lat"));
				hotelVO.setHotel_name(rs.getString("hotel_name"));
				hotelVO.setHotel_add(rs.getString("hotel_add"));
			}

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
		return hotelVO;
	}

	@Override
	public HotelVO findByHotelName(String hotel_name) {
		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_HOTEL_NAME);

			pstmt.setString(1, hotel_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelVO = new HotelVO();
				hotelVO.setHotel_no(rs.getString("hotel_no"));
				hotelVO.setHotel_lng(rs.getDouble("hotel_lng"));
				hotelVO.setHotel_lat(rs.getDouble("hotel_lat"));
				hotelVO.setHotel_name(rs.getString("hotel_name"));
				hotelVO.setHotel_add(rs.getString("hotel_add"));
			}

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
		return hotelVO;
	}	
}