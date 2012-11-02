package it.softphone.rd.model.shared;

import it.softphone.rd.gwt.client.widget.base.list.FormDetailDataGrid;


/**
 * Interface used by {@link FormDetailDataGrid}, to show the detail form.
 * @author Alessandro Casolla
 *
 */
public interface Selectable {

	void setSelected( boolean selected );
	boolean isSelected();
	

}
