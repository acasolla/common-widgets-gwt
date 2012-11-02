package it.softphone.rd.gwt.client.resources;

import it.softphone.rd.gwt.client.resources.base.CommentBoxCss;
import it.softphone.rd.gwt.client.resources.base.FeedBoxCss;
import it.softphone.rd.gwt.client.resources.base.FilterCalendarCss;
import it.softphone.rd.gwt.client.resources.base.FilterPopupPanelCss;
import it.softphone.rd.gwt.client.resources.base.FilterTextBoxCss;
import it.softphone.rd.gwt.client.resources.base.FormDetailDataGridCss;
import it.softphone.rd.gwt.client.resources.base.HTMLLinkCss;
import it.softphone.rd.gwt.client.resources.base.HintTextBoxCss;
import it.softphone.rd.gwt.client.resources.base.MaskCss;
import it.softphone.rd.gwt.client.resources.base.NamedColumnCss;
import it.softphone.rd.gwt.client.resources.base.PortraitCss;
import it.softphone.rd.gwt.client.resources.base.RatingBoxCss;
import it.softphone.rd.gwt.client.resources.base.ReplyBoxCss;
import it.softphone.rd.gwt.client.resources.base.RichTextAreaHintCss;
import it.softphone.rd.gwt.client.resources.base.RichTextToolbarCss;
import it.softphone.rd.gwt.client.resources.base.SearchBoxCss;
import it.softphone.rd.gwt.client.resources.base.SocialBoxCss;
import it.softphone.rd.gwt.client.resources.base.SortFilterButtonCellCss;
import it.softphone.rd.gwt.client.resources.base.TabCommandLinkCss;
import it.softphone.rd.gwt.client.resources.base.TabHeaderCloseButtonCss;
import it.softphone.rd.gwt.client.resources.base.TagBoxCss;
import it.softphone.rd.gwt.client.resources.base.TagElementCss;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.user.cellview.client.DataGrid;


public interface CommonWidgetsCssResource extends ClientBundle {
	
	@Source("base/css/tabHeaderCloseButton.css")
	public TabHeaderCloseButtonCss tabHeaderCloseButtonCss();

	@Source("base/css/tagBox.css")
	public TagBoxCss tagBox();

	@Source("base/css/tagElement.css")
	public TagElementCss tagElement();

	@Source("base/css/htmlLink.css")
	public HTMLLinkCss htmlLink();

	@Source("base/css/commentBox.css")
	public CommentBoxCss commentBox();

	@Source("base/css/hintTextBox.css")
	public HintTextBoxCss hintTextBoxCss();

	@Source("base/css/replyBox.css")
	public ReplyBoxCss replyBox();

	@Source("base/css/socialBox.css")
	public SocialBoxCss socialBox();

	@Source( {"base/css/socialBox.css",
	  		  "base/css/feedBox.css"})
	public FeedBoxCss feedBox();

	@Source( {"base/css/hintTextBox.css",
			  "base/css/filterTextBox.css"})
	public FilterTextBoxCss filterTextBox();

	@Source("base/css/filterCalendar.css")
	public FilterCalendarCss filterCalendar();

	@Source( {DataGrid.Style.DEFAULT_CSS,
	 "base/css/formDetailDataGrid.css"})
	public FormDetailDataGridCss formDetailDataGrid();

	@Source("base/css/filterDialog.css")
	public FilterPopupPanelCss filterDialog();

	@Source("base/css/tabCommandLink.css")
	public TabCommandLinkCss tabCommandLink();

	@Source("base/css/richTextToolbar.css")
	public RichTextToolbarCss richTextToolbar();

	@Source("base/css/richTextAreaHint.css")
	public RichTextAreaHintCss richTextAreaHint();

	@Source("base/css/mask.css")
	public MaskCss mask();

	@Source("base/css/sortFilterButtonCell.css")
	public SortFilterButtonCellCss sortFilterButtonCell();

	@Source("base/css/portrait.css")
	public PortraitCss portrait();

	@Source("base/css/searchBox.css")
	public SearchBoxCss searchBox();

	@Source("base/css/ratingBox.css")
	public RatingBoxCss ratingBox();
	
	@Source("base/css/namedColumn.css")
	public NamedColumnCss namedColumn();
	

	@Source("img/search20x20.png")
	DataResource search();


}
