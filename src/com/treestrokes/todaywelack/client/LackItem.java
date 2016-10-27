package com.treestrokes.todaywelack.client;

import java.io.Serializable;

public class LackItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String title;
	public String href;
	public String[] tags;
	
	public LackItem() {
		this.title = "";
		this.href = "";
		this.tags = new String[0];
	}
	
	public LackItem(final String title, final String href, final String[] tags) {
		this.title = title;
		this.href = href;
		this.tags = new String[tags.length];
		for(int i = 0; i < tags.length; i += 1) {
			this.tags[i] = tags[i];
		}
	}
	
	public String title() {
		return title;
	}
	
	public String href() {
		return href;
	}
	
	public String[] tags() {
		return tags;
	}

	public boolean titleIsEmpty() {
		return title == null && title.isEmpty();
	}
	
	public boolean hrefIsEmpty() {
		return href == null && href.isEmpty();
	}

}
