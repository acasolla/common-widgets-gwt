package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.social.FeedBox;

import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for feed box
 * 
 * for an explanation on styling see {@link FeedBox}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface FeedBoxCss extends SocialBoxCss {
 
	String feedBoxContainer();
	String feedBoxImageContainer();
	String feedBoxImage();
}

