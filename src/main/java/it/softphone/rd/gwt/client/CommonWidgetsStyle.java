package it.softphone.rd.gwt.client;

import it.softphone.rd.gwt.client.theme.CommonsWidgetThemeStardandImpl;


public class CommonWidgetsStyle {
	
	private static CommonWidgetsTheme theme;
	public static final String FORM_DETAIL_GRID_CSS = "it/softphone/rd/gwt/client/resources/base/css/formDetailDataGrid.css";
	
	public static final CommonWidgetsTheme getTheme() {
		if (theme == null) {
			theme = new CommonsWidgetThemeStardandImpl();
			theme.getCommonsWidgetClientBundle().tabHeaderCloseButtonCss().ensureInjected();

		}
		return theme;
	}


}
