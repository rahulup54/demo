package com.vrs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.BaseController;
import com.vrs.common.View;
import com.vrs.dao.VehicleDaoImpl;
import com.vrs.util.RequestToVoUtil;
import com.vrs.vo.Vehicle;

public class VehicleController extends BaseController {

	private VehicleDaoImpl dao;
	
	public VehicleController() 
	{
		dao = new VehicleDaoImpl();
	}
	
	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		String action = request.getParameter("action");
		View view = null;
		if(action.equals("view")) 
		{
			view = new View("/vehicle/List.jsp");
			view.addAttribute("vehicles", dao.load());
		}
		else if(action.equals("add"))
		{
			Vehicle v = RequestToVoUtil.populateVehicleVo(request);
			dao.add(v);
			view = new View("/vehicle/List.jsp");
			view.addAttribute("message", "Vehicle details added successfully.");
			view.addAttribute("success", true);
			view.addAttribute("vehicles", dao.load());
		}
		else if(action.equals("viewUserVeh"))
		{
			int userId = Integer.parseInt(request.getParameter("userId"));
			Vehicle v = dao.getVehicleByUser(userId);
			if(null == v)
			{
				view = new View("/vehicle/add.jsp");
				view.addAttribute("message", "Vehicle details not found for this user, Please add details.");
				view.addAttribute("success", true);
			}
			else
			{
				view = new View("/vehicle/ViewVehicle.jsp");
				view.addAttribute("vehicle", v);
			}
		}
		else if(action.equals("viewVehicle"))
		{
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			view = new View("/vehicle/ViewVehicle.jsp");
			view.addAttribute("vehicle", dao.get(vehId));
		}
		else if(action.equals("delete"))
		{
			int vehId = Integer.parseInt(request.getParameter("vehId"));
			dao.delete(vehId);
			view = new View("/vehicle/List.jsp");
			view.addAttribute("vehicles", dao.load());
			view.addAttribute("message", "Vehicle successfully deleted.");
			view.addAttribute("success", true);
		}
		else if(action.equals("edit"))
		{
			Vehicle v = RequestToVoUtil.populateVehicleVo(request);
			dao.update(v);
			view = new View("/vehicle/List.jsp");
			view.addAttribute("message", "Vehicle detail edit successfully.");
			view.addAttribute("success", true);
			view.addAttribute("vehicles", dao.load());
		}
		return view;
	}
}
