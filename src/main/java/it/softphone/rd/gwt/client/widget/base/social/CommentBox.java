package it.softphone.rd.gwt.client.widget.base.social;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.base.CommentBoxCss;
import it.softphone.rd.gwt.client.widget.base.HTMLLink;
import it.softphone.rd.model.shared.Feed;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.ScrollPanel;
/**
 * <h1>A box containing a {@link Feed}</h1>
 * 
 * This class contains a {@link Feed} and consists in:
 * 
 * <ul>
 * <li>The text of the comment</li>
 * <li>The username</li>
 * <li>The insert date</li>
 * </ul>
 * 
 * If a comment length is provided, the comment will be truncated and a link will be added to the box.
 * Clicking the link the entire comment will be showed.
 * If no lenght is provided, will be used the default value of 200

 * @author Alessandro Casolla
 *
 * @param <T> the type of the class
 */
public class CommentBox<T extends Feed> extends SocialBox {
	private final static Logger logger = Logger.getLogger("");
	private final FlowPanel mainContainer = new FlowPanel();
	private final ScrollPanel scroll = new ScrollPanel();
	private final FlowPanel scrollContainer = new FlowPanel();
	private final HTMLLink more = new HTMLLink("More");
	private String displayedText = "";
	private String user;
	private Date date;
	/**
	 * The default comment length
	 */
	private static final int DEFAULT_MAX_TEXT_LENGTH = 200;
	private final int textLength;
	private String textExtended;
	private final DateTimeFormat dtf = DateTimeFormat.getFormat("MMMM yyyy HH:mm");
	private final FlowPanel infoContainer = new FlowPanel(); 
	private  InlineHTML hComment = new InlineHTML("");
	private final HTML info = new HTML();
	private CommentBoxCss css;
	private boolean isExpanded = false;
	
	private ClickHandler moreHandler =  new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			if ( isExpanded ){
				hComment.setHTML(displayedText);
				more.setText("More");
				isExpanded = false;
			} else {
				hComment.setHTML(textExtended);
				more.setText("Back");
				isExpanded = true;
			}
			
			
		}
	};
	
	private final static String infoTemplate = 
				"<div class='{0}'>" +
					"<div>Entered by <b>{1}</b> on <b>{2}</b></div>" +
				"</div>";


	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(infoTemplate)
		SafeHtml info(String styleContainer, SafeHtml user, SafeHtml date);


	}

	private static Template TEMPLATE_COMMENT = GWT.create(Template.class);

	/**
	 * Constructs an empty CommentBox
	 */
	public CommentBox( ){
		this("","",new Date(),DEFAULT_MAX_TEXT_LENGTH);
	}

	/**
	 * Constructs a CommentBox by the fiven params
	 * @param comment the text of the comment
	 * @param user the user name
	 * @param date the insert date
	 * @param commentLength the maximum commentlength
	 */
	public CommentBox( String comment, String user, Date date,int commentLength ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().commentBox(),comment,user,date,null,true,commentLength);
	}
	/**
	 * Constructs a CommentBox by the fiven params
	 * @param feed the feed
	 * @param isReply the comment is a reply
	 */

	public CommentBox( T feed,boolean isReply ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().commentBox(),
				feed.getText(),
				feed.getUser(),
				feed.getDate(),
				feed.getIcon(),
				isReply,
				DEFAULT_MAX_TEXT_LENGTH);
	}

	/**
	 * Constructs a CommentBox by the fiven params
	 * @param feed the feed
	 * @param commentLength the maximum commentlength
	 */

	public CommentBox( T feed,int commentLength ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().commentBox(),
				feed.getText(),
				feed.getUser(),
				feed.getDate(),
				feed.getIcon(),
				true,
				commentLength);
	}

	/**
	 * Constructs a CommentBox by the fiven params
	 * @param comment the text of the comment
	 * @param user the user name
	 * @param date the insert date
	 * @param commentLength the maximum commentlength
	 * @param css the css to use
	 * @param isUser if true, the customer agent image will be used
	 */
	public CommentBox( CommentBoxCss css,String comment, String user, Date date,final String icon,boolean isUser,int commentLength ){
		this.textLength = commentLength;
		this.css = css;
		css.ensureInjected();
		setText(comment);
		setUser(user);
		setDate(date);
		mainContainer.setStylePrimaryName(css.commentBoxListItem());
		scroll.setStylePrimaryName(css.commentBoxContainer());
		infoContainer.setStylePrimaryName(css.commentBoxInfoContainer());
		SafeUri uri = null;
		if ( icon != null ){
			uri = new SafeUri() {
				
				@Override
				public String asString() {
					return icon;
				}
			};
		}
		if ( isUser ){
			if ( uri != null )
				setImage(css.commentBoxImageUser(), uri);
			else
				setImage(css.commentBoxImageUser(), resources.callCenterUser().getSafeUri());
			
		} else {
			if ( uri != null )
				setImage(css.commentBoxImageCustomer(), uri);
			else
				setImage(css.commentBoxImageCustomer(), resources.customer().getSafeUri());
					
		}
		
		init();
		checkCommentLength();
	}

	private void init(){
		mainContainer.add(scroll);
		scroll.setWidget(scrollContainer);
		scrollContainer.add(hComment);
	
		more.addClickHandler(moreHandler);
		scrollContainer.add(infoContainer);
		info.setHTML(new SafeHtmlBuilder().append(
				TEMPLATE_COMMENT.info(css.commentBoxInfoContainer(), 
							  SafeHtmlUtils.fromString(getUser()), 
							  SafeHtmlUtils.fromString(getStringDate())))
							  .toSafeHtml());

		infoContainer.add(info);
		setContent(mainContainer);
		
	}
	
	/**
	 * Returns the username
	 * @return a string
	 */
	public String getUser(){
		if ( user == null || user.isEmpty() ) return "ok";
		return user;
	}
	
	/**
	 * Returns the inser comment date
	 * @return a date
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * Returns the insert comment date formatted
	 * @return a string
	 */
	public String getStringDate(){
		if ( date == null ) return "";
		return dtf.format(getDate());
	}
	
	/**
	 * Sets the comment
	 * @param feed the feed to set
	 */
	public void setFeed( T feed ){
		setText(feed.getText());
		setUser(feed.getUser());
		setDate(feed.getDate());
		
	}
	
	private void checkCommentLength(){
		if (displayedText == null ) return;
		if ( textExtended == null ){
			textExtended = new String(displayedText);
		}
		boolean isTruncated = displayedText.length() > textLength; 
		displayedText =  isTruncated ? displayedText.substring(0, textLength - 3 ).concat("...") : displayedText;
		hComment.setHTML(displayedText);
		if ( isTruncated ){
			infoContainer.add(more);
		}
		
	}
	
	private void setText( String text ){
		this.displayedText = text;
		textExtended = null;
		checkCommentLength();
		
	}
	
	private void setDate( Date date ){
		this.date = date;
		renderInfoContainer();
	}

	private void setUser( String user ){
		this.user = user;
		renderInfoContainer();
	}
	
	private void renderInfoContainer(){
		logger.info("user=" + getUser() + " date=" + getStringDate());
		info.setHTML(new SafeHtmlBuilder().append(
				TEMPLATE_COMMENT.info(css.commentBoxInfoContainer(), 
							  SafeHtmlUtils.fromString(getUser()), 
							  SafeHtmlUtils.fromString(getStringDate())))
							  .toSafeHtml());

	}

	
}
