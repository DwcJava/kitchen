package org.dwcj.kitchen.multiselectcombo.event;

import org.dwcj.component.element.annotation.EventName;
import org.dwcj.component.event.ComponentEvent;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBox;

import java.util.Map;

/**
 * Emitted when the filtered options changed.
 *
 * @author ElyasSalar
 */
@EventName("bbj-filter-changed")
public class MultiSelectComboBoxFilterChangedEvent extends ComponentEvent<MultiSelectComboBox> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public MultiSelectComboBoxFilterChangedEvent(MultiSelectComboBox control,
      Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
