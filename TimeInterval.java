import java.time.LocalTime;

public class TimeInterval {
	private LocalTime start;
	private LocalTime end;
	
	//ctor
	public TimeInterval(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}
	
	//checks if two events overlap
	//@param TimeInterval second
	public boolean overlap(TimeInterval second) {
		if (this.end.isAfter(second.start) || second.end.isAfter(this.start) || (second.start.isAfter(this.start) && second.end.isBefore(this.end)) || (this.start.isAfter(second.start) && this.end.isBefore(second.end))) {
			return true;
		}
		return false;
	}
	
	//returns timeinterval in strong format
	public String toString() {
		return start.toString() + " - " + end.toString();
	}
	
	//returns start time
	public LocalTime getStart() {
		return start;
	}
	
	//returns end time
	public LocalTime getEnd() {
		return end;
	}
	
	
}
