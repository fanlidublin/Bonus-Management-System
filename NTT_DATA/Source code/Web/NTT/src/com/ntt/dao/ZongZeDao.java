package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ntt.model.PageBean;
import com.ntt.model.ZongZe;
import com.ntt.util.StringUtil;

public class ZongZeDao {

	/**
	 * zongZe List method
	 * 
	 * @param con
	 * @param pageBean
	 * @param s_zongZe
	 * @return
	 * @throws Exception
	 */
	public List<ZongZe> zongZeList(Connection con, PageBean pageBean, ZongZe s_zongZe) throws Exception {
		List<ZongZe> zongZesList = new ArrayList<ZongZe>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_zongze t1");
		if (StringUtil.isNotEmpty(s_zongZe.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zongZe.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ZongZe zongZe = new ZongZe();
			zongZe.setCenterId(rs.getInt("centerId"));
			zongZe.setCenterName(rs.getString("centerName"));
			zongZe.setBpRate(rs.getString("bpRate"));
			zongZe.setProjectRateUp(rs.getString("projectRateUp"));
			zongZe.setProjectRateDown(rs.getString("projectRateDown"));
			zongZe.setShareBonusRateUp(rs.getString("shareBonusRateUp"));
			zongZe.setShareBonusRateDown(rs.getString("shareBonusRateDown"));
			zongZe.setTuijianRate(rs.getString("tuijianRate"));
			zongZesList.add(zongZe);
		}
		return zongZesList;
	}

	/**
	 * zongze count function
	 * 
	 * @param con
	 * @param s_ZongZe
	 * @return
	 * @throws Exception
	 */
	public int zongZeCount(Connection con, ZongZe s_ZongZe) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_zongze t1");
		if (StringUtil.isNotEmpty(s_ZongZe.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_ZongZe.getCenterName() + "%'");
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
	public ZongZe zongZeShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_zongze t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		ZongZe zongZe = new ZongZe();
		if (rs.next()) {
			zongZe.setCenterId(rs.getInt("centerId"));
			zongZe.setCenterName(rs.getString("centerName"));
			zongZe.setBpRate(rs.getString("bpRate"));
			zongZe.setProjectRateUp(rs.getString("projectRateUp"));
			zongZe.setProjectRateDown(rs.getString("projectRateDown"));
			zongZe.setShareBonusRateUp(rs.getString("shareBonusRateUp"));
			zongZe.setShareBonusRateDown(rs.getString("shareBonusRateDown"));
			zongZe.setTuijianRate(rs.getString("tuijianRate"));
		}
		return zongZe;
	}

	/**
	 * ZONGZE ADD fUNCTION
	 * 
	 * @param con
	 * @param zongZe
	 * @return
	 * @throws Exception
	 */
	public int zongZeAdd(Connection con, ZongZe zongZe) throws Exception {
		String sql = "insert into t_zongze values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zongZe.getCenterName());
		pstmt.setString(2, zongZe.getBpRate());
		pstmt.setString(3, zongZe.getProjectRateUp());
		pstmt.setString(4, zongZe.getProjectRateDown());
		pstmt.setString(5, zongZe.getShareBonusRateUp());
		pstmt.setString(6, zongZe.getShareBonusRateDown());
		pstmt.setString(7, zongZe.getTuijianRate());
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
	public int zongZeDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_zongze where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
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
	public int zongZeUpdate(Connection con, ZongZe zongze) throws Exception {
		String sql = "update t_zongze set centerName=?,bpRate=?,projectRateUp=?,projectRateDown=?,shareBonusRateUp=?,shareBonusRateDown=?,tuijianRate=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zongze.getCenterName());
		pstmt.setString(2, zongze.getBpRate());
		pstmt.setString(3, zongze.getProjectRateUp());
		pstmt.setString(4, zongze.getProjectRateDown());
		pstmt.setString(5, zongze.getShareBonusRateUp());
		pstmt.setString(6, zongze.getShareBonusRateDown());
		pstmt.setString(7, zongze.getTuijianRate());
		pstmt.setInt(8, zongze.getCenterId());
		return pstmt.executeUpdate();
	}

	/**
	 * serach data by centername
	 * 
	 * @param con
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_zongze t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
