import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {
	private Map<Event, Set<Time>> catalog;

	public EventCatalogImpl() {
		catalog = new TreeMap<>();
	}


	Event getMatchingEvent(Event e) {
		for (Map.Entry<Event, Set<Time>> mEntry : catalog.entrySet()) {
			if (e.compareTo(mEntry.getKey()) == 0) {
				return mEntry.getKey();
			}
		}
		return null;
	}


	@Override
	public boolean addCatalogEntry(Event e, Set<Time> tSet) {
		if (e == null || tSet == null) {
			throw new NullPointerException();
		}
		for (Time t : tSet) {
			if (t == null) {
				throw new NullPointerException();
			}
		}

		if (getMatchingEvent(e) != null) {
			return false; // event already exists
		}

		catalog.put(e, tSet);
		return true;
	}

	@Override
	public boolean addTimeToEvent(Event e, Time t) {
		if (e == null || t == null) {
			throw new NullPointerException();
		}

		Event mEvent = getMatchingEvent(e);
		if (mEvent == null) {
			return false;
		}

		Set<Time> tSet = catalog.computeIfAbsent(mEvent, k -> new TreeSet<>());
		for (Time t2 : tSet) {
			if (t.equals(t2)) {
				return false;
			}
		}

		tSet.add(t);
		return true;
	}

	@Override
	public Set<Event> getAllEvents() {
		Set<Event> result = new TreeSet<>();
		for (Map.Entry<Event, Set<Time>> mEntry : catalog.entrySet()) {
			result.add(mEntry.getKey());
		}
		return result;
	}

	@Override
	public Set<Time> getTimesOfEvent(Event e) {
		Event mEvent = getMatchingEvent(e);
		if (mEvent == null) {
			return null;
		}
		return catalog.get(mEvent);
	}

	@Override
	public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
		Map<Event, Set<Time>> result = new TreeMap<>();
		for (Map.Entry<Event, Set<Time>> mEntry : catalog.entrySet()) {
			if (category.equals(mEntry.getKey().getCategory())) {
				result.put(mEntry.getKey(), mEntry.getValue());
			}
		}
		return result;
	}

	@Override
	public Set<Time> deleteEvent(Event e) {
		Event mEvent = getMatchingEvent(e);
		if (mEvent == null) {
			return null;
		}

		Set<Time> tSet = catalog.get(mEvent);
		catalog.remove(mEvent);
		return tSet;
	}

	@Override
	public boolean deleteTime(Event e, Time t) {
		if (t == null) {
			throw new NullPointerException();
		}

		Event mEvent = getMatchingEvent(e);
		if (mEvent == null) {
			return false;
		}

		Set<Time> tSet = catalog.get(mEvent);
		if (tSet == null) {
			return false;
		}

		return tSet.remove(t);
	}
}
