package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.HintTextBoxCss;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;

/**
 * <h1>A text box that handles hints</h1>
 * <br></br>
 * This is a simple text box, that manages an hint message.
 * 
 * 
 * @author Alessandro Casolla
 *
 */
public class HintTextBox extends TextBox implements BlurHandler, FocusHandler{
	private String hint;
	private HandlerRegistration blurHandler;
	private HandlerRegistration focusHandler;
	private HintTextBoxCss css;
	
	/**
	 * Construnct an hint text box
	 */
	public HintTextBox( ){
		this("","");
	}
	
	/**
	 * Construct an hint box by the given params
	 * @param defaultValue the default value
	 */
	public HintTextBox(String defaultValue){
		this(defaultValue,"");
		
	}
	
	/**
	 * Construct an hint box by the given params
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */
	
	public HintTextBox(String defaultValue, String hint){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().hintTextBoxCss(),defaultValue,hint);
	}

	/**
	 * Construct an hint box by the given params
	 * @param css the css to use
	 * @param defaultValue the default value
	 * @param hint the displayed hint
	 */
	public HintTextBox(HintTextBoxCss css,String defaultValue, String hint){
		super();
		this.css = css;
		css.ensureInjected();
		setStylePrimaryName(css.textInput());
		setText(defaultValue);
		if ( hint != null && hint != "")
			setHint(hint);
	}

	/**
	 * Adds an hint if the parameter is not NULL or EMPTY
	 * 
	 * @param hint the hint displayed
	 */
	public void setHint(final String hint){
		this.hint = hint;
		
		if ((hint != null) && (hint != "")){
			blurHandler = addBlurHandler(this);
			focusHandler = addFocusHandler(this);
			enableHint();
		
		} else {
			// Remove handlers
			blurHandler.removeHandler();
			focusHandler.removeHandler();
		}
	}

	@Override
	public void onBlur(BlurEvent event){
		enableHint();
	}
	
	void enableHint(){
		String text = getText();
		if (( text == null || text.trim().length() == 0) )
		{
			setText(hint);
			addStyleName(css.textInputHint());
		}
	}

	public void reset(){
		setText(hint);
		addStyleName(css.textInputHint());

	}
	@Override
	public void onFocus(FocusEvent event){
		removeStyleName(css.textInputHint());
			
		if (getValue().equalsIgnoreCase(hint) || getText().trim().length() == 0 ){
			setText("");
		}
		
	}


	@Override
	public String getText() {
		String text = super.getText();
		
		if ( text == null || text.equalsIgnoreCase(hint) ) text = "";
		return text;
	}
	
	
	
	
}
