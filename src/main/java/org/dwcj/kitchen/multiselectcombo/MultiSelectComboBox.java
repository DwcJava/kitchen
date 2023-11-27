package org.dwcj.kitchen.multiselectcombo;


import org.dwcj.annotation.Attribute;
import org.dwcj.annotation.InlineJavaScript;
import org.dwcj.component.webcomponent.PropertyDescriptor;
import org.dwcj.component.webcomponent.WebComponent;
import org.dwcj.component.webcomponent.annotation.NodeName;
import org.dwcj.concern.*;
import org.dwcj.dispatcher.EventListener;
import org.dwcj.kitchen.multiselectcombo.event.*;

import java.util.ArrayList;
import java.util.List;

@InlineJavaScript(id = "multiselectcombobox",
    value = "context://webcomponents/bbj-multi-select-combo-box.js",
    attributes = {@Attribute(name = "type", value = "module")})
@NodeName("bbj-multi-select-combo-box")
public class MultiSelectComboBox extends WebComponent
    implements HasPlaceholder<MultiSelectComboBox>, HasReadOnly<MultiSelectComboBox>,
    HasClassName<MultiSelectComboBox>, HasStyle<MultiSelectComboBox>,
    HasAttribute<MultiSelectComboBox> {

  private String label = "";

  /**
   * Placement enum.
   */
  public enum Placement {
    /** Will be placed at the top start of the input. */
    TOP_START("top-start"),

    /** Will be placed at the top of the input. */
    TOP("top"),

    /** Will be placed at the top end of the input. */
    TOP_END("top-end"),

    /** Will be placed at the bottom start of the input. */
    BOTTOM_START("bottom-start"),

    /** Will be placed at the bottom of the input. */
    BOTTOM("bottom"),

    /** Will be placed at the bottom end of the input. */
    BOTTOM_END("bottom-end"),

    /** Will be placed at the right start of the input. */
    RIGHT_START("right-start"),

    /** Will be placed at the right of the input. */
    RIGHT("right"),

    /** Will be placed at the right end of the input. */
    RIGHT_END("right-end"),

    /** Will be placed at the left start of the input. */
    LEFT_START("left-start"),

    /** Will be placed at the left of the input. */
    LEFT("left"),

    /** Will be placed at the left end of the input. */
    LEFT_END("left-end");

    /** The placement value. */
    private final String value;

    /**
     * Instantiates a new placement.
     *
     * @param value the value
     */
    Placement(String value) {
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
     * Gets the placement from value.
     *
     * @param value the value to parse
     * @return the placement
     */
    public static Placement fromValue(String value) {
      for (Placement placement : Placement.values()) {
        if (placement.getValue().equals(value)) {
          return placement;
        }
      }

      return null;
    }

    /**
     * Gets the placement value as string.
     *
     * @return the string
     */
    @Override
    public String toString() {
      return value;
    }
  }

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

  private final PropertyDescriptor<String> placementProp =
      PropertyDescriptor.property("placement", Placement.BOTTOM.getValue());
  private final PropertyDescriptor<String> expanseProp =
      PropertyDescriptor.property("expanse", Expanse.MEDIUM.getValue());
  // private final PropertyDescriptor<String> validationPopoverPlacement =
  // PropertyDescriptor.property("validationPopoverPlacement",
  // MultiSelectComboBox.Placement.BOTTOM_START.getValue());
  // private final PropertyDescriptor<Boolean> autoValidate =
  // PropertyDescriptor.property("autoValidate", true);
  // private final PropertyDescriptor<Boolean> autoValidateOnLoad =
  // PropertyDescriptor.property("autoValidateOnLoad", false);
  // private final PropertyDescriptor<Boolean> autoWasValidated =
  // PropertyDescriptor.property("autoWasValidated", false);
  // private final PropertyDescriptor<Boolean> validatorProp =
  // PropertyDescriptor.property("validator", false);
  // private final PropertyDescriptor<Boolean> validationIconProp =
  // PropertyDescriptor.property("validationIcon", false);
  // private final PropertyDescriptor<Boolean> validationPopoverDistanceProp =
  // PropertyDescriptor.property("validationPopoverDistance", false);
  // private final PropertyDescriptor<Boolean> validationPopoverSkiddingProp =
  // PropertyDescriptor.property("validationPopoverSkidding", false);
  // private final PropertyDescriptor<Boolean> validationStyleProp =
  // PropertyDescriptor.property("validationStyle", false);
  // private final PropertyDescriptor<Boolean> validProp =
  // PropertyDescriptor.property("valid", false);
  // private final PropertyDescriptor<Boolean> invalidProp =
  // PropertyDescriptor.property("invalid", false);
  // private final PropertyDescriptor<Boolean> invalidMessageProp =
  // PropertyDescriptor.property("invalidMessage", false);
  private final PropertyDescriptor<String> rendererProp =
      PropertyDescriptor.property("renderer", null);
  private final PropertyDescriptor<List<MultiSelectComboBoxItem>> itemsProp =
      PropertyDescriptor.property("items", null);
  private final PropertyDescriptor<List<String>> selectedProp =
      PropertyDescriptor.property("selected", null);
  private final PropertyDescriptor<Boolean> autoFocusProp =
      PropertyDescriptor.attribute("autofocus", false);
  private final PropertyDescriptor<Boolean> hasFocusProp =
      PropertyDescriptor.property("hasFocus", false);
  private final PropertyDescriptor<Boolean> requiredProp =
      PropertyDescriptor.attribute("required", false);
  private final PropertyDescriptor<Integer> tabTraversableProp =
      PropertyDescriptor.property("tabTraversable", 0);
  private final PropertyDescriptor<String> inputValueProp =
      PropertyDescriptor.property("inputValue", "");
  private final PropertyDescriptor<List<HighlightBehaviours>> highlightBehaviorsProp =
      PropertyDescriptor.property("highlightBehaviors", new ArrayList<>());
  private final PropertyDescriptor<String> nameProp = PropertyDescriptor.attribute("name", "");
  private final PropertyDescriptor<String> placeholderProp =
      PropertyDescriptor.attribute("placeholder", "");
  private final PropertyDescriptor<Float> inputSizeProp =
      PropertyDescriptor.property("inputSize", null);
  private final PropertyDescriptor<Boolean> allowCustomValueProp =
      PropertyDescriptor.property("allowCustomValue", false);
  private final PropertyDescriptor<Integer> maxRowCountProp =
      PropertyDescriptor.property("maxRowCount", null);
  private final PropertyDescriptor<Boolean> openOnArrowProp =
      PropertyDescriptor.property("openOnArrow", true);
  private final PropertyDescriptor<Boolean> disabledProp =
      PropertyDescriptor.attribute("disabled", false);
  private final PropertyDescriptor<Boolean> readonlyProp =
      PropertyDescriptor.attribute("readonly", false);
  private final PropertyDescriptor<Float> distanceProp =
      PropertyDescriptor.property("distance", 0f);
  private final PropertyDescriptor<Integer> maxChipsLengthProp =
      PropertyDescriptor.property("maxChipsLength", 65);
  private final PropertyDescriptor<Boolean> openedProp =
      PropertyDescriptor.property("opened", false);
  private final PropertyDescriptor<Float> openWidthProp =
      PropertyDescriptor.property("openWidth", null);
  private final PropertyDescriptor<Float> openHeightProp =
      PropertyDescriptor.property("openHeight", null);
  private final PropertyDescriptor<Float> skiddingProp =
      PropertyDescriptor.property("skidding", 0f);

  /**
   * Add a listener for the opened event.
   *
   * @param listener the listener
   * @return the control
   */
  public MultiSelectComboBox addOpenedChangedListener(
      EventListener<MultiSelectComboBoxOpenedChangedEvent> listener) {
    super.addEventListener(MultiSelectComboBoxOpenedChangedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the input change event.
   *
   * @param listener the listener
   * @return the control
   */
  public MultiSelectComboBox addInputListener(
      EventListener<MultiSelectComboBoxInputEvent> listener) {
    super.addEventListener(MultiSelectComboBoxInputEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the change of selected options' event.
   *
   * @param listener the listener
   * @return the control
   */
  public MultiSelectComboBox addSelectedChangedListener(
      EventListener<MultiSelectComboBoxSelectedChangedEvent> listener) {
    super.addEventListener(MultiSelectComboBoxSelectedChangedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the change of filtered options event.
   *
   * @param listener the listener
   * @return the control
   */
  public MultiSelectComboBox addFilterChangedListener(
      EventListener<MultiSelectComboBoxFilterChangedEvent> listener) {
    super.addEventListener(MultiSelectComboBoxFilterChangedEvent.class, listener);
    return this;
  }

  /**
   * Add a listener for the validated event.
   *
   * @param listener the listener
   * @return the control
   */
  public MultiSelectComboBox addValidatedListener(
      EventListener<MultiSelectComboBoxValidatedEvent> listener) {
    super.addEventListener(MultiSelectComboBoxValidatedEvent.class, listener);
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
  public MultiSelectComboBox setAttribute(String attribute, String value) {
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
  public MultiSelectComboBox setProperty(String property, Object value) {
    super.setComponentProperty(property, value);
    return this;
  }

  /**
   * Get the label value
   *
   * @return label value
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * Set the label value
   *
   * @param label
   * @return the control
   */
  public MultiSelectComboBox setLabel(String label) {
    setAttribute("label", label);
    this.label = label;
    return this;
  }

  /**
   * Get the placement value
   *
   * @return placement value
   */
  public String getPlacement() {
    return super.get(this.placementProp);
  }

  /**
   * Set the placement value
   *
   * @param placement
   * @return the control
   */
  public MultiSelectComboBox setPlacement(Placement placement) {
    super.set(this.placementProp, placement.getValue());
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
  public MultiSelectComboBox setExpanse(Expanse expanse) {
    super.set(this.expanseProp, expanse.getValue());
    return this;
  }

  /**
   * Get the items value
   *
   * @return items value
   */
  public List<MultiSelectComboBoxItem> getItems() {
    return super.get(this.itemsProp);
  }

  /**
   * Set the items value
   *
   * @param items
   * @return the control
   */
  public MultiSelectComboBox setItems(List<MultiSelectComboBoxItem> items) {
    super.set(this.itemsProp, items);
    return this;
  }

  /**
   * Get the renderer value
   *
   * @return renderer value
   */
  public String getRenderer() {
    return super.get(this.rendererProp);
  }

  /**
   * Set the renderer value
   *
   * @param renderer
   * @return the control
   */
  public MultiSelectComboBox setRenderer(String renderer) {
    super.set(this.rendererProp, renderer);
    return this;
  }

  /**
   * Get the selected value
   *
   * @return selected value
   */
  public List<String> getSelected() {
    return super.get(this.selectedProp, true, List.class);
  }

  /**
   * Set the selected value
   *
   * @param selected
   * @return the control
   */
  public MultiSelectComboBox setSelected(List<String> selected) {
    super.set(this.selectedProp, selected);
    return this;
  }

  /**
   * Get the autofocus value
   *
   * @return autofocus value
   */
  public Boolean getAutoFocus() {
    return super.get(this.autoFocusProp);
  }

  /**
   * Set the autofocus value
   *
   * @param autofocus
   * @return the control
   */
  public MultiSelectComboBox setAutoFocus(Boolean autofocus) {
    super.set(this.autoFocusProp, autofocus);
    return this;
  }

  /**
   * Get the hasFocus value
   *
   * @return hasFocus value
   */
  public Boolean getHasFocus() {
    return super.get(this.hasFocusProp);
  }

  /**
   * Set the hasFocus value
   *
   * @param hasFocus
   * @return the control
   */
  public MultiSelectComboBox setHasFocus(Boolean hasFocus) {
    super.set(this.hasFocusProp, hasFocus);
    return this;
  }

  /**
   * Get the required value
   *
   * @return required value
   */
  public Boolean getRequired() {
    return super.get(this.requiredProp);
  }

  /**
   * Set the required value
   *
   * @param required
   * @return the control
   */
  public MultiSelectComboBox setRequired(Boolean required) {
    super.set(this.requiredProp, required);
    return this;
  }

  /**
   * Get the tabTraversable value
   *
   * @return tabTraversable value
   */
  public Integer getTabTraversable() {
    return super.get(this.tabTraversableProp);
  }

  /**
   * Set the tabTraversable value
   *
   * @param tabTraversable
   * @return the control
   */
  public MultiSelectComboBox setTabTraversable(Integer tabTraversable) {
    super.set(this.tabTraversableProp, tabTraversable);
    return this;
  }

  /**
   * Get the inputValue value
   *
   * @return inputValue value
   */
  public String getInputValue() {
    return super.get(this.inputValueProp);
  }

  /**
   * Set the inputValue value
   *
   * @param inputValue
   * @return the control
   */
  public MultiSelectComboBox setInputValue(String inputValue) {
    super.set(this.inputValueProp, inputValue);
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
  public MultiSelectComboBox setHighlightBehaviors(List<HighlightBehaviours> highlightBehaviors) {
    super.set(this.highlightBehaviorsProp, highlightBehaviors);
    return this;
  }

  /**
   * Get the name value
   *
   * @return name value
   */
  public String getName() {
    return super.get(this.nameProp);
  }

  /**
   * Set the name value
   *
   * @param name
   * @return the control
   */
  public MultiSelectComboBox setName(String name) {
    super.set(this.nameProp, name);
    return this;
  }

  /**
   * Get the inputSize value
   *
   * @return inputSize value
   */
  public Float getInputSize() {
    return super.get(this.inputSizeProp);
  }

  /**
   * Set the inputSize value
   *
   * @param inputSize
   * @return the control
   */
  public MultiSelectComboBox setInputSize(Float inputSize) {
    super.set(this.inputSizeProp, inputSize);
    return this;
  }

  /**
   * Get the allowCustomValue value
   *
   * @return allowCustomValue value
   */
  public Boolean getAllowCustomValue() {
    return super.get(this.allowCustomValueProp);
  }

  /**
   * Set the allowCustomValue value
   *
   * @param allowCustomValue
   * @return the control
   */
  public MultiSelectComboBox setAllowCustomValue(Boolean allowCustomValue) {
    super.set(this.allowCustomValueProp, allowCustomValue);
    return this;
  }

  /**
   * Get the maxRowCount value
   *
   * @return maxRowCount value
   */
  public Integer getMaxRowCount() {
    return super.get(this.maxRowCountProp);
  }

  /**
   * Set the allowCustomValue value
   *
   * @param maxRowCount
   * @return the control
   */
  public MultiSelectComboBox setMaxRowCount(Integer maxRowCount) {
    super.set(this.maxRowCountProp, maxRowCount);
    return this;
  }

  /**
   * Get the openOnArrow value
   *
   * @return openOnArrow value
   */
  public Boolean getOpenOnArrow() {
    return super.get(this.openOnArrowProp);
  }

  /**
   * Set the allowCustomValue value
   *
   * @param openOnArrow
   * @return the control
   */
  public MultiSelectComboBox setOpenOnArrow(Boolean openOnArrow) {
    super.set(this.openOnArrowProp, openOnArrow);
    return this;
  }

  /**
   * Get the disabled value
   *
   * @return disabled value
   */
  public Boolean getDisabled() {
    return super.get(this.disabledProp);
  }

  /**
   * Set the disabled value
   *
   * @param disabled
   * @return the control
   */
  public MultiSelectComboBox setDisabled(Boolean disabled) {
    super.set(this.disabledProp, disabled);
    return this;
  }

  /**
   * Get the distance value
   *
   * @return distance value
   */
  public Float getDistance() {
    return super.get(this.distanceProp);
  }

  /**
   * Set the distance value
   *
   * @param distance
   * @return the control
   */
  public MultiSelectComboBox setDistance(Float distance) {
    super.set(this.distanceProp, distance);
    return this;
  }

  /**
   * Get the maxChipsLength value
   *
   * @return maxChipsLength value
   */
  public Integer getMaxChipsLength() {
    return super.get(this.maxChipsLengthProp);
  }

  /**
   * Set the maxChipsLength value
   *
   * @param maxChipsLength
   * @return the control
   */
  public MultiSelectComboBox setMaxChipsLength(Integer maxChipsLength) {
    super.set(this.maxChipsLengthProp, maxChipsLength);
    return this;
  }

  /**
   * Get the opened value
   *
   * @return opened value
   */
  public Boolean getOpened() {
    return super.get(this.openedProp);
  }

  /**
   * Set the opened value
   *
   * @param opened
   * @return the control
   */
  public MultiSelectComboBox setOpened(Boolean opened) {
    super.set(this.openedProp, opened);
    return this;
  }

  /**
   * Get the openWidth value
   *
   * @return openWidth value
   */
  public Float getOpenWidth() {
    return super.get(this.openWidthProp);
  }

  /**
   * Set the openWidth value
   *
   * @param openWidth
   * @return the control
   */
  public MultiSelectComboBox setOpenWidth(Float openWidth) {
    super.set(this.openWidthProp, openWidth);
    return this;
  }

  /**
   * Get the openHeight value
   *
   * @return openHeight value
   */
  public Float getOpenHeight() {
    return super.get(this.openHeightProp);
  }

  /**
   * Set the openHeight value
   *
   * @param openHeight
   * @return the control
   */
  public MultiSelectComboBox setOpenHeight(Float openHeight) {
    super.set(this.openHeightProp, openHeight);
    return this;
  }

  /**
   * Get the skidding value
   *
   * @return skidding value
   */
  public Float getSkidding() {
    return super.get(this.skiddingProp);
  }

  /**
   * Set the skidding value
   *
   * @param skidding
   * @return the control
   */
  public MultiSelectComboBox setSkidding(Float skidding) {
    super.set(this.skiddingProp, skidding);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox removeAttribute(String attribute) {
    super.removeComponentAttribute(attribute);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox addClassName(String className) {
    super.addComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox removeClassName(String className) {
    super.removeComponentClassName(className);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox focus() {
    super.callAsyncFunction("focus");
    return this;
  }


  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox setPlaceholder(String placeholder) {
    super.set(placeholderProp, placeholder);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public String getPlaceholder() {
    return super.get(placeholderProp);
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox setReadOnly(Boolean readOnly) {
    super.set(readonlyProp, readOnly);
    return this;
  }

  @Override
  public MultiSelectComboBox setReadOnly(boolean b) {
    return null; // TODO
  }

  /**
   * {@inheritDoc}
   */
  public boolean isReadOnly() {
    return super.get(readonlyProp);
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
  public MultiSelectComboBox setStyle(String property, String value) {
    super.setComponentStyle(property, value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  public MultiSelectComboBox removeStyle(String property) {
    super.removeComponentStyle(property);
    return this;
  }
}
