package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.BpXizeDao;
import com.ntt.model.PageBean;
import com.ntt.model.BpXize;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class BpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	BpXizeDao bpXizeDao = new BpXizeDao();
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
		String s_bpXizeText = request.getParameter("s_bpXizeText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		BpXize bpXize = new BpXize();
		if ("preSave".equals(action)) {
			bpXizePreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			bpXizeSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			bpXizeDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_bpXizeText)) {
				if ("centerName".equals(searchType)) {
					bpXize.setCenterName(s_bpXizeText);
				}
			}
			session.removeAttribute("s_bpXizeText");
			session.removeAttribute("searchType");
			request.setAttribute("s_bpXizeText", s_bpXizeText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_bpXizeText)) {
				if ("centerName".equals(searchType)) {
					bpXize.setCenterName(s_bpXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_bpXizeText", s_bpXizeText);
			} else {
				session.removeAttribute("s_bpXizeText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_bpXizeText)) {
				if ("centerName".equals(searchType)) {
					bpXize.setCenterName(s_bpXizeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_bpXizeText", s_bpXizeText);
			}
			if (StringUtil.isEmpty(s_bpXizeText)) {
				Object o1 = session.getAttribute("s_bpXizeText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						bpXize.setCenterName((String) o1);
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
			List<BpXize> bpXizesList = bpXizeDao.bpXizeList(con, pageBean, bpXize);
			int total = bpXizeDao.bpXizeCount(con, bpXize);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("bpXizeList", bpXizesList);
			request.setAttribute("mainPage", "admin/bpXize.jsp");
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


	private void bpXizeDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			bpXizeDao.bpXizeDelete(con, centerId);
			request.getRequestDispatcher("bpXize?action=list").forward(request, response);
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


	private void bpXizeSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zxFund = request.getParameter("zxFund");
		String bbFund = request.getParameter("bbFund");
		String bmFund = request.getParameter("bmFund");
		String xmFund = request.getParameter("xmFund");
		String sumFund = request.getParameter("sumFund");

		BpXize bpXize = new BpXize(centerName, zxFund, bbFund, bmFund, xmFund, sumFund);
		if (StringUtil.isNotEmpty(centerId)) {
			bpXize.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = bpXizeDao.bpXizeUpdate(con, bpXize);
			} else if (bpXizeDao.haveCenterByCenterName(con, bpXize.getCenterName())) {
				request.setAttribute("bpXize", bpXize);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "admin/bpXizeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = bpXizeDao.bpXizeAdd(con, bpXize);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("bpXize?action=list").forward(request, response);
			} else {
				request.setAttribute("bpXize", bpXize);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "admin/bpXizeSave.jsp");
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


	private void bpXizePreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				BpXize bpXize = bpXizeDao.bpXizeShow(con, centerId);
				request.setAttribute("bpXize", bpXize);
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
		request.setAttribute("mainPage", "admin/bpXizeSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='bpXize?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='bpXize?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='bpXize?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='bpXize?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='bpXize?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
