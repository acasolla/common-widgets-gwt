package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.HTMLLink;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for html link
 * 
 * for an explanation on styling see {@link HTMLLink}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface HTMLLinkCss extends CssResource {

  @ClassName("htmlLink")
  String htmlLink();
  
}

