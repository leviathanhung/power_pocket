package com.se4f7.SWP.service.impl;

import java.sql.SQLException;

import com.se4f7.SWP.repository.ViewRepository;
import com.se4f7.SWP.service.ViewService;

public class ViewServiceImpl implements ViewService {

	private ViewRepository view = new ViewRepository();

	public int count() {
		int result = 0;
		try {
			result = this.view.getViews();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public void updateView() {
		view.updateView();
	}
}
