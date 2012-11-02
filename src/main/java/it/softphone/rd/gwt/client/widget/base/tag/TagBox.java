package it.softphone.rd.gwt.client.widget.base.tag;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.TagBoxCss;
import it.softphone.rd.gwt.client.widget.base.HintTextBox;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>A box containing {@link TagElement}</h1>
 * 
 * This class consists in a container holding:
 * 
 * <ul>
 * <li>{@link HintTextBox} that can add new TagElement to the box</li>
 * <li>A list of suggested tags, that on click will be added to the box</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 */
public class TagBox implements IsWidget {
	private final ScrollPanel scroll;
	private final FlowPanel externalContainer;
	private final FlowPanel container;
	private final FlowPanel suggestContainer;
	
	private final TagBoxCss css;
	private final HintTextBox input;
	private final Label lTags = new Label("Tags:");
	private final Label lSuggestedTags = new Label("Suggested Tags:");
	private List<TagElement> elements = new ArrayList<TagElement>();
	
	/**
	 * Constructs an Empty TgBox
	 */
	public TagBox(){
		this.scroll = new ScrollPanel();
		this.externalContainer = new FlowPanel();
		this.container = new FlowPanel();
		this.suggestContainer = new FlowPanel();
		this.input = new HintTextBox();
		this.css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().tagBox();
		css.ensureInjected();
		init();
	}
	
	/**
	 * Sets the suggested tags
	 * @param tags the tags to add as suggested
	 */
	public void setSuggestedTags( List<String> tags){
		suggestContainer.clear();
		for ( String s : tags ){
			suggestContainer.add(new TagElement(this,s,true));
		}
	}
	
	/**
	 * Creates a tag from the string, and adds it to the suggested tag container
	 * @param tag the tag to add
	 */
	public void addSuggestedTag( String tag ){
		suggestContainer.add(new TagElement(this,tag,true));
	}
	
	private void init(){
		externalContainer.add(lTags);
		scroll.setWidget(container);
		scroll.setWidth("99%");
//		scroll.setAlwaysShowScrollBars(true);
		externalContainer.add(scroll);
		externalContainer.add(lSuggestedTags);
		externalContainer.add(suggestContainer);
		input.setWidth("100px");
		input.setHint("Write a tag");
		scroll.addStyleName(css.tagContainer());
		externalContainer.addStyleName(css.tagExternalContainer());
		container.add(input);
		
		input.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String text = input.getValue();
					TagElement element = new TagElement(TagBox.this,text);
					input.setText("");
					
					if( elements.contains(element)) {
						return;
					}
					elements.add(element);
					container.add(element);
					
				}
				
			}
		});
		
		
	}
	
	/**
	 * Removes the given element from the box
	 * @param e the element to remove
	 * @return a boolean
	 */
	public boolean removeElement( TagElement e ){
		return elements.remove(e);
	}
	
	/**
	 * Adds an element to the box, if not already contained
	 * @param tag the element to add
	 */
	public void addElement( TagElement tag ){
		if ( elements.contains(tag) ) return;
		container.add(tag);
		elements.add(tag);
	}
	
	@Override
	public Widget asWidget() {
		return externalContainer;
	}
}
