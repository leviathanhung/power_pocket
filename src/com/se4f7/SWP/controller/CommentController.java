package com.se4f7.SWP.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CommentEntity;
import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CommentService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CommentServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class CommentController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;
	private AuthService authService;
	private CommentService commentService;

	public void init() {
		couponService = new CouponServiceImpl();
		authService = new AuthServiceImpl();
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("taskId");
		CouponEntity toDo = couponService.getWorkById(idString);
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		int id = Integer.parseInt(request.getParameter("taskId"));
		String createdByUser = couponService.getCreatedById(id);
		int a = authService.getUserIdByUsername(userName);
		List<CommentEntity> comments = commentService.getCommentsByTaskId(id, a, createdByUser);
		request.setAttribute("comments", comments);
		request.setAttribute("comment", toDo);
		request.getRequestDispatcher("comments.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("taskId");
		CouponEntity toDo = couponService.getWorkById(idString);
		int id = Integer.parseInt(request.getParameter("taskId"));
		String text = request.getParameter("textarea");
		String user = request.getParameter("createBy");
		String createdByUser = couponService.getCreatedById(id);
		int admin = authService.getUserIdByUsername(user);
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String createdDate = fomatter.format(date);

		boolean inserted = commentService.insertComments(text, admin, id, createdByUser, createdDate);
		if (inserted == true) {
			List<CommentEntity> comments = commentService.getCommentsByTaskId(id, admin, createdByUser);
			request.setAttribute("comments", comments);
			request.setAttribute("comment", toDo);
			request.getRequestDispatcher("comments.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "Don't added!!");
			request.getRequestDispatcher("comments.jsp").forward(request, response);
		}
	}
}
