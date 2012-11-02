package it.softphone.rd.gwt.client.widget.base.social;

import it.softphone.rd.gwt.client.ClientUtils;
import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.FeedBoxCss;
import it.softphone.rd.model.shared.Feed;

import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
/**
 * <h1>A box holding a {@link Feed}</h1>
 * 
 * This class extends {@link SocialBox} and consists of a box, composed by:
 * <ul>
 * <li>An image</li>
 * <li>The user name</li>
 * <li>The FeedAction action</li>
 * <li>The feed text, truncated at 40 characters</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 */
public class FeedBox extends SocialBox {
	private final FlowPanel container = new FlowPanel();
	/**
	 * Constructs a feedbox by the given params
	 * @param feed the feed to render
	 */
	public FeedBox( Feed feed ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().feedBox(),feed);

	}
	/**
	 * Constructs a feedbox by the given params
	 * @param css the css to use
	 * @param feed the feed to render
	 */
	
	public FeedBox( FeedBoxCss css, final Feed feed){
		super(css);
		css.ensureInjected();
		container.setStylePrimaryName(css.feedBoxContainer());
		container.setTitle(feed.getText());
		
		String text = ClientUtils.truncate(40, feed.getText());
		
		StringBuilder sb = new StringBuilder();
		sb.append("<b>").append(feed.getUser()).append("</b>").append("&nbsp;")
		.append(ClientUtils.format(feed.getAction().getAction(), feed.getIssueId()) ).append(" : \" ").append(text).append(" \" ");
		
		HTML html = new HTML();
		html.setHTML(sb.toString());
		container.add(html);
		setContent(container);
		
		
		
		if ( feed.getIcon() != null ){
			SafeUri icon = new SafeUri() {
				
				@Override
				public String asString() {
					return feed.getIcon();
				}
			};
			setImage(css.feedBoxImage(),icon);
		} else {
		
			String img = feed.getUser();
			
			if ( img.equals("agent"))
				setImage(css.feedBoxImage(),resources.callCenterUser().getSafeUri());
			else
				setImage(css.feedBoxImage(),resources.customer().getSafeUri());
		}
	}
	
}
