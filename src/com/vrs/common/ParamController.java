package com.vrs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamController implements Controller {

	@Override
	public View execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception 
	{
		String view = request.getParameter("v") != null ? 
					  request.getParameter("v") : request.getParameter("view");
		String folder = request.getParameter("folder");
		
		String query = "";
		if(null == folder) {
			query = String.format("/%s.jsp", view);
		}
		else {
			query = String.format("/%s/%s.jsp", folder, view);
		}
		return new View(query);
	}

}
