package it.softphone.rd.model.shared;

import com.google.gwt.safehtml.shared.SafeUri;

import it.softphone.rd.gwt.client.widget.base.Portrait;

/**
 * This interface is used by the {@link Portrait} class, providing the necessary information to render
 * @author Alessandro Casolla
 *
 */
public interface BaseUser {
	String getName();
	String getSurname();
	SafeUri getImageUri();
	
}
