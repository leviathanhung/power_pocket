package com.se4f7.SWP.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.CouponServiceImpl;
import com.se4f7.SWP.utils.MailMessage;

public class TimeOutController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;

	public void init() {
		couponService = new CouponServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate = formatter.format(date);
		int id = Integer.parseInt(request.getParameter("id"));
		String msg = null;

		String due = couponService.getDueById(id);
		String created = couponService.getCreatedById(id);

		if (due.compareTo(currentDate) <= 0) {
			MailMessage.sendDueDate(created, due);
			msg = "Sent Successfully";
		} else {
			msg = "Sent Fail - Because the time is not over!!";
		}

		request.setAttribute("message", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/load-data");
		rd.forward(request, response);
	}

}
