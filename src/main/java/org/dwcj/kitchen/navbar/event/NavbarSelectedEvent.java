package org.dwcj.kitchen.navbar.event;

import org.dwcj.component.event.ComponentEvent;
import org.dwcj.component.element.annotation.EventName;
import org.dwcj.component.element.annotation.EventOptions;
import org.dwcj.kitchen.navbar.Navbar;

import java.util.Map;

/**
 * Emitted when the selected item changes.
 *
 * @author ElyasSalar
 */
@EventName("bbj-selected")
@EventOptions(map = @EventOptions.EventMapItem(key = "detail", exp = "event.detail"))
public class NavbarSelectedEvent extends ComponentEvent<Navbar> {
  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public NavbarSelectedEvent(Navbar control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
