package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.BonusNormDao;
import com.ntt.model.PageBean;
import com.ntt.model.BonusNorm;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class BonusNormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	BonusNormDao bonusNormDao = new BonusNormDao();
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
		String s_bonusNormText = request.getParameter("s_bonusNormText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		BonusNorm bonusNorm = new BonusNorm();
		if ("preSave".equals(action)) {
			bonusNormPreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			bonusNormSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			bonusNormDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_bonusNormText)) {
				if ("centerName".equals(searchType)) {
					bonusNorm.setCenterName(s_bonusNormText);
				}
			}
			session.removeAttribute("s_bonusNormText");
			session.removeAttribute("searchType");
			request.setAttribute("s_bonusNormText", s_bonusNormText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_bonusNormText)) {
				if ("centerName".equals(searchType)) {
					bonusNorm.setCenterName(s_bonusNormText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_bonusNormText", s_bonusNormText);
			} else {
				session.removeAttribute("s_bonusNormText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_bonusNormText)) {
				if ("centerName".equals(searchType)) {
					bonusNorm.setCenterName(s_bonusNormText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_bonusNormText", s_bonusNormText);
			}
			if (StringUtil.isEmpty(s_bonusNormText)) {
				Object o1 = session.getAttribute("s_bonusNormText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						bonusNorm.setCenterName((String) o1);
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
			List<BonusNorm> bonusNormsList = bonusNormDao.bonusNormList(con, pageBean, bonusNorm);
			int total = bonusNormDao.bonusNormCount(con, bonusNorm);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("bonusNormList", bonusNormsList);
			request.setAttribute("mainPage", "admin/bonusNorm.jsp");
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

	/**
	 * Delete method .....................................
	 * 
	 * @param request
	 * @param response
	 */
	private void bonusNormDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			bonusNormDao.bonusNormDelete(con, centerId);
			request.getRequestDispatcher("bonusNorm?action=list").forward(request, response);
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

	/**
	 * Save method .........................................................
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void bonusNormSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String zhengshiNorm = request.getParameter("zhengshiNorm");
		String bpNorm = request.getParameter("bpNorm");
		String lianxieNorm = request.getParameter("lianxieNorm");
		String tuizhiNorm = request.getParameter("tuizhiNorm");

		BonusNorm bonusNorm = new BonusNorm(centerName, zhengshiNorm, bpNorm, lianxieNorm, tuizhiNorm);
		if (StringUtil.isNotEmpty(centerId)) {
			bonusNorm.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = bonusNormDao.bonusNormUpdate(con, bonusNorm);
			} else if (bonusNormDao.haveCenterByCenterName(con, bonusNorm.getCenterName())) {
				request.setAttribute("bonusNorm", bonusNorm);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "admin/bonusNormSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = bonusNormDao.bonusNormAdd(con, bonusNorm);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("bonusNorm?action=list").forward(request, response);
			} else {
				request.setAttribute("bonusNorm", bonusNorm);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "admin/bonusNormSave.jsp");
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

	/**
	 * ZONG ZE PRESAVE __________________________
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void bonusNormPreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				BonusNorm bonusNorm = bonusNormDao.bonusNormShow(con, centerId);
				request.setAttribute("bonusNorm", bonusNorm);
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
		request.setAttribute("mainPage", "admin/bonusNormSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}

	/**
	 * Page control
	 * 
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='bonusNorm?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='bonusNorm?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='bonusNorm?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='bonusNorm?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='bonusNorm?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
