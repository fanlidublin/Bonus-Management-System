package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.AppliBonusDao2;
import com.ntt.dao.UserDao;
import com.ntt.model.PageBean;
import com.ntt.model.AppliBonus2;
import com.ntt.model.Charger;
import com.ntt.util.StringUtil;
import com.ntt.util.DbUtil;
import com.ntt.util.PropertiesUtil;
import com.ntt.util.MD5Util;

public class AppliBonusServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	AppliBonusDao2 appliBonusDao2 = new AppliBonusDao2();
	UserDao userDao=new UserDao();
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
		String s_appliBonusText2 = request.getParameter("s_appliBonusText2");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		String projectId = request.getParameter("projectId");
		
		
		AppliBonus2 appliBonus2 = new AppliBonus2();
		if ("preSave".equals(action)) {
			appliBonusPreSave2(request, response);
			return;
		} else 
//			if ("save".equals(action)) {
//			appliBonusSave(request, response);
//			return;
//		} else 
			if ("delete".equals(action)) {
			appliBonusDelete(request, response);
			return;
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_appliBonusText2)) {
				if ("projectName".equals(searchType)) {
					appliBonus2.setProjectName(s_appliBonusText2);
				}
			}
			session.removeAttribute("s_appliBonusText2");
			session.removeAttribute("searchType");
			request.setAttribute("s_appliBonusText2", s_appliBonusText2);
			request.setAttribute("searchType", searchType);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_appliBonusText2)) {
				if ("projectName".equals(searchType)) {
					appliBonus2.setProjectName(s_appliBonusText2);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_appliBonusText2", s_appliBonusText2);
			} else {
				session.removeAttribute("s_appliBonusText2");
				session.removeAttribute("searchType");
			}
		} else {
			if (StringUtil.isNotEmpty(s_appliBonusText2)) {
				if ("projectName".equals(searchType)) {
					appliBonus2.setProjectName(s_appliBonusText2);
				}
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_appliBonusText2", s_appliBonusText2);
			}
			if (StringUtil.isEmpty(s_appliBonusText2)) {
				Object o1 = session.getAttribute("s_appliBonusText2");
				Object o2 = session.getAttribute("searchType");
				if (o1 != null) {
					if ("projectName".equals((String) o2)) {
						appliBonus2.setProjectName((String) o1);
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
			List<AppliBonus2> appliBonusList2 = appliBonusDao2.appliBonusList2(con, pageBean, appliBonus2, projectId);
			int total = appliBonusDao2.appliBonusCount(con, appliBonus2);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("appliBonusList2", appliBonusList2);
			request.setAttribute("mainPage", "charger/appliBonus2.jsp");
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


	/**
	 * Delete method .....................................
	 * 
	 * @param request
	 * @param response
	 */
	private void appliBonusDelete(HttpServletRequest request, HttpServletResponse response) {
		String projectId = request.getParameter("projectId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			appliBonusDao2.appliBonusDelete(con, projectId);
			request.getRequestDispatcher("appliBonus2?action=list").forward(request, response);
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
//	private void appliBonusSave(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String projectId = request.getParameter("projectId");
//		String financialId = request.getParameter("financialId");
//		String projectName = request.getParameter("projectName");
//		String orderPerformance = request.getParameter("orderPerformance");
//		String requestPerformance = request.getParameter("requestPerformance");
//		String supportPerformance = request.getParameter("supportPerformance");
//		Double i1 = Double.parseDouble(orderPerformance);
//		Double i2 = Double.parseDouble(requestPerformance);
//		Double i3 = Double.parseDouble(supportPerformance);
//		String projectStartDate = request.getParameter("projectStartDate");
//		String projectEndDate = request.getParameter("projectEndDate");
//		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
//		Date date1 = null;
//		Date date2 = null;
//		try {
//			date1 = fmt.parse(projectStartDate);
//			date2 = fmt.parse(projectStartDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//
//		
//		String centerId = request.getParameter("centerId");
//		int center = Integer.parseInt(centerId);
//		
//		
//		AppliBonus appliBonus = new AppliBonus(financialId, projectName, i1, i2, i3, date1, date2);
//		if (StringUtil.isNotEmpty(projectId)) {
//			appliBonus.setCenterId(Integer.parseInt(projectId));
//		}
//		Connection con = null;
//		try {
//			con = dbUtil.getCon();
//			int saveNum = 0;
//			if (StringUtil.isNotEmpty(projectId)) {
//				saveNum = appliBonusDao.appliBonusUpdate(con, appliBonus);
//			} else if (appliBonusDao.haveCenterByProjectName(con, appliBonus.getProjectName())) {
//				request.setAttribute("appliBonus", appliBonus);
//				request.setAttribute("error", "该项目已存在");
//				request.setAttribute("mainPage", "charger/appliBonusSave2.jsp");
//				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
//				try {
//					dbUtil.closeCon(con);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return;
//			} else {
//				saveNum = appliBonusDao.appliBonusAdd(con, appliBonus);
//			}
//			if (saveNum > 0) {
//				request.getRequestDispatcher("appliBonus?action=list").forward(request, response);
//			} else {
//				request.setAttribute("appliBonus", appliBonus);
//				request.setAttribute("error", "保存失败");
//				request.setAttribute("mainPage", "appliBonus/appliBonusSave2.jsp");
//				request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				dbUtil.closeCon(con);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * ZONG ZE PRESAVE __________________________
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void appliBonusPreSave2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String projectId = request.getParameter("projectId");
		
		String userName=(String)request.getAttribute("currentUser");
		Connection con2 = null;
		try {
			con2 = dbUtil.getCon();
			Charger charger = userDao.Role(con2, userName);
			request.setAttribute("charger", charger);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (StringUtil.isNotEmpty(projectId)) {
			Connection con = null;		
			try {
				con = dbUtil.getCon();
				AppliBonus2 appliBonus2 = appliBonusDao2.appliBonusShow2(con, projectId);
				request.setAttribute("appliBonus2", appliBonus2);
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
		request.setAttribute("mainPage", "charger/appliBonusSave2.jsp");
		request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
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
		pageCode.append("<a href='appliBonus2?page=1'>首页</a>");
		if (currentPage == 1) {
			pageCode.append("<class='disabled'><a href='#'>上一页</a>");
		} else {
			pageCode.append("<a href='appliBonus2?page=" + (currentPage - 1) + "'>上一页</a>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<class='active'><a href='#'>" + i + "</a>");
			} else {
				pageCode.append("<a href='appliBonus2?page=" + i + "'>" + i + "</a>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<class='disabled'><a href='#'>下一页</a>");
		} else {
			pageCode.append("<a href='appliBonus2?page=" + (currentPage + 1) + "'>下一页</a>");
		}
		pageCode.append("<a href='appliBonus2?page=" + totalPage + "'>尾页</a>");
		return pageCode.toString();
	}
}
