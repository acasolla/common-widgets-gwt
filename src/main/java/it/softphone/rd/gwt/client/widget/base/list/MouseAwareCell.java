package it.softphone.rd.gwt.client.widget.base.list;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.NamedColumnCss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.cell.client.Cell;
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

public  class MouseAwareCell<C> implements Cell<C>{
	
	public MouseAwareCell(){
		NamedColumnCss css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().namedColumn();
		css.ensureInjected();
		ppHover.setStylePrimaryName(css.popup());
	}
	
	private final SafeHtmlRenderer<String> renderer = SimpleSafeHtmlRenderer.getInstance(); 
	private static 	Set<String> consumedEvents = new HashSet<String>();
	private int x = 0;
	private int y = 0;
	private final Map<Integer,SafeHtml> map = new HashMap<Integer, SafeHtml>();
	final  PopupPanel ppHover = new PopupPanel(true);
	static{
	    consumedEvents.add("click");
	    consumedEvents.add("blur");
	    consumedEvents.add("mouseout");
	    consumedEvents.add("mouseover");
	}
	
	

	private static final Logger logger = Logger.getLogger("");

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
			Element parent, C value, NativeEvent event,
			ValueUpdater<C> valueUpdater) {
	  	y = parent.getAbsoluteTop() + ( parent.getOffsetHeight() * 2 ) - 2;
		x = parent.getAbsoluteLeft();
		ppHover.setPopupPosition(x, y);
		logger.info("context column " + context.getColumn() + " index " + context.getIndex() + " key " + context.getKey() + " subindex " + context.getSubIndex());
		logger.info("element Parent " + parent.getId() + " value " + value + " valueS " + value.toString() + " valueUpdater " + valueUpdater);
		switch (DOM.eventGetType((Event)event)) {
		case Event.ONCLICK:
			logger.info("ONCLICK");
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
			Element parent,C value) {
		return false;
	}
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			C data, SafeHtmlBuilder sb) {
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
			Element parent, C value) {
		return false;
	}
	@Override
	public void setValue(com.google.gwt.cell.client.Cell.Context context,
			Element parent, C value) {
		  SafeHtmlBuilder sb = new SafeHtmlBuilder();
		  render(context, value, sb);
		  parent.setInnerHTML(sb.toSafeHtml().asString());
	}
	
	public void setTooltip( com.google.gwt.cell.client.Cell.Context context,
			String value ){
		  SafeHtmlBuilder sb = new SafeHtmlBuilder();
		  sb.appendEscaped(value);
		  map.put(context.getIndex(), sb.toSafeHtml());
		
	}
	
	private void setTooltip( com.google.gwt.cell.client.Cell.Context context ){
		String s = map.get(context.getIndex()).asString();
		ppHover.getElement().setInnerHTML(s);
	}

	
	  public void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
	    if (value != null) {
	      sb.append(value);
	    }
	  }
	
	
	
}
