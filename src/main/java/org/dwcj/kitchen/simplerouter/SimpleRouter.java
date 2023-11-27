package org.dwcj.kitchen.simplerouter;


import org.dwcj.App;
import org.dwcj.dispatcher.EventDispatcher;
import org.dwcj.dispatcher.EventListener;
import org.dwcj.environment.ObjectTable;
import org.dwcj.kitchen.simplerouter.event.SimpleRouteMatchEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SimpleRouter {

  private Map<String, EventDispatcher> eventMap = new HashMap<>();

  private String currentRoute = "";

  private String baseUrl;


  /**
   * private constructor
   */
  private SimpleRouter() {
    String url = App.getUrl();
    String name = App.getApplicationName();
    if (ObjectTable.get("dwcj_base_url") != null) {
      this.baseUrl = ObjectTable.get("dwcj_base_url").toString();
    } else {
      this.baseUrl = url.substring(0, url.indexOf(name) + name.length());
    }
  }

  /**
   * Get the current page instance.
   *
   * @return the current page instance
   */
  public static SimpleRouter getInstance() {
    String key = "dwcj.SimpleRouter.instance";
    if (ObjectTable.contains(key)) {
      return (SimpleRouter) ObjectTable.get(key);
    }

    SimpleRouter instance = new SimpleRouter();
    ObjectTable.put(key, instance);

    return instance;
  }

  private void updateUrl(String routeString) {
    App.getPage().executeJs(
        "window.history.replaceState({},'title','" + baseUrl + "/" + routeString + "');");
  }

  public void onRouteMatch(EventListener<SimpleRouteMatchEvent> listener, String... routeStrings) {
    Iterator<String> it = Arrays.stream(routeStrings).iterator();
    while (it.hasNext()) {
      String routeString = it.next();
      EventDispatcher dispatcher;
      if (eventMap.containsKey(routeString)) {
        dispatcher = eventMap.get(routeString);
      } else {
        dispatcher = new EventDispatcher();
        eventMap.put(routeString, dispatcher);
      }
      dispatcher.addListener(SimpleRouteMatchEvent.class, listener);
    }
  }


  public String getCurrentRoute() {
    return currentRoute;
  }

  /**
   * navigates according to the current location
   */
  public void navigate() {
    String url = App.getUrl();
    if (url.startsWith(this.baseUrl + "/")) {
      url = url.substring(this.baseUrl.length() + 1);
      navigate(url);
    }
  }


  public void navigate(String routeString) {
    Boolean done = false;
    Iterator<String> it = eventMap.keySet().iterator();

    while (it.hasNext()) {
      String registeredRouteString = it.next();
      Route r = new Route(registeredRouteString);
      if (r.matches(routeString)) {
        if (!done) {
          currentRoute = routeString;
          done = true;
        }
        EventDispatcher dispatcher = eventMap.get(registeredRouteString);
        dispatcher.dispatchEvent(new SimpleRouteMatchEvent(this, r));
      }
    }
    if (done) {
      updateUrl(currentRoute);
    }
  }
}
