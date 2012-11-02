package it.softphone.rd.gwt.client.widget.base.social;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.ReplyBoxCss;
import it.softphone.rd.gwt.client.widget.base.HintTextBox;
import it.softphone.rd.gwt.client.widget.base.RichTextAreaHint;
import it.softphone.rd.gwt.client.widget.base.RichTextToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>A box with a {@link RichTextAreaHint}</h1>
 * 
 * This class consists in:
 * <ul>
 * <li>{@link RichTextToolbar} at the top of the text area</li>
 * <li>{@link RichTextAreaHint} holding the text of the reply</li>
 * <li>{@link Button} the button that fires the reply action</li>
 * </ul>
 * 
 * 
 * @author Alessandro Casolla
 *
 */
public abstract class ReplyBox extends SocialBox {
	private final FlowPanel contentContainer = new FlowPanel();
	private final HintTextBox hText = new HintTextBox();
	private final RichTextAreaHint textArea = new RichTextAreaHint();
	private final CheckBox cVisible = new CheckBox();
	private final ListBox lStatus = new ListBox();
	private final HTML statusLabel = new HTML("Change Status");
	private Button btn = new Button("Reply");
	private Label cVisibleLabel = new Label("This comment is visible to the customer");
	private ReplyBoxCss css;
	
	/**
	 * The action to fire, when the button is pressed
	 */
	public abstract void reply(); 

	/**
	 * Constructs a ReplyBox
	 */
	public ReplyBox(){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().replyBox());
	}

	/**
	 * Constructs a ReplyBox by the given params
	 * @param css the css to use
	 */

	public ReplyBox( ReplyBoxCss css ){
		this.css = css;
		css.ensureInjected();
		contentContainer.addStyleName(css.answerBoxContainer());		
		init();
	}
	
	/**
	 * Sets the reply button text
	 * @param text the text of the button
	 */
	public void setButtonText( String text ){
		btn.setText(text);
	}
	
	/**
	 * The hint of the text area
	 * @param hint the displayed hint
	 */
	public void setHint( String hint ){
		hText.setHint(hint);
	}

	private void init(){
		textArea.setHint("add your comment");
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				reply();
				
			}
		});
		RichTextToolbar tt = new RichTextToolbar(textArea);
		
		textArea.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
					reply();
				}
				
			}
		});
		contentContainer.add(tt);
		contentContainer.add(textArea);
		
		contentContainer.add(btn);
		setImage(css.answerBoxImageContainer(), resources.callCenterUser().getSafeUri());
		setContent(contentContainer);
	}
	
	/**
	 * Returns the value holded by the text area
	 * @return a string
	 */
	public String getReplyText(){
		return textArea.getText();
	}
	
	/**
	 * Returns the value holded by the text area, as {@link HTML}
	 * @return HTML
	 */
	public String getReplyHTML(){
		return textArea.getHTML();
	}
	
	/**
	 * 
	 * @param visible
	 */
	public void setListVisible(boolean visible){		
		lStatus.setVisible(visible);
		statusLabel.setVisible(visible);
	}
	
	public void setCheckVisible(boolean visible){		
		cVisible.setVisible(visible);
		cVisibleLabel.setVisible(visible);
	}
	
	public void addWidget(Widget w){
		contentContainer.add(w);
	}

	public void setText( String text ){
		textArea.setText(text);
	}
}
