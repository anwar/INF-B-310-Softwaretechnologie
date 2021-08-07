import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class EventTest {
    private Event event;

    @Before
    public void setUp() {
        event = new Event("Science Slam", EventCategory.PRESENTATION);
    }

    @Test
    public void testConstructorNullArgument() {
        try {
            new Event(null, EventCategory.EXHIBITION);
            fail("Event.Event() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Event("Technical Collections", null);
            fail("Event.Event() should throw a NullPointerException if the category argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testConstructorIllegalArgument() {
        try {
            new Event("", EventCategory.EXHIBITION);
            fail("Event.Event() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testGetTitle() {
        assertEquals("Event.getTitle() should return the correct value!", "Science Slam", event.getTitle());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Event.getCategory() should return the correct value!", EventCategory.PRESENTATION,
                event.getCategory());
    }

    @Test
    public void testCompareToNullArgument() {
        try {
            event.compareTo(null);
            fail("Event.compareTo() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void eventsWithEqualTitleAndCategoryShouldCompareEqual() {
        Event myEvent = new Event("My Event", EventCategory.EXHIBITION);
        assertEquals("Event.compareTo(…) should return 0 when comparing the same object!", 0,
                myEvent.compareTo(myEvent));
        assertEquals("Event.compareTo(…) should return 0 if compared event has equal title and category!", 0,
                myEvent.compareTo(new Event("My Event", EventCategory.EXHIBITION)));
    }

    @Test
    public void eventsWithEqualTitleAreComparedByCategory() {
        Event lowest = new Event("My Event", EventCategory.EXHIBITION);
        Event middle = new Event("My Event", EventCategory.PRESENTATION);
        Event highest = new Event("My Event", EventCategory.SHOW);

        String message1 = "Event.compareTo(…) should return a negative value if the given "
                + "event has the same title but a higher category!";

        assertTrue(message1, lowest.compareTo(middle) < 0);
        assertTrue(message1, lowest.compareTo(highest) < 0);
        assertTrue(message1, middle.compareTo(highest) < 0);

        String message2 = "Event.compareTo(…) should return a positive value if the given "
                + "event has the same title but a lower category!";

        assertTrue(message2, highest.compareTo(lowest) > 0);
        assertTrue(message2, highest.compareTo(middle) > 0);
        assertTrue(message2, middle.compareTo(lowest) > 0);
    }

    @Test
    public void eventsWithUnequalTitleAreComparedByTitle() {
        Event lowest = new Event("Event A", EventCategory.SHOW);
        Event middle = new Event("Event B", EventCategory.PRESENTATION);
        Event highest = new Event("Event C", EventCategory.EXHIBITION);

        String message1 = "Event.compareTo(…) should return a negative value if the given "
                + "event has a higher title (e.g., 'Event A' < 'Event B')!";

        assertTrue(message1, lowest.compareTo(middle) < 0);
        assertTrue(message1, lowest.compareTo(highest) < 0);
        assertTrue(message1, middle.compareTo(highest) < 0);

        String message2 = "Event.compareTo(…) should return a positive value if the given "
                + "event has a lower title (e.g., 'Event B' > 'Event A')!";

        assertTrue(message2, highest.compareTo(lowest) > 0);
        assertTrue(message2, highest.compareTo(middle) > 0);
        assertTrue(message2, middle.compareTo(lowest) > 0);
    }
}
