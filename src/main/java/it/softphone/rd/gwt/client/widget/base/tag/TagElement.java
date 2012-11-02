/**
	 * Constructs a TagElement by the given params
	 * @param parent the TagBox parent
	 * @param text the element text
	 * @param isSuggested is true, the close button is hidden
	 */
	package it.softphone.rd.gwt.client.widget.base.tag;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.TagElementCss;
import it.softphone.rd.gwt.client.widget.MouseAwareFlowPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h1>A Box representing a Tag</h1>
 *
 * This class consists in box containing a text and, eventually, a delete button.
 * The create an element, a {@link TagBox} must be provided.  
 * 
 * @author Alessandro Casolla
 *
 */
public class TagElement implements IsWidget {

	private final MouseAwareFlowPanel container = new MouseAwareFlowPanel();
	private String text;
	private final TagBox parent;
	private  HTML html = new HTML();
	private final Button closeButton = new Button("x");
	
	/**
	 * Constructs a TagElement by the given params
	 * @param parent the TagBox parent
	 * @param text the element text
	 */
	public TagElement(  TagBox parent ,String text ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tagElement(),
				parent,text,false);
	}

	/**
	 * Constructs a TagElement by the given params
	 * @param tag the tag to copy
	 */

	public TagElement( TagElement tag ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tagElement(),tag.parent,tag.text,false);
	}

	/**
	 * Constructs a TagElement by the given params
	 * @param parent the TagBox parent
	 * @param text the element text
	 * @param isSuggested is true, the close button is hidden
	 */
	public TagElement( final TagBox parent ,String text,boolean isSuggested ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tagElement(),parent,text,isSuggested);
	}
	
	/**
	 * Constructs a TagElement by the given params
	 * @param css the css to use
	 * @param parent the TagBox parent
	 * @param text the element text
	 * @param isSuggested is true, the close button is hidden
	 */
	
	public TagElement(  TagElementCss css, final TagBox parent ,String text,boolean isSuggested ){
		this.parent = parent;
		setText(text);
		css.ensureInjected();
		init();
		container.setStylePrimaryName(css.tagElementContainer());

		if (! isSuggested ){
			closeButton.addClickHandler(btnCloseHandler);
			return;
		}
		container.setStylePrimaryName(css.tagElementContainerSuggested());
		container.addClickHandler(containerHandler);
		
	}
	
	private void setText( String text ){
		if ( text == null ) return;
		this.text = text;
		html.setHTML(SafeHtmlUtils.fromString(text));

	}
	
	private void init(){
		container.add(closeButton);
		container.add(html);
		container.add(closeButton);
	}
	
	@Override
	public boolean equals( Object obj ){
		if ( this == obj ) return true;
		if( !(obj instanceof TagElement)) return false;
		
		TagElement other = (TagElement) obj;
		return text.equals(other.text);
	}
	
	@Override
	public Widget asWidget() {
		return container;
	}

	private ClickHandler btnCloseHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			container.removeFromParent();
			parent.removeElement(TagElement.this);
		}
	};

	private ClickHandler containerHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			TagElement tag = new TagElement(TagElement.this);
			parent.addElement(tag);
		}
	};

}
