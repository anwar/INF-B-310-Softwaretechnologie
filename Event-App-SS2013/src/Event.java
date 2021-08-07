public class Event implements Comparable<Event> {
	private String title;
	private EventCategory category;

	public Event(String title, EventCategory category) {
		if (title == null || category == null) {
			throw new NullPointerException();
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException();
		}
		boolean isValidCategory = false;
		for (EventCategory ec : EventCategory.values()) {
			if (category.equals(ec)) {
				isValidCategory = true;
				break;
			}
		}
		if (!isValidCategory) {
			throw new IllegalArgumentException();
		}

		this.title = title;
		this.category = category;
	}

	@Override
	public int compareTo(Event event) {
		int i = title.compareTo(event.getTitle());
		if (i == 0) i = category.compareTo(event.getCategory());
		return i;
	}

	public String getTitle() {
		return title;
	}

	public EventCategory getCategory() {
		return category;
	}
}
