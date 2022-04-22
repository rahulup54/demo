package com.vrs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public View execute(HttpServletRequest request,
	        			HttpServletResponse response) throws Exception;

}
