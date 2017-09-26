package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.LxXizeDao;
import com.ntt.model.PageBean;
import com.ntt.model.LxXize;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class LxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	LxXizeDao lxXizeDao = new LxXizeDao();
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
		String s_lxXizeText = request.getParameter("s_lxXizeText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		LxXize lxXize = new LxXize();
		if ("preSave".equals(action)) {
			lxXizePreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			lxXizeSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			lxXizeDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_lxXizeText)) {
				if ("centerName".equals(searchType)) {
					lxXize.setCenterName(s_lxXizeText);
				}
			}
			session.removeAttribute("s_lxXizeText");
			session.removeAttribute("searchType");
			request.setAttribute("s_lxXizeText", s_lxXizeText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_lxXizeText)) {
				if ("centerName".equals(searchType)) {
					lxXize.setCenterName(s_lxXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_lxXizeText", s_lxXizeText);
			} else {
				session.removeAttribute("s_lxXizeText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_lxXizeText)) {
				if ("centerName".equals(searchType)) {
					lxXize.setCenterName(s_lxXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_lxXizeText", s_lxXizeText);
			}
			if (StringUtil.isEmpty(s_lxXizeText)) {
				Object o1 = session.getAttribute("s_lxXizeText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						lxXize.setCenterName((String) o1);
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
			List<LxXize> lxXizesList = lxXizeDao.lxXizeList(con, pageBean, lxXize);
			int total = lxXizeDao.lxXizeCount(con, lxXize);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("lxXizeList", lxXizesList);
			request.setAttribute("mainPage", "admin/lxXize.jsp");
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


	private void lxXizeDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			lxXizeDao.lxXizeDelete(con, centerId);
			request.getRequestDispatcher("lxXize?action=list").forward(request, response);
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


	private void lxXizeSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zxFund = request.getParameter("zxFund");
		String bbFund = request.getParameter("bbFund");
		String bmFund = request.getParameter("bmFund");
		String xmFund = request.getParameter("xmFund");
		String sumFund = request.getParameter("sumFund");

		LxXize lxXize = new LxXize(centerName, zxFund, bbFund, bmFund, xmFund, sumFund);
		if (StringUtil.isNotEmpty(centerId)) {
			lxXize.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = lxXizeDao.lxXizeUpdate(con, lxXize);
			} else if (lxXizeDao.haveCenterByCenterName(con, lxXize.getCenterName())) {
				request.setAttribute("lxXize", lxXize);
				request.setAttribute("error", "�������Ѵ���");
				request.setAttribute("mainPage", "admin/lxXizeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = lxXizeDao.lxXizeAdd(con, lxXize);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("lxXize?action=list").forward(request, response);
			} else {
				request.setAttribute("lxXize", lxXize);
				request.setAttribute("error", "����ʧ��");
				request.setAttribute("mainPage", "admin/lxXizeSave.jsp");
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


	private void lxXizePreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				LxXize lxXize = lxXizeDao.lxXizeShow(con, centerId);
				request.setAttribute("lxXize", lxXize);
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
		request.setAttribute("mainPage", "admin/lxXizeSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='lxXize?page=1'>��ҳ</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			pageCode.append("<li><a href='lxXize?page=" + (currentPage - 1) + "'>��һҳ</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='lxXize?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			pageCode.append("<li><a href='lxXize?page=" + (currentPage + 1) + "'>��һҳ</a></li>");
		}
		pageCode.append("<li><a href='lxXize?page=" + totalPage + "'>βҳ</a></li>");
		return pageCode.toString();
	}
}
