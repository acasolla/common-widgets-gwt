package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.social.CommentBox;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for comment box
 * 
 * for an explanation on styling see {@link CommentBox}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface CommentBoxCss extends CssResource {
  
  String commentBoxContainer();
  
  String commentBoxListItem();
  
  String commentBoxListExternal();
  
  String commentBoxInfoContainer();
  
  
  String commentBoxImageUser();
  
  String commentBoxImageCustomer();
}

