package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.AppliBonus2;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class AppliBonusDao2 {

	public List<AppliBonus2> appliBonusList2(Connection con, PageBean pageBean, AppliBonus2 s_AppliBonus2, String projectId) throws Exception {
		List<AppliBonus2> appliBonusList2 = new ArrayList<AppliBonus2>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_applibonus t1 where t1.projectId=?");
		if (StringUtil.isNotEmpty(s_AppliBonus2.getProjectName())) {
			sb.append(" where t1.projectName like '%" + s_AppliBonus2.getProjectName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		pstmt.setString(1, projectId);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			AppliBonus2 appliBonus2 = new AppliBonus2();
			appliBonus2.setProjectId(rs.getInt("projectId"));
			appliBonus2.setFinancialId(rs.getString("financialId"));
			appliBonus2.setProjectName(rs.getString("projectName"));
			appliBonus2.setEmployId(rs.getInt("employId"));
			appliBonus2.setEmployName(rs.getString("employName"));
			appliBonus2.setDepId(rs.getInt("depId"));
			appliBonus2.setDepName(rs.getString("depName"));
			appliBonus2.setZhengshiMonth(rs.getDouble("zhengshiMonth"));
			appliBonus2.setBPMonth(rs.getDouble("BPMonth"));
			appliBonus2.setLianxieMonth(rs.getDouble("lianxieMonth"));
			appliBonus2.setLizhiMonth(rs.getDouble("lizhiMonth"));
			appliBonus2.setApplyDate(rs.getString("applyDate"));
			appliBonus2.setApplyDate2(rs.getDate("applyDate2"));
			appliBonus2.setBonustotal(rs.getString("bonustotal"));
			appliBonus2.setCenterId(rs.getInt("centerId"));
			appliBonusList2.add(appliBonus2);
		}
		return appliBonusList2;
	}
	
	
	
	public List<AppliBonus2> appliBonusList3(Connection con, PageBean pageBean, AppliBonus2 s_AppliBonus2, String projectId) throws Exception {
		List<AppliBonus2> appliBonusList3 = new ArrayList<AppliBonus2>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_applibonus t1");
		if (StringUtil.isNotEmpty(s_AppliBonus2.getProjectName())) {
			sb.append(" where t1.projectName like '%" + s_AppliBonus2.getProjectName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			AppliBonus2 appliBonus2 = new AppliBonus2();
			appliBonus2.setProjectId(rs.getInt("projectId"));
			appliBonus2.setFinancialId(rs.getString("financialId"));
			appliBonus2.setProjectName(rs.getString("projectName"));
			appliBonus2.setEmployId(rs.getInt("employId"));
			appliBonus2.setEmployName(rs.getString("employName"));
			appliBonus2.setDepId(rs.getInt("depId"));
			appliBonus2.setDepName(rs.getString("depName"));
			appliBonus2.setZhengshiMonth(rs.getDouble("zhengshiMonth"));
			appliBonus2.setBPMonth(rs.getDouble("BPMonth"));
			appliBonus2.setLianxieMonth(rs.getDouble("lianxieMonth"));
			appliBonus2.setLizhiMonth(rs.getDouble("lizhiMonth"));
			appliBonus2.setApplyDate(rs.getString("applyDate"));
			appliBonus2.setApplyDate2(rs.getDate("applyDate2"));
			appliBonus2.setBonustotal(rs.getString("bonustotal"));
			appliBonus2.setBonushuafen(rs.getString("bonushuafen"));
			appliBonus2.setCenterId(rs.getInt("centerId"));
			appliBonusList3.add(appliBonus2);
		}
		return appliBonusList3;
	}

	/**
	 * zongze count function
	 * 
	 * @param con
	 * @param s_ZongZe
	 * @return
	 * @throws Exception
	 */
	public int appliBonusCount(Connection con, AppliBonus2 s_AppliBonus2) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_applibonus t1");
		if (StringUtil.isNotEmpty(s_AppliBonus2.getProjectName())) {
			sb.append(" where t1.projectName like '%" + s_AppliBonus2.getProjectName() + "%'");
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
	public AppliBonus2 appliBonusShow2(Connection con, String projectId) throws Exception {
		String sql = "select * from t_applibonus t1 where t1.projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, projectId);
		ResultSet rs = pstmt.executeQuery();
		AppliBonus2 appliBonus2 = new AppliBonus2();
		if (rs.next()) {
			appliBonus2.setProjectId(rs.getInt("projectId"));
			appliBonus2.setProjectName(rs.getString("projectName"));
			appliBonus2.setEmployId(rs.getInt("employId"));
			appliBonus2.setEmployName(rs.getString("employName"));
			appliBonus2.setDepId(rs.getInt("depId"));
			appliBonus2.setDepName(rs.getString("depName"));
			appliBonus2.setZhengshiMonth(rs.getDouble("zhengshiMonth"));
			appliBonus2.setBPMonth(rs.getDouble("BPMonth"));
			appliBonus2.setLianxieMonth(rs.getDouble("lianxieMonth"));
			appliBonus2.setLizhiMonth(rs.getDouble("lizhiMonth"));
			appliBonus2.setApplyDate(rs.getString("applyDate"));
			appliBonus2.setApplyDate2(rs.getDate("applyDate2"));
			appliBonus2.setCenterId(rs.getInt("centerId"));
		}
		return appliBonus2;
	}

	/**
	 * ZONGZE ADD fUNCTION
	 * 
	 * @param con
	 * @param zongZe
	 * @return
	 * @throws Exception
	 */

	
	public int appliBonusAdd(Connection con, AppliBonus2 appliBonus2) throws Exception {
		String sql = "insert into t_applibonus values(?,?,?,?,?,?,?,?,null)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, appliBonus2.getProjectId());
		pstmt.setString(2, appliBonus2.getProjectName());
		pstmt.setInt(3, appliBonus2.getEmployId());
		pstmt.setString(4, appliBonus2.getEmployName());
		pstmt.setInt(5, appliBonus2.getDepId());
		pstmt.setString(6, appliBonus2.getDepName());
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
		String sql = "delete from t_applibonus where projectId=?";
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
//	public int appliBonusUpdate(Connection con, AppliBonus2 appliBonus2) throws Exception {
//		String sql = "update t_project set financialId=?,projectName=?,orderPerformance=?,requestPerformance=?,supportPerformance=?,projectStartDate=?,projectEndDate=? where projectId=?";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(8, appliBonus.getProjectId());
//		pstmt.setString(1, appliBonus.getFinancialId());
//		pstmt.setString(2, appliBonus.getProjectName());
//		pstmt.setDouble(3, appliBonus.getOrderPerformance());
//		pstmt.setDouble(4, appliBonus.getRequestPerformance());
//		pstmt.setDouble(5, appliBonus.getSupportPerformance());
//		pstmt.setDate(6, new java.sql.Date(appliBonus.getProjectStartDate().getTime()));
//		pstmt.setDate(7, new java.sql.Date(appliBonus.getProjectEndDate().getTime()));
//		return pstmt.executeUpdate();
//	}

	/**
	 * serach data by centername
	 * 
	 * @param con
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public boolean haveCenterByProjectName(Connection con, String name) throws Exception {
		String sql = "select * from t_applibonus t1 where t1.projectName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
