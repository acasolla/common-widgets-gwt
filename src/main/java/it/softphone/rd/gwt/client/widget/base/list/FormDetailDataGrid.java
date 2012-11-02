package it.softphone.rd.gwt.client.widget.base.list;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.FormDetailDataGridCss;
import it.softphone.rd.gwt.client.widget.AnimatedContent;
import it.softphone.rd.model.shared.Selectable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
/**
 * <h1>A custom data grid</h1>
 * 
 * This class, extends {@link DataGrid}, and on the row click, appends the given widget to the table.
 * This is very useful for the grid containig forms.
 * 
 * The mandatory params are:
 * <ul>
 * <li>{@link ProvidesKey} keyProvider, as for standard DataGrid</li>
 * <li>List of values, holded by the grid</li>
 * <li>{@link RowDetailWidget} detail, that will be appended to the clicked row</li>
 * <li>An instance of the object used as empty row container</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 * @param <T> the type of the grid, that must extend the {@link Selectable} interface
 */
public class FormDetailDataGrid<T extends Selectable> extends DataGrid<T> implements ResourceAware{
	
	private FormDetailDataGridCss css;
	final SelectableSelectionModel selectionModel;
	static DataGrid.Resources resource = GWT.create(DataGridResource.class);
	
	/**
	 * Constructs a FormDetailDataGrid by the given params
	 * @param keyProvider the keyProvider
	 * @param values grid values
	 * @param detail the widget to append to the row
	 * @param object used as emtpy container
	 */
	public FormDetailDataGrid(ProvidesKey<T> keyProvider,List<T>values,RowDetailWidget<T> detail,T object) {
		this(resource,keyProvider,values,detail,object,false,false);
	}

	/**
	 * Constructs a FormDetailDataGrid by the given params
	 * @param keyProvider the keyProvider
	 * @param values grid values
	 * @param detail the widget to append to the row
	 * @param object used as emtpy container
	 * @param animationEnabled is the open row animation enabled
	 */
	public FormDetailDataGrid(ProvidesKey<T> keyProvider,List<T>values,RowDetailWidget<T> detail,T object,boolean animationEnabled ) {
		this(resource,keyProvider,values,detail,object,animationEnabled,false);
	}

	/**
	 * Constructs a FormDetailDataGrid by the given params
	 * @param keyProvider the keyProvider
	 * @param values grid values
	 * @param detail the widget to append to the row
	 * @param object used as emtpy container
	 * @param animationEnabled is the open row animation enabled
	 * @param doubleAnimationEnabled is the close row animation enabled
	 */
	public FormDetailDataGrid(ProvidesKey<T> keyProvider,List<T>values,RowDetailWidget<T> detail,T object,boolean animationEnabled,boolean doubleAnimationEnabled ) {
		this(resource,keyProvider,values,detail,object,animationEnabled,doubleAnimationEnabled);
	}

	/**
	 * Constructs a FormDetailDataGrid by the given params
	 * @param resource the resource to use
	 * @param keyProvider the keyProvider
	 * @param values grid values
	 * @param detail the widget to append to the row
	 * @param object used as emtpy container
	 * @param animationEnabled is the open row animation enabled
	 * @param doubleAnimationEnabled is the close row animation enabled
	 */
	public FormDetailDataGrid(Resources resource,
							  ProvidesKey<T> keyProvider,
							  List<T>values,RowDetailWidget<T> detail,
							  T object,
							  boolean animationEnabled,
							  boolean doubleAnimationEnabled ) {
		super(0,resource,keyProvider);
		css = CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().formDetailDataGrid();
		css.ensureInjected();
		
		addStyleName(css.formDetailDataGrid());
		selectionModel = new SelectableSelectionModel(keyProvider, values, FormDetailDataGrid.this,detail,object,animationEnabled,doubleAnimationEnabled);
		   final ImageSelectableCell t = new ImageSelectableCell(selectionModel);
				Column<T, ImageResource> imageCol = 
					new Column<T, ImageResource>(t ) {

						@Override
						public ImageResource getValue(T object) {
							t.setObject(object);
							if ( object == null) return resources.expand();
							return object.isSelected() ? resources.collapse() : resources.expand();
						}
					};
			    addColumn(imageCol, SafeHtmlUtils.fromSafeConstant("<br/>"));
			    imageCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			    setColumnWidth(imageCol, 40, Unit.PX);

	}

	
	public void remove(){
		selectionModel.resetSelection();
		 
	}
	
	static interface DataGridResource extends DataGrid.Resources {

		  @Source({ DataGrid.Style.DEFAULT_CSS, CommonWidgetsStyle.FORM_DETAIL_GRID_CSS })
		  DataGrid.Style dataGridStyle();
		};

		/**
		 * <h1>A simple selector cell</h1>
		 * 
		 * This class is used by the grid as the expand/collapse command.
		 * 
		 * @author Alessandro Casolla
		 *
		 */
	 class ImageSelectableCell extends ImageResourceCell {
		final SelectionModel<T> selectionModel;
		private T object;
		
		public final T getObject() {
			return object;
		}
		
		public final void setObject(T obj) {
			this.object = obj;
		}
		
		public ImageSelectableCell(SelectionModel<T> selectionModel) {
			this.selectionModel = selectionModel;
		}
		
		public void selectObject(T obj) {
			selectionModel.setSelected(obj, true);
		}
		
		@Override
		public Set<String> getConsumedEvents() {
			Set<String> consumedEvents = new HashSet<String>();
			consumedEvents.add("dblclick");
			consumedEvents.add("click");
			return consumedEvents;
		}
		
