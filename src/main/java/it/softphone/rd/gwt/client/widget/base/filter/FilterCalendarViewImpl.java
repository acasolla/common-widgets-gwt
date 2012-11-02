package it.softphone.rd.gwt.client.widget.base.filter;


import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.FilterCalendarCss;
import it.softphone.rd.gwt.client.resources.base.FilterPopupPanelCss;
import it.softphone.rd.gwt.client.widget.base.HTMLLink;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
/**
 * <h1>A popup to filter Dates</h1>
 * 
 * This popup extends {@link FilterPopupPanel}, and consists of two {@link DateBox}.
 * This class can be used to filter dates, returning the selected values.
 * 
 * @author Alessandro Casolla
 *
 */
public abstract class FilterCalendarViewImpl extends FilterPopupPanel implements FilterCalendarView{

	@UiField DatePicker from;
	@UiField DatePicker to;
	@UiField Button closeButton;
	@UiField HTMLLink fromToday;
	@UiField HTMLLink toToday;
	private final Date today = new Date();
	
	interface Binder extends UiBinder<Widget, FilterCalendarViewImpl> {
	}
	private static final Binder binder = GWT.create(Binder.class);
	/**
	 * Constructs a FilterCalendarView
	 */
	public FilterCalendarViewImpl() {
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterCalendar(),
			 CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterDialog());
	}
	
	/**
	 * Constructs a FilterCalendarView by the given params
	 * @param css the css to use
	 * @param cssD the css to use
	 */
	public FilterCalendarViewImpl(FilterCalendarCss css,FilterPopupPanelCss cssD) {
		super(cssD);
		setWidget(binder.createAndBindUi(this));
		css.ensureInjected();
		closeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FilterCalendarViewImpl.this.hide();
				
			}
		});
		
		fromToday.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				from.setCurrentMonth(today);
				from.setValue(today);
				
			}
		});
		toToday.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				to.setCurrentMonth(today);
				to.setValue(today);
				
			}
		});

	}
	
	/**
	 * Returns the From date
	 * @return a date
	 */
	public Date getFrom(){
		return from.getValue();
	}
	
	/**
	 * Returns the To date
	 * @return a date
	 */
	public Date getTo(){
		return to.getValue();
	}
	
}
