package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.LxXize;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class LxXizeDao {
	public List<LxXize> lxXizeList(Connection con, PageBean pageBean, LxXize s_lxXize) throws Exception {
		List<LxXize> lxXizesList = new ArrayList<LxXize>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_lianxie t1");
		if (StringUtil.isNotEmpty(s_lxXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_lxXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			LxXize lxXize = new LxXize();
			lxXize.setCenterId(rs.getInt("centerId"));
			lxXize.setCenterName(rs.getString("centerName"));
			lxXize.setZxFund(rs.getString("zxFund"));
			lxXize.setBbFund(rs.getString("bbFund"));
			lxXize.setBmFund(rs.getString("bmFund"));
			lxXize.setXmFund(rs.getString("xmFund"));
			lxXize.setSumFund(rs.getString("sumFund"));
			lxXizesList.add(lxXize);
		}
		return lxXizesList;
	}

	public int lxXizeCount(Connection con, LxXize s_lxXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_lianxie t1");
		if (StringUtil.isNotEmpty(s_lxXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_lxXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public LxXize lxXizeShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_lianxie t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		LxXize lxXize = new LxXize();
		if (rs.next()) {
			lxXize.setCenterId(rs.getInt("centerId"));
			lxXize.setCenterName(rs.getString("centerName"));
			lxXize.setZxFund(rs.getString("zxFund"));
			lxXize.setBbFund(rs.getString("bbFund"));
			lxXize.setBmFund(rs.getString("bmFund"));
			lxXize.setXmFund(rs.getString("xmFund"));
			lxXize.setSumFund(rs.getString("sumFund"));
		}
		return lxXize;
	}

	public int lxXizeAdd(Connection con, LxXize lxXize) throws Exception {
		String sql = "insert into t_lianxie values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, lxXize.getCenterName());
		pstmt.setString(2, lxXize.getZxFund());
		pstmt.setString(3, lxXize.getBbFund());
		pstmt.setString(4, lxXize.getBmFund());
		pstmt.setString(5, lxXize.getXmFund());
		pstmt.setString(6, lxXize.getSumFund());
		return pstmt.executeUpdate();
	}

	public int lxXizeDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_lianxie where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int lxXizeUpdate(Connection con, LxXize lxXize) throws Exception {
		String sql = "update t_lianxie set centerName=?,zxFund=?,bbFund=?,bmFund=?,xmFund=?,sumFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, lxXize.getCenterName());
		pstmt.setString(2, lxXize.getZxFund());
		pstmt.setString(3, lxXize.getBbFund());
		pstmt.setString(4, lxXize.getBmFund());
		pstmt.setString(5, lxXize.getXmFund());
		pstmt.setString(6, lxXize.getSumFund());
		pstmt.setInt(7, lxXize.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_lianxie t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
