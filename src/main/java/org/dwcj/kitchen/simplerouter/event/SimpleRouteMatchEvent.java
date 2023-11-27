package org.dwcj.kitchen.simplerouter.event;


import org.dwcj.kitchen.simplerouter.Route;

import java.util.EventObject;

public class SimpleRouteMatchEvent extends EventObject {

  private final Route route;


  /**
   * Constructs a prototypical Event.
   *
   * @param source the object on which the Event initially occurred
   * @throws IllegalArgumentException if source is null
   */
  public SimpleRouteMatchEvent(Object source, Route route) {
    super(source);
    this.route = route;
  }

  public Route getRoute() {
    return route;
  }

  @Override
  public String toString() {
    return "SimpleRouteMatchEvent{" + "route=" + route.getRoute() + '}';
  }
}
