package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.xiZeModify2;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class xiZeModifyDao2 {

	public List<xiZeModify2> xiZeModifyList2(Connection con, PageBean pageBean, xiZeModify2 s_xiZeModify2) throws Exception {
		List<xiZeModify2> xiZeModifyList2 = new ArrayList<xiZeModify2>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_xiZeModify t1");
		if (StringUtil.isNotEmpty(s_xiZeModify2.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_xiZeModify2.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			xiZeModify2 xiZeModify2 = new xiZeModify2();
			xiZeModify2.setProjectId(rs.getInt("projectId"));
			xiZeModify2.setCenterId(rs.getInt("centerId"));
			xiZeModify2.setName(rs.getString("name"));
			xiZeModify2.setCenterName(rs.getString("centerName"));
			xiZeModify2.setBonustotal(rs.getInt("bonustotal"));			
			xiZeModify2.setZhongxin(rs.getString("zhongxin"));
			xiZeModify2.setBenbu(rs.getString("benbu"));
			xiZeModify2.setBumen(rs.getString("bumen"));
			xiZeModify2.setPm(rs.getString("pm"));
			

			xiZeModifyList2.add(xiZeModify2);
		}
		return xiZeModifyList2;
	}

	/**
	 * zongze count function
	 * 
	 * @param con
	 * @param s_ZongZe
	 * @return
	 * @throws Exception
	 */
	public int xiZeModifyCount(Connection con, xiZeModify2 s_xiZeModify2) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_xiZeModify t1");
		if (StringUtil.isNotEmpty(s_xiZeModify2.getCenterName())) {
			sb.append(" where t1.projectName like '%" + s_xiZeModify2.getCenterName() + "%'");
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
	public xiZeModify2 xiZeModifyShow2(Connection con, String projectId) throws Exception {
		String sql = "select * from t_xiZeModify t1 where t1.projectId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, projectId);
		ResultSet rs = pstmt.executeQuery();
		xiZeModify2 xiZeModify2 = new xiZeModify2();
		if (rs.next()) {		
			xiZeModify2.setProjectId(rs.getInt("projectId"));
			xiZeModify2.setCenterId(rs.getInt("centerId"));
			xiZeModify2.setName(rs.getString("name"));
			xiZeModify2.setCenterName(rs.getString("centerName"));
			xiZeModify2.setBonustotal(rs.getInt("bonustotal"));			
			xiZeModify2.setZhongxin(rs.getString("zhongxin"));
			xiZeModify2.setBenbu(rs.getString("benbu"));
			xiZeModify2.setBumen(rs.getString("bumen"));
			xiZeModify2.setPm(rs.getString("pm"));
		}
		return xiZeModify2;
	}

	/**
	 * ZONGZE ADD fUNCTION
	 * 
	 * @param con
	 * @param zongZe
	 * @return
	 * @throws Exception
	 */

	
//	public int xiZeModifyAdd(Connection con, xiZeModify2 xiZeModify2) throws Exception {
//		String sql = "insert into t_xiZeModify values(?,?,?,?,?,?,?,?,null)";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(1, xiZeModify2.getProjectId());
//		pstmt.setString(2, xiZeModify2.getProjectName());
//		pstmt.setInt(3, xiZeModify2.getEmployId());
//		pstmt.setString(4, xiZeModify2.getEmployName());
//		pstmt.setInt(5, xiZeModify2.getDepId());
//		pstmt.setString(6, xiZeModify2.getDepName());
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
//	public int xiZeModifyDelete(Connection con, String projectId) throws Exception {
//		String sql = "delete from t_xiZeModify where projectId=?";
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
//	public int xiZeModifyUpdate(Connection con, xiZeModify2 xiZeModify2) throws Exception {
//		String sql = "update t_project set financialId=?,projectName=?,orderPerformance=?,requestPerformance=?,supportPerformance=?,projectStartDate=?,projectEndDate=? where projectId=?";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(8, xiZeModify.getProjectId());
//		pstmt.setString(1, xiZeModify.getFinancialId());
//		pstmt.setString(2, xiZeModify.getProjectName());
//		pstmt.setDouble(3, xiZeModify.getOrderPerformance());
//		pstmt.setDouble(4, xiZeModify.getRequestPerformance());
//		pstmt.setDouble(5, xiZeModify.getSupportPerformance());
//		pstmt.setDate(6, new java.sql.Date(xiZeModify.getProjectStartDate().getTime()));
//		pstmt.setDate(7, new java.sql.Date(xiZeModify.getProjectEndDate().getTime()));
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
		String sql = "select * from t_xiZeModify t1 where t1.CenterName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
