package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.filter.FilterCalendarViewImpl;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for filter calendar
 * 
 * for an explanation on styling see {@link FilterCalendarViewImpl}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface FilterCalendarCss extends CssResource {
 
	String filterCalendarContainer();
	String filterCalendarLabel();
	String filterCalendarButton();
}

