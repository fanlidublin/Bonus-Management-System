package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.ZsXize;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class ZsXizeDao {

	public List<ZsXize> zsXizeList(Connection con, PageBean pageBean, ZsXize s_zsXize) throws Exception {
		List<ZsXize> zsXizesList = new ArrayList<ZsXize>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_zhengshi t1");
		if (StringUtil.isNotEmpty(s_zsXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zsXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ZsXize zsXize = new ZsXize();
			zsXize.setCenterId(rs.getInt("centerId"));
			zsXize.setCenterName(rs.getString("centerName"));
			zsXize.setZxFund(rs.getString("zxFund"));
			zsXize.setBbFund(rs.getString("bbFund"));
			zsXize.setBmFund(rs.getString("bmFund"));
			zsXize.setXmFund(rs.getString("xmFund"));
			zsXize.setSumFund(rs.getString("sumFund"));	
			zsXizesList.add(zsXize);
		}
		return zsXizesList;
	}

	public int zsXizeCount(Connection con, ZsXize s_zsXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_zhengshi t1");
		if (StringUtil.isNotEmpty(s_zsXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zsXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public ZsXize zsXizeShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_zhengshi t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		ZsXize zsXize = new ZsXize();
		if (rs.next()) {
			zsXize.setCenterId(rs.getInt("centerId"));
			zsXize.setCenterName(rs.getString("centerName"));
			zsXize.setZxFund(rs.getString("zxFund"));
			zsXize.setBbFund(rs.getString("bbFund"));
			zsXize.setBmFund(rs.getString("bmFund"));
			zsXize.setXmFund(rs.getString("xmFund"));
			zsXize.setSumFund(rs.getString("sumFund"));	
		}
		return zsXize;
	}

	public int zsXizeAdd(Connection con, ZsXize zsXize) throws Exception {
		String sql = "insert into t_zhengshi values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zsXize.getCenterName());
		pstmt.setString(2, zsXize.getZxFund());
		pstmt.setString(3, zsXize.getBbFund());
		pstmt.setString(4, zsXize.getBmFund());
		pstmt.setString(5, zsXize.getXmFund());
		pstmt.setString(6, zsXize.getSumFund());
		return pstmt.executeUpdate();
	}

	public int zsXizeDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_zhengshi where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int zsXizeUpdate(Connection con, ZsXize zsXize) throws Exception {
		String sql = "update t_zhengshi set centerName=?,zxFund=?,bbFund=?,bmFund=?,xmFund=?,sumFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zsXize.getCenterName());
		pstmt.setString(2, zsXize.getZxFund());
		pstmt.setString(3, zsXize.getBbFund());
		pstmt.setString(4, zsXize.getBmFund());
		pstmt.setString(5, zsXize.getXmFund());
		pstmt.setString(6, zsXize.getSumFund());
		pstmt.setInt(7, zsXize.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_zhengshi t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
