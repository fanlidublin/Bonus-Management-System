package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.FaFang;
import com.ntt.model.PageBean;
import com.ntt.util.StringUtil;

public class FaFangDao {

	public List<FaFang> faFangList(Connection con, PageBean pageBean, FaFang s_faFangXize) throws Exception {
		List<FaFang> faFangList = new ArrayList<FaFang>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_fafang t1");
		if (StringUtil.isNotEmpty(s_faFangXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_faFangXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			FaFang faFang = new FaFang();
			faFang.setCenterId(rs.getInt("centerId"));
			faFang.setCenterName(rs.getString("centerName"));
			faFang.setZxFund(rs.getString("zxFund"));
			faFang.setBbFund(rs.getString("bbFund"));
			faFang.setBmFund(rs.getString("bmFund"));
			faFang.setXmFund(rs.getString("xmFund"));
			faFang.setSumFund(rs.getString("sumFund"));	
			faFangList.add(faFang);
		}
		return faFangList;
	}

	public int faFangCount(Connection con, FaFang s_faFangXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_fafang t1");
		if (StringUtil.isNotEmpty(s_faFangXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_faFangXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public FaFang faFangShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_fafang t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		FaFang faFang = new FaFang();
		if (rs.next()) {
			faFang.setCenterId(rs.getInt("centerId"));
			faFang.setCenterName(rs.getString("centerName"));
			faFang.setZxFund(rs.getString("zxFund"));
			faFang.setBbFund(rs.getString("bbFund"));
			faFang.setBmFund(rs.getString("bmFund"));
			faFang.setXmFund(rs.getString("xmFund"));
			faFang.setSumFund(rs.getString("sumFund"));	
		}
		return faFang;
	}

	public int faFangAdd(Connection con, FaFang faFang) throws Exception {
		String sql = "insert into t_fafang values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, faFang.getCenterName());
		pstmt.setString(2, faFang.getZxFund());
		pstmt.setString(3, faFang.getBbFund());
		pstmt.setString(4, faFang.getBmFund());
		pstmt.setString(5, faFang.getXmFund());
		pstmt.setString(6, faFang.getSumFund());
		return pstmt.executeUpdate();
	}

	public int faFangDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_fafang where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int faFangUpdate(Connection con, FaFang faFang) throws Exception {
		String sql = "update t_fafang set centerName=?,zxFund=?,bbFund=?,bmFund=?,xmFund=?,sumFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, faFang.getCenterName());
		pstmt.setString(2, faFang.getZxFund());
		pstmt.setString(3, faFang.getBbFund());
		pstmt.setString(4, faFang.getBmFund());
		pstmt.setString(5, faFang.getXmFund());
		pstmt.setString(6, faFang.getSumFund());
		pstmt.setInt(7, faFang.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_fafang t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
