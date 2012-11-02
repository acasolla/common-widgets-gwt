package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.RichTextToolbarCss;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.RichTextArea.Formatter;

/**
 * <h1>A rich tool bar</h1>
 * 
 * A toolbar to use with a {@link RichTextArea}
 * 
 * @author Alessandro Casolla
 *
 */
public class RichTextToolbar extends Composite  implements ResourceAware{
	
	private VerticalPanel outer;
	private HorizontalPanel topPanel;
 
	private RichTextArea styleText;
	private Formatter styleTextFormatter;
 
	private EventHandler evHandler;
 
	private ToggleButton bold;
	private ToggleButton italic;
	private ToggleButton underline;
	private ToggleButton stroke;
	private PushButton generatelink;
	private PushButton breaklink;
	private PushButton insertimage;
 
	private ListBox fontlist;
	private ListBox colorlist;
	
	private final RichTextToolbarCss css;
 
	/**
	 * Constructs a rich text toolbar
	 * @param richtext the textArea
	 */
	public RichTextToolbar(RichTextArea richtext) {
		this.css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().richTextToolbar();
		css.ensureInjected();
		//Initialize the main-panel
		outer = new VerticalPanel();
 
		//Initialize the two inner panels
		topPanel = new HorizontalPanel();
		topPanel.setStylePrimaryName(css.richTextToolbar());
 
		//Save the reference to the RichText area we refer to and get the interfaces to the stylings
 
		styleText = richtext;
		styleTextFormatter = styleText.getFormatter();
 
		//Set some graphical options, so this toolbar looks how we like it.
		topPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
	
		//Add the two inner panels to the main panel
		outer.add(topPanel);
 
		//Some graphical stuff to the main panel and the initialisation of the new widget
		outer.setWidth("100%");
		outer.setStyleName("RichTextToolbar");
		initWidget(outer);
 
		//
		evHandler = new EventHandler();
 
		//Add KeyUp and Click-Handler to the RichText, so that we can actualize the toolbar if neccessary
		styleText.addKeyUpHandler(evHandler);
		styleText.addClickHandler(evHandler);
 
		//Now lets fill the new toolbar with life
		buildTools();
	}
 
	/** 
	 * Click Handler of the Toolbar 
	**/
	private class EventHandler implements ClickHandler,KeyUpHandler, ChangeHandler {
		public void onClick(ClickEvent event) {
			if (event.getSource().equals(bold)) {
					styleTextFormatter.toggleBold();
			} else if (event.getSource().equals(italic)) {
					styleTextFormatter.toggleItalic();
			} else if (event.getSource().equals(underline)) {
					styleTextFormatter.toggleUnderline();
			} else if (event.getSource().equals(stroke)) {
					styleTextFormatter.toggleStrikethrough();
			} else if (event.getSource().equals(generatelink)) {
				String url = Window.prompt("Enter a link URL:", "http://");
				if (url != null) {
						styleTextFormatter.createLink(url);
				}
			} else if (event.getSource().equals(breaklink)) {
					styleTextFormatter.removeLink();
			} else if (event.getSource().equals(insertimage)) {
				String url = Window.prompt("Enter an image URL:", "http://");
				if (url != null) {
						styleTextFormatter.insertImage(url);
				}
			
			
			} else if (event.getSource().equals(styleText)) {
				//Change invoked by the richtextArea
			}
			updateStatus();
		}
 
		public void onKeyUp(KeyUpEvent event) {
			updateStatus();
		}
 
		public void onChange(ChangeEvent event) {
			System.out.println("fire");
			if (event.getSource().equals(fontlist)) {
					styleTextFormatter.setFontName(fontlist.getValue(fontlist.getSelectedIndex()));
			} else if (event.getSource().equals(colorlist)) {
					styleTextFormatter.setForeColor(colorlist.getValue(colorlist.getSelectedIndex()));
			}
		}
	}
 
	/** 
	 * Native JavaScript that returns the selected text and position of the start 
	 */
	public static native JsArrayString getSelection(Element elem) /*-{
		var txt = "";
		var pos = 0;
  		if (elem.contentWindow.getSelection) {
        	txt = elem.contentWindow.getSelection();
        	pos = elem.contentWindow.getSelection().getRangeAt(0).startOffset;
        } else if (elem.contentWindow.document.getSelection) {
        	txt = elem.contentWindow.document.getSelection();
        	pos = elem.contentWindow.document.getSelection().getRangeAt(0).startOffset;
  		} else if (elem.contentWindow.document.selection) {
        	txt = elem.contentWindow.document.selection.createRange().text;
        	pos = elem.contentWindow.document.selection.getRangeAt(0).startOffset;
        }
  		return [""+txt,""+pos];
	}-*/;
 
