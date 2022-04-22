package com.vrs.common.template;

public final class Template {
	
	private String name;
	private String header, menu, body, footer;
	private String skeleton;
	
	public Template(String name) {
		this.name = name;
	}
	
	public Template(Template tpl) {
		this.name = tpl.name;
		this.body = tpl.body;
		this.header = tpl.header;
		this.footer = tpl.footer;
		this.menu = tpl.menu;
		this.skeleton = tpl.skeleton;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(String skeleton) {
		this.skeleton = skeleton;
	}	
}
