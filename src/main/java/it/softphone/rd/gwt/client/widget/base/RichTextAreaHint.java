package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.RichTextAreaHintCss;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RichTextArea;
/**
 * <h1>A Rich Text Area Class</h1>
 * 
 * This class provides a text area with a rich toolbar and an hint
 * 
 * @author Alessandro Casolla
 *
 */
public class RichTextAreaHint extends RichTextArea implements BlurHandler, FocusHandler{

	private String hint;
	private HandlerRegistration blurHandler;
	private HandlerRegistration focusHandler;
	private RichTextAreaHintCss css;

	/**
	 * Constructs a rich text area
	 */
	public RichTextAreaHint( ){
		this("","");
	}
	
	/**
	 * Constructs a rich text area by the given params
	 * @param defaultValue the default value
	 */
	public RichTextAreaHint(String defaultValue){
		this(defaultValue,"");
		
	}
	/**
	 * Constructs a rich text area by the given params
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */
	public RichTextAreaHint(String defaultValue, String hint){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().richTextAreaHint(),defaultValue,hint);
	}
	/**
	 * Constructs a rich text area by the given params
	 * @param css the css to use
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */
	public RichTextAreaHint(RichTextAreaHintCss css,String defaultValue, String hint){
		super();
		this.css = css;
		css.ensureInjected();
		addStyleName(css.textInput());
		setText(defaultValue);
		if ( hint != null && hint != "")
			setHint(hint);
	}

	/**
	 * Adds an hint if the parameter is not NULL or EMPTY
	 * 
	 * @param hint
	 */
	public void setHint(final String hint)
	{
		this.hint = hint;
		
		if ((hint != null) && (hint != ""))
		{
			blurHandler = addBlurHandler(this);
			focusHandler = addFocusHandler(this);
			enableHint();
		}
		else
		{
			// Remove handlers
			blurHandler.removeHandler();
			focusHandler.removeHandler();
		}
	}

	@Override
	public void onBlur(BlurEvent event)
	{
		enableHint();
	}
	
	void enableHint()
	{
		String text = getText(); 
		if ((text.length() == 0) || (text.equalsIgnoreCase(hint)))
		{
			setText(hint);
			addStyleName(css.textInputHint());
		}
	}

	@Override
	public void onFocus(FocusEvent event)
	{
		removeStyleName(css.textInputHint());
		
		if (getText().equalsIgnoreCase(hint))
		{
			// Hide watermark
			setText("");
		}
	}
	

}
