package com.treestrokes.todaywelack.client;

import com.treestrokes.todaywelack.shared.FieldVerifier;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Todaywelack implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final LackItemsServiceAsync lackItemsService = GWT.create(LackItemsService.class);
	
	private AnchorElement makeA(final String href, final String text, boolean target) {
		AnchorElement aElement = AnchorElement.as(DOM.createAnchor());
		aElement.setAttribute("href", href);
		aElement.setInnerText(text);
		aElement.setAttribute("target", "_blank");
		return aElement;
	}
	
	private AnchorElement makeA(final String href, final String text) {
		return makeA(href, text, true);
	}

	private LIElement makeLi(Element htmlElement) {
		LIElement listItem = LIElement.as(DOM.createElement("li"));
		listItem.appendChild(htmlElement);
		return listItem;
	}
	
	private LIElement makeLi(String listItemText) {
		LIElement listItem = LIElement.as(DOM.createElement("li"));
		listItem.setInnerText(listItemText);
		return listItem;
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		final Element lacklist = Document.get().getElementById("lacklist");

		AsyncCallback<ArrayList<LackItem>> lackItemsSvcCallBack = new AsyncCallback<ArrayList<LackItem>>() {

			@Override
			public void onFailure(Throwable caught) {
				lacklist.appendChild(makeLi("Now, this is awkward..."));
				lacklist.appendChild(makeLi("Our backend service is broken, and we are working hard to fix it."));
				lacklist.appendChild(makeLi("So, stay tuned, and thank you for your interest and support!"));
			}

			@Override
			public void onSuccess(ArrayList<LackItem> items) {
				for(LackItem item : items) {
					if(item == null || item.titleIsEmpty() || item.hrefIsEmpty())
						continue;

					AnchorElement aElement = makeA(item.href(), item.title());
					lacklist.appendChild(makeLi(aElement));
				}
				
			}
		};

		lackItemsService.getLackItems("Delhi", 2010, lackItemsSvcCallBack);
	}
}