import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


abstract class Event {
    private String date;
    private String description;

    public Event(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // You can add more methods or abstract methods specific to events here.
}

class SimpleEvent extends Event {
    public SimpleEvent(String date, String description) {
        super(date, description);
    }

    // Implement any specific methods or behavior for simple events here.
}

public class CalendarApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Event>> eventsMap = new HashMap();

        while (true) {
            System.out.println("Calendar Application");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Edit Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateStr = scanner.nextLine();

                    if (isValidDateFormat(dateStr)) {
                        System.out.print("Enter event description: ");
                        String description = scanner.nextLine();

                        Event event = new SimpleEvent(dateStr, description);

                        List<Event> events = eventsMap.getOrDefault(dateStr, new ArrayList<>());
                        events.add(event);
                        eventsMap.put(dateStr, events);
                        System.out.println("Event added successfully!");
                    } else {
                        System.out.println("Invalid date format. Use yyyy-MM-dd.");
                    }
                    break;
                case 2:
                    System.out.print("Enter date (yyyy-MM-dd) to view events: ");
                    String viewDate = scanner.nextLine();
                    List<Event> viewEvents = eventsMap.get(viewDate);

                    if (viewEvents != null) {
                        System.out.println("Events on " + viewDate + ":");
                        for (Event event : viewEvents) {
                            System.out.println("- " + event.getDescription());
                        }
                    } else {
                        System.out.println("No events found for " + viewDate);
                    }
                    break;
                case 3:
                     System.out.print("Enter date (yyyy-MM-dd) to edit events: ");
                    String editDate = scanner.nextLine();
                    List<Event> editEvents = eventsMap.get(editDate);

                    if (editEvents != null) {
                        System.out.println("Current events on " + editDate + ":");
                        for (int i = 0; i < editEvents.size(); i++) {
                            System.out.println((i + 1) + ". " + editEvents.get(i).getDescription());
                        }

                        System.out.print("Enter the event number to edit: ");
                        int eventNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (eventNumber >= 1 && eventNumber <= editEvents.size()) {
                            System.out.print("Enter the updated event: ");
                            String updatedEvent = scanner.nextLine();
                            editEvents.get(eventNumber - 1).setDescription(updatedEvent);
                            System.out.println("Event edited successfully!");
                        } else {
                            System.out.println("Invalid event number.");
                        }
                    } else {
                        System.out.println("No events found for " + editDate);
                    }
                    break;
                case 4:
                
                    System.out.print("Enter date (yyyy-MM-dd) to delete events: ");
                    String deleteDate = scanner.nextLine();
                    List<Event> deleteEvents = eventsMap.get(deleteDate);

                    if (deleteEvents != null) {
                        System.out.println("Current events on " + deleteDate + ":");
                        for (int i = 0; i < deleteEvents.size(); i++) {
                            System.out.println((i + 1) + ". " + deleteEvents.get(i).getDescription());
                        }

                        System.out.print("Enter the event number to delete: ");
                        int eventNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (eventNumber >= 1 && eventNumber <= deleteEvents.size()) {
                            deleteEvents.remove(eventNumber - 1);
                            System.out.println("Event deleted successfully!");
                        } else {
                            System.out.println("Invalid event number.");
                        }
                    } else {
                        System.out.println("No events found for " + deleteDate);
                    }
                    break;
                case 5:
                    System.out.println("Exiting Calendar Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

    private static boolean isValidDateFormat(String date) {
        if (date.length() != 10) {
            return false;
        }
        String[] parts = date.split("-");
        if (parts.length != 3) {
            return false;
        }
        if (parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
            return false;
        }
        for (char c : date.toCharArray()) {
            if (c != '-' && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}