		@Override
		public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,
				Element parent, ImageResource value, NativeEvent event,
				ValueUpdater<ImageResource> valueUpdater) {
			switch (DOM.eventGetType((Event) event)) {
			case Event.ONCLICK:
				selectionModel.setSelected(object, true);
				break;
			default:
				break;
			}
		
		}
		
	}

	 public interface RowDetailWidget<T> extends IsWidget{
		 
		 public void setRowItem ( T item );
	 }
	 /**
	  * <h1>A selection model class</h1>
	  * 
	  * This class extends {@link AbstractSelectionModel}, and is used by the grid, 
	  * to show the given widget.
	  * 
	  * @author Alessandro Casolla
	  *
	  */
	 class SelectableSelectionModel extends AbstractSelectionModel<T> {

		private Object curKey;
		private T curSelection;
		private T object;
		private RowDetailWidget<T> detail;
		private final List<T> values;
		private final DataGrid<T> dataGrid;
	
		// Pending selection change
		private boolean newSelected;
		private T newSelectedObject = null;
		private boolean newSelectedPending;
		private int index = 0;
		private final String ID_ELEMENT;
		private AnimatedContent container = new AnimatedContent();
		private TableRowElement tre;
		
		private boolean doubleAnimationEnabled = false;
		
		public List<T> getValues(){
			return values;
		}
	
		public AnimatedContent getContainer(){
			return container;
		}
		public SelectableSelectionModel(ProvidesKey<T> keyProvider, List<T> values,
				DataGrid<T> dataGrid, RowDetailWidget<T> detail,T object,boolean animationEnabled,boolean doubleAnimationEnabled) {
			super(keyProvider);
			this.values = values;
			this.dataGrid = dataGrid;
			this.object = object; 	
			ID_ELEMENT = object.toString();
			this.detail = detail;
			this.doubleAnimationEnabled = doubleAnimationEnabled;
			container.setAnimationEnabled(animationEnabled);
			addSelectionChangeHandler(handler);
		}

		
		private SelectionChangeEvent.Handler handler = new SelectionChangeEvent.Handler() {
	
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
				 tre = dataGrid.getRowElement(index);
				 int time = AnimatedContent.getAnimationDuration();
				 
				 if ( container.isOpen() && getSelectedObject() != null && !doubleAnimationEnabled){
					 time = 0;
				 }
				if (container.isOpen()){
					container.setOpen(false);
					Timer t = new Timer() {
						
						@Override
						public void run() {
							values.remove(index + 1);
						}
					};
					t.schedule(time);
					
				}
				if (getSelectedObject() == null) {
					Timer t = new Timer() {
						
						@Override
						public void run() {
							dataGrid.setRowCount(values.size(), true);
							dataGrid.setRowData(values);
									
						}
					};
					t.schedule(300);
					return;
				}
				Timer t = new Timer() {
					
					@Override
					public void run() {
						index = values.indexOf(getSelectedObject());
						
						detail.setRowItem(getSelectedObject());
						values.add(index + 1, object);
						dataGrid.setRowCount(values.size(), true);
						dataGrid.setRowData(values);
			
						tre = dataGrid.getRowElement(index + 1);
						tre.setId(ID_ELEMENT);
						
						container.setPixelSize(dataGrid.getOffsetWidth(), 0);
						tre.setInnerHTML("");
			
						try {
							RootPanel.get(ID_ELEMENT);
						} catch (AssertionError ae) {
			
						}
						RootPanel.get(ID_ELEMENT).add(container);
						container.setContent(detail);
						container.setOpen(true);
					}
				};
				t.schedule(time);
			}
	
		};
	
	
		/**
		 * Gets the currently-selected object.
		 * 
		 * @return the selected object
		 */
		public T getSelectedObject() {
			resolveChanges();
			return curSelection;
		}
		
		public void resetSelection(){
			if ( object != null && curSelection != null ){
				 getValues().remove(object);
				 setSelected(object, false);
				 fireSelectionChangeEvent();
				 container.setOpen(false);
				 newSelectedPending = true;
				 resolveChanges();
			}
					 
		
		}
	
		public boolean isSelected(T object) {
			resolveChanges();
			if (curSelection == null || curKey == null || object == null) {
	
				return false;
			}
			return curKey.equals(getKey(object));
		}
	
		public void setSelected(T object, boolean selected) {
			if (object.isSelected())
				selected = false;
			if (curSelection != null)
				curSelection.setSelected(false);
	
			if (!selected) {
				Object oldKey = newSelectedPending ? getKey(newSelectedObject)
						: curKey;
				Object newKey = getKey(object);
				if (!equalsOrBothNull(oldKey, newKey)) {
					return;
				}
			}
			newSelectedObject = object;
			newSelectedObject.setSelected(true);
			newSelected = selected;
			newSelectedPending = true;
			scheduleSelectionChangeEvent();
		}
	
		@Override
		protected void fireSelectionChangeEvent() {
			if (isEventScheduled()) {
				setEventCancelled(true);
			}
			resolveChanges();
		}
	
		private boolean equalsOrBothNull(Object a, Object b) {
			return (a == null) ? (b == null) : a.equals(b);
		}
	
		private void resolveChanges() {
			if (!newSelectedPending) {
				return;
			}
	
			Object key = getKey(newSelectedObject);
			boolean sameKey = equalsOrBothNull(curKey, key);
			boolean changed = false;
			if (newSelected) {
				changed = !sameKey;
				curSelection = newSelectedObject;
				curKey = key;
			} else if (sameKey) {
				changed = true;
				curSelection.setSelected(false);
				curSelection = null;
				curKey = null;
			}
	
			newSelectedObject = null;
			newSelectedPending = false;
	
			// Fire a selection change event.
			if (changed) {
				SelectionChangeEvent.fire(this);
			}
		}
	 }
}

