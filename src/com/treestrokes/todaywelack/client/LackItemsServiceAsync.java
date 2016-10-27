package com.treestrokes.todaywelack.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LackItemsServiceAsync {
	void getLackItems(String city, int year, AsyncCallback<ArrayList<LackItem>> callBack);
}
