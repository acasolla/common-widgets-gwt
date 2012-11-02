package it.softphone.rd.gwt.client.history;

import com.google.gwt.place.shared.Place;

public interface HistoryHandler {
	public void replaceCurrentPlace(Place place);

	public void pushPlace(Place place);

	public void goTo(Place place);

	public void goTo(Place place, boolean ignore);

}
