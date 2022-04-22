package com.vrs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController implements Controller
{

	//base controller can have 
	public BaseController() {}
	
	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		return new View("");
	}

}