	/** 
	 * Method called to toggle the style in HTML-Mode 
	 */
	private void changeHtmlStyle(String startTag, String stopTag) {
		JsArrayString tx = getSelection(styleText.getElement());
		String txbuffer = styleText.getText();
		Integer startpos = Integer.parseInt(tx.get(1));
		String selectedText = tx.get(0);
		styleText.setText(txbuffer.substring(0, startpos)+startTag+selectedText+stopTag+txbuffer.substring(startpos+selectedText.length()));
	}
 
	
	/** 
	 * Private method to set the toggle buttons and disable/enable buttons which do not work in html-mode 
	 */
	private void updateStatus() {
		if (styleTextFormatter != null) {
			bold.setDown(styleTextFormatter.isBold());
			italic.setDown(styleTextFormatter.isItalic());
			underline.setDown(styleTextFormatter.isUnderlined());
			stroke.setDown(styleTextFormatter.isStrikethrough());
		}
 
		
	}
 
	/** 
	 * Initialize the options on the toolbar 
	 */
	private void buildTools() {
		//Init the TOP Panel forst
		topPanel.add(bold = createToggleButton(0,0,20,20,"Bold"));
		topPanel.add(italic = createToggleButton(0,60,20,20,"Italic"));
		topPanel.add(underline = createToggleButton(0,140,20,20,"Underline"));
		topPanel.add(stroke = createToggleButton(0,120,20,20,"Stroke"));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(generatelink = createPushButton(0,500,20,20,"Generate Link"));
		topPanel.add(breaklink = createPushButton(0,640,20,20,"Break Link"));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(insertimage = createPushButton(0,380,20,20,"Insert Image"));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(new HTML("&nbsp;"));
		
		//Init the BOTTOM Panel
		topPanel.add(fontlist = createFontList());
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(colorlist = createColorList());
	}
 
	/** 
	 * Method to create a Toggle button for the toolbar 
	 */
	private ToggleButton createToggleButton(Integer top, Integer left, Integer width, Integer height, String tip) {
	
		Image extract = new Image(resources.icons().getSafeUri(), left, top, width, height);
		ToggleButton tb = new ToggleButton(extract);
		tb.setHeight(height+"px");
		tb.setWidth(width+"px");
		tb.addClickHandler(evHandler);
		if (tip != null) {
			tb.setTitle(tip);
		}
		return tb;
	}
 
	/** 
	 * Method to create a Push button for the toolbar 
	 */
	private PushButton createPushButton( Integer top, Integer left, Integer width, Integer height, String tip) {
		Image extract = new Image(resources.icons().getSafeUri(), left, top, width, height);
		PushButton tb = new PushButton(extract);
		tb.setHeight(height+"px");
		tb.setWidth(width+"px");
		tb.addClickHandler(evHandler);
		if (tip != null) {
			tb.setTitle(tip);
		}
		return tb;
	}
 
	/** 
	 * Method to create the fontlist for the toolbar 
	 */
	private ListBox createFontList() {
	    ListBox mylistBox = new ListBox();
	    mylistBox.setStylePrimaryName(css.richTextList());
	    mylistBox.addChangeHandler(evHandler);
	    mylistBox.setVisibleItemCount(1);
 
	    mylistBox.addItem("Fonts", "");
	    mylistBox.addItem("Times New Roman", "Times New Roman");
	    mylistBox.addItem("Arial", "Arial");
	    mylistBox.addItem("Courier New", "Courier New");
	    mylistBox.addItem("Georgia", "Georgia");
	    mylistBox.addItem("Trebuchet", "Trebuchet");
	    mylistBox.addItem("Verdana", "Verdana");
	    return mylistBox;
	}
 
	/** 
	 * Method to create the colorlist for the toolbar 
	 */
	private ListBox createColorList() {
	    ListBox mylistBox = new ListBox();
	    mylistBox.setStylePrimaryName(css.richTextList());
	    mylistBox.addChangeHandler(evHandler);
	    mylistBox.setVisibleItemCount(1);
 
	    mylistBox.addItem("Colors");
	    mylistBox.addItem("white", "white");
	    mylistBox.addItem("black", "black");
	    mylistBox.addItem("red", "red");
	    mylistBox.addItem("green", "green");
	    mylistBox.addItem("yellow", "yellow");
	    mylistBox.addItem("blue", "blue");
	    return mylistBox;
	}
 
}