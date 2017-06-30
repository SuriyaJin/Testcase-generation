package com.ebox.WebTest;

public class TestCase {
	private Integer id;
	private String selector;
	private String selectorValue;
	private String action;
	private Boolean isText;
	private String text;
	
	public TestCase() {}
	
	public TestCase(Integer id,String selector, String selectorValue, String action,Boolean isText) {
		super();
		this.id = id;
		this.selector = selector;
		this.selectorValue = selectorValue;
		this.action = action;
		this.isText = isText;
	}

	public TestCase(Integer id,String selector, String selectorValue, String action, Boolean isText, String text) {
		super();
		this.id = id;
		this.selector = selector;
		this.selectorValue = selectorValue;
		this.action = action;
		this.isText = isText;
		this.text = text;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getSelectorValue() {
		return selectorValue;
	}

	public void setSelectorValue(String selectorValue) {
		this.selectorValue = selectorValue;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Boolean getIsText() {
		return isText;
	}

	public void setIsText(Boolean isText) {
		this.isText = isText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
