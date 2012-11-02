package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.RatingBox;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for rating box
 * 
 * for an explanation on styling see {@link RatingBox}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface RatingBoxCss extends CssResource {
  
  String ratingContainer();
  
  String starContainer();
  
  String contentContainer();
  
  
}

