package com.vrs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.BaseController;
import com.vrs.common.View;
import com.vrs.dao.UserDaoImpl;
import com.vrs.util.RequestToVoUtil;
import com.vrs.vo.User;

public class VehicleSearchController extends BaseController {

	private UserDaoImpl dao = null;
	
	public VehicleSearchController() 
	{
		dao = new UserDaoImpl();
	}
	
	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		View view = null;
		String action = request.getParameter("action");
		//view will load search form in template
		if(action.equals("view"))
		{
			view = new View("/search/form.jsp");
		}
		else if(action.equals("search"))
		{
			User result = dao.search(RequestToVoUtil.populateUserVoForSearch(request));
			view = new View("/search/form.jsp");
			if(null == result)
			{
				view.addAttribute("message", "No record found, please search again with valid information.");
				view.addAttribute("success", true);	
			}
			else 
			{
				view.addAttribute("search", result);
			}
		}
		return view;
	}
}
