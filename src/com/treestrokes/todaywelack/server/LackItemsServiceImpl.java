package com.treestrokes.todaywelack.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.treestrokes.todaywelack.client.LackItem;
import com.treestrokes.todaywelack.client.LackItemsService;

@SuppressWarnings("serial")
public class LackItemsServiceImpl extends RemoteServiceServlet implements LackItemsService {
	
	final static String[] empty = new String[0];
	private final ArrayList<LackItem> items = new ArrayList<>();
	
	public LackItemsServiceImpl() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("C:\\Users\\vijay\\Documents\\git\\todaywelack\\todaywelack\\lack.csv")	);
			while(scanner.hasNext()) {
				final String line = scanner.nextLine();
				if(line == null || line.isEmpty())
					continue;
				
				String[] split = line.split(",");
				items.add(new LackItem(split[1], split[2], empty));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<LackItem> getLackItems(String city, int year) {
		items.add(new LackItem("Vijay's website", "http://vpalepu.com", empty));
		items.add(new LackItem("Google", "http://google.com", empty));
		items.add(new LackItem("UCI's homepage", "http://uci.edu", empty));
		return items;
	}

}
