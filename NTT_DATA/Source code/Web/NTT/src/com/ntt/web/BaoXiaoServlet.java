package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.BaoXiaoDao;
import com.ntt.model.PageBean;
import com.ntt.model.BaoXiao;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class BaoXiaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	BaoXiaoDao zhiQuDao = new BaoXiaoDao();
	MD5Util md5util = new MD5Util();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String s_baoXiaoText = request.getParameter("s_baoXiaoText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		BaoXiao baoXiao = new BaoXiao();
		if ("preSave".equals(action)) {
			zhiQuPreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			zhiQuSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			zhiQuDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_baoXiaoText)) {
				if ("centerName".equals(searchType)) {
					baoXiao.setCenterName(s_baoXiaoText);
				}
			}
			session.removeAttribute("s_baoXiaoText");
			session.removeAttribute("searchType");
			request.setAttribute("s_baoXiaoText", s_baoXiaoText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_baoXiaoText)) {
				if ("centerName".equals(searchType)) {
					baoXiao.setCenterName(s_baoXiaoText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_baoXiaoText", s_baoXiaoText);
			} else {
				session.removeAttribute("s_baoXiaoText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_baoXiaoText)) {
				if ("centerName".equals(searchType)) {
					baoXiao.setCenterName(s_baoXiaoText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_baoXiaoText", s_baoXiaoText);
			}
			if (StringUtil.isEmpty(s_baoXiaoText)) {
				Object o1 = session.getAttribute("s_baoXiaoText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						baoXiao.setCenterName((String) o1);
					}
				}
			}
		}
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		request.setAttribute("pageSize", pageBean.getPageSize());
		request.setAttribute("page", pageBean.getPage());

		try {
			con = dbUtil.getCon();
			List<BaoXiao> zhiQuText = zhiQuDao.baoXiaoList(con, pageBean, baoXiao);
			int total = zhiQuDao.baoXiaoCount(con, baoXiao);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("zsXizeList", zhiQuText);
			request.setAttribute("mainPage", "charger/baoXiao.jsp");
			request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void zhiQuDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			zhiQuDao.baoXiaoDelete(con, centerId);
			request.getRequestDispatcher("baoXiao?action=list").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void zhiQuSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zxFund = request.getParameter("zxFund");
		String bbFund = request.getParameter("bbFund");
		String bmFund = request.getParameter("bmFund");


		BaoXiao baoXiao = new BaoXiao(centerName, zxFund, bbFund, bmFund);
		if (StringUtil.isNotEmpty(centerId)) {
			baoXiao.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = zhiQuDao.baoXiaoUpdate(con, baoXiao);
			} else if (zhiQuDao.haveCenterByCenterName(con, baoXiao.getCenterName())) {
				request.setAttribute("baoXiao", baoXiao);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "charger/zhiQuSave.jsp");
				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = zhiQuDao.baoXiaoAdd(con, baoXiao);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("baoXiao?action=list").forward(request, response);
			} else {
				request.setAttribute("baoXiao", baoXiao);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "charger/baoXiaoSave.jsp");
				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void zhiQuPreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				BaoXiao baoXiao = zhiQuDao.baoXiaoShow(con, centerId);
				request.setAttribute("baoXiao", baoXiao);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("mainPage", "charger/baoXiaoSave.jsp");
		request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='baoXiao?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='baoXiao?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='baoXiao?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='baoXiao?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='baoXiao?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
