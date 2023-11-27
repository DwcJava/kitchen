package samples;

import org.dwcj.kitchen.simplerouter.Route;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

  @Test
  void matchesStar() {
    Route route = new Route("customer/*");
    assertTrue(route.matches("customer"));
    assertTrue(route.matches("customer/"));
    assertTrue(route.matches("customer/1332/edit"));
  }

  @Test
  void matchesPlaceholders() {
    Route route = new Route("customer/:custno:num/edit/:contact:int/:page:bool");
    final String routeString = "customer/133.2/edit/876187/1";
    assertTrue(route.matches(routeString));
  }

  @Test
  void getVars() {
    Route route = new Route("customer/:custno:num/edit/:contact:int/:page:bool");
    final String routeString = "customer/133.2/edit/876187/1";
    assertTrue(route.matches(routeString));
    assertTrue(route.matches(routeString));

    assertEquals("133.2", route.getString(routeString, "custno"));
    assertEquals(new BigDecimal("133.2"), route.getNum(routeString, "custno"));
    assertNull(route.getInt(routeString, "custno"));
    assertNull(route.getBool(routeString, "custno"));

    assertEquals("876187", route.getString(routeString, "contact"));
    assertNull(route.getNum(routeString, "contact"));
    assertEquals(876187, route.getInt(routeString, "contact"));
    assertNull(route.getBool(routeString, "contact"));

    assertEquals("1", route.getString(routeString, "page"));
    assertNull(route.getNum(routeString, "page"));
    assertNull(route.getInt(routeString, "page"));
    assertTrue(route.getBool(routeString, "page"));

    assertNull(route.getInt(routeString, "foobar"));
  }

  @Test
  void getBoolVars() {
    Route route =
        new Route("customer/:bool1:bool/edit/:bool2:bool/:bool3:bool/:bool4:bool/:bool5:bool");
    final String routeString = "customer/1/edit/0/true/false/something";

    assertTrue(route.getBool(routeString, "bool1"));
    assertFalse(route.getBool(routeString, "bool2"));
    assertTrue(route.getBool(routeString, "bool3"));
    assertFalse(route.getBool(routeString, "bool4"));
    assertTrue(route.getBool(routeString, "bool5"));

  }

  @Test
  void endingVariable() {
    Route route = new Route("customer/:custnum");
    assertTrue(route.matches("customer/123"));
    assertFalse(route.matches("customer"));
  }

}
