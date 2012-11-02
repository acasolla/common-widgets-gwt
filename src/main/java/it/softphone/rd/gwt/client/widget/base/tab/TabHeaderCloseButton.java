package it.softphone.rd.gwt.client.widget.base.tab;


import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.TabHeaderCloseButtonCss;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>A custom tab item</h1>
 * 
 * This class renders a custom item tab consisting in:
 * <ul>
 * <li>An HTML title</li>
 * <li>A Close Button</li>
 * </ul>
 * 
 * The mandatory params are:
 * <ul>
 * <li>{@link TabLayoutPanel} parent table</li>
 * <li>String title</li>
 * <li>{@link Widget} the widget to hold</li>
 * <li>A map the wich key are the id of the opened widgets</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 * @param <T> the type of the class
 */
public class TabHeaderCloseButton<T extends IsWidget> implements IsWidget{

	private FlowPanel container;
	private HTML header;
	private Button close;
	private Widget widget;
	private TabLayoutPanel tabPanel;
	private final TabHeaderCloseButtonCss css;
	private  final Map<Long,T> openedTabs;
	
	/**
	 * Constructs a TabHeaderCloseButton by the given params
	 * @param tabPanel the parent tab
	 * @param header the title of the tab item
	 * @param widget the child widget
	 * @param openedTabs a map of the opened tabs
	 */
	public TabHeaderCloseButton( TabLayoutPanel tabPanel, String header, Widget widget, Map<Long,T> openedTabs){
		this.tabPanel = tabPanel;
		this.header = new HTML(SafeHtmlUtils.fromString(header));
		this.widget = widget;
		this.openedTabs = openedTabs;
		this.css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tabHeaderCloseButtonCss();
		css.ensureInjected();
		init();
	}
	
	
	private void init(){
		container = new FlowPanel();
		close = new Button("x");
		close.addStyleName(css.tabHeaderBtn());
		close.addClickHandler(handler);
		container.add(header);
		container.add(close);
		container.addStyleName(css.tabHeaderContainer());
	}
	
	private ClickHandler handler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			tabPanel.remove(widget);
			long key = -1;
			for ( Long i : openedTabs.keySet()){
				IsWidget w = openedTabs.get(i);
				if ( w.equals(widget)) {
					key = i;
					break;
				}
			}
			
			if ( key > 0 ){
				openedTabs.remove(key);
			}
			
			
		}
	};

	@Override
	public Widget asWidget() {
		return container;
	}
}
