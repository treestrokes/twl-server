package com.treestrokes.todaywelack.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("twel")
public interface LackItemsService extends RemoteService {
	ArrayList<LackItem> getLackItems(final String city, final int year);
}
