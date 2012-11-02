package it.softphone.rd.gwt.client.widget.base.filter;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.FilterPopupPanelCss;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.PopupPanel;
/**
 * <h1>A popup</h1>
 * 
 * This popup is used as base element of the following classes:
 * 
 * {@link FilterCalendarViewImpl}
 * {@link FilterTextBox}
 * {@link FilterEnum}
 * 
 * Manages the following {@link KeyDownHandler}:
 * <ul>
 * <li>KEY_ESCAPE : hides the popup</li>
 * <li>KEY_ENTER  : starts the processEnterKeyDown() method</li>
 * </ul>
 * @author Alessandro Casolla
 *
 */
public abstract class FilterPopupPanel extends PopupPanel  {
		
	
	public FilterPopupPanel( ) {
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterDialog());
	}


	public FilterPopupPanel(FilterPopupPanelCss css) {
		css.ensureInjected();
		setStylePrimaryName(css.filterDialog());
		setAutoHideEnabled(true);
	}
	
	public abstract void processEnterKeyDown();

	
	@Override
	  protected void onPreviewNativeEvent(final NativePreviewEvent event) {
	    super.onPreviewNativeEvent(event);
	    switch (event.getTypeInt()) {
	        case Event.ONKEYDOWN:
	            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
	            	hide();
	            }
	            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
	            	processEnterKeyDown();
	            	hide();
	            }
	            
	            break;
	            
	        }
	    }
}
