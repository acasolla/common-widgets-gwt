package it.softphone.rd.model.shared;

import it.softphone.rd.gwt.client.widget.base.social.CommentBox;
import it.softphone.rd.gwt.client.widget.base.social.CommentBoxList;
import it.softphone.rd.gwt.client.widget.base.social.FeedBox;
import it.softphone.rd.gwt.client.widget.base.social.SocialBox;

/**
 * This interface represent a social feed, and cosumes the following json string:
 * 
 * <pre>
 * { 
 * 	'id':1,
 * 	'issueId':1,
 * 	'requestorId':'userId',
 * 	'date':1341928641045,
 * 	'action':'COMMENT',
 * 	'text':'my message'
 * }
 * </pre>
 * 
 * An interface used by the following classes:
 * 
 * <ul>
 * <li>{@link SocialBox}</li>
 * <li>{@link CommentBox}</li>
 * <li>{@link CommentBoxList}</li>
 * <li>{@link FeedBox}</li>
 * </ul>
 * 
 * @author Alessandro Casolla
 *
 */
public interface Feed {

	Long getId();
	void setId( Long id );

	Long getIssueId();
	void setIssueId( Long issueId );
	
	String getRequestorId();
	void setRequestorId( String requestorId );
	
	FeedAction getAction();
	void setAction( FeedAction action );
	
	String getText();
	void setText( String text );
	
	String getUser();
	void setUser( String user );
	
	java.util.Date getDate();
	void setDate( java.util.Date date );
	
	String getIcon();
	void setIcon( String icon );
	
	
	public interface ResultSet{
		java.util.List<Feed> getFeeds();
	}
	
}
