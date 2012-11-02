package it.softphone.rd.gwt.client.widget.base.list;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Column;

/**
 * <h1>A simple column class</h1>
 * 
 * This class extends {@link Column}, and needs a name as mandatory param.
 * 
 * 
 * @author Alessandro Casolla
 *
 * @param <T> the row type
 * @param <C> the column type
 */
public abstract class NamedColumn<T,C> extends Column<T, C> {

	private final String name;
	
	/**
	 * Constructs a cell by the given param
	 * @param cell The cell type
	 * @param name the column ok
	 */
	public NamedColumn(Cell<C> cell,String name) {
		super(cell);
		this.name = name;
	}
	
	/**
	 * Returns the name of the column 
	 * @return a string
	 */
	public final String getName() {
		return name;
	}

	

}
