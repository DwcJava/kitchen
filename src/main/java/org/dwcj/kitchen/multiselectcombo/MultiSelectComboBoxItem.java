package org.dwcj.kitchen.multiselectcombo;

public class MultiSelectComboBoxItem {
  private String value = "";
  private String label = "";
  private String prefix;
  private String suffix;
  private Boolean disabled = false;

  public MultiSelectComboBoxItem(String value, String label) {
    this.value = value;
    this.label = label;
  }

  public MultiSelectComboBoxItem(String value, String label, String prefix, String suffix,
      Boolean disabled) {
    this.value = value;
    this.label = label;
    this.prefix = prefix;
    this.suffix = suffix;
    this.disabled = disabled;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return label;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getSuffix() {
    return suffix;
  }

  public Boolean getDisabled() {
    return disabled;
  }
}
