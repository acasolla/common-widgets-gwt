package it.softphone.rd.gwt.client.widget.base.social;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.SocialBoxCss;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>Base container for social elements</h1>
 * 
 * This class is used as base container for all the social widgets, and consists of an image, and a content container.
 * 
 * These are the known extending classes:
 * <ul>
 * <li>{@link CommentBox}</li>
 * <li>{@link CommentBoxList}</li>
 * <li>{@link FeedBox}</li>
 * <li>{@link ReplyBox}</li>
 * 
 * </ul>
 * @author Alessandro Casolla
 *
 */
public abstract class SocialBox implements IsWidget,HasVisibility, ResourceAware {
	private final FlowPanel mainContainer = new FlowPanel();
	private final FlowPanel contentContainer = new FlowPanel();
	private final HTML hImage = new HTML();
	
	private ImageResource image;
	@Override
	public boolean isVisible() {
		return mainContainer.isVisible();
	}


	@Override
	public void setVisible(boolean visible) {
		mainContainer.setVisible(visible);
		
	}


	private final static String imageTemplate = 
				"<div class='{0}'>" +
						"<img src=\"{2}\" class=\"{1}\"></img>" +
					"</div>" 		
					;

	public interface Template extends SafeHtmlTemplates {

		@SafeHtmlTemplates.Template(imageTemplate)
		SafeHtml imageTemplate(String imgContainerStyle, String imgStyle,SafeUri src);

	}

	protected static Template TEMPLATE = GWT.create(Template.class);
	
	/**
	 * Constructs a SocialBox
	 */
	public SocialBox(){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().socialBox());
	}

	/**
	 * Constructs a SocialBox by the given params
	 * @param css the css to use
	 */
	public SocialBox( SocialBoxCss css ){
		css.ensureInjected();
		mainContainer.setStylePrimaryName(css.socialBoxContainer());
		mainContainer.add(hImage);
		mainContainer.add(contentContainer);
	//	contentContainer.setStylePrimaryName(css.socialContentBox());
	}
	
	
	
	
	@Override
	public Widget asWidget() {
		return mainContainer;
	}
	
	/**
	 * Retuns the ImageResource 
	 * @return an ImageResource
	 */
	public final ImageResource getImage() {
		return image;
	}
	
	protected void setImage( String imgContainerStyle, SafeUri imgUri ){
		SafeHtml html  = new SafeHtmlBuilder()
		.append(TEMPLATE.imageTemplate( imgContainerStyle , "",imgUri ))
		.toSafeHtml();
		
		hImage.setHTML(html);
	}

	protected void setImage( String imgContainerStyle, String imgStyle,SafeUri imgUri ){
		SafeHtml html  = new SafeHtmlBuilder()
		.append(TEMPLATE.imageTemplate( imgContainerStyle , imgStyle,imgUri ))
		.toSafeHtml();
		
		hImage.setHTML(html);
	}

	protected void setContent( FlowPanel content ){
		contentContainer.add(content);
	}
	
	
	
}
