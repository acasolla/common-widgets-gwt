package it.softphone.rd.gwt.client.resources.base;
import it.softphone.rd.gwt.client.widget.base.HintTextBox;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Shared;
/**
 * The css interface for hint text box
 * 
 * for an explanation on styling see {@link HintTextBox}
 * 
 * @author Alessandro Casolla
 * 
 */

@Shared
public interface HintTextBoxCss extends CssResource {

  @ClassName("textInput")
  String textInput();

  @ClassName("textInput-hint")
  String textInputHint();

}

