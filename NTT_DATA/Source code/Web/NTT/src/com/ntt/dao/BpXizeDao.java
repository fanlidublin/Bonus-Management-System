package com.ntt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ntt.model.PageBean;
import com.ntt.model.BpXize;
import com.ntt.util.StringUtil;

public class BpXizeDao {
	public List<BpXize> bpXizeList(Connection con, PageBean pageBean, BpXize s_bpXize) throws Exception {
		List<BpXize> bpXizesList = new ArrayList<BpXize>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_bp t1");
		if (StringUtil.isNotEmpty(s_bpXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_bpXize.getCenterName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			BpXize bpXize = new BpXize();
			bpXize.setCenterId(rs.getInt("centerId"));
			bpXize.setCenterName(rs.getString("centerName"));
			bpXize.setZxFund(rs.getString("zxFund"));
			bpXize.setBbFund(rs.getString("bbFund"));
			bpXize.setBmFund(rs.getString("bmFund"));
			bpXize.setXmFund(rs.getString("xmFund"));
			bpXize.setSumFund(rs.getString("sumFund"));	
			bpXizesList.add(bpXize);
		}
		return bpXizesList;
	}

	public int bpXizeCount(Connection con, BpXize s_bpXize) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_bp t1");
		if (StringUtil.isNotEmpty(s_bpXize.getCenterName())) {
			sb.append(" where t1.centerName like '%" + s_bpXize.getCenterName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public BpXize bpXizeShow(Connection con, String centerId) throws Exception {
		String sql = "select * from t_bp t1 where t1.centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		ResultSet rs = pstmt.executeQuery();
		BpXize bpXize = new BpXize();
		if (rs.next()) {
			bpXize.setCenterId(rs.getInt("centerId"));
			bpXize.setCenterName(rs.getString("centerName"));
			bpXize.setZxFund(rs.getString("zxFund"));
			bpXize.setBbFund(rs.getString("bbFund"));
			bpXize.setBmFund(rs.getString("bmFund"));
			bpXize.setXmFund(rs.getString("xmFund"));
			bpXize.setSumFund(rs.getString("sumFund"));	
		}
		return bpXize;
	}

	public int bpXizeAdd(Connection con, BpXize bpXize) throws Exception {
		String sql = "insert into t_bp values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bpXize.getCenterName());
		pstmt.setString(2, bpXize.getZxFund());
		pstmt.setString(3, bpXize.getBbFund());
		pstmt.setString(4, bpXize.getBmFund());
		pstmt.setString(5, bpXize.getXmFund());
		pstmt.setString(6, bpXize.getSumFund());
		return pstmt.executeUpdate();
	}

	public int bpXizeDelete(Connection con, String centerId) throws Exception {
		String sql = "delete from t_bp where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, centerId);
		return pstmt.executeUpdate();
	}

	public int bpXizeUpdate(Connection con, BpXize bpXize) throws Exception {
		String sql = "update t_bp set centerName=?,zxFund=?,bbFund=?,bmFund=?,xmFund=?,sumFund=? where centerId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bpXize.getCenterName());
		pstmt.setString(2, bpXize.getZxFund());
		pstmt.setString(3, bpXize.getBbFund());
		pstmt.setString(4, bpXize.getBmFund());
		pstmt.setString(5, bpXize.getXmFund());
		pstmt.setString(6, bpXize.getSumFund());
		pstmt.setInt(7, bpXize.getCenterId());
		return pstmt.executeUpdate();
	}

	public boolean haveCenterByCenterName(Connection con, String name) throws Exception {
		String sql = "select * from t_bp t1 where t1.centerName=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
