package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.BonusNorm;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class BonusNormDao {

	public List<BonusNorm> bonusNormList(Connection con, PageBean pageBean, BonusNorm s_bonusNorm) throws Exception {
		List<BonusNorm> bonusNormsList = new ArrayList<BonusNorm>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_bonus t1");
		if (StringUtil.isNotEmpty(s_bonusNorm.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_bonusNorm.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			BonusNorm bonusNorm = new BonusNorm();
			bonusNorm.setCenterId(rs.getInt("centerId"));
			bonusNorm.setCenterName(rs.getString("centerName"));
			bonusNorm.setZhengshiNorm(rs.getString("zhengshiNorm"));
			bonusNorm.setBpNorm(rs.getString("bpNorm"));
			bonusNorm.setLianxieNorm(rs.getString("lianxieNorm"));
			bonusNorm.setTuizhiNorm(rs.getString("tuizhiNorm"));
			bonusNormsList.add(bonusNorm);
		}
		return bonusNormsList;
	}

	public int bonusNormCount(Connection con, BonusNorm s_BonusNorm) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_bonus t1");
		if (StringUtil.isNotEmpty(s_BonusNorm.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_BonusNorm.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public BonusNorm bonusNormShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_bonus t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		BonusNorm bonusNorm = new BonusNorm();
		if (rs.next()) {
			bonusNorm.setCenterId(rs.getInt("centerId"));
			bonusNorm.setCenterName(rs.getString("centerName"));
			bonusNorm.setZhengshiNorm(rs.getString("zhengshiNorm"));
			bonusNorm.setBpNorm(rs.getString("bpNorm"));
			bonusNorm.setLianxieNorm(rs.getString("lianxieNorm"));
			bonusNorm.setTuizhiNorm(rs.getString("tuizhiNorm"));
		}
		return bonusNorm;
	}

	public int bonusNormAdd(Connection con, BonusNorm bonusNorm) throws Exception {
		String sql = "insert into t_bonus values(null,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bonusNorm.getCenterName());
		pstmt.setString(2, bonusNorm.getZhengshiNorm());
		pstmt.setString(3, bonusNorm.getBpNorm());
		pstmt.setString(4, bonusNorm.getLianxieNorm());
		pstmt.setString(5, bonusNorm.getTuizhiNorm());
		return pstmt.executeUpdate();
	}

	public int bonusNormDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_bonus where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int bonusNormUpdate(Connection con, BonusNorm bonusNorm) throws Exception {
		String sql = "update t_bonus set centerName=?,zhengshiNorm=?,bpNorm=?,lianxieNorm=?,tuizhiNorm=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bonusNorm.getCenterName());
		pstmt.setString(2, bonusNorm.getZhengshiNorm());
		pstmt.setString(3, bonusNorm.getBpNorm());
		pstmt.setString(4, bonusNorm.getLianxieNorm());
		pstmt.setString(5, bonusNorm.getTuizhiNorm());
		pstmt.setInt(6, bonusNorm.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_bonus t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
