package com.vrs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.BaseController;
import com.vrs.common.View;
import com.vrs.dao.InsuranceDaoImpl;
import com.vrs.util.RequestToVoUtil;
import com.vrs.vo.Insurance;

public final class InsuranceController extends BaseController {

	private InsuranceDaoImpl dao = null;
	
	public InsuranceController() 
	{
		dao = new InsuranceDaoImpl();
	}

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		String action = request.getParameter("action");
		View view = null;
		if(action.equals("view")) 
		{
			view = new View("/insurance/List.jsp");
			view.addAttribute("insurances", dao.load());
		}
		else if(action.equals("add"))
		{
			Insurance ins = RequestToVoUtil.populateInsuranceVo(request);
			dao.add(ins);
			view = new View("/insurance/List.jsp");
			view.addAttribute("message", "Insurance details added successfully.");
			view.addAttribute("success", true);
			view.addAttribute("insurances", dao.load());
		}
		else if(action.equals("viewUserIns"))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			Insurance ins = dao.getInsuranceByUser(userId);
			if(null == ins)
			{
				view = new View("/insurance/add.jsp");
				view.addAttribute("message", "Insurance details not found for this user, Please add details.");
				view.addAttribute("success", true);
			}
			else
			{
				view = new View("/insurance/ViewInsurance.jsp");
				view.addAttribute("insurance", ins);
			}
		}
		else if(action.equals("viewIns"))
		{
			int insId = Integer.parseInt(request.getParameter("insId"));
			view = new View("/insurance/ViewInsurance.jsp");
			view.addAttribute("insurance", dao.get(insId));
		}
		else if(action.equals("delete"))
		{
			int insId = Integer.parseInt(request.getParameter("insId"));
			dao.delete(insId);
			view = new View("/insurance/List.jsp");
			view.addAttribute("insurances", dao.load());
			view.addAttribute("message", "Insurance successfully deleted.");
			view.addAttribute("success", true);
		}
		else if(action.equals("edit"))
		{
			Insurance ins = RequestToVoUtil.populateInsuranceVo(request);
			dao.update(ins);
			view = new View("/insurance/List.jsp");
			view.addAttribute("message", "Insurance detail edit successfully.");
			view.addAttribute("success", true);
			view.addAttribute("insurance", dao.load());
		}
		return view;
	}
	
}
