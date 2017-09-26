package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.ZhiQu;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class ZhiQuDao {

	public List<ZhiQu> zhiQuList(Connection con, PageBean pageBean, ZhiQu s_zhiQuXize) throws Exception {
		List<ZhiQu> zhiQuList = new ArrayList<ZhiQu>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_zhiqu t1");
		if (StringUtil.isNotEmpty(s_zhiQuXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zhiQuXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ZhiQu zhiQu = new ZhiQu();
			zhiQu.setCenterId(rs.getInt("centerId"));
			zhiQu.setCenterName(rs.getString("centerName"));
			zhiQu.setZxFund(rs.getString("zxFund"));
			zhiQu.setBbFund(rs.getString("bbFund"));
			zhiQu.setBmFund(rs.getString("bmFund"));
			zhiQuList.add(zhiQu);
		}
		return zhiQuList;
	}

	public int zhiQuCount(Connection con, ZhiQu s_zhiQuXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_zhiqu t1");
		if (StringUtil.isNotEmpty(s_zhiQuXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_zhiQuXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public ZhiQu zhiQuShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_zhiqu t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		ZhiQu zhiQu = new ZhiQu();
		if (rs.next()) {
			zhiQu.setCenterId(rs.getInt("centerId"));
			zhiQu.setCenterName(rs.getString("centerName"));
			zhiQu.setZxFund(rs.getString("zxFund"));
			zhiQu.setBbFund(rs.getString("bbFund"));
			zhiQu.setBmFund(rs.getString("bmFund"));
		}
		return zhiQu;
	}

	public int zhiQuAdd(Connection con, ZhiQu zhiQu) throws Exception {
		String sql = "insert into t_zhiqu values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zhiQu.getCenterName());
		pstmt.setString(2, zhiQu.getZxFund());
		pstmt.setString(3, zhiQu.getBbFund());
		pstmt.setString(4, zhiQu.getBmFund());
		return pstmt.executeUpdate();
	}

	public int zhiQuDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_zhiqu where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int zhiQuUpdate(Connection con, ZhiQu zhiQu) throws Exception {
		String sql = "update t_zhiqu set centerName=?,zxFund=?,bbFund=?,bmFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, zhiQu.getCenterName());
		pstmt.setString(2, zhiQu.getZxFund());
		pstmt.setString(3, zhiQu.getBbFund());
		pstmt.setString(4, zhiQu.getBmFund());
		pstmt.setInt(5, zhiQu.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_zhiqu t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
