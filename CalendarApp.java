import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CalendarApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> eventsMap = new HashMap<>();

        while (true) {
            System.out.println("Calendar Application");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Edit Event");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter event: ");
                    String event = scanner.nextLine();

                    List<String> events = eventsMap.getOrDefault(date, new ArrayList<>());
                    events.add(event);
                    eventsMap.put(date, events);
                    System.out.println("Event added successfully!");
                    break;
                case 2:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    date = scanner.nextLine();
                    List<String> eventsOnDate = eventsMap.get(date);

                    if (eventsOnDate != null) {
                        System.out.println("Events on " + date + ":");
                        for (String e : eventsOnDate) {
                            System.out.println("- " + e);
                        }
                    } else {
                        System.out.println("No events found for " + date);
                    }
                    break;
                case 3:
                    System.out.print("Enter date (yyyy-mm-dd) to edit events: ");
                    date = scanner.nextLine();
                    List<String> existingEvents = eventsMap.get(date);

                    if (existingEvents != null) {
                        System.out.println("Current events on " + date + ":");
                        for (int i = 0; i < existingEvents.size(); i++) {
                            System.out.println(i + 1 + ". " + existingEvents.get(i));
                        }

                        System.out.print("Enter the event number to edit: ");
                        int eventNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (eventNumber >= 1 && eventNumber <= existingEvents.size()) {
                            System.out.print("Enter the updated event: ");
                            String updatedEvent = scanner.nextLine();
                            existingEvents.set(eventNumber - 1, updatedEvent);
                            System.out.println("Event edited successfully!");
                        } else {
                            System.out.println("Invalid event number.");
                        }
                    } else {
                        System.out.println("No events found for " + date);
                    }
                    break;
                case 4:
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
