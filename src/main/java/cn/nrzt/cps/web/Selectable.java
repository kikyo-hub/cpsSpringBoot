package cn.nrzt.cps.web;

public class Selectable{
	private String key;
	private String display;
	private Object value;
	
	public Selectable() {}
	
	public Selectable(String key,Object value) {
		this.setKey(key);
		this.setDisplay(key);
		this.setValue(value);
	}
	
	public Selectable(String key,String display,Object value) {
		this.setKey(key);
		this.setDisplay(display);
		this.setValue(value);
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

}
