package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntt.dao.UserDao;
import com.ntt.model.Admin;
import com.ntt.model.Charger;
import com.ntt.model.Staff;
import com.ntt.util.DbUtil;
import com.ntt.util.MD5Util;

public class PasswordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new  UserDao();
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
		String action = request.getParameter("action");
		
		if("preChange".equals(action)) {
			passwordPreChange(request, response);
			return;
		} else if("change".equals(action)) {
			passwordChange(request, response);
			return;
		}
	}

	private void passwordChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		String oldPassword = md5util.md5(request.getParameter("oldPassword"));
		String newPassword = md5util.md5(request.getParameter("newPassword"));
		System.out.print(newPassword);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			if("admin".equals((String)currentUserType)) {
				Admin admin = (Admin)(session.getAttribute("currentUser"));
				if(oldPassword.equals(admin.getPassword())) {
					userDao.adminUpdate(con, admin.getAdminId(), newPassword);
					admin.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "admin/passwordChange.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错误");
					request.setAttribute("mainPage", "admin/passwordChange.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				}
			} else if("charger".equals((String)currentUserType)) {
				Charger charger = (Charger)(session.getAttribute("currentUser"));
				if(oldPassword.equals(charger.getPassword())) {
					userDao.managerUpdate(con, charger.getChargerId(), newPassword);
					charger.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "charger/passwordChange.jsp");
					request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错wu");
					request.setAttribute("mainPage", "charger/passwordChange.jsp");
					request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				}
			} else if("staff".equals((String)currentUserType)) {
				Staff staff = (Staff)(session.getAttribute("currentUser"));
				if(oldPassword.equals(staff.getPassword())) {
					userDao.studentUpdate(con, staff.getStaffId(), newPassword);
					staff.setPassword(newPassword);
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "修改成功 ");
					request.setAttribute("mainPage", "staff/passwordChange.jsp");
					request.getRequestDispatcher("mainStaff.jsp").forward(request, response);
				} else {
					request.setAttribute("oldPassword", oldPassword);
					request.setAttribute("newPassword", newPassword);
					request.setAttribute("rPassword", newPassword);
					request.setAttribute("error", "原密码错wu");
					request.setAttribute("mainPage", "staff/passwordChange.jsp");
					request.getRequestDispatcher("mainStaff.jsp").forward(request, response);
				}
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

	private void passwordPreChange(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "admin/passwordChange.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} else if("charger".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "charger/passwordChange.jsp");
			request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
		} else if("staff".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "staff/passwordChange.jsp");
			request.getRequestDispatcher("mainStaff.jsp").forward(request, response);
		}
	}
	
}
