package org.dwcj.kitchen.navbar;

import org.dwcj.annotation.Attribute;
import org.dwcj.annotation.InlineJavaScript;
import org.dwcj.component.webcomponent.PropertyDescriptor;
import org.dwcj.component.webcomponent.WebComponent;
import org.dwcj.component.webcomponent.annotation.NodeName;
import org.dwcj.concern.HasAttribute;
import org.dwcj.concern.HasClassName;
import org.dwcj.concern.HasFocus;
import org.dwcj.concern.HasStyle;
import org.dwcj.dispatcher.EventListener;
import org.dwcj.kitchen.navbar.event.NavbarInputEvent;
import org.dwcj.kitchen.navbar.event.NavbarSelectedEvent;

import java.util.List;

@NodeName("bbj-navbar")

@InlineJavaScript(value = "context://webcomponents/bbj-navbar.js",
    attributes = {@Attribute(name = "type", value = "module")})
public class Navbar extends WebComponent
    implements HasClassName<Navbar>, HasStyle<Navbar>, HasAttribute<Navbar> {

  /**
   * The control's expanse.
   */
  public enum Expanse {
    XSMALL("xs"), SMALL("s"), MEDIUM("m"), LARGE("l"), XLARGE("xl");

    /** The expanse of the control value. */
    private final String value;

    /**
     * Instantiates a new expanse for the control.
     *
     * @param value the value
     */
    Expanse(String value) {
      this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * Gets the expanse of the control from value.
     *
     * @param value the value to parse
     * @return the expanse of the control
     */
    public static Expanse fromValue(String value) {
      for (Expanse expanse : Expanse.values()) {
        if (expanse.getValue().equals(value)) {
          return expanse;
        }
      }

      return null;
    }

    /**
     * Gets the expanse of the control value as string.
     *
     * @return the string
     */
    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * The possible behaviours for highlighting input value.
   */
  public enum HighlightBehaviours {
    KEY, MOUSE, REQUEST
  }

  // Properties
  private final PropertyDescriptor<String> searchKeywordProp =
      PropertyDescriptor.property("searchKeyword", null);
  private final PropertyDescriptor<String> inputPlaceholderProp =
      PropertyDescriptor.property("inputPlaceholder", null);
  private final PropertyDescriptor<String> navItemsTitleProp =
      PropertyDescriptor.property("navItemsTitle", null);
  private final PropertyDescriptor<String> selectedIdProp =
      PropertyDescriptor.property("selectedId", null);
  private final PropertyDescriptor<String> expanseProp =
      PropertyDescriptor.property("expanse", Expanse.MEDIUM.getValue());
  private final PropertyDescriptor<String> newTabIconProp =
      PropertyDescriptor.property("newTabIcon", null);
  private final PropertyDescriptor<List<HighlightBehaviours>> highlightBehaviorsProp =
      PropertyDescriptor.property("highlightBehaviors", null);
  private final PropertyDescriptor<List<NavbarItem>> filteredNavItemsProp =
      PropertyDescriptor.property("filteredNavItems", null);
  private final PropertyDescriptor<List<NavbarItem>> navItemsProp =
      PropertyDescriptor.property("navItems", null);
  private final PropertyDescriptor<String> favoriteIconProp =
      PropertyDescriptor.property("favoriteIcon", null);
  private final PropertyDescriptor<String> favoriteFilledIconProp =
      PropertyDescriptor.property("favoriteFilledIcon", null);
  private final PropertyDescriptor<Boolean> showNewTabProp =
      PropertyDescriptor.property("showNewTab", false);
  private final PropertyDescriptor<Boolean> showFavoritesProp =
      PropertyDescriptor.property("showFavorites", false);

  /**
   * Add a listener for change in selected item.
   *
   * @param listener the listener
   * @return the control
   */
  public Navbar addSelectedEventListener(EventListener<NavbarSelectedEvent> listener) {
    super.addEventListener(NavbarSelectedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the input change event.
   *
   * @param listener the listener
   * @return the control
   */
  public Navbar addInputListener(EventListener<NavbarInputEvent> listener) {
    super.addEventListener(NavbarInputEvent.class, listener);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public String getAttribute(String attribute) {
    return super.getComponentAttribute(attribute);
  }

  /**
   * {@inheritDoc}
   */
  public Navbar setAttribute(String attribute, String value) {
    super.setComponentAttribute(attribute, value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Object getProperty(String property) {
    return super.getComponentProperty(property);
  }

  /**
   * {@inheritDoc}
   */
  public Navbar setProperty(String property, Object value) {
    super.setComponentProperty(property, value);
    return this;
  }

  /**
   * Get the search keyword value
   *
   * @return search keyword value
   */
  public String getSearchKeyword() {
    return super.get(this.searchKeywordProp);
  }

  /**
   * Set the search keyword value
   *
   * @param searchKeyword
   * @return the control
   */
  public Navbar setSearchKeyword(String searchKeyword) {
    super.set(this.searchKeywordProp, searchKeyword);
    return this;
  }

  /**
   * Get the input placeholder value
   *
   * @return input placeholder value
   */
  public String getInputPlaceholder() {
    return super.get(this.inputPlaceholderProp);
  }

  /**
   * Set the input placeholder value
   *
   * @param inputPlaceholder
   * @return the control
   */
  public Navbar setInputPlaceholder(String inputPlaceholder) {
    super.set(this.inputPlaceholderProp, inputPlaceholder);
    return this;
  }

  /**
   * Get the nav items title value
   *
   * @return nav items title value
   */
  public String getNavItemsTitle() {
    return super.get(this.navItemsTitleProp);
  }

  /**
   * Set the nav items title value
   *
   * @param navItemsTitle
   * @return the control
   */
  public Navbar setNavItemsTitle(String navItemsTitle) {
    super.set(this.navItemsTitleProp, navItemsTitle);
    return this;
  }

  /**
   * Get the selected id value
   *
   * @return selected id value
   */
  public String getSelectedId() {
    return super.get(this.selectedIdProp);
  }

  /**
   * Set the selected id value
   *
   * @param selectedId
   * @return the control
   */
  public Navbar setSelectedId(String selectedId) {
    super.set(this.selectedIdProp, selectedId);
    return this;
  }

  /**
   * Get the expanse value
   *
   * @return expanse value
   */
  public String getExpanse() {
    return super.get(this.expanseProp);
  }

  /**
   * Set the expanse value
   *
   * @param expanse
   * @return the control
   */
  public Navbar setExpanse(Expanse expanse) {
    super.set(this.expanseProp, expanse.getValue());
    return this;
  }

  /**
   * Get the new tab icon value
   *
   * @return new tab icon value
   */
  public String getNewTabIcon() {
    return super.get(this.newTabIconProp);
  }

  /**
   * Set the new tab icon value
   *
   * @param newTabIcon
   * @return the control
   */
  public Navbar setNewTabIcon(String newTabIcon) {
    super.set(this.newTabIconProp, newTabIcon);
    return this;
  }

  /**
   * Get the nav items value
   *
   * @return nav items value
   */
  public List<NavbarItem> getNavItems() {
    return super.get(this.navItemsProp);
  }

  /**
   * Set the nav items value
   *
   * @param navItems
   * @return the control
   */
  public Navbar setNavItems(List<NavbarItem> navItems) {
    super.set(this.navItemsProp, navItems);
    return this;
  }

  /**
   * Get the filtered nav items value
   *
   * @return filtered nav items value
   */
  public List<NavbarItem> getFilteredNavItems() {
    return super.get(this.filteredNavItemsProp);
  }

  /**
   * Set the filtered nav items value
   *
   * @param filteredNavItems
   * @return the control
   */
  public Navbar setFilteredNavItems(List<NavbarItem> filteredNavItems) {
    super.set(this.filteredNavItemsProp, filteredNavItems);
    return this;
  }

  /**
   * Get the highlightBehaviors value
   *
   * @return highlightBehaviors value
   */
  public List<HighlightBehaviours> getHighlightBehaviors() {
    return super.get(this.highlightBehaviorsProp);
  }

  /**
   * Set the highlightBehaviors value
   *
   * @param highlightBehaviors
   * @return the control
   */
  public Navbar setHighlightBehaviors(List<HighlightBehaviours> highlightBehaviors) {
    super.set(this.highlightBehaviorsProp, highlightBehaviors);
    return this;
  }

  /**
   * Get the favorite icon value
   *
   * @return favorite icon value
   */
  public String getFavoriteIcon() {
    return super.get(this.favoriteIconProp);
  }

  /**
   * Set the favorite icon value
   *
   * @param favoriteIcon
   * @return the control
   */
  public Navbar setFavoriteIcon(String favoriteIcon) {
    super.set(this.favoriteIconProp, favoriteIcon);
    return this;
  }

  /**
   * Get the favorite filled icon value
   *
   * @return favorite filled icon value
   */
  public String getFavoriteFilledIcon() {
    return super.get(this.favoriteFilledIconProp);
  }

  /**
   * Set the favorite filled icon value
   *
   * @param favoriteFilledIcon
   * @return the control
   */
  public Navbar setFavoriteFilledIcon(String favoriteFilledIcon) {
    super.set(this.favoriteFilledIconProp, favoriteFilledIcon);
    return this;
  }

  /**
   * Get the show new tab value
   *
   * @return show new tab value
   */
  public Boolean getShowNewTab() {
    return super.get(this.showNewTabProp);
  }

  /**
   * Set the show new tab value
   *
   * @param showNewTab
   * @return the control
   */
  public Navbar setShowNewTab(Boolean showNewTab) {
    super.set(this.showNewTabProp, showNewTab);
    return this;
  }

  /**
   * Get the show favorites value
   *
   * @return show favorites value
   */
  public Boolean getShowFavorites() {
    return super.get(this.showFavoritesProp);
  }

  /**
   * Set the show favorites value
   *
   * @param showFavorites
   * @return the control
   */
  public Navbar setShowFavorites(Boolean showFavorites) {
    super.set(this.showFavoritesProp, showFavorites);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Navbar removeAttribute(String attribute) {
    super.removeComponentAttribute(attribute);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Navbar addClassName(String className) {
    super.addComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Navbar removeClassName(String className) {
    super.removeComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Navbar focus() {
    super.callAsyncFunction("focus");
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public String getStyle(String property) {
    return super.getComponentStyle(property);
  }

  /**
   * {@inheritDoc}
   */
  public String getComputedStyle(String property) {
    return super.getComponentComputedStyle(property);
  }

  /**
   * {@inheritDoc}
   */
  public Navbar setStyle(String property, String value) {
    super.setComponentStyle(property, value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public Navbar removeStyle(String property) {
    super.removeComponentStyle(property);
    return this;
  }
}
