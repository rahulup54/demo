package com.vrs.common;

import java.util.HashMap;
import java.util.Map;

import com.vrs.common.template.Template;
import com.vrs.common.template.Templates;

public class View {
	
	private Map<String, Object> model;
    private String forward;
    private boolean viewInTpl = Boolean.TRUE; 
    
    private static Templates tpls;
 
    public View() {
    	if(null == tpls) {
    		initializeBaseTemplate();
    	}
    }

	public View(String forward) {
        this();
    	this.setForward(forward);
        this.model = new HashMap<String, Object>(0);
    }
	
	public View(String forward, boolean viewInTpl) {
        this();
    	this.setForward(forward);
    	this.viewInTpl = viewInTpl;
        this.model = new HashMap<String, Object>(0);
    }
	
    public View(String forward, Map<String, Object> data) {
        this();
    	this.setForward(forward);
        if (data != null) {
            this.model = data;
        }
    }
 
    public void addAttribute(String name, Object value) {
    	model.put(name, value);
    }
    
    public Object getAttribute(String name) {
    	Object o = null;
    	if(model.containsKey(name)) {
    		o = model.get(name);
    	}
    	return o;
    }
    
    public void clean() {
        this.model = null;
    }
 
    public Map<String, Object> getModel() {
        return model;
    }
 
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
 
    public String getForward() {
        return forward;
    }
 
    public void setForward(String forward) {
        this.forward = forward;
    }
    
    public Templates getTemplates() {
    	return tpls;
    }
    
    public boolean isViewInTemplate() {
    	return viewInTpl;
    }
    
    private void initializeBaseTemplate() {
    	Template tpl = new Template("BaseTemplate");
		tpl.setSkeleton("/template/Template.jsp");
		tpl.setHeader("/template/header.jsp");
		tpl.setFooter("/template/footer.jsp");
		tpl.setMenu("/template/menu.jsp");
		tpl.setBody("/template/body.jsp");
		tpls = new Templates(tpl);
	}
}
