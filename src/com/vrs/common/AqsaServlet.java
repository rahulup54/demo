package com.vrs.common;

import static java.lang.String.format;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrs.common.template.Template;
import com.vrs.common.template.Templates;

public class AqsaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String MAPPING_FILE = "mapping";

	private ServletContext context;
	private WebConfig webConfig = null;

	public void init(ServletConfig config) 
	throws ServletException 
	{
		super.init(config);
		this.context = config.getServletContext();
		String mappingFile = null;
		String controllersProps = config.getInitParameter(MAPPING_FILE);
		mappingFile = context.getRealPath(controllersProps);
		webConfig = new WebConfig(mappingFile);
	}

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) 
	throws ServletException, IOException 
	{
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
	throws ServletException, IOException 
	{
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
						   HttpServletResponse response) 
	{
		final String servletPath = request.getServletPath();
		final String actionPath = servletPath.substring(1,servletPath.lastIndexOf("."));
		final Map<String, ControllerConfig> controllers = webConfig.getControllers();
		final ControllerConfig ctrConfig = controllers.get(actionPath);

		View view = null;
		if (ctrConfig != null) {
			String actionUrl = ctrConfig.getAction();
			if (actionPath.equals(actionUrl)) {
				try 
				{
					final Controller controller = ctrConfig.invokeController();
					view = controller.execute(request, response);
					prepareModelData(request, view);
				} 
				catch (Exception e)
				{
					System.err.println("Exception in casting controller class: "+ e.getMessage());
				}
				dispatchRequestToView(view, webConfig, request, response);
			}
		} 
		else
			throw new UnsupportedOperationException(
				format("action %s is not found in mapping file.", actionPath)
			);
	}

	private void dispatchRequestToView(View view, 
									   WebConfig webConfig,
									   HttpServletRequest request, 
									   HttpServletResponse response)
	{
		try
		{
			RequestDispatcher rd = null;
			Templates tpls = view.getTemplates();
			Template tpl = tpls.getBaseTemplate();
			//if user don't want to render view in tpl. 
			if(view.isViewInTemplate()) 
			{
				tpl.setBody(view.getForward());
				rd = context.getRequestDispatcher(tpl.getSkeleton());
				request.setAttribute("template", tpl);
			}
			else 
			{
				rd = context.getRequestDispatcher(view.getForward());
			}
			rd.forward(request, response);
		}
		catch (Exception e)
		{
			System.err.println("Exception in dispatching view : " + e);
		}
	}

	private void prepareModelData(HttpServletRequest request, View view) 
	{
		final Map<String, Object> model = view.getModel();
		if (model != null) 
		{
			for (Entry<String, Object> data : model.entrySet())
			{
				request.setAttribute(data.getKey(), data.getValue());
			}
		}
	}
	
}
