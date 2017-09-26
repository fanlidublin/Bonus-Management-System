package com.ntt.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.ntt.dao.UserDao;
import com.ntt.model.Admin;
import com.ntt.model.Charger;
import com.ntt.model.Staff;
import com.ntt.util.DbUtil;

import com.ntt.util.MD5Util;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
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
		String userName = request.getParameter("userName");
		String password = md5util.md5(request.getParameter("password"));
		String remember = request.getParameter("remember");
		String userType = request.getParameter("userType");
  
		System.out.println("name:" + userName + "password:" + password + "userType" + userType);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Admin currentAdmin = null;
			Charger currentCharger = null;
			Staff currentStaff = null;
			if ("admin".equals(userType)) {
				Admin admin = new Admin(userName, password);
				currentAdmin = userDao.Login(con, admin);
				if (currentAdmin == null) {
					request.setAttribute("admin", admin);
					request.setAttribute("error", "用户名或密码错误！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if ("remember-me".equals(remember)) {
						rememberMe(userName, password, userType, response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "admin");
					session.setAttribute("currentUser", currentAdmin);
					request.setAttribute("mainPage", "admin/blank.jsp");
					request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				}
			} else if ("charger".equals(userType)) {
				Charger charger = new Charger(userName, password);
				currentCharger = userDao.Login(con, charger);
				if (currentCharger == null) {
					request.setAttribute("charger", charger);
					request.setAttribute("error", "用户名或密码错误！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if ("remember-me".equals(remember)) {
						rememberMe(userName, password, userType, response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "charger");
					session.setAttribute("currentUser", currentCharger);
					request.setAttribute("mainPage", "charger/blank.jsp");
					request.getRequestDispatcher("mainCharger.jsp").forward(request, response);
				}
			} else if ("staff".equals(userType)) {
				Staff staff = new Staff(userName, password);
				currentStaff = userDao.Login(con, staff);
				if (currentStaff == null) {
					request.setAttribute("staff", staff);
					request.setAttribute("error", "用户名或密码错误！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if ("remember-me".equals(remember)) {
						rememberMe(userName, password, userType, response);
					} else {
						deleteCookie(userName, request, response);
					}
					session.setAttribute("currentUserType", "staff");
					session.setAttribute("currentUser", currentStaff);
					request.setAttribute("mainPage", "staff/blank.jsp");
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

	private void rememberMe(String userName, String password, String userType, HttpServletResponse response) {
		Cookie user = new Cookie("nttuser", userName + "-" + password + "-" + userType + "-" + "yes");
		user.setMaxAge(1 * 60 * 60 * 24 * 7);
		response.addCookie(user);
	}

	private void deleteCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			if (cookies[i].getName().equals("nttuser")) {
				if (userName.equals(userName = cookies[i].getValue().split("-")[0])) {
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}
