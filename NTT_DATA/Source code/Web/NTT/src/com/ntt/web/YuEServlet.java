package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.YuEDao;
import com.ntt.model.PageBean;
import com.ntt.model.YuE;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class YuEServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	YuEDao yuEDao = new YuEDao();
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
		String s_yuEText = request.getParameter("s_yuEText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		YuE yuE = new YuE();
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
			if (StringUtil.isNotEmpty(s_yuEText)) {
				if ("centerName".equals(searchType)) {
					yuE.setCenterName(s_yuEText);
				}
			}
			session.removeAttribute("s_yuEText");
			session.removeAttribute("searchType");
			request.setAttribute("s_yuEText", s_yuEText);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_yuEText)) {
				if ("centerName".equals(searchType)) {
					yuE.setCenterName(s_yuEText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_yuEText", s_yuEText);
			} else {
				session.removeAttribute("s_yuEText");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_yuEText)) {
				if ("centerName".equals(searchType)) {
					yuE.setCenterName(s_yuEText);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_yuEText", s_yuEText);
			}
			if (StringUtil.isEmpty(s_yuEText)) {
				Object o1 = session.getAttribute("s_yuEText");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("centerName".equals((String) o2)) {
						yuE.setCenterName((String) o1);
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
			List<YuE> yuEList = yuEDao.yuEList(con, pageBean, yuE);
			int total = yuEDao.yuECount(con, yuE);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("yuEList", yuEList);
			request.setAttribute("mainPage", "charger/yuE.jsp");
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
			yuEDao.yuEDelete(con, centerId);
			request.getRequestDispatcher("yuE?action=list").forward(request, response);
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


		YuE yuE = new YuE(centerName, zxFund, bbFund, bmFund);
		if (StringUtil.isNotEmpty(centerId)) {
			yuE.setCenterId(Integer.parseInt(centerId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(centerId)) {
				saveNum = yuEDao.yuEUpdate(con, yuE);
			} else if (yuEDao.haveCenterByCenterName(con, yuE.getCenterName())) {
				request.setAttribute("yuE", yuE);
				request.setAttribute("error", "该中心已存在");
				request.setAttribute("mainPage", "charger/yuESave.jsp");
				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = yuEDao.yuEAdd(con, yuE);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("yuE?action=list").forward(request, response);
			} else {
				request.setAttribute("yuE", yuE);
				request.setAttribute("error", "保存失败");
				request.setAttribute("mainPage", "charger/yuESave.jsp");
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
				YuE yuE = yuEDao.yuEShow(con, centerId);
				request.setAttribute("yuE", yuE);
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
		request.setAttribute("mainPage", "charger/yuESave.jsp");
		request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
	}


	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='yuE?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='yuE?page=" + (currentPage - 1) + "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='yuE?page=" + i + "'>" + i + "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='yuE?page=" + (currentPage + 1) + "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='yuE?page=" + totalPage + "'>尾页</a></li>");
		return pageCode.toString();
	}
}
