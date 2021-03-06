package com.github.kratorius.cuckoohash;

import junit.framework.TestCase;

import java.util.Map;

/**
 * Tests for the basic map behaviour.
 */
public class BasicBehaviourTest extends TestCase {
  @SuppressWarnings("UnnecessaryBoxing")
  public void testMap() {
    CuckooHashMap<Character, Boolean> map = new CuckooHashMap<>();

    put(map, 'A', true,  null);
    put(map, 'A', false, true); // Guaranteed identical by JLS
    put(map, 'B', true,  null);
    put(map, new Character('A'), false, false);

    assertEquals(null, map.get(null));

    map.put(null, true);
    assertEquals(true, (boolean) map.get(null));

    try {
      map.put('C', null);
    } catch (NullPointerException e) {
      fail("Rejected null value");
    }
  }

  private static void put(Map<Character, Boolean> m,
                          Character key,
                          Boolean value,
                          Boolean oldValue) {
    if (oldValue != null) {
      assertTrue(m.containsValue(oldValue));
      assertTrue(m.values().contains(oldValue));
    }

    assertEquals(m.put(key, value), oldValue);
    assertEquals(m.get(key), value);

    assertTrue(m.containsKey(key));
    assertTrue(m.containsValue(value));

    assertTrue(m.values().contains(value));

    assertFalse(m.isEmpty());
  }
}
