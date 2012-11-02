package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.tag.TagBox;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for tag box
 * 
 * for an explanation on styling see {@link TagBox}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface TagBoxCss extends CssResource {
  
  String tagContainer();
  
  String tagExternalContainer();
}

