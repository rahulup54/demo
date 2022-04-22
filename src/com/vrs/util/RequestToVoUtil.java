package com.vrs.util;

import javax.servlet.http.HttpServletRequest;

import com.vrs.vo.Insurance;
import com.vrs.vo.User;
import com.vrs.vo.Vehicle;

public class RequestToVoUtil {

	private RequestToVoUtil(){};
	
	public static User populateUserVo(HttpServletRequest request) {
		User user = new User();
		if(isNotNull(request.getParameter("userId"))) {
			user.setId(Integer.parseInt(request.getParameter("userId")));
		}
		user.setGender(request.getParameter("gender"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setNationality(request.getParameter("nationality"));
		user.setLicence(request.getParameter("licence"));
		return user;
	}
	
	public static User populateUserVoForSearch(HttpServletRequest request) {
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setLicence(request.getParameter("licence"));
		Vehicle v = new Vehicle();
		v.setPlateNumber(request.getParameter("plateNumber"));
		user.setVehicle(v);
		return user;
	}
	
	public static Insurance populateInsuranceVo(HttpServletRequest request) {
		Insurance ins = new Insurance();
		if(isNotNull(request.getParameter("insId"))){
			ins.setId(Integer.parseInt(request.getParameter("insId")));
		}
		if(isNotNull(request.getParameter("userId"))) {
			ins.setUser(new User(Integer.parseInt(request.getParameter("userId"))));
		}
		ins.setInsuranceNumber(request.getParameter("insuranceNumber"));
		ins.setInsuranceValidDate(request.getParameter("insuranceValidDate"));
		ins.setProvider(request.getParameter("provider"));
		return ins;
	}
	
	public static Vehicle populateVehicleVo(HttpServletRequest request) {
		Vehicle v = new Vehicle();
		if(isNotNull(request.getParameter("vehId"))){
			v.setId(Integer.parseInt(request.getParameter("vehId")));
		}
		if(isNotNull(request.getParameter("userId"))) {
			v.setUser(new User(Integer.parseInt(request.getParameter("userId"))));
		}
		v.setCategory(request.getParameter("category"));
		v.setColor(request.getParameter("color"));
		v.setManufacture(request.getParameter("manufacture"));
		v.setPendingFines(request.getParameter("pendingFines"));
		v.setPlateNumber(request.getParameter("plateNumber"));
		v.setRegistrationDate(request.getParameter("registrationDate"));
		v.setSource(request.getParameter("source"));
		v.setType(request.getParameter("type"));
		return v;
	}
	
	public static boolean isNotNull(String value) {
		return (null != value);
	}
	
}
