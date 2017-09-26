package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.AppliBonus;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class AppliBonusDao {

	public List<AppliBonus> appliBonusList(Connection con, PageBean pageBean, AppliBonus s_AppliBonus) throws Exception {
		List<AppliBonus> appliBonusList = new ArrayList<AppliBonus>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_project t1");
		if (StringUtil.isNotEmpty(s_AppliBonus.getProjectName())) {
			sb.append(" where t1.projectName like '%" + s_AppliBonus.getProjectName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			AppliBonus appliBonus = new AppliBonus();
			appliBonus.setProjectId(rs.getInt("projectId"));
			appliBonus.setFinancialId(rs.getString("financialId"));
			appliBonus.setProjectName(rs.getString("projectName"));
			appliBonus.setOrderPerformance(rs.getDouble("orderPerformance"));
			appliBonus.setRequestPerformance(rs.getDouble("requestPerformance"));
			appliBonus.setSupportPerformance(rs.getDouble("supportPerformance"));
			appliBonus.setProjectStartDate(rs.getDate("projectStartDate"));
			appliBonus.setProjectEndDate(rs.getDate("projectEndDate"));
			appliBonus.setCenterId(rs.getInt("centerId"));
			appliBonusList.add(appliBonus);
		}
		return appliBonusList;
	}

	/**
	 * zongze count function
	 * 
	 * @param con
	 * @param s_ZongZe
	 * @return
	 * @throws Exception
	 */
	public int appliBonusCount(Connection con, AppliBonus s_AppliBonus) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_project t1");
		if (StringUtil.isNotEmpty(s_AppliBonus.getProjectName())) {
			sb.append(" where t1.projectName like '%" + s_AppliBonus.getProjectName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * zongze show function
	 * 
	 * @param con
	 * @param centerId
	 * @return
	 * @throws Exception
	 */
	public AppliBonus appliBonusShow(Connection con, String projectId) throws Exception {
		String sql = "select * from t_project t1 where t1.projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, projectId);
		ResultSet rs = pstmt.executeQuery();
		AppliBonus appliBonus = new AppliBonus();
		if (rs.next()) {
			appliBonus.setProjectId(rs.getInt("projectId"));
			appliBonus.setFinancialId(rs.getString("financialId"));
			appliBonus.setProjectName(rs.getString("projectName"));
			appliBonus.setOrderPerformance(rs.getDouble("orderPerformance"));
			appliBonus.setRequestPerformance(rs.getDouble("requestPerformance"));
			appliBonus.setSupportPerformance(rs.getDouble("supportPerformance"));
			appliBonus.setProjectStartDate(rs.getDate("projectStartDate"));
			appliBonus.setProjectEndDate(rs.getDate("projectEndDate"));
		}
		return appliBonus;
	}

	/**
	 * ZONGZE ADD fUNCTION
	 * 
	 * @param con
	 * @param zongZe
	 * @return
	 * @throws Exception
	 */
	public int appliBonusAdd(Connection con, AppliBonus appliBonus) throws Exception {
		String sql = "insert into t_project values(null,?,?,?,?,?,?,?,null)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, appliBonus.getFinancialId());
		pstmt.setString(2, appliBonus.getProjectName());
		pstmt.setDouble(3, appliBonus.getOrderPerformance());
		pstmt.setDouble(4, appliBonus.getRequestPerformance());
		pstmt.setDouble(5, appliBonus.getSupportPerformance());
		pstmt.setDate(6, new java.sql.Date(appliBonus.getProjectStartDate().getTime()));
		pstmt.setDate(7, new java.sql.Date(appliBonus.getProjectEndDate().getTime()));
		return pstmt.executeUpdate();
	}

	/**
	 * Delete Function
	 * 
	 * @param con
	 * @param centerId
	 * @return
	 * @throws Exception
	 */
	public int appliBonusDelete(Connection con, String projectId) throws Exception {
		String sql = "delete from t_project where projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, projectId);
		return pstmt.executeUpdate();
	}

	/**
	 * ZONGZE UPDATE FUNCTION
	 * 
	 * @param con
	 * @param zongze
	 * @return
	 * @throws Exception
	 */
	public int appliBonusUpdate(Connection con, AppliBonus appliBonus) throws Exception {
		String sql = "update t_project set financialId=?,projectName=?,orderPerformance=?,requestPerformance=?,supportPerformance=?,projectStartDate=?,projectEndDate=? where projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, appliBonus.getFinancialId());
		pstmt.setString(2, appliBonus.getProjectName());
		pstmt.setDouble(3, appliBonus.getOrderPerformance());
		pstmt.setDouble(4, appliBonus.getRequestPerformance());
		pstmt.setDouble(5, appliBonus.getSupportPerformance());
		pstmt.setDate(6, new java.sql.Date(appliBonus.getProjectStartDate().getTime()));
		pstmt.setDate(7, new java.sql.Date(appliBonus.getProjectEndDate().getTime()));
		pstmt.setInt(8, appliBonus.getProjectId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByProjectName(Connection con, String name) throws Exception {
		String sql = "select * from t_project t1 where t1.projectName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
