package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.RichTextAreaHint;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for rich text area hint
 * 
 * for an explanation on styling see {@link RichTextAreaHint}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface RichTextAreaHintCss extends CssResource {

  @ClassName("textInput")
  String textInput();

  @ClassName("textInput-hint")
  String textInputHint();

  
}

