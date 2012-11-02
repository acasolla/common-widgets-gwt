package it.softphone.rd.gwt.client.widget.base.list;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.NamedColumnCss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * <h1>A column class</h1>
 * 
 * This class adds a tooltip to a named column.
 * 
 * 
 * @author Alessandro Casolla
 *
 * @param <T>
 * @param <C>
 */
public abstract class NamedTooltipTextColumn<T> extends NamedColumn<T, String> implements ResourceAware {
	
	/**
	 * Constructs a NamedToolTipColumn by the given params
	 * @param name the column name
	 */
	public NamedTooltipTextColumn(String name) {
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().namedColumn(),name);
	}
	
	/**
	 * Constructs a NamedTooltipTextColumn by the given params
	 * @param css the css to set
	 * @param name the column name
	 */
	public NamedTooltipTextColumn(NamedColumnCss css, String name) {
		super(new TooltipCell(),name);
		css.ensureInjected();
	}
	
    public abstract String getTooltipValue(T object);
    


	@Override 
     public void render(Context context, T object, SafeHtmlBuilder sb) { 
            getCell().render(context, getValue(object), sb); 
            ((TooltipCell)getCell()).setTooltip(context,getTooltipValue(object));
      }


	/**
	 * An implementaion of cell, that on hover, shows a popup with the tooltip
	 * 
	 * @author Alessandro Casolla
	 *
	 */
	private static class TooltipCell implements Cell<String>{
		
		
		public TooltipCell(){
			NamedColumnCss css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().namedColumn();
			css.ensureInjected();
			ppHover.setStylePrimaryName(css.popup());
		}
		
		private final SafeHtmlRenderer<String> renderer = SimpleSafeHtmlRenderer.getInstance(); 
		private int x = 0;
		private int y = 0;
		private final Map<Integer,SafeHtml> tooltips = new HashMap<Integer,SafeHtml>();
		final  PopupPanel ppHover = new PopupPanel(true);
		
	

		@Override
		public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
				Element parent, String value, NativeEvent event,
				ValueUpdater<String> valueUpdater) {
		  	y = parent.getAbsoluteTop() + ( parent.getOffsetHeight() * 2 ) - 2;
			x = parent.getAbsoluteLeft();
			ppHover.setPopupPosition(x, y);
			switch (DOM.eventGetType((Event)event)) {
			case Event.ONCLICK:
				break;
			case Event.ONMOUSEOVER:
				setTooltip(context);
				ppHover.show();
				break;
			 case Event.ONMOUSEOUT:
				 ppHover.hide();
				break;
			}
			
		}

		@Override
		public boolean dependsOnSelection() {
			return false;
		}
		@Override
		public Set<String> getConsumedEvents() {
			Set<String> consumedEvents = new HashSet<String>();
		    consumedEvents.add("click");
		    consumedEvents.add("blur");
		    consumedEvents.add("mouseout");
		    consumedEvents.add("mouseover");
		    return consumedEvents;

		}
		@Override
		public boolean handlesSelection() {
			
			return false;
		}
		@Override
		public boolean isEditing(com.google.gwt.cell.client.Cell.Context context,
				Element parent,String value) {
			return false;
		}
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context,
				String data, SafeHtmlBuilder sb) {
			if (data == null) {
			      render(context, (SafeHtml) null, sb);
			    } else {
			    	if ( data instanceof String ){
			    		String s = (String)data;
			    		render(context, renderer.render(s), sb);
			    	}
			    }
			
		}
		

		@Override
		public boolean resetFocus(com.google.gwt.cell.client.Cell.Context context,
				Element parent, String value) {
			return false;
		}
		@Override
		public void setValue(com.google.gwt.cell.client.Cell.Context context,
				Element parent, String value) {
			  SafeHtmlBuilder sb = new SafeHtmlBuilder();
			  render(context, value, sb);
			  parent.setInnerHTML(sb.toSafeHtml().asString());
		}
		
		/**
		 * The tooltip to show
		 * @param context the context
		 * @param value the tooltip
		 */
		public void setTooltip( com.google.gwt.cell.client.Cell.Context context,
				String value ){
			  SafeHtmlBuilder sb = new SafeHtmlBuilder();
			  sb.appendEscaped(value);
			  tooltips.put(context.getIndex(), sb.toSafeHtml());
			
		}
		
		private void setTooltip( com.google.gwt.cell.client.Cell.Context context ){
			ppHover.getElement().setInnerHTML(tooltips.get(context.getIndex()).asString());
		}

		
		  public void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
		    if (value != null) {
		      sb.append(value);
		    }
		  }
		
		
		
	}

}
