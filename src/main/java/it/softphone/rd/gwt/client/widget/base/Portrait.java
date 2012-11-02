package it.softphone.rd.gwt.client.widget.base;

import it.softphone.rd.gwt.client.CommonWidgetsStyle;
import it.softphone.rd.gwt.client.resources.ResourceAware;
import it.softphone.rd.gwt.client.resources.base.PortraitCss;
import it.softphone.rd.gwt.client.widget.MouseAwareFlowPanel;
import it.softphone.rd.model.shared.BaseUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>A container class for Image </h1>
 *	<br></br>
 *
 * This class will build a portrait from a {@link BaseUser} object.
 * <br></br>
 * 
 *
 * @author Alessandro Casolla
 *
 * 
 */
public class Portrait<T extends BaseUser> implements IsWidget,ResourceAware{

	private T user;
	private final static String imageTemplate = 
			"<div class='{0}'>" +
					"<img src=\"{1}\"></img>" +
				"</div>" 		
				;

	public interface Template extends SafeHtmlTemplates {
	
		@SafeHtmlTemplates.Template(imageTemplate)
		SafeHtml imageTemplate(String imgContainerStyle, SafeUri src);
	
	}

	protected static Template TEMPLATE = GWT.create(Template.class);

	private MouseAwareFlowPanel container = new MouseAwareFlowPanel();
	
	private final HTML hImage = new HTML();

	/**
	 * Construct a portrait by the given params
	 * @param user the user object
	 */
	public Portrait( T user ){
		this(CommonWidgetsStyle.getTheme().getCommonsWidgetClientBundle().portrait(),
				user);
	}
	
	/**
	 * Construct a portrait by the given params
	 * @param css the css to use
	 * @param user the user object
	 */
	
	public Portrait( PortraitCss css, T user ){
		this.user = user;
		css.ensureInjected();
		if ( user != null && user.getImageUri() != null)
			setImage(user.getImageUri());
		else
			setImage(resources.person().getSafeUri());
		container.add(hImage);
		container.add(new HTML(user.getName()));
		container.add(new HTML(user.getSurname()));
		container.setStylePrimaryName(css.portraitContainer());
	}
	@Override
	public Widget asWidget() {
		return container;
	}
	
	private void setImage( SafeUri imgUri ){
		SafeHtml html  = new SafeHtmlBuilder()
		.append(TEMPLATE.imageTemplate( "" , imgUri ))
		.toSafeHtml();
		
		hImage.setHTML(html);
	}

	/**
	 * Returns the user object 
	 * @return the type
	 */
	public T getUser(){
		return user;
	}
	
	/**
	 * Adds a click handler to the portrait container
	 * @param handler
	 */
	public void addClickHandler( ClickHandler handler ){
		container.addClickHandler(handler);
	}
}
