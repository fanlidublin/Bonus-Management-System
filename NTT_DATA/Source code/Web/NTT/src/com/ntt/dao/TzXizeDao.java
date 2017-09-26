package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.TzXize;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class TzXizeDao {
	public List<TzXize> tzXizeList(Connection con, PageBean pageBean, TzXize s_tzXize) throws Exception {
		List<TzXize> tzXizesList = new ArrayList<TzXize>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_tuizhi t1");
		if (StringUtil.isNotEmpty(s_tzXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_tzXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			TzXize tzXize = new TzXize();
			tzXize.setCenterId(rs.getInt("centerId"));
			tzXize.setCenterName(rs.getString("centerName"));
			tzXize.setZxFund(rs.getString("zxFund"));
			tzXize.setBbFund(rs.getString("bbFund"));
			tzXize.setBmFund(rs.getString("bmFund"));
			tzXize.setXmFund(rs.getString("xmFund"));
			tzXize.setSumFund(rs.getString("sumFund"));
			tzXizesList.add(tzXize);
		}
		return tzXizesList;
	}

	public int tzXizeCount(Connection con, TzXize s_tzXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_tuizhi t1");
		if (StringUtil.isNotEmpty(s_tzXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_tzXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public TzXize tzXizeShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_tuizhi t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		TzXize tzXize = new TzXize();
		if (rs.next()) {
			tzXize.setCenterId(rs.getInt("centerId"));
			tzXize.setCenterName(rs.getString("centerName"));
			tzXize.setZxFund(rs.getString("zxFund"));
			tzXize.setBbFund(rs.getString("bbFund"));
			tzXize.setBmFund(rs.getString("bmFund"));
			tzXize.setXmFund(rs.getString("xmFund"));
			tzXize.setSumFund(rs.getString("sumFund"));
		}
		return tzXize;
	}

	public int tzXizeAdd(Connection con, TzXize tzXize) throws Exception {
		String sql = "insert into t_tuizhi values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, tzXize.getCenterName());
		pstmt.setString(2, tzXize.getZxFund());
		pstmt.setString(3, tzXize.getBbFund());
		pstmt.setString(4, tzXize.getBmFund());
		pstmt.setString(5, tzXize.getXmFund());
		pstmt.setString(6, tzXize.getSumFund());
		return pstmt.executeUpdate();
	}

	public int tzXizeDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_tuizhi where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int tzXizeUpdate(Connection con, TzXize tzXize) throws Exception {
		String sql = "update t_tuizhi set centerName=?,zxFund=?,bbFund=?,bmFund=?,xmFund=?,sumFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, tzXize.getCenterName());
		pstmt.setString(2, tzXize.getZxFund());
		pstmt.setString(3, tzXize.getBbFund());
		pstmt.setString(4, tzXize.getBmFund());
		pstmt.setString(5, tzXize.getXmFund());
		pstmt.setString(6, tzXize.getSumFund());
		pstmt.setInt(7, tzXize.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_tuizhi t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
