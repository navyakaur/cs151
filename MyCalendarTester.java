import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class MyCalendarTester {
	public static void main(String [] args) throws FileNotFoundException
    {
            LocalDate cal = LocalDate.now(); // capture today
            
            MyCalendar calendar = new MyCalendar();
            printCalendar(cal);
            System.out.println("Select one of the following options: [L]oad  [V]iew  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
 
            Scanner sc = new Scanner(System.in);
            Scanner fin = new Scanner(new File("/Users/navyakaur/Documents/cs151/PA1/My First Calendar/src/events.txt"));
            
            prompt(sc, fin, cal, calendar);
            
            fin.close();
            sc.close();
    }
	
	public static void prompt(Scanner sc, Scanner fin, LocalDate cal, MyCalendar calendar) {
		String input = null;
        while (!(input = sc.nextLine()).equals("Q") && (!input.equals("q")))
        {
            if (input.equals("L") || input.equals("l")) {
            	String name = null;
            	String in = null;
            	int i = 0;
            	
            	while (fin.hasNextLine()) {
            		in = fin.nextLine();
            		i++;
            		if (i == 1) {
            			name = in;	
            		}
            		if (i == 2) {
            			
            			String days = null;
            			String startTime = null;
            			String endTime = null;
 
            			//get name, time interval, date
            			//get name, time interval, days
            			
            			String[] splited = in.split(" ");
            			days = splited[0];
            			TimeInterval tInt = convert(splited[1], splited[2]);
            			
            			
            			
            			LocalDate date = null;
            		
            			if (!days.contains("/")) {
            				for (int l = 0; l < days.length(); l++) {
            					date = next(days.substring(l, l + 1));
            					for (int k = 0; k <= 10; k++) {
                     				 Event recurr = new Event(name, tInt, date);
                     				 calendar.addEvent(recurr);
                     				 date = date.plusDays(7);
                     				 
                  				}
            				}

            			}
            			
            			else if (days.contains("/")) {
            				date = convertToDate(days);
            				Event once = new Event(name, tInt, date);
            				calendar.addEvent(once);
            				
            			}
            			i = 0;
            		}
            	}
            	printCalendar(cal);
                System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
                prompt(sc, fin, cal, calendar);
            }
            
            if (input.equals("c") || input.equals("C")) {
    			System.out.print("Title: ");
    			String answer = sc.nextLine();
    			String name = answer;
    			System.out.println();
    			System.out.print("Date [mm/dd/yy]: ");
    			answer = sc.nextLine();
    			String date = answer;
    			System.out.println();
    			System.out.print("Start Time: ");
    			answer = sc.nextLine();
    			String startTime = answer; 
    			System.out.println();
    			System.out.print("End Time: ");
    			answer = sc.nextLine();
    			String endTime = answer;
    			TimeInterval createInt = convert(startTime, endTime);
				LocalDate curr = convertToDate(date);
    			Event creation = new Event(name, createInt, curr);
    			calendar.addEvent(creation);
    			for (int b = 0; b < calendar.get(curr).size() - 1; b++) {
    				if (calendar.get(curr).get(b).getEventTimeInterval().overlap(creation.getEventTimeInterval()) == true) {
    					System.out.println("This event overlaps with " + calendar.get(curr).get(b).getEventName());
    				}
    			}
    			System.out.println("Event successfully created!");
    			System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
    			prompt(sc, fin, cal, calendar);
            }
            
            if (input.equals("G") || input.equals("g")) {
            	System.out.print("Date [mm/dd/yy]: ");
            	String answer = sc.nextLine();
				LocalDate curr = convertToDate(answer);
				calendar.getEventsOnDate(curr);
				System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
				prompt(sc, fin, cal, calendar);
            }
            
            if (input.equals("E") || input.equals("e")) {
            	calendar.sort();
            	System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
            	prompt(sc, fin, cal, calendar);
            }
            
            if (input.equals("D") || input.equals("d")) {
            	System.out.println("[S]elected or [A]ll ? ");
            	String answer = sc.nextLine();
            	if (answer.equals("S") || answer.equals("s")) {
            		System.out.println("Enter the date [dd/mm/yy]");
            		answer = sc.nextLine();
    				LocalDate curr = convertToDate(answer);
    				calendar.getEventsOnDate(curr);
    				
            		System.out.print("Enter the name of the event to delete: ");
            		answer = sc.nextLine();
            		calendar.delete(curr, answer);
            		System.out.println("The event is deleted. Here are the current scheduled event(s): ");
            		
    				String pattern = "MM/dd/yyyy";
    				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
    				System.out.println(" " + dateFormat.format(curr));
            		calendar.getEventsOnDate(curr);
            		System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
            		prompt(sc, fin, cal, calendar);
            	}
            	else if (answer.equals("A") || answer.equals("a")) {
            		calendar.deleteAll();
            		System.out.println("All events are successfully deleted.");
            		System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
            		prompt(sc, fin, cal, calendar);
            	}
            }
            
        		if (input.equals("V") || input.equals("v")) {
        			System.out.println("[D]ay view or [M]view ?");
        			String answer = sc.nextLine();
        			if (answer.equals("M") || answer.equals("m")) {
        				printEventCalendar(cal, calendar);
        				System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
        				String second = sc.nextLine();
	                    
        				while (second.equals("p") || second.equals("P"))
	                    {
	                            cal = cal.minusMonths(1); // LocalDateTime is immutable
	                            printCalendar(cal);
	                            System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
	            				second = sc.nextLine();
	                            
	                    }
	                    while (second.equals("n") || second.equals("N"))
	                    {
	                            cal = cal.plusMonths(1); // LocalDateTime is immutable
	                            printCalendar(cal);
	                            System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
	            				second = sc.nextLine();
	                            
	                    }
	                    if (second.equals("G") || second.equals("g")) {
	                    	printCalendar(cal);
	                    	System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
	                    	prompt(sc, fin, cal, calendar);
	                    }
        				prompt(sc, fin, cal, calendar);
        			}
        			else if (answer.equals("D") || answer.equals("d")) {
        				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy");
        				System.out.println(" " + formatter.format(cal));
        				
        				calendar.getEventsOnDate(cal);
        				System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
        				String second = sc.nextLine();
        				while (second.equals("p") || second.equals("P"))
	                    {
	                            cal = cal.minusDays(1); // LocalDateTime is immutable
	                            printDate(cal);
	                            calendar.getEventsOnDate(cal);
	            				System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
	            				second = sc.nextLine();
	                    }
	                    while (second.equals("n") || second.equals("N"))
	                    {
	                            cal = cal.plusDays(1); // LocalDateTime is immutable
	                            printDate(cal);
	                            calendar.getEventsOnDate(cal);
	            				System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
	            				second = sc.nextLine();
	                            
	                    }
	                    if (second.equals("G") || second.equals("g")) {
	                    	printCalendar(cal);
	                    	System.out.println("Select one of the following options: [L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
	                    	prompt(sc, fin, cal, calendar);
	                    }
	                    
        			}
        		}
        		
        		
        }
        System.out.println("Goodbye!");
	}
	
	
	//prints the date in a specific E, MMM d, yyyy format
	//@param LocalDate c
	public static void printDate(LocalDate c) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy");
		System.out.println(" " + formatter.format(c));
	}
	
	//finds the next closest day of the week date using String input
	//@param String x
	public static LocalDate next(String x) {
		int val = 0;
		
		if (x.contains("M")) {
			val = 1;
		}
		if (x.contains("T")) {
			val = 2;
		}
		if (x.contains("W")) {
			val = 3;
		}
		if (x.contains("R")) {
			val = 4;
		}
		if (x.contains("F")) {
			val = 5;
		}
		if (x.contains("A")) {
			val = 6;
		}
		
		LocalDate curr = LocalDate.now();
		int currVal = curr.getDayOfWeek().getValue();
		
		int date = curr.getDayOfMonth() + (7 - (currVal - val));
		return LocalDate.of(curr.getYear(), curr.getMonthValue(), date);
	}
	
	//convert String to Timeinterval
	//@param String begin, String end
	public static TimeInterval convert(String begin, String end) {
			String[] time1 = begin.split(":");
			String[] time2 = end.split(":");
			String hour1 = time1[0];
			String min1 = time1[1];
			String hour2 = time2[0];
			String min2 = time2[1];
			LocalTime start = LocalTime.of(Integer.parseInt(hour1), Integer.parseInt(min1));
			LocalTime last = LocalTime.of(Integer.parseInt(hour2), Integer.parseInt(min2));
			return new TimeInterval(start, last);
	}
	
	//converts string to LocalDate
	//@param String answer
	public static LocalDate convertToDate(String answer) {
		String[] actualDate = answer.split("/");
		int year = Integer.parseInt(actualDate[2]) + 2000;
		int dayOfMonth = Integer.parseInt(actualDate[1]);
		int month = Integer.parseInt(actualDate[0]);
		LocalDate curr = LocalDate.of(year, month, dayOfMonth);
		return curr;
	}
	
	//prints the calendar with { } around days that have events
	//@param LocalDate c, MyCalendar calendar
	public static void printEventCalendar(LocalDate c, MyCalendar calendar)
    {  
        
    	System.out.print(c.getMonth() + " ");
    	System.out.println(c.getYear());
     
        LocalDate firstDay = c.minusDays((c.getDayOfMonth() - 1));
        
        System.out.println("Su Mo Tu We Th Fr Sa");
       
        int spaces = firstDay.getDayOfWeek().getValue();
        for (int i = 1; i <= spaces; i++) {
        	System.out.print("   ");
        }
        
        System.out.print(" 1  ");
        
        LocalDate update = firstDay;
        
        for (int j = 2; j <= firstDay.lengthOfMonth(); j++) {
        	
        	if (update.getDayOfWeek().getValue() == 6) {
        		System.out.println();
        	}
        	update = LocalDate.of(update.getYear(), update.getMonth(), j);
        	if (calendar.get(update) != null) {
        		
        		System.out.print("{" + j + "}");
        	}
        	else if (j >=10) {
        		System.out.print(j + " ");
        	}
        	else {
        		System.out.print(" " + j + " ");
        	}
        }
        
        System.out.println();
        System.out.println();
    }	
	
	//Prints calendar with [ ] around today's date
	//@param LocalDate c
    public static void printCalendar(LocalDate c)
    {  
        
    	System.out.print(c.getMonth() + " ");
    	System.out.println(c.getYear());
     
        LocalDate firstDay = c.minusDays((c.getDayOfMonth() - 1));
        
        System.out.println("Su Mo Tu We Th Fr Sa");
       
        int spaces = firstDay.getDayOfWeek().getValue();
        for (int i = 1; i <= spaces; i++) {
        	System.out.print("   ");
        }
        
        System.out.print(" 1  ");
        
        LocalDate update = firstDay;
        
        for (int j = 2; j <= firstDay.lengthOfMonth(); j++) {
        	
        	if (update.getDayOfWeek().getValue() == 6) {
        		System.out.println();
        	}
        	update = LocalDate.of(update.getYear(), update.getMonth(), j);
        	if (update.getDayOfMonth() == c.getDayOfMonth()) {
        		
        		System.out.print("[" + j + "]");
        	}
        	else if (j >=10) {
        		System.out.print(j + " ");
        	}
        	else {
        		System.out.print(" " + j + " ");
        	}
        }
        
        System.out.println();
        System.out.println();
    }
}
