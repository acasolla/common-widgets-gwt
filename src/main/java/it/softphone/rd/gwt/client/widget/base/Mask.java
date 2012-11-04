/*
 * Copyright 2011 Softphone
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @author Alessandro Casolla
 */
package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.MaskCss;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * <h1>A simple popup panel</h1>
 * 
 * This class will rendere a popup panel with an animated gif inside.
 * If not provided, the popup will be rendered with the default size:
 * <pre>
 * width: 180px;
 * heigth:140px;
 * </pre>
 * 
 * When the mask is showed, the background will be greyed.
 * 
 * The following class will be added:
 * 
 * <ul>
 * <li>.gwt-PopupPanelGlass</li>
 * </ul>

 * 
 * @author Alessandro Casolla
 *
 */
public class Mask extends PopupPanel implements ResourceAware{
	private FlowPanel container = new FlowPanel();
	private static String heigth = "140px";
	private static String width = "180px";
	private Label label = new Label("Loading...");
	private Image loader = new Image(resources.loaderCircle());
	private Button ok = new Button("Ok");
	
	/**
	 * Construct the mask with default width and heigth
	 * 
	 * @param modal
	 */
	public Mask( boolean modal ){
		this(modal,width,heigth);
	}

	/**
	 * Construct the mask by the given params
	 * 
	 * @param modal
	 * @param width the mask width
	 * @param heigth the mask heigth
	 */
	public Mask( boolean modal, String width, String heigth ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().mask(),
				modal,
				width,
				heigth);
	}

	/**
	 * Construct the mask by the given params
	 * 
	 * @param css the css to use
	 * @param modal
	 * @param width the mask width
	 * @param heigth the mask heigth
	 */
	public Mask( MaskCss css, boolean modal,String width, String heigth ){
		css.ensureInjected();
		setWidth(width);
		setHeight(heigth);
		setModal(modal);
		setAutoHideEnabled(false);
		
		setGlassEnabled(true);
		container.setStylePrimaryName(css.maskContainer());
		container.add(label);
		container.add(loader);
		add(container);
	    addStyleName(css.maskContainer());
	    ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
				container.clear();
				container.add(label);
				container.add(loader);
			
				
			}
		});
	}
	
	/**
	 * Shows the mask
	 */
	public void mask(){
		label.setText("Loading...");
		center();
	}
	
	/**
	 * Hides the mask
	 */
	public void unmask(){
		hide();
	}
	
	/**
	 * Updates the mask message
	 * @param msg the new message
	 */
	public void updateMsg( String msg ){
		label.setText(msg);
	}
	
	/**
	 * Terminates the loading, sets the message and adds a close button to the mask.
	 * 
	 * @param msg the end loading message
	 */
	public void endLoading( String msg ){
		container.clear();
		container.add(new Label(msg));
		container.add(ok);
	}

}
