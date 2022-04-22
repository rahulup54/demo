package com.vrs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.BaseController;
import com.vrs.common.View;
import com.vrs.dao.UserDaoImpl;
import com.vrs.dao.VrsDao;
import com.vrs.util.RequestToVoUtil;
import com.vrs.vo.User;

public class UserController extends BaseController {

	private VrsDao<User> dao = null;
	
	public UserController() {
		dao = new UserDaoImpl();
	}
	
	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		String action = request.getParameter("action");
		View view = null;
		
		if(action.equals("home")) 
		{
			view = new View("/body.jsp");
		}
		else if(action.equals("view")) 
		{
			view = new View("/user/List.jsp");
			view.addAttribute("users", dao.load());
		}
		else if(action.equals("add"))
		{
			User user = RequestToVoUtil.populateUserVo(request);
			dao.add(user);
			String message = String.format("User %s added successfully.", user.getName());
			view = new View("/user/List.jsp");
			view.addAttribute("message", message);
			view.addAttribute("success", true);
			view.addAttribute("users", dao.load());
		}
		else if(action.equals("viewUser"))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			view = new View("/user/ViewUser.jsp");
			view.addAttribute("user", dao.get(userId));
		}
		else if(action.equals("edit"))
		{
			User user = RequestToVoUtil.populateUserVo(request);
			dao.update(user);
			String message = String.format("User %s detail edit successfully.", user.getName());
			view = new View("/user/List.jsp");
			view.addAttribute("message", message);
			view.addAttribute("success", true);
			view.addAttribute("users", dao.load());
		}
		else if(action.equals("delete"))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			dao.delete(userId);
			view = new View("/user/List.jsp");
			view.addAttribute("users", dao.load());
			view.addAttribute("message", "User successfully deleted.");
			view.addAttribute("success", true);
		}
		return view;
	}
	
}
