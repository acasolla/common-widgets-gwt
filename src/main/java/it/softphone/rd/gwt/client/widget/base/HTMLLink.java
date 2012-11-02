package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.HTMLLinkCss;

import com.google.gwt.user.client.ui.HTML;

/**
 * <h1>An HTML link</h1>
 * <br></br>
 * An HTML that looks like an hyperlink
 * 
 * The class consist of a simple dive like this:
 * <pre>
 * &lt;div class="htmlLink">value&lt;/div>
 * </pre>
 * 
 * @author Alessandro Casolla
 *
 */
public class HTMLLink extends HTML{

	boolean clicked = false;

	/**
	 * Construct an HTMLLink
	 */
	public HTMLLink( ){
		this("");
	}
	
	/**
	 * Construct an HTMLLink by the given params
	 * @param defaultValue the default value
	 */
	public HTMLLink(String defaultValue){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().htmlLink(),defaultValue);
	}
	
	/**
	 * Construt an HTMLLink by the given params
	 * @param css the css to use
	 * @param defaultValue the default value
	 */
	public HTMLLink(HTMLLinkCss css ,String defaultValue){
		css.ensureInjected();
		setStylePrimaryName(css.htmlLink());
		setHTML(defaultValue);
		
	}
	
	public final boolean isClicked() {
		return clicked;
	}

	public final void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	

}
