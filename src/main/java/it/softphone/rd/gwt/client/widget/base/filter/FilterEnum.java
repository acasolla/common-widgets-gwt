package it.softphone.rd.gwt.client.widget.base.filter;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.FilterPopupPanelCss;
import it.softphone.rd.gwt.client.resources.base.FilterTextBoxCss;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
/**
 * <h1>A popup to filter values</h1>
 * 
 * This popup extends {@link FilterPopupPanel}, and consists will be filled
 * by all the values of the enum provided
 * 
 * @author Alessandro Casolla
 *
 */
public abstract class FilterEnum extends FilterPopupPanel {
	
	private FlowPanel container = new FlowPanel();
	private String value = null;
	/**
	 * Constructs a filter enum with all the values of the enum provided
	 * @param e a value of the desired enum
	 */
	public FilterEnum( Enum<?> e ) {
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterTextBox(),
			 CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().filterDialog(),e);
	}
	/**
	 * Constructs a filter enum with all the values of the enum provided
	 * @param css the css to use
	 * @param cssD the css of the dialog to use
	 * @param e a value of the desired enum
	 */

	public  FilterEnum(FilterTextBoxCss css, FilterPopupPanelCss cssD,Enum<?> e) {
		super(cssD);
		css.ensureInjected();
		buildFilter(e);
		setWidget(container);
		
	}
	
	private void buildFilter( Enum<?> e ){
    	for ( int i = 0; i< e.getDeclaringClass().getEnumConstants().length; i++ ){
    		String name = "</br>";
    	
    		if ( i > -1){
    			name = e.getDeclaringClass().getEnumConstants()[i].name();
    		}
    		//final Label l = new Label(name);
    		final HTML l = new HTML();
    		l.setHTML(name);
    		l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					value = l.getText();
					processEnterKeyDown();
					hide();
					
				}
			});
    		container.add(l);
    	}

	}
	/**
	 * Returns the selected value	
	 * @return a string
	 */
	public String getValue(){
		return value;
	}

	

	
}
