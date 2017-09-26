package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.zongZeModify2;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class zongZeModifyDao2 {

	public List<zongZeModify2> zongZeModifyList2(Connection con, PageBean pageBean, zongZeModify2 s_zongZeModify2) throws Exception {
		List<zongZeModify2> zongZeModifyList2 = new ArrayList<zongZeModify2>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_zongzemodify t1");
		if (StringUtil.isNotEmpty(s_zongZeModify2.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zongZeModify2.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			zongZeModify2 zongZeModify2 = new zongZeModify2();
			zongZeModify2.setProjectId(rs.getInt("projectId"));
			zongZeModify2.setCenterId(rs.getInt("centerId"));
			zongZeModify2.setName(rs.getString("name"));
			zongZeModify2.setCenterName(rs.getString("centerName"));
			zongZeModify2.setBonustotal(rs.getInt("bonustotal"));			
			zongZeModify2.setProMoneyRatioMax(rs.getDouble("proMoneyRatioMax"));
			zongZeModify2.setProMoneyRatioMin(rs.getDouble("proMoneyRatioMin"));
			zongZeModify2.setProBonusRatioMin(rs.getDouble("proBonusRatioMin"));
			zongZeModify2.setProMoneyMax(rs.getString("proMoneyMax"));
			zongZeModify2.setProMoneyMin(rs.getString("proMoneyMin"));
			zongZeModify2.setProBonusMin(rs.getString("proBonusMin"));
			zongZeModify2.setZhongxinMoney(rs.getString("zhongxinMoney"));
			

			zongZeModifyList2.add(zongZeModify2);
		}
		return zongZeModifyList2;
	}

	/**
	 * zongze count function
	 * 
	 * @param con
	 * @param s_ZongZe
	 * @return
	 * @throws Exception
	 */
	public int zongZeModifyCount(Connection con, zongZeModify2 s_zongZeModify2) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_zongzemodify t1");
		if (StringUtil.isNotEmpty(s_zongZeModify2.getCenterName())) {
			sb.append(" where t1.projectName like '%" + s_zongZeModify2.getCenterName() + "%'");
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
	public zongZeModify2 zongZeModifyShow2(Connection con, String projectId) throws Exception {
		String sql = "select * from t_zongzemodify t1 where t1.projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, projectId);
		ResultSet rs = pstmt.executeQuery();
		zongZeModify2 zongZeModify2 = new zongZeModify2();
		if (rs.next()) {		
			zongZeModify2.setProjectId(rs.getInt("projectId"));
			zongZeModify2.setName(rs.getString("projectName"));
			zongZeModify2.setCenterId(rs.getInt("centerId"));
			zongZeModify2.setCenterName(rs.getString("centerName"));
			zongZeModify2.setBonustotal(rs.getInt("bonustotal"));			
			zongZeModify2.setProMoneyRatioMax(rs.getDouble("proMoneyRatioMax"));
			zongZeModify2.setProMoneyRatioMin(rs.getDouble("proMoneyRatioMin"));
			zongZeModify2.setProBonusRatioMin(rs.getDouble("proBonusRatioMin"));
			zongZeModify2.setProMoneyMax(rs.getString("proMoneyMax"));
			zongZeModify2.setProMoneyMin(rs.getString("proMoneyMin"));
			zongZeModify2.setProBonusMin(rs.getString("proBonusMin"));
			zongZeModify2.setZhongxinMoney(rs.getString("zhongxinMoney"));
		}
		return zongZeModify2;
	}

	/**
	 * ZONGZE ADD fUNCTION
	 * 
	 * @param con
	 * @param zongZe
	 * @return
	 * @throws Exception
	 */

	
//	public int zongZeModifyAdd(Connection con, zongZeModify2 zongZeModify2) throws Exception {
//		String sql = "insert into t_zongzemodify values(?,?,?,?,?,?,?,?,null)";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(1, zongZeModify2.getProjectId());
//		pstmt.setString(2, zongZeModify2.getProjectName());
//		pstmt.setInt(3, zongZeModify2.getEmployId());
//		pstmt.setString(4, zongZeModify2.getEmployName());
//		pstmt.setInt(5, zongZeModify2.getDepId());
//		pstmt.setString(6, zongZeModify2.getDepName());
//		return pstmt.executeUpdate();
//	}
//
//	/**
//	 * Delete Function
//	 * 
//	 * @param con
//	 * @param centerId
//	 * @return
//	 * @throws Exception
//	 */
//	public int zongZeModifyDelete(Connection con, String projectId) throws Exception {
//		String sql = "delete from t_zongzemodify where projectId=?";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, projectId);
//		return pstmt.executeUpdate();
//	}

	/**
	 * ZONGZE UPDATE FUNCTION
	 * 
	 * @param con
	 * @param zongze
	 * @return
	 * @throws Exception
	 */
//	public int zongZeModifyUpdate(Connection con, zongZeModify2 zongZeModify2) throws Exception {
//		String sql = "update t_project set financialId=?,projectName=?,orderPerformance=?,requestPerformance=?,supportPerformance=?,projectStartDate=?,projectEndDate=? where projectId=?";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(8, zongZeModify.getProjectId());
//		pstmt.setString(1, zongZeModify.getFinancialId());
//		pstmt.setString(2, zongZeModify.getProjectName());
//		pstmt.setDouble(3, zongZeModify.getOrderPerformance());
//		pstmt.setDouble(4, zongZeModify.getRequestPerformance());
//		pstmt.setDouble(5, zongZeModify.getSupportPerformance());
//		pstmt.setDate(6, new java.sql.Date(zongZeModify.getProjectStartDate().getTime()));
//		pstmt.setDate(7, new java.sql.Date(zongZeModify.getProjectEndDate().getTime()));
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
		String sql = "select * from t_zongzemodify t1 where t1.CenterName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
