package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.YuE;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class YuEDao {

	public List<YuE> yuEList(Connection con, PageBean pageBean, YuE s_yue) throws Exception {
		List<YuE> yuEList = new ArrayList<YuE>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_yue t1");
		if (StringUtil.isNotEmpty(s_yue.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_yue.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			YuE yuE = new YuE();
			yuE.setCenterId(rs.getInt("centerId"));
			yuE.setCenterName(rs.getString("centerName"));
			yuE.setZxFund(rs.getString("zxFund"));
			yuE.setBbFund(rs.getString("bbFund"));
			yuE.setBmFund(rs.getString("bmFund"));
			yuEList.add(yuE);
		}
		return yuEList;
	}

	public int yuECount(Connection con, YuE s_yue) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_yue t1");
		if (StringUtil.isNotEmpty(s_yue.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_yue.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public YuE yuEShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_yue t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		YuE yuE = new YuE();
		if (rs.next()) {
			yuE.setCenterId(rs.getInt("centerId"));
			yuE.setCenterName(rs.getString("centerName"));
			yuE.setZxFund(rs.getString("zxFund"));
			yuE.setBbFund(rs.getString("bbFund"));
			yuE.setBmFund(rs.getString("bmFund"));
		}
		return yuE;
	}

	public int yuEAdd(Connection con, YuE yuE) throws Exception {
		String sql = "insert into t_yue values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, yuE.getCenterName());
		pstmt.setString(2, yuE.getZxFund());
		pstmt.setString(3, yuE.getBbFund());
		pstmt.setString(4, yuE.getBmFund());
		return pstmt.executeUpdate();
	}

	public int yuEDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_yue where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int yuEUpdate(Connection con, YuE yuE) throws Exception {
		String sql = "update t_yue set centerName=?,zxFund=?,bbFund=?,bmFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, yuE.getCenterName());
		pstmt.setString(2, yuE.getZxFund());
		pstmt.setString(3, yuE.getBbFund());
		pstmt.setString(4, yuE.getBmFund());
		pstmt.setInt(5, yuE.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_yue t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
