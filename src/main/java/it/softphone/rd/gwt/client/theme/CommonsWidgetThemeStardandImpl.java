package it.softphone.rd.gwt.client.theme;

import it.softphone.rd.gwt.client.CommonWidgetsTheme;
import it.softphone.rd.gwt.client.resources.CommonWidgetsCssResource;

import com.google.gwt.core.shared.GWT;

public class CommonsWidgetThemeStardandImpl implements CommonWidgetsTheme{

	private CommonWidgetsCssResource bundle;
	
	public CommonsWidgetThemeStardandImpl(){
		bundle = GWT.create(CommonWidgetsCssResource.class);
	}

	@Override
	public CommonWidgetsCssResource getCommonsWidgetClientBundle() {
		return bundle;
	}
}
