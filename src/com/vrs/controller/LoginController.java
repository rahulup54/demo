package com.vrs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.BaseController;
import com.vrs.common.View;

public class LoginController extends BaseController {

	public LoginController(){}
	
	@Override
	public View execute(HttpServletRequest request, 
						HttpServletResponse response)
	throws Exception
	{
		//View view = super.execute(request, response);
		String action = request.getParameter("action");
		if(action.equals("login")) 
		{
			String name = request.getParameter("name");
			String pwd	= request.getParameter("password");
			if(name.equals("fatma") && pwd.equals("fatma123")) 
			{
				return new View("/body.jsp");
			}
			else 
			{
				View view =  new View("/index.jsp", false);
				view.addAttribute("success", false);
				view.addAttribute("message", "Invalid credentials, Please try again.");
				return view;
			}
		}
		else 
		{
			return new View("/index.jsp", false);
		}
	}

}
