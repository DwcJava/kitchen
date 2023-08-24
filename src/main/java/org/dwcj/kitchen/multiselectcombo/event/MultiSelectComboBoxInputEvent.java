package org.dwcj.kitchen.multiselectcombo.event;

import org.dwcj.component.event.Event;
import org.dwcj.component.webcomponent.annotation.EventName;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBox;

import java.util.Map;

/**
 * Emitted when the input changed.
 *
 * @author ElyasSalar
 */
@EventName("bbj-input")
public class MultiSelectComboBoxInputEvent extends Event<MultiSelectComboBox> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public MultiSelectComboBoxInputEvent(MultiSelectComboBox control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
