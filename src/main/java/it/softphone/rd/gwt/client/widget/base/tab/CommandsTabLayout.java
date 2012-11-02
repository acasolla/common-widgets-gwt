package it.softphone.rd.gwt.client.widget.base.tab;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 * <h1>A {@link TabLayoutPanel} extends</h1>
 * 
 * This class injects at the top rigth of the panel a {@link TabCommandLink}.
 *  
 * @author Alessandro Casolla
 *
 */
public class CommandsTabLayout extends TabLayoutPanel{

	private final static String ID_ELEMENT = "commands";
	private TabCommandLink link = new TabCommandLink();

	/**
	 * Constructs a CommandsTabLayout  
	 * @param barHeight
	 * @param barUnit
	 */
	public CommandsTabLayout( double barHeight, Unit barUnit ){
		super(barHeight,barUnit);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		addCommandLinks();	
		
	}
	
	/**
	 * Updates the command link
	 * @param tbl the new command link
	 */
	public void updateCommandLink( TabCommandLink tbl ){
		try {
			RootPanel.get(ID_ELEMENT);
		} catch (AssertionError ae) {

		}
		Widget w = RootPanel.get(ID_ELEMENT);
		if ( w == null ) {
			addCommandLinks();
			return;
		}
		RootPanel.get(ID_ELEMENT).remove(link);
		RootPanel.get(ID_ELEMENT).add(tbl);
		this.link = tbl;
	}
	

	private void addCommandLinks(){
		final Element e = DOM.createDiv();
		e.setId(ID_ELEMENT );
		DOM.insertBefore(this.getElement(), e, DOM.getFirstChild(getElement()));
		
		try {
			RootPanel.get(ID_ELEMENT);
		} catch (AssertionError ae) {

		}

		RootPanel.get(ID_ELEMENT).add(link);
		}
	

}
