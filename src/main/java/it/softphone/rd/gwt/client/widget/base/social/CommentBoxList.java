package it.softphone.rd.gwt.client.widget.base.social;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.CommentBoxCss;
import it.softphone.rd.model.shared.Feed;
import it.softphone.rd.model.shared.FeedComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1> A List of {@link CommentBox}</h1>
 * 
 * This class consists of a container holding a list of {@link CommentBox}.
 * When a list of {@link Feed} is provided, the class will provide to create a list of {@link CommentBox},
 * adding it to the class.
 * 
 * 
 * 
 * @author Alessandro Casolla
 *
 * @param <T> the type of the class
 */
public class CommentBoxList<T extends Feed> implements IsWidget,HasVisibility{

	private FlowPanel container = new FlowPanel();
	
	public List<T> feeds = new ArrayList<T>();

	/**
	 * Constructs an empty CommentBoxList
	 */
	public CommentBoxList(){
		this(null);
		
	}
	
	/**
	 * Constructs a CommentBoxList by the given params
	 * @param feeds the list of feeds
	 */
	public CommentBoxList( List<T> feeds ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().commentBox(),feeds);		
	}

	/**
	/**
	 * Constructs a CommentBoxList by the given params
	 * @param feeds the list of feeds
	 * @param css the css to use
	 */
	public CommentBoxList( CommentBoxCss css,List<T> feeds ){
		css.ensureInjected();
		setFeeds(feeds);
		container.setStylePrimaryName(css.commentBoxListExternal());
		init();
		
	}

	
	private void init(){
		if( feeds == null ) return;
		container.clear();
		Collections.sort(feeds, new FeedComparator());
		for ( T comment : feeds ){
			CommentBox<T> box = new CommentBox<T>(comment,200);
			container.add(box);
		}
	}
	
	/**
	 *Creates a {@link CommentBox} by the given feed, and adds it to the list
	 * @param feed the feed to add
	 */
	public final void addFeed( T feed ){
		if ( feeds == null )
			feeds = new ArrayList<T>();
		CommentBox<T> box = new CommentBox<T>(feed,200);

		container.insert(box, 0);
	}
	
	/**
	 * Returns the feeds holded by the class
	 * @return a list of feeds
	 */
	public final List<T> getFeeds() {
		return feeds;
	}

	/**
	 * Sets the feeds, and initialize the class
	 * @param feeds the feeds to render
	 */
	public final void setFeeds(List<T> feeds) {
		this.feeds = feeds;
		init();
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public boolean isVisible() {
		return container.isVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		container.setVisible(visible);
		
	}

		
}
