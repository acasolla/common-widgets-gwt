package it.softphone.rd.gwt.client.widget.base.filter;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.FilterPopupPanelCss;
import it.softphone.rd.gwt.client.resources.base.FilterTextBoxCss;
import it.softphone.rd.gwt.client.widget.base.HintTextBox;
/**
 * <h1>A popup to filter Strings</h1>
 * 
 * This popup extends {@link FilterPopupPanel}, and contains an {@link HintTextBox}
 * 
 * @author Alessandro Casolla
 *
 */

public abstract class FilterTextBox extends FilterPopupPanel {
	
	private HintTextBox box;

	/**
	 * Constructs a FilterTextBox
	 */
	public FilterTextBox(){
		this("");
	}
	
	/**
	 * Constructs a FilterTextBox by the given params
	 * @param defaultValue the default value
	 */
	public FilterTextBox(String defaultValue) {
		this(""," ");
	}
	
	/**
	 * Constructs a FilterTextBox by the given params
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */
	public FilterTextBox(String defaultValue,String hint) {
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterTextBox(),
			 CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterDialog(),
			 defaultValue,hint);
	}
	/**
	 * Constructs a FilterTextBox by the given params
	 * @param css the css to use
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */

	public FilterTextBox(FilterTextBoxCss css, FilterPopupPanelCss cssD,String defaultValue, String hint) {
		super(cssD);
		css.ensureInjected();
		box = new HintTextBox(css, defaultValue, hint);
		box.addStyleName(css.filterTextBox());
		setWidget(box);
		
	}
		
	/**
	 * Returns the typed value
	 * @return a string
	 */
	public String getValue(){
		return box.getValue();
	}

	

	
}
