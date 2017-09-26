package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.ZongZeDao;
import com.ntt.model.PageBean;
import com.ntt.model.ZongZe;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class zongZeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	ZongZeDao zongZeDao = new ZongZeDao();
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
		String s_zongZeText = request.getParameter("s_zongZeText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		ZongZe zongZe = new ZongZe();
		if ("preSave".equals(action)) {
			zongZePreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			zongZeSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			zongZeDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_zongZeText)) {
				if ("centerName".equals(searchType)) {
					zongZe.setCenterName(s_zongZeText);
				}
			}
			session.removeAttribute("s_zongZeText");
			session.removeAttribute("searchType");
			request.setAttribute("s_zongZeText", s_zongZeText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_zongZeText)) {
				if ("centerName".equals(searchType)) {
					zongZe.setCenterName(s_zongZeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_zongZeText", s_zongZeText);
			} else {
				session.removeAttribute("s_zongZeText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_zongZeText)) {
				if ("centerName".equals(searchType)) {
					zongZe.setCenterName(s_zongZeText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_zongZeText", s_zongZeText);
			}
			if (StringUtil.isEmpty(s_zongZeText)) {
				Object o1 = session.getAttribute("s_zongZeText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						zongZe.setCenterName((String) o1);
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
			List<ZongZe> zongZesList = zongZeDao.zongZeList(con, pageBean, zongZe);
			int total = zongZeDao.zongZeCount(con, zongZe);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("zongZeList", zongZesList);
			request.setAttribute("mainPage", "admin/zongZe.jsp");
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
	private void zongZeDelete(HttpServletRequest request, HttpServletResponse response) {
		String centerId = request.getParameter("centerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			zongZeDao.zongZeDelete(con, centerId);
			request.getRequestDispatcher("zongZe?action=list").forward(request, response);
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
	private void zongZeSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		String centerName = request.getParameter("centerName");
		String bpRate = request.getParameter("bpRate");
		String projectRateUp = request.getParameter("projectRateUp");
		String projectRateDown = request.getParameter("projectRateDown");
		String shareBonusRateUp = request.getParameter("shareBonusRateUp");
		String shareBonusRateDown = request.getParameter("shareBonusRateDown");
		String tuijianRate = request.getParameter("tuijianRate");
		
		ZongZe zongZe = new ZongZe(centerName,bpRate,projectRateUp,projectRateDown,shareBonusRateUp,shareBonusRateDown,tuijianRate);
		if (StringUtil.isNotEmpty(centerId)) {
			zongZe.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = zongZeDao.zongZeUpdate(con, zongZe);
			} else if (zongZeDao.haveCenterByCenterName(con, zongZe.getCenterName())) {
				request.setAttribute("zongZe", zongZe);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "admin/zongZeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = zongZeDao.zongZeAdd(con, zongZe);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("zongZe?action=list").forward(request, response);
			} else {
				request.setAttribute("zongZe", zongZe);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "zongZe/zongZeSave.jsp");
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
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void zongZePreSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String centerId = request.getParameter("centerId");
		if (StringUtil.isNotEmpty(centerId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				ZongZe zongZe = zongZeDao.zongZeShow(con, centerId);
				request.setAttribute("zongZe", zongZe);
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
		request.setAttribute("mainPage", "admin/zongZeSave.jsp");
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
		pageCode.append("<li><a href='zongZe?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='zongZe?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='zongZe?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='zongZe?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='zongZe?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
