import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.time.DayOfWeek;

public class Event implements Comparable<Object> {
	private String name;
	private TimeInterval time;
	private LocalDate date;

	//ctor
	public Event(String name, TimeInterval time, LocalDate date) {
		this.name = name;
		this.time = time;
		this.date = date;
	}

	//returns date
	public LocalDate getEventLocalDate() {
		return date;
	}
	
	//returns event name
	public String getEventName() {
		return name;
	}
	
	//returns event time 
	public TimeInterval getEventTimeInterval() {
		return time;
	}
	
	//compares events by time
	@Override
	public int compareTo(Object o1) {
		Event other = (Event) o1;
		
		if (other.time.getStart().isBefore(time.getStart())) {
			return 1;
		}
		if (other.time.getStart().isAfter(time.getStart())) {
			return - 1;
		}
		return 0;	
	}
	
}
