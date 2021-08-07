import java.util.Map;
import java.util.Set;

public interface EventCatalog {

	boolean addCatalogEntry(Event e, Set<Time> tSet);

	boolean addTimeToEvent(Event e, Time t);

	Set<Event> getAllEvents();

	Set<Time> getTimesOfEvent(Event e);

	Map<Event, Set<Time>> filterByEventCategory(EventCategory category);

	Set<Time> deleteEvent(Event e);

	boolean deleteTime(Event e, Time t);
}
