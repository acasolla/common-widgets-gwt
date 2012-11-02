package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.tag.TagElement;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for tag element
 * 
 * for an explanation on styling see {@link TagElement}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface TagElementCss extends CssResource {
  
  String tagElementContainer();

  @ClassName("tagElementContainer-Suggested")
  String tagElementContainerSuggested();
  

}

