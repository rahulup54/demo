package com.vrs.common.template;

import java.util.HashMap;
import java.util.Map;

public class Templates {

	private Template 	baseTemplate;
	private String 		baseTemplateName;
	
	private Map<String, Template> templates;
	
	public Templates(Template tpl) {
		this.baseTemplateName = tpl.getName();
		this.baseTemplate = tpl;
	}
	
	public Templates(String name) {
		this.baseTemplateName = name;
		this.baseTemplate = new Template(name);
	}
	
	public Map<String, Template> getTemplates() {
		return templates;
	}
	
	public void addTemplate(Template tpl) {
		if(null == templates) {
			this.templates = new HashMap<String, Template>(0);
		}
		if(templates.containsKey(tpl.getName())) {
			throw new UnsupportedOperationException(
				String.format("Template [%s] is already available.", tpl.getName())
			);
		}
		templates.put(tpl.getName(), tpl);
	}
	
	public Template getTemplate(String name) {
		if(!this.templates.containsKey(name)){
			throw new UnsupportedOperationException(
				String.format("Template [%s] is not available.", name)
			);
		}
		return templates.get(name);
	}
	
	public Template getBaseTemplate() {
		return baseTemplate;
	}
	
	public String getBaseTemplateName() {
		return baseTemplateName;
	}
	
}
