package it.softphone.rd.gwt.client.widget.base.tab;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.TabCommandLinkCss;
import it.softphone.rd.gwt.client.widget.base.HTMLLink;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
/**
 * <h1>A container holdig {@link HTMLLink}</h1>
 * 
 * A simple class with a container holding a list of {@link HTMLLink}.
 * 
 * @author Alessandro Casolla
 *
 */
public class TabCommandLink implements IsWidget,HasVisibility{

	private FlowPanel container = new FlowPanel();
	private List<HTMLLink> mLinks = new ArrayList<HTMLLink>();
	
	/**
	 * Constructs an empty CommandLink
	 */
	public TabCommandLink(){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tabCommandLink(),new HTMLLink());
	}
	
	/**
	 * Constructs a CommandLink by the given params
	 * @param links a list of links
	 */
	public TabCommandLink(HTMLLink... links ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tabCommandLink(),links);
	}
	/**
	 * Constructs a CommandLink by the given params
	 * @param css the css to use
	 * @param links a list of links
	 */

	public TabCommandLink(TabCommandLinkCss css,HTMLLink... links){
		css.ensureInjected();
		container.setStylePrimaryName(css.tabCommandLink());
		
		buildTab(links);
}
	
	private void buildTab(HTMLLink... links){
		mLinks.clear();
		for ( HTMLLink l : links ){
			container.add(l);
			mLinks.add(l);
		}
	}
	
	/**
	 * Adds a link to the container
	 * @param link the link to add
	 */
	public void addHTMLLink( HTMLLink link ){
		container.add(link);
		mLinks.add(link);
	}
	
	public List<HTMLLink> getLinks(){
		return mLinks;
	}
	
	
	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public boolean isVisible() {
		return container.isVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		container.setVisible(visible);
		
	}

	
}
