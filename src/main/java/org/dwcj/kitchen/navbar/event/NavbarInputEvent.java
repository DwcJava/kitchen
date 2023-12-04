package org.dwcj.kitchen.navbar.event;


import org.dwcj.component.event.ComponentEvent;
import org.dwcj.component.element.annotation.EventName;
import org.dwcj.kitchen.navbar.Navbar;

import java.util.Map;

/**
 * Emitted when the input changed.
 *
 * @author ElyasSalar
 */
@EventName("bbj-input")
public class NavbarInputEvent extends ComponentEvent<Navbar> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public NavbarInputEvent(Navbar control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
