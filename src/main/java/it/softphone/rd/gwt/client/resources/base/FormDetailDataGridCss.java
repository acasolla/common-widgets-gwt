package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.list.FormDetailDataGrid;

import com.google.gwt.resources.client.CssResource.Shared;
import com.google.gwt.user.cellview.client.DataGrid;
/**
 * The css interface for form detail data grid
 * 
 * for an explanation on styling see {@link FormDetailDataGrid}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface FormDetailDataGridCss extends DataGrid.Style {

	@ClassName("formDetailDataGrid")
	String formDetailDataGrid();
}

