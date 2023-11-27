package org.dwcj.kitchen.multiselectcombo.event;



import org.dwcj.component.event.ComponentEvent;
import org.dwcj.component.webcomponent.annotation.EventName;
import org.dwcj.kitchen.multiselectcombo.MultiSelectComboBox;

import java.util.Map;

/**
 * Emitted when the control's value is validated.
 *
 * @author ElyasSalar
 */
@EventName("bbj-validated")
public class MultiSelectComboBoxValidatedEvent extends ComponentEvent<MultiSelectComboBox> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public MultiSelectComboBoxValidatedEvent(MultiSelectComboBox control,
      Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
