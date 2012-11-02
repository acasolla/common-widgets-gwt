package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.SearchBoxCss;

import com.google.gwt.user.client.ui.TextBox;
/**
 * <h1>A simple search box</h1>
 * 
 * This class, extends {@link TextBox} giving it a background with a lens.
 * 
 *  @author Alessandro Casolla
 */
public class SearchBox extends TextBox{
	/**
	 * Constructs a SearchBox
	 */
	public SearchBox(){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().searchBox());
		
	}
	
	/**
	 * Constructs a search box by the given param
	 * @param css the css to use
	 */
	public SearchBox( SearchBoxCss css ){
		 css.ensureInjected();
		 addStyleName(css.searchBoxContainer());
	}
}
