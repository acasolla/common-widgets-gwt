package it.softphone.rd.gwt.client.widget;
/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import it.softphone.rd.gwt.client.resources.ResourceAware;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A widget that consists of  a content panel that discloses the
 * content.
 * 
 * <h3>CSS Style Rules</h3> 
 * <dl class="css"> 
 * <dt>.gwt-DisclosurePanel 
 * <dd>the panel's primary style 
 * <dt>.gwt-DisclosurePanel-open 
 * <dd> dependent style set when panel is open 
 * <dt>.gwt-DisclosurePanel-closed 
 * <dd> dependent style set when panel is closed
 * </dl>
 * 
 * @author Alessandro Casolla
 */

public final class AnimatedContent extends Composite implements HasAnimation,
    HasOpenHandlers<AnimatedContent>, HasCloseHandlers<AnimatedContent>,ResourceAware {
 
  /**
   * An {@link Animation} used to open the content.
   */
  private static class ContentAnimation extends Animation {
    /**
     * Whether the item is being opened or closed.
     */
    private boolean opening;

    /**
     * The {@link AnimatedContent} being affected.
     */
    private AnimatedContent curPanel;

    /**
     * Open or close the content.
     * 
     * @param panel the panel to open or close
     * @param animate true to animate, false to open instantly
     */
    public void setOpen(AnimatedContent panel, boolean animate) {
      // Immediately complete previous open
      cancel();
      // Open the new item
      if (animate) {
        curPanel = panel;
        opening = panel.isOpen;
        run(ANIMATION_DURATION);
      } else {
        panel.contentWrapper.setVisible(panel.isOpen);
        if (panel.isOpen) {
          // Special treatment on the visible case to ensure LazyPanel works
          panel.getContent().setVisible(true);
        }
      }
    }

    @Override
    protected void onComplete() {
      if (!opening) {
        curPanel.contentWrapper.setVisible(false);
      }
      DOM.setStyleAttribute(curPanel.contentWrapper.getElement(), "height",
          "auto");
      curPanel = null;
    }

    @Override
    protected void onStart() {
      super.onStart();
      if (opening) {
        curPanel.contentWrapper.setVisible(true);
        // Special treatment on the visible case to ensure LazyPanel works
        curPanel.getContent().setVisible(true);
      }
    }

    @Override
    protected void onUpdate(double progress) {
      int scrollHeight = DOM.getElementPropertyInt(
          curPanel.contentWrapper.getElement(), "scrollHeight");
      int height = (int) (progress * scrollHeight);
      if (!opening) {
        height = scrollHeight - height;
      }
      height = Math.max(height, 1);
      DOM.setStyleAttribute(curPanel.contentWrapper.getElement(), "height",
          height + "px");
      DOM.setStyleAttribute(curPanel.contentWrapper.getElement(), "width",
          "auto");
    }
  }

 
  /**
   * The duration of the animation.
   */
  private static  int ANIMATION_DURATION = 250;

  private static final String STYLENAME_DEFAULT = "gwt-AnimatedContent";

  private static final String STYLENAME_SUFFIX_OPEN = "open";

  private static final String STYLENAME_SUFFIX_CLOSED = "closed";

  private static final String STYLENAME_CONTENT = "content";

  /**
   * The {@link Animation} used to open and close the content.
   */
  private static ContentAnimation contentAnimation;

  /**
   * top level widget. The first child will be a reference to {@link #header}.
   * The second child will be a reference to {@link #contentWrapper}.
   */
  private final VerticalPanel mainPanel = new VerticalPanel();

  /**
   * The wrapper around the content widget.
   */
  private final SimplePanel contentWrapper = new SimplePanel();

  /**
   * holds the header widget.
   */
 
  private boolean isAnimationEnabled = true;

  private boolean isOpen = false;

  /**
   * Creates an empty AnimatedContent that is initially closed.
   */
  public AnimatedContent() {
    initWidget(mainPanel);
    mainPanel.add(contentWrapper);
    DOM.setStyleAttribute(contentWrapper.getElement(), "padding", "0px");
    DOM.setStyleAttribute(contentWrapper.getElement(), "overflow", "hidden");
    setStyleName(STYLENAME_DEFAULT);
    setContentDisplay(false);
  }
 
  
  public static int getAnimationDuration(){
	  return ANIMATION_DURATION;
  }
 
  public HandlerRegistration addCloseHandler(
      CloseHandler<AnimatedContent> handler) {
    return addHandler(handler, CloseEvent.getType());
  }

 
  public HandlerRegistration addOpenHandler(OpenHandler<AnimatedContent> handler) {
    return addHandler(handler, OpenEvent.getType());
  }

  public void clear() {
    setContent(null);
  }

  /**
   * Gets the widget that was previously set in {@link #setContent(Widget)}.
   * 
   * @return the panel's current content widget
   */
  public Widget getContent() {
    return contentWrapper.getWidget();
  }

  
  public boolean isAnimationEnabled() {
    return isAnimationEnabled;
  }

  /**
   * Determines whether the panel is open.
   * 
   * @return <code>true</code> if panel is in open state
   */
  public boolean isOpen() {
    return isOpen;
  }
 
  public void setAnimationEnabled(boolean enable) {
    isAnimationEnabled = enable;
  }


  /**
   * Sets the content widget which can be opened and closed by this panel. If
   * there is a preexisting content widget, it will be detached.
   * 
   * @param content the widget to be used as the content panel
   */
  public void setContent(IsWidget content) {
    final Widget currentContent = getContent();

    // Remove existing content widget.
    if (currentContent != null) {
      contentWrapper.setWidget(null);
      currentContent.removeStyleName(STYLENAME_CONTENT);
    }

    // Add new content widget if != null.
    if (content != null) {
      contentWrapper.setWidget(content);
      content.asWidget().addStyleName(STYLENAME_CONTENT);
      setContentDisplay(false);
    }
  }

  
  
  /**
   * Changes the visible state of this <code>AnimatedContent</code>.
   * 
   * @param isOpen <code>true</code> to open the panel, <code>false</code> to
   *          close
   */
  public void setOpen(boolean isOpen) {
    if (this.isOpen != isOpen) {
      this.isOpen = isOpen;
      setContentDisplay(true);
      fireEvent();
    }
  }

  private void fireEvent() {
    if (isOpen) {
      OpenEvent.fire(this, this);
    } else {
      CloseEvent.fire(this, this);
    }
  }

  private void setContentDisplay(boolean animate) {
    if (isOpen) {
      removeStyleDependentName(STYLENAME_SUFFIX_CLOSED);
      addStyleDependentName(STYLENAME_SUFFIX_OPEN);
    } else {
      removeStyleDependentName(STYLENAME_SUFFIX_OPEN);
      addStyleDependentName(STYLENAME_SUFFIX_CLOSED);
    }

    if (getContent() != null) {
      if (contentAnimation == null) {
        contentAnimation = new ContentAnimation();
      }
      contentAnimation.setOpen(this, animate && isAnimationEnabled);
    }
  }


}
