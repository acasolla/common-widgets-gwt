package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.RatingBoxCss;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>A box rating class</h1>
 * <br></br>
 * 
 * This class builds a box with the following elements :
 * 
 *  <ul>
 *  <li>A title</li>
 *  <li>5 stars fulled accordingly with the rating param.</li>
 *  <li>A content</li>
 *  </ul>
 * <br></br>
 * 
 * If the rating is decimal, and is greater then 0.5, a star will be fulled by half
 * <br></br>
 * @author Alessandro Casolla
 *
 */
public class RatingBox implements IsWidget,ResourceAware{

	
	private FlowPanel container = new FlowPanel();
	private static final double STARS  = 5D;

	/**
	 * Construct a rating box with default values.
	 * <pre>
	 * title empty
	 * rating 0
	 * </pre>
	 */
	public RatingBox(){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().ratingBox(),"",0);
	}
	/**
	 * Construct a rating box by the given params
	 * @param title the title
	 * @param rating the rating
	 */
	public RatingBox( String title, double rating ){		
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().ratingBox(),title,rating);
	}
	
	/**
	 * Construct a rating box by the given params
	 * @param css the css to use
	 * @param title the title
	 * @param rating the rating
	 */
	public RatingBox( RatingBoxCss css,String title, double rating ){
		css.ensureInjected();
		if ( rating > STARS )
			rating = STARS;
		container.setStylePrimaryName(css.ratingContainer());
		
		InlineHTML t = new InlineHTML(title);
		container.add(t);
		
		FlowPanel starsContainer = new FlowPanel();
		starsContainer.setStylePrimaryName(css.starContainer());
		
		int ipart = (int)rating;
		double fpart = rating - ipart;
		
		int full = (int) rating;
		int half = fpart > 0.5 ? 1 : 0;
		int empty =(int) (STARS - full - half);
		
		for ( int i = 0; i< full ; i++ ){
			Image starFull = new Image();
			starFull.setUrl(resources.starFull().getSafeUri());
			starFull.setWidth("22px");
			
			starsContainer.add(starFull);
		}
		
		if ( half > 0 ){
			Image starHalf = new Image();
			starHalf.setUrl(resources.starHalf().getSafeUri());
			starHalf.setWidth("22px");
			starsContainer.add(starHalf);
		
		} else {
			//empty++;
		}
		for ( int i = 0; i< empty; i++ ){
			Image starEmpty = new Image();
			starEmpty.setUrl(resources.starEmpty().getSafeUri());
			starEmpty.setWidth("22px");
			starsContainer.add(starEmpty);
		}
		container.add(starsContainer);
		
		FlowPanel contentContainer = new FlowPanel();
		contentContainer.setStylePrimaryName(css.contentContainer());
		
		contentContainer.add(new InlineHTML("TBD"));
		
		container.add(contentContainer);
		
		
	}

	@Override
	public Widget asWidget() {
		return container;
	}
}
