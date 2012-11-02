package it.softphone.rd.gwt.client.widget.base.filter;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.HasLogger;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.SortFilterButtonCellCss;
import it.softphone.rd.gwt.client.widget.base.list.NamedColumn;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * <h1>The button cell data grid header</h1>
 * <br></br>
 * 
 * This class is used as data grid's button cell.
 * When clicked, shows the given {@link PopupPanel} at it's bottom.
 * If enabled, can also display a tool tip.
 * 
 * Mandatory params:
 * 
 * <ul>
 * <li>{@link DataGrid} the parent grid</li>
 * <li>{@link NamedColumn} the parent column, from which takes the name</li>
 * <li>{@link PopupPanel} the filterPopup to show on click</li>
 *  </ul>
 *  
 * @author Alessandro Casolla
 *
 * @param <T1> the DataGrid type class
 * @param <T2> the Cell type inside NamedColumn
 */
public class SortFilterButtonCell<T1, T2> extends ButtonCell implements ResourceAware,HasLogger {
	final PopupPanel filterPopup;
 	private boolean filterPopupShowed = false;
    
	final PopupPanel ppHover = new PopupPanel(true);
    private static String defaultToolTipLabel = "click to filter content";
    private  Label hoverLabel;
    private boolean isTooltipEnabled = true;
	private final SortFilterButtonCellCss css;
	private final NamedColumn<T1,T2> column;
	private final DataGrid<T1> dataGrid;
	
	private final static String htmlTemplate = 
			"<div >" +
				"<div class=\"{2}\">" +
					"<img src=\"{1}\" rowspan=\"2\"></img>" +
				"</div>" +
				"<div class=\"{3}\">{0}</div>" +
			"</div>";

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(htmlTemplate)
		SafeHtml headerSort(String title, SafeUri imgSource,String styleFilter, String styleHeader);
		
	}

	private static Template TEMPLATE = GWT.create(Template.class);
   
	/**
	 * Constructs a SortFilterButtonCell by the given params
	 * @param dataGrid the parent grid
	 * @param column the parent column
	 * @param widget the filterPopup to show
	 */
	public SortFilterButtonCell( DataGrid<T1> dataGrid,NamedColumn<T1,T2> column,PopupPanel filterPopup ){
    	this(dataGrid,column,filterPopup,false);
    }

	/**
	 * Constructs a SortFilterButtonCell by the given params
	 * @param dataGrid the parent grid
	 * @param column the parent column
	 * @param widget the filterPopup to show
	 * @param dysplayToolTip true to show the tooltip with the default value
	 */

	public SortFilterButtonCell( DataGrid<T1> dataGrid,NamedColumn<T1,T2> column,PopupPanel filterPopup,boolean dysplayToolTip ){
    	this(dataGrid,column,filterPopup,dysplayToolTip,defaultToolTipLabel);
    }

	/**
	 * Constructs a SortFilterButtonCell by the given params
	 * @param dataGrid the parent grid
	 * @param column the parent column
	 * @param widget the filterPopup to show
	 * @param dysplayToolTip true to show the tooltip 
	 * @param toolTip the the message to show in the tooltip
	 */

	public SortFilterButtonCell( DataGrid<T1> dataGrid,
								 NamedColumn<T1,T2> column,
								 PopupPanel filterPopup,
								 boolean dysplayToolTip,
								 String tooltip ){
    	this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().sortFilterButtonCell(),
    			dataGrid,
    			column,
    			filterPopup,
    			dysplayToolTip,
    			tooltip);
    }
	/**
	 * Constructs a SortFilterButtonCell by the given params
	 * @param css the css to use
	 * @param dataGrid the parent grid
	 * @param column the parent column
	 * @param widget the filterPopup to show
	 * @param dysplayToolTip true to show the toolTip 
	 */

	public SortFilterButtonCell(SortFilterButtonCellCss css, 
								DataGrid<T1> dataGrid,
								NamedColumn<T1,T2> column,
								PopupPanel filterPopup,
								boolean dysplayToolTip,
								String label ){
    	this.dataGrid = dataGrid;
		this.column = column;
		this.filterPopup = filterPopup;
		this.css = css;
		hoverLabel = new Label(label);
		ppHover.add(hoverLabel);
		css.ensureInjected();
		ppHover.setStylePrimaryName(css.popup());

   	  
    }

	  @Override
	  public void render(Context context, SafeHtml data, SafeHtmlBuilder safeHtmlBuilder) {
		  safeHtmlBuilder.append(TEMPLATE.headerSort(column.getName(), 
				  									 resources.sort().getSafeUri(),
				  									 css.headerFilter(),
				  									 css.headerTitle()));
	    
	  }

	@Override
	public Set<String> getConsumedEvents() {
		
        Set<String> consumedEvents = new HashSet<String>();
  //      consumedEvents.add("dblclick");
        consumedEvents.add("click");
        consumedEvents.add("blur");
        consumedEvents.add("mouseout");
        consumedEvents.add("mouseover");
        return consumedEvents;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
			Element parent, String value, NativeEvent event,
			ValueUpdater<String> valueUpdater) {
		
	  	final int filterY = parent.getAbsoluteTop() + parent.getOffsetHeight();
	  	final int tooltipY = parent.getAbsoluteTop() - parent.getOffsetHeight() -5;
    	 int x = parent.getAbsoluteLeft();
    	 logger.info("parent.getOffsetWidth()=" + parent.getOffsetWidth());
    	 logger.info("parent.getAbsoluteRight()=" + parent.getAbsoluteRight());
    	 if ( filterPopup instanceof FilterCalendarViewImpl ){
	    	if ( 425 > parent.getOffsetWidth() ){
	    		x = parent.getAbsoluteRight() - 425;
	    	}
    	 }
		switch (DOM.eventGetType((Event)event)) {
		
         case Event.ONCLICK:
        	 
        	 if ( isFilterPopupShowed() ) {
        		 filterPopup.hide();
        		 setFilterPopupShowed(false);
        		 return;
        	 }
        	 
         	 filterPopup.setPopupPosition(x,filterY);
        	 
         	 if ( (parent.getAbsoluteRight() - 20) < event.getClientX() ){
        		 column.setSortable(true);
        		 return;
        	 }
	 
    		 if ( ! column.isSortable() ){
    			 filterPopup.show();
    			 setFilterPopupShowed(true);
    				
    		 } else {
    			 column.setSortable(false);
    		 }
    		 
    		 dataGrid.redrawHeaders();
    		 	
          	 
             break;
         case Event.ONMOUSEOVER:
        	 if ( isTooltipEnabled && !column.isSortable()){
	        	 ppHover.setPopupPosition(x,tooltipY - 3);
	        	 if ( !isFilterPopupShowed() )
	        		 ppHover.show();
        	 	}
             break;
         case Event.ONMOUSEOUT:
        	 if ( isTooltipEnabled ){
        		 ppHover.hide();       
        	 }
             break;
         

         default:
             break;
         }

	}
	/**
	 * Returns true is the widget is currently showed
	 * @return a boolean
	 */
	public final boolean isFilterPopupShowed() {
		return filterPopupShowed;
	}

	/**
	 * Set the status of thw widget, to be set to false, when hide method is called on the widget
	 * @param filterPopupShowed 
	 */
	public final void setFilterPopupShowed(boolean filterPopupShowed) {
		this.filterPopupShowed = filterPopupShowed;
	}

	  

}
