package cn.nrzt.cps.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectableResult implements Serializable{
	private static final long serialVersionUID = -6462318299245695310L;
	private List<Selectable> selectable =new ArrayList<>();

	public SelectableResult() {}
	
	public SelectableResult(List<Selectable> list) {
		selectable.addAll(list);
	}
	 
	 public void  insert(Selectable kv) {
		 this.selectable.add(kv);
	 }
	 
	 public void  insert(String key,Object value) {
		 this.selectable.add(new Selectable(key, value));
	 }
	 
	 public void  insert(String key,String displayValue,Object value) {
		 this.selectable.add(new Selectable(key, displayValue,value));
	 }

	public List<Selectable> getSelectable() {
		return selectable;
	}

	public void setSelectable(List<Selectable> selectable) {
		this.selectable = selectable;
	}
	 

}
