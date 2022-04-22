package com.vrs.common.template;

public class TemplatesFactory {

	private static TemplatesFactory factory;
	
	private Templates templates;
	
	private TemplatesFactory(){}
	
	public static TemplatesFactory getTemplateFactory() {
		if(null == factory) {
			factory = new TemplatesFactory();
		}
		return factory;
	}
	
	public Templates getTemplates() {
		if(null == templates) {
			throw new UnsupportedOperationException(
				"Templates is null, templates are not initialized yet.");
		}
		return templates;
	}
	
	public void addTemplates(Templates tpls) {
		if(null != templates) {
			throw new UnsupportedOperationException(
				"Tempaltes are already initialized.");
		}
		this.templates = tpls;
	}
	
}
