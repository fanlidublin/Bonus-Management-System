package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.BaoXiao;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class BaoXiaoDao {

	public List<BaoXiao> baoXiaoList(Connection con, PageBean pageBean, BaoXiao s_baoXiao) throws Exception {
		List<BaoXiao> baoXiaoList = new ArrayList<BaoXiao>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_blank t1");
		if (StringUtil.isNotEmpty(s_baoXiao.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_baoXiao.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			BaoXiao baoXiao = new BaoXiao();
			baoXiao.setCenterId(rs.getInt("centerId"));
			baoXiao.setCenterName(rs.getString("centerName"));
			baoXiao.setZxFund(rs.getString("zxFund"));
			baoXiao.setBbFund(rs.getString("bbFund"));
			baoXiao.setBmFund(rs.getString("bmFund"));
			baoXiaoList.add(baoXiao);
		}
		return baoXiaoList;
	}

	public int baoXiaoCount(Connection con, BaoXiao s_baoXiao) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_blank t1");
		if (StringUtil.isNotEmpty(s_baoXiao.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_baoXiao.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public BaoXiao baoXiaoShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_blank t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		BaoXiao baoXiao = new BaoXiao();
		if (rs.next()) {
			baoXiao.setCenterId(rs.getInt("centerId"));
			baoXiao.setCenterName(rs.getString("centerName"));
			baoXiao.setZxFund(rs.getString("zxFund"));
			baoXiao.setBbFund(rs.getString("bbFund"));
			baoXiao.setBmFund(rs.getString("bmFund"));
		}
		return baoXiao;
	}

	public int baoXiaoAdd(Connection con, BaoXiao baoXiao) throws Exception {
		String sql = "insert into t_blank values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, baoXiao.getCenterName());
		pstmt.setString(2, baoXiao.getZxFund());
		pstmt.setString(3, baoXiao.getBbFund());
		pstmt.setString(4, baoXiao.getBmFund());
		return pstmt.executeUpdate();
	}

	public int baoXiaoDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_blank where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int baoXiaoUpdate(Connection con, BaoXiao baoXiao) throws Exception {
		String sql = "update t_blank set centerName=?,zxFund=?,bbFund=?,bmFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, baoXiao.getCenterName());
		pstmt.setString(2, baoXiao.getZxFund());
		pstmt.setString(3, baoXiao.getBbFund());
		pstmt.setString(4, baoXiao.getBmFund());
		pstmt.setInt(5, baoXiao.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_blank t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
