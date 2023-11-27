package org.dwcj.kitchen.simplerouter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {



  private class RouteSegment {
    public int position;

    public enum SegmentType {
      STR, INT, NUM, BOOL
    };

    public SegmentType type;

    public String name;

    public RouteSegment(int position, String segStr) {
      this.position = position;

      String[] tmp = segStr.split(":");
      this.name = tmp[1];
      this.type = SegmentType.STR;
      if (tmp.length == 3) {
        switch (tmp[2]) {
          case "int":
            this.type = SegmentType.INT;
            break;
          case "num":
            this.type = SegmentType.NUM;
            break;
          case "bool":
            this.type = SegmentType.BOOL;
            break;
        }
      }

    }

    @Override
    public String toString() {
      return "RouteSegment{" + "position=" + position + ", type='" + type + '\'' + ", name='" + name
          + '\'' + '}';
    }
  }


  final private String route;
  final private Pattern routepattern;
  private HashMap<String, RouteSegment> routeSegments = new HashMap<>();

  public Route(String route) {
    this.route = route;

    String routepatterns = "^";
    String[] a = route.split("/");
    for (int i = 0; i < a.length; i++) {
      if (i > 0) {
        routepatterns += "/";
      }
      if (a[i].equals("*")) {
        routepatterns += ".*";
        continue;
      }
      if (a[i].startsWith(":")) {
        routepatterns += ".*";
        RouteSegment seg = new RouteSegment(i, a[i]);
        routeSegments.put(seg.name, seg);
      } else {
        routepatterns += a[i];
      }
    }
    this.routepattern = Pattern.compile(routepatterns, Pattern.CASE_INSENSITIVE);
  }

  public Boolean matches(String routeString) {
    Matcher matcher = routepattern.matcher(routeString + "/");
    if (matcher.matches()) {
      if (routeSegments.size() > 0) {
        // check if segments resolve in terms of correct types
        Iterator<String> it = routeSegments.keySet().iterator();
        while (it.hasNext()) {
          RouteSegment rs = routeSegments.get(it.next());
          if (rs.type == RouteSegment.SegmentType.STR && getString(routeString, rs.name) == null) {
            return false;
          }
          if (rs.type == RouteSegment.SegmentType.BOOL && getBool(routeString, rs.name) == null) {
            return false;
          }
          if (rs.type == RouteSegment.SegmentType.INT && getInt(routeString, rs.name) == null) {
            return false;
          }
          if (rs.type == RouteSegment.SegmentType.NUM && getNum(routeString, rs.name) == null) {
            return false;
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  public String getString(String routeString, String segmentName) {
    if (routeSegments.containsKey(segmentName)) {
      String s;
      RouteSegment segment = routeSegments.get(segmentName);
      String[] tmp = routeString.split("/");
      if (tmp.length < segment.position) {
        return null;
      }
      if (segment.position < tmp.length)
        return tmp[segment.position];
      else
        return null;
    } else {
      return null;
    }
  }

  public Integer getInt(String routeString, String segmentName) {
    String tmp = getString(routeString, segmentName);
    if (tmp != null && routeSegments.get(segmentName).type == RouteSegment.SegmentType.INT) {
      try {
        return Integer.parseInt(tmp);
      } catch (NumberFormatException e) {
      }
    }
    return null;
  }

  public BigDecimal getNum(String routeString, String segmentName) {
    String tmp = getString(routeString, segmentName);
    if (tmp != null && routeSegments.get(segmentName).type == RouteSegment.SegmentType.NUM) {
      try {
        return new BigDecimal(tmp);
      } catch (NumberFormatException e) {
      }
    }
    return null;
  }

  public Boolean getBool(String routeString, String segmentName) {
    String tmp = getString(routeString, segmentName);
    if (tmp != null && routeSegments.get(segmentName).type == RouteSegment.SegmentType.BOOL) {
      return !(tmp.toLowerCase().equals("false") || tmp.equals("0"));
    }
    return null;
  }


  public String getRoute() {
    return route;
  }

  public Pattern getRoutepattern() {
    return routepattern;
  }

}
