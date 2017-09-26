package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.FaFangDao;
import com.ntt.model.PageBean;
import com.ntt.model.FaFang;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class FaFangServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	FaFangDao faFangDao = new FaFangDao();
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
		String s_faFangText = request.getParameter("s_faFangText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		FaFang faFang = new FaFang();
		if ("preSave".equals(action)) {
			faFangPreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			faFangSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			faFangDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_faFangText)) {
				if ("centerName".equals(searchType)) {
					faFang.setCenterName(s_faFangText);
				}
			}
			session.removeAttribute("s_faFangText");
			session.removeAttribute("searchType");
			request.setAttribute("s_faFangText", s_faFangText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_faFangText)) {
				if ("centerName".equals(searchType)) {
					faFang.setCenterName(s_faFangText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_faFangText", s_faFangText);
			} else {
				session.removeAttribute("s_faFangText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_faFangText)) {
				if ("centerName".equals(searchType)) {
					faFang.setCenterName(s_faFangText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_faFangText", s_faFangText);
			}
			if (StringUtil.isEmpty(s_faFangText)) {
				Object o1 = session.getAttribute("s_faFangText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						faFang.setCenterName((String) o1);
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
			List<FaFang> faFangText = faFangDao.faFangList(con, pageBean, faFang);
			int total = faFangDao.faFangCount(con, faFang);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("zsXizeList", faFangText);
			request.setAttribute("mainPage", "charger/faFang.jsp");
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


	private void faFangDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			faFangDao.faFangDelete(con, centerId);
			request.getRequestDispatcher("faFang?action=list").forward(request, response);
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


	private void faFangSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zxFund = request.getParameter("zxFund");
		String bbFund = request.getParameter("bbFund");
		String bmFund = request.getParameter("bmFund");
		String xmFund = request.getParameter("xmFund");
		String sumFund = request.getParameter("sumFund");

		FaFang faFang = new FaFang(centerName, zxFund, bbFund, bmFund, xmFund, sumFund);
		if (StringUtil.isNotEmpty(centerId)) {
			faFang.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = faFangDao.faFangUpdate(con, faFang);
			} else if (faFangDao.haveCenterByCenterName(con, faFang.getCenterName())) {
				request.setAttribute("faFang", faFang);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "charger/faFangSave.jsp");
				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = faFangDao.faFangAdd(con, faFang);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("faFang?action=list").forward(request, response);
			} else {
				request.setAttribute("faFang", faFang);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "charger/faFangSave.jsp");
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


	private void faFangPreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				FaFang faFang = faFangDao.faFangShow(con, centerId);
				request.setAttribute("faFang", faFang);
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
		request.setAttribute("mainPage", "charger/faFangSave.jsp");
		request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='faFang?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='faFang?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='faFang?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='faFang?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='faFang?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
