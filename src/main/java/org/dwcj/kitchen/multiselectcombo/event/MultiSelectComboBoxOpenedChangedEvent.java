package org.dwcj.kitchen.multiselectcombo.event;

import org.dwcj.component.event.ComponentEvent;
import org.dwcj.component.webcomponent.annotation.EventName;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBox;

import java.util.Map;

/**
 * Emitted when the open state of the dropdown changes.
 *
 * @author ElyasSalar
 */
@EventName("bbj-opened-changed")
public class MultiSelectComboBoxOpenedChangedEvent extends ComponentEvent<MultiSelectComboBox> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public MultiSelectComboBoxOpenedChangedEvent(MultiSelectComboBox control,
      Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
