package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.ZsXizeDao;
import com.ntt.model.PageBean;
import com.ntt.model.ZsXize;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class ZsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	ZsXizeDao zsXizeDao = new ZsXizeDao();
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
		String s_zsXizeText = request.getParameter("s_zsXizeText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		ZsXize zsXize = new ZsXize();
		if ("preSave".equals(action)) {
			zsXizePreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			zsXizeSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			zsXizeDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_zsXizeText)) {
				if ("centerName".equals(searchType)) {
					zsXize.setCenterName(s_zsXizeText);
				}
			}
			session.removeAttribute("s_zsXizeText");
			session.removeAttribute("searchType");
			request.setAttribute("s_zsXizeText", s_zsXizeText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_zsXizeText)) {
				if ("centerName".equals(searchType)) {
					zsXize.setCenterName(s_zsXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_zsXizeText", s_zsXizeText);
			} else {
				session.removeAttribute("s_zsXizeText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_zsXizeText)) {
				if ("centerName".equals(searchType)) {
					zsXize.setCenterName(s_zsXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_zsXizeText", s_zsXizeText);
			}
			if (StringUtil.isEmpty(s_zsXizeText)) {
				Object o1 = session.getAttribute("s_zsXizeText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						zsXize.setCenterName((String) o1);
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
			List<ZsXize> zsXizesList = zsXizeDao.zsXizeList(con, pageBean, zsXize);
			int total = zsXizeDao.zsXizeCount(con, zsXize);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("zsXizeList", zsXizesList);
			request.setAttribute("mainPage", "admin/zsXize.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
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


	private void zsXizeDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			zsXizeDao.zsXizeDelete(con, centerId);
			request.getRequestDispatcher("zsXize?action=list").forward(request, response);
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


	private void zsXizeSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zxFund = request.getParameter("zxFund");
		String bbFund = request.getParameter("bbFund");
		String bmFund = request.getParameter("bmFund");
		String xmFund = request.getParameter("xmFund");
		String sumFund = request.getParameter("sumFund");

		ZsXize zsXize = new ZsXize(centerName, zxFund, bbFund, bmFund, xmFund, sumFund);
		if (StringUtil.isNotEmpty(centerId)) {
			zsXize.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = zsXizeDao.zsXizeUpdate(con, zsXize);
			} else if (zsXizeDao.haveCenterByCenterName(con, zsXize.getCenterName())) {
				request.setAttribute("zsXize", zsXize);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "admin/zsXizeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = zsXizeDao.zsXizeAdd(con, zsXize);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("zsXize?action=list").forward(request, response);
			} else {
				request.setAttribute("zsXize", zsXize);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "admin/zsXizeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
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


	private void zsXizePreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				ZsXize zsXize = zsXizeDao.zsXizeShow(con, centerId);
				request.setAttribute("zsXize", zsXize);
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
		request.setAttribute("mainPage", "admin/zsXizeSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='zsXize?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='zsXize?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='zsXize?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='zsXize?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='zsXize?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
