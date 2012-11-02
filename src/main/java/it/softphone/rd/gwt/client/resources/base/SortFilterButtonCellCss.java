package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.filter.SortFilterButtonCell;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for SortFilterButton
 * 
 * for an explanation on styling see {@link SortFilterButtonCell}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface SortFilterButtonCellCss extends CssResource {
  
	String headerTitle();
	String headerFilter();
	String popup();
	
}

