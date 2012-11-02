package it.softphone.rd.gwt.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
/**
 * <h1>A base flow panel</h1>
 * <br></br>
 * This flow panel, handles the following events:
 * 
 * <br></br>
 * 
 * <ul>
 * <li>{@link HasMouseOverHandlers}</li>
 * <li>{@link HasMouseOutHandlers}</li>
 * <li>{@link HasClickHandlers}</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 */
public class MouseAwareFlowPanel extends FlowPanel implements 
HasMouseOverHandlers, HasMouseOutHandlers, HasClickHandlers 
{ 
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) 
    { 
        return addDomHandler(handler, MouseOverEvent.getType()); 
    } 
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) 
    { 
        return addDomHandler(handler, MouseOutEvent.getType()); 
    } 
    public HandlerRegistration addClickHandler(ClickHandler handler) 
    { 
        return addDomHandler(handler, ClickEvent.getType()); 
    }
    
    public void removeClickHandler(){
    	
    }
    
} 