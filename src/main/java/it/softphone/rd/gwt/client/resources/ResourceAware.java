package it.softphone.rd.gwt.client.resources;

import com.google.gwt.core.client.GWT;

public interface ResourceAware {

	Resources resources = GWT.create(Resources.class);
}
