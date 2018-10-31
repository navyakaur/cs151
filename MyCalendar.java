import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.time.format.DateTimeFormatter;

public class MyCalendar {

	private TreeMap<LocalDate, ArrayList<Event>> calendar;
	
	//ctor
	public MyCalendar() {
		calendar = new TreeMap<LocalDate, ArrayList<Event>>();
		
	}
	
	
	// Adds an event to the end of the ArrayList of events for a particular date.
	// @param Event x
	public void addEvent(Event x) {
		calendar.putIfAbsent(x.getEventLocalDate(), new ArrayList<Event>());
		calendar.get(x.getEventLocalDate()).add(x);
	}
	
	// Sorts the HashMap by extracting the keySet and sorting the local dates
	public void sort() {
		Set<LocalDate> vals = calendar.keySet();
		for (LocalDate dates : vals) {
			String pattern = "E, MMM d, yyyy";
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
			System.out.println(" " + dateFormat.format(dates));
			Collections.sort(calendar.get(dates));
			if (calendar.get(dates) != null) {
				for (int i = 0; i < calendar.get(dates).size(); i++) {
					System.out.println(calendar.get(dates).get(i).getEventName() + ": " + calendar.get(dates).get(i).getEventTimeInterval().toString());
				}
			}
			System.out.println();
		}
	}
	
	// Accesses the ArrayList of events for a particular date
	// @param LocalDate x
	public ArrayList<Event> get(LocalDate x) {
		return calendar.get(x);
	}
	
	// Gets and prints all the events on a particular date.
	// @param LocalDate x
	public void getEventsOnDate(LocalDate x) {
		if (calendar.get(x) != null) {
			for (int i = 0; i < calendar.get(x).size(); i++) {
				System.out.println(calendar.get(x).get(i).getEventName() + ": " + calendar.get(x).get(i).getEventTimeInterval().toString());
			}
		}
		else {
		 System.out.println("There are none.");
		}
	}
	
	//returns size of hashmap
	public int getSize() {
		return calendar.size();
	}
	
	//deletes one event from the calendar
	//@param LocalDate l, String name
	public void delete(LocalDate l, String name) {
		for (int w = 0; w < calendar.get(l).size(); w++) {
			if (calendar.get(l).get(w).getEventName().equals(name)) {
				calendar.get(l).remove(w);
			}
		}
	}
	
	//deletes all events in the calendar
	public void deleteAll() {
		calendar.clear();
	}
	
}
