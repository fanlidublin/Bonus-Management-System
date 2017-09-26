package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ntt.model.Admin;
import com.ntt.model.Charger;
import com.ntt.model.Staff;

public class UserDao {

	public Admin Login(Connection con, Admin admin) throws Exception {
		Admin resultAdmin = null;
		String sql = "select * from t_admin where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, admin.getUserName());
		pstmt.setString(2, admin.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultAdmin = new Admin();
			resultAdmin.setAdminId(rs.getInt("adminId"));
			resultAdmin.setUserName(rs.getString("userName"));
			resultAdmin.setPassword(rs.getString("password"));
		}
		return resultAdmin;
	}

	public Charger Login(Connection con, Charger charger) throws Exception {
		Charger resultDormManager = null;
		String sql = "select * from t_charger where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, charger.getUserName());
		pstmt.setString(2, charger.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultDormManager = new Charger();
			resultDormManager.setChargerId(rs.getInt("chargerId"));
			resultDormManager.setUserName(rs.getString("userName"));
			resultDormManager.setPassword(rs.getString("password"));
		}
		return resultDormManager;
	}

	public Staff Login(Connection con, Staff staff) throws Exception {
		Staff resultStaff = null;
		String sql = "select * from t_staff where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, staff.getUserName());
		pstmt.setString(2, staff.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultStaff = new Staff();
			resultStaff.setStaffId(rs.getInt("staffId"));
			resultStaff.setUserName(rs.getString("userName"));
			resultStaff.setPassword(rs.getString("password"));
		}
		return resultStaff;
	}

	public int adminUpdate(Connection con, int adminId, String password) throws Exception {
		String sql = "update t_admin set password=? where adminId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, adminId);
		return pstmt.executeUpdate();
	}

	public int managerUpdate(Connection con, int chargerId, String password) throws Exception {
		String sql = "update t_charger set password=? where chargerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, chargerId);
		return pstmt.executeUpdate();
	}

	public int studentUpdate(Connection con, int staffId, String password) throws Exception {
		String sql = "update t_staff set password=? where staffId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, staffId);
		return pstmt.executeUpdate();
	}
	
	public Charger Role(Connection con, String userName) throws Exception {
		Charger role = null;
		String sql = "select centerId from t_charger t1 where t1.userName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			role = new Charger();
			role.setChargerId(rs.getInt("chargerId"));
		}
		return role;
	}

}
