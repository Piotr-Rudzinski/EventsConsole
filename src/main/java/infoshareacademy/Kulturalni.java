package infoshareacademy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kulturalni {
    EventsDB eventsDB;
    Configuration configuration;
    Favourities favourities;
    Reservations reservations;
    BoughtTickets boughtTickets;
    Integer identifier;
    String lackOfParameters = "Niewstarczająca liczba parametrów.";

    public static void main(String[] args) {
        new Kulturalni().doJob();
    }

    public void doJob() {
        configuration = new Configuration();
        configuration.readConfiguration();
        favourities = new Favourities();
        reservations = new Reservations();
        boughtTickets = new BoughtTickets();

        eventsDB = new EventsDB();
        eventsDB.readEvent();

        favourities.readFavourities();
        reservations.readReservations();
        boughtTickets.readBoughtTickets();

/*        while (true) {

 // Wywołanie menu z parametrami tekstowymi
 //           String[] command = displayPrompt();
 //           parseCommands(command, eventsDB);

 // Wywołanie menu tekstowego

            //parseCommands(command, eventsDB);

        }*/

        List<String> menu = createMenu();
        Integer choiceMenuLevel1;
        Integer choiceMenuLevel2;
        Integer choice;
        String choiceString;
        boolean programmNotEnded = true;


        while (programmNotEnded) {

            String menuChoice = "01";
            Integer menuLevel = 1;

            choiceMenuLevel1 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
            clearScreen();

            switch (choiceMenuLevel1) {
                case 1:
                    menuLevel = 2;
                    menuChoice = "10";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case 1:
                            clearScreen();
                            eventsDB.setAllEventsToDisplay();
                            eventsDB.sortByConfiguration(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 2:
                            menuLevel = 2;
                            menuChoice = "15";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (eventsDB.isEvent(choice)) {
                                    clearScreen();
                                    eventsDB.displaySingleEvent(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                            break;
                        case 8:
                            choice = 0;
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    menuLevel = 2;
                    menuChoice = "20";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case 1:
                            eventsDB.setAllFavouritiesToDisplay(favourities);
                            eventsDB.displayHeaderFavourite();
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 2:
                            menuLevel = 2;
                            menuChoice = "25";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (eventsDB.isEvent(choice)) {
                                    favourities.addFavourite(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                            break;
                        case 3:
                            menuLevel = 2;
                            menuChoice = "26";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (eventsDB.isEvent(choice)) {
                                    favourities.removeFavourite(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                             break;
                        case 8:
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    menuLevel = 2;
                    menuChoice = "30";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case 1:
                            eventsDB.setAllEventsToDisplay();
                            eventsDB.sortByID(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 2:
                            eventsDB.setAllEventsToDisplay();
                            eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 3:
                            eventsDB.setAllEventsToDisplay();
                            eventsDB.sortByOrganizer(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 8:
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    menuLevel = 2;
                    menuChoice = "40";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case 1:
                            menuLevel = 2;
                            menuChoice = "43";
                            choiceString = getChoiceString(menu, menuLevel, menuChoice, "    Podaj nazwę wydarzenia: ");
                            eventsDB.setFoundEventsToDisplay("-NAME", choiceString);
                            eventsDB.sortByID(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 2:
                            menuLevel = 2;
                            menuChoice = "45";
                            String eventDateString = getChoiceDate(menu, menuLevel, menuChoice, "    Podaj datę wydarzenia (yyyy-MM-dd): ", "    Niepoprawny format. Podaj datę początkową (yyyy-MM-dd): ");
                            eventsDB.setFoundEventsToDisplay("-DATE", eventDateString);
                            eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 3:
                            menuLevel = 2;
                            menuChoice = "47";
                            choiceString = getChoiceString(menu, menuLevel, menuChoice, "    Podaj nazwę organizatora: ");
                            eventsDB.setFoundEventsToDisplay("-ORG", choiceString);
                            eventsDB.sortByOrganizer(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case 8:
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    menuLevel = 2;
                    menuChoice = "50";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case (1):
                            menuLevel = 2;
                            menuChoice = "53";
                            String choiceStartDateString = getChoiceDate(menu, menuLevel, menuChoice, "    Podaj datę początkową (yyyy-MM-dd): ", "    Niepoprawny format daty. Podaj datę początkową: ");
                            String choiceStopDateString = getChoiceDate(menu, menuLevel, menuChoice, "    Podaj datę końcową (yyyy-MM-dd): ", "    Niepoprawny format daty. Podaj datę końcową: ");
                            LocalDate startDateLocalDate = LocalDate.parse(choiceStartDateString);
                            LocalDate endDateLocalDate = LocalDate.parse(choiceStopDateString);
                            if (startDateLocalDate.isAfter(endDateLocalDate)) {
                                startDateLocalDate = LocalDate.parse(choiceStopDateString);
                                endDateLocalDate = LocalDate.parse(choiceStartDateString);
                            }
                            eventsDB.setFilterByDate(startDateLocalDate, endDateLocalDate);
                            eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case (2):
                            menuLevel = 2;
                            menuChoice = "55";
                            choiceString = getChoiceString(menu, menuLevel, menuChoice, "    Podaj nazwę organizatora: ");
                            eventsDB.setFilterByName(choiceString);
                            eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                            eventsDB.displayHeaderAll(configuration.getDateFormat());
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        default:
                            break;
                    }
                    choice = 0;
                    break;
                case 6:
                    menuLevel = 2;
                    menuChoice = "60";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case (1):
                            eventsDB.setAllReservationsToDisplay(reservations);
                            eventsDB.displayHeaderReservations();
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case (2):
                            menuLevel = 2;
                            menuChoice = "65";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (eventsDB.isEvent(choice)) {
                                    reservations.addReservation(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                            break;
                        case (3):
                            menuLevel = 2;
                            menuChoice = "67";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (reservations.isReservation(choice)) {
                                    reservations.removeReservation(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                            break;
                        case 8:
                            break;
                    }
                    break;
                case 7:
                    menuLevel = 2;
                    menuChoice = "70";
                    choiceMenuLevel2 = getChoice(menu, menuLevel, menuChoice, "    Podaj co chcesz zrobić: ", "    Niepoprawna wartość. Podaj co chcesz zrobić: ");
                    clearScreen();
                    switch (choiceMenuLevel2) {
                        case (1):
                            eventsDB.setAllBoughtTicketsToDisplay(boughtTickets);
                            eventsDB.displayHeaderBoughtTickets();
                            eventsDB.displayEvents(configuration.getDateFormat());
                            displayReturnMessage();
                            readAnyKey();
                            break;
                        case (2):
                            menuLevel = 2;
                            menuChoice = "75";
                            choice = getChoice(menu, menuLevel, menuChoice, "    Podaj ID wydarzenia: ", "    Niepoprawna wartość. Podaj ID wydarzenia: ");
                            if (choice != 8) {
                                if (eventsDB.isEvent(choice)) {
                                    boughtTickets.addBoughtTicket(choice);
                                } else {
                                    System.out.println("    Nie ma wydarzenia o takim ID.");
                                }
                                displayReturnMessage();
                                readAnyKey();
                            }
                            break;
                        case 8:
                            break;
                    }
                    break;
                case 9:
                    programmNotEnded = false;
                    break;
                default:
                    break;

            }
        }
    }

    public static Integer getChoice (List<String> menu, Integer menuLevel, String menuChoice, String message, String errorMessage) {
        Integer menuRepeatitionCounter = 0;
        boolean isMenuChoiceNotValid = true;
        Integer choice = 0;

        while (isMenuChoiceNotValid) {

            displayMenu(menu, menuLevel, menuChoice);
            if (menuRepeatitionCounter.equals(0)) {
                displayMenuSelectionText(message);
            } else {
                displayMenuSelectionNotValidText(errorMessage);
            }

            try {
                isMenuChoiceNotValid = false;
                choice = getMenuIntegerChoice();
            } catch (Exception e) {
                isMenuChoiceNotValid = true;
                menuRepeatitionCounter++;
            }
        }
        return choice;
    }

    public static String getChoiceString (List<String> menu, Integer menuLevel, String menuChoice, String message) {
        displayMenu(menu, menuLevel, menuChoice);
        displayMenuSelectionText(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String getChoiceDate (List<String> menu, Integer menuLevel, String menuChoice, String message, String errorMessage) {
        Integer menuRepeatitionCounter = 0;
        boolean isMenuChoiceNotValid = true;
        String date = "";
        Scanner scanner = new Scanner(System.in);

        while (isMenuChoiceNotValid) {

            displayMenu(menu, menuLevel, menuChoice);
            if (menuRepeatitionCounter.equals(0)) {
                displayMenuSelectionText(message);
            } else {
                displayMenuSelectionNotValidText(errorMessage);
            }

            date = scanner.nextLine();
            if (isDateValid(date)) {
                isMenuChoiceNotValid = false;
            } else {
                isMenuChoiceNotValid = true;
                menuRepeatitionCounter++;
            }
        }
        return date;
    }

    public void displayReturnMessage() {
        System.out.println();
        System.out.print("    Naciśnij dowolny klawisz aby powrócić do menu głównego. ");
    }

    private void readAnyKey () {
        Scanner scanner = new Scanner(System.in);
        String anyKey = scanner.nextLine();
    }

    public static void displayMenu(List<String> menu, Integer menuLevel, String menuChoice) {
        menu.stream().map(s -> s.split(";"))
            .filter(s -> (s[menuLevel - 1].equals(menuChoice) || s[menuLevel - 1].equals("99")))
            .forEach(s -> System.out.println(s[2]));
    }

    public static void displayMenuSelectionText (String message) {
        System.out.print(message);
    }

    public static void displayMenuSelectionNotValidText (String errorMessage) {
        System.out.print(errorMessage);
    }

    public static Integer getMenuIntegerChoice () throws Exception {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static boolean isDateValid (String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateValidatorUsingLocalDate dateValidatorUsingLocalDate = new DateValidatorUsingLocalDate(dateTimeFormatter);

        return (dateValidatorUsingLocalDate.isValid(date));
    }

    public static List<String> createMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("99;99;********************************************************************");
        menu.add("99;99;*                                                                  *");
        menu.add("99;99;*   SYSTEM REZERWACJI BILETÓW NA TRÓJMIEJSKIE IMPREZY KULTURALNE   *");
        menu.add("99;99;*                                                                  *");
        menu.add("99;99;********************************************************************");
        menu.add("99;00;    MENU                                                            ");
        menu.add("00;10;    MENU / WYŚWIETLANIE WYDARZEŃ                                    ");
        menu.add("00;15;    MENU / WYŚWIETLANIE WYDARZEŃ / INFORMACJA O WYDARZENIU          ");
        menu.add("00;20;    MENU / ULUBIONE WYDARZENIA                                      ");
        menu.add("00;25;    MENU / ULUBIONE WYDARZENIA / DODAWANIE ULUBIONEGO WYDARZENIA    ");
        menu.add("00;26;    MENU / ULUBIONE WYDARZENIA / USUWANIE ULUBIONEGO WYDARZENIA     ");
        menu.add("00;30;    MENU / SORTOWANIE WYDARZEŃ                                      ");
        menu.add("00;33;    MENU / SORTOWANIE WYDARZEŃ / SORTOWANIE PO NUMERZE WYDARZENIA   ");
        menu.add("00;35;    MENU / SORTOWANIE WYDARZEŃ / SORTOWANIE PO DACIE WYDARZENIA     ");
        menu.add("00;37;    MENU / SORTOWANIE WYDARZEŃ / SORTOWANIE PO NAZWIE WYDARZENIA    ");
        menu.add("00;40;    MENU / WYSZUKIWANIE WYDARZEŃ                                    ");
        menu.add("00;43;    MENU / WYSZUKIWANIE WYDARZEŃ / WYSZUKIWANIE PO NAZWIE           ");
        menu.add("00;45;    MENU / WYSZUKIWANIE WYDARZEŃ / WYSZUKIWANIE PO DACIE            ");
        menu.add("00;47;    MENU / WYSZUKIWANIE WYDARZEŃ / WYSZUKIWANIE PO ORGANIZATORZE    ");
        menu.add("00;50;    MENU / FILTROWANIE WYDARZEŃ                                     ");
        menu.add("00;53;    MENU / FILTROWANIE WYDARZEŃ / FILTROWANIE PO DACIE              ");
        menu.add("00;55;    MENU / FILTROWANIE WYDARZEŃ / FILTROWANIE PO ORGANIZATORZE      ");
        menu.add("00;60;    MENU / REZERWACJE BILETÓW                                       ");
        menu.add("00;63;    MENU / REZERWACJE BILETÓW / WYŚWIETLANIE REZERWACJI             ");
        menu.add("00;65;    MENU / REZERWACJE BILETÓW / REZERWACJA BILETÓW                  ");
        menu.add("00;67;    MENU / REZERWACJE BILETÓW / USUNIĘCIE REZERWACJI                ");
        menu.add("00;70;    MENU / ZAKUPIONE BILETY                                         ");
        menu.add("00;73;    MENU / ZAKUPIONE BILETY / WYŚWIETLANIE ZAKUPIONYCH BILETÓW      ");
        menu.add("00;75;    MENU / ZAKUPIONE BILETY / ZAKUP BILETÓW                         ");
        menu.add("99;99;                                                                    ");
        menu.add("01;00;    1. Wyświetlanie wydarzeń                                        ");
        menu.add("00;10;       1. Wyświetl wszystkie wydarzenia                             ");
        menu.add("00;10;       2. Wyświetl informacje o wydarzeniu                          ");
        menu.add("01;00;    2. Ulubione wydarzenia                                          ");
        menu.add("00;20;       1. Wyświetl wszystkie wydarzenia z ulubionych                ");
        menu.add("00;20;       2. Dodaj wydarzenie do ulubionych                            ");
        menu.add("00;20;       3. Usuń wydarzenie z ulubionych                              ");
        menu.add("01;00;    3. Sortowanie wydarzeń                                          ");
        menu.add("00;30;       1. Sortowanie po numerze wydarzenia                          ");
        menu.add("00;30;       2. Sortowanie po dacie wydarzenia                            ");
        menu.add("00;30;       3. Sortowanie po nazwie wydarzenia                           ");
        menu.add("01;00;    4. Wyszukiwanie wydarzeń                                        ");
        menu.add("00;40;       1. Wyszukiwanie po nazwie wydarzenia                         ");
        menu.add("00;40;       2. Wyszukiwanie po dacie wydarzenia                          ");
        menu.add("00;40;       3. Wyszukiwanie po organizatorze wydarzenia                  ");
        menu.add("01;00;    5. Filtrowanie wydarzeń                                         ");
        menu.add("00;50;       1. Filtrowanie po dacie wydarzenia                           ");
        menu.add("00;50;       2. Filtrowanie po organizatorze wydarzenia                   ");
        menu.add("01;00;    6. Rezerwacje biletów                                           ");
        menu.add("00;60;       1. Wyświetl rezerwacje                                       ");
        menu.add("00;60;       2. Zarezerwuj bilet                                          ");
        menu.add("00;60;       3. Odwołaj rezerwację                                        ");
        menu.add("01;00;    7. Zakup biletów                                                ");
        menu.add("00;70;       1. Wyświetl wszystkie zakupione bilety                       ");
        menu.add("00;70;       2. Kup bilet                                                 ");
        menu.add("80;99;                                                                    ");
        menu.add("80;99;    8. Powrót                                                       ");
        menu.add("01;00;                                                                    ");
        menu.add("01;00;    9. Koniec programu                                              ");
        menu.add("99;99;********************************************************************");
        //menu.add("99;99;    Podaj co chcesz zrobić: ");
        //menu.add("99;99;********************************************************************");
        return menu;
    }

    public void clearScreen(){
        //Clears Screen in java

        Process process;
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (IOException | InterruptedException ex) {}

    }


















//---------------------------------------------------------------------------------------

    public String[] displayPrompt() {
        String[] command;
        System.out.println();
        System.out.print("Podaj polecenie: Kulturalni ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.toUpperCase();
        command = input.split(" ");

        return command;
    }

    public void parseCommands (String[] args, EventsDB eventsDB) {
         if (args.length > 0) {
            switch (args[0]) {
                case "-SHOW":
                    if (args.length > 1) {
                        switch (args[1]) {
                            case "-ALL":
                                eventsDB.setAllEventsToDisplay();
                                eventsDB.sortByConfiguration(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-ID":
                                if (args.length > 2) {
                                    try {
                                        Integer ident = Math.abs(Integer.parseInt(args[2]));
                                        eventsDB.displaySingleEvent(ident);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Podaj właściwy ID wydarzenia.");
                                    }
                                } else {
                                    System.out.println(lackOfParameters);
                                }
                                break;
                            default:
                                displayHelp();
                                break;
                        }
                    } else {
                        System.out.println(lackOfParameters);
                    }
                    break;
                case "-FAV":
                    if (args.length > 1) {
                        switch (args[1]) {
                            case "-ALL":
                                eventsDB.setAllFavouritiesToDisplay(favourities);
                                eventsDB.displayHeaderFavourite();
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-ADD":
                                if (args.length > 2) {
                                    if (parseThirdParameter(args[2])) {
                                        favourities.addFavourite(identifier);
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println(lackOfParameters);
                                }
                                break;
                            case "-DELETE":
                                if (args.length > 2) {
                                    if (parseThirdParameter(args[2])) {
                                        favourities.removeFavourite(identifier);
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println(lackOfParameters);
                                }
                                break;
                            default:
                                displayHelp();
                                break;
                        }
                    } else {
                        System.out.println(lackOfParameters);
                    }
                    break;
                case "-SORT":
                    if (args.length > 1) {
                        switch (args[1]) {
                            case "-ID":
                                eventsDB.setAllEventsToDisplay();
                                eventsDB.sortByID(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-DATE":
                                eventsDB.setAllEventsToDisplay();
                                eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-NAME":
                                eventsDB.setAllEventsToDisplay();
                                eventsDB.sortByOrganizer(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            default:
                                displayHelp();
                                break;
                        }
                    } else {
                        System.out.println(lackOfParameters);
                    }
                    break;
                case "-SEARCH":
                    if (args.length > 2) {
                        switch (args[1]) {
                            case "-ORG":
                                eventsDB.setFoundEventsToDisplay(args[1], args[2]);
                                eventsDB.sortByID(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-DATE":
                                eventsDB.setFoundEventsToDisplay(args[1], args[2]);
                                eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            case "-NAME":
                                eventsDB.setFoundEventsToDisplay(args[1], args[2]);
                                eventsDB.sortByOrganizer(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());
                                break;
                            default:
                                displayHelp();
                                break;
                        }
                    } else {
                        System.out.println(lackOfParameters);
                    }
                    break;
                case "-FILTER":
                    if (args.length > 2) {
                        switch (args[1]) {
                            case "-NAME":
/*                                eventsDB.setFilterByName(args);
                                eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                                eventsDB.displayHeaderAll(configuration.getDateFormat());
                                eventsDB.displayEvents(configuration.getDateFormat());*/
                                break;
                            case "-DATE":
/*                                if (eventsDB.setFilterByDate(args)) {
                                    eventsDB.sortByDate(configuration.getDirection(), configuration.getSortParameter());
                                    eventsDB.displayHeaderAll(configuration.getDateFormat());
                                    eventsDB.displayEvents(configuration.getDateFormat());
                                }*/
                                break;
                            default:
                                displayHelp();
                                break;
                        }
                    } else {
                        System.out.println(lackOfParameters);
                    }
                    break;
                default:
                    displayHelp();
                    break;
            }
         } else {
            displayHelp();
         }
    }




/*    public static String[] getMainMenu() {
        String[] mainMenu = new String[6];
        mainMenu[0] = "";



    }*/




    public static void displayHelp() {
        System.out.println("Kulturalni help.");
        System.out.println();
        System.out.println("Commands description:");
        System.out.println("kulturalni -show -id -<event id>                        -> displays one event denoted by id. Example: kulturalni -show 12345");
        System.out.println("kulturalni -show -all                                   -> displays all events");
        System.out.println("kulturalni -fav -all                                    -> displays all favourite events");
        System.out.println("kulturalni -fav -add -<event id>                        -> adds event to favourities");
        System.out.println("kulturalni -fav -delete -<event id>                     -> removes event from favourities");
        System.out.println("kulturalni -sort -id                                    -> sorts events by id");
        System.out.println("kulturalni -sort -date                                  -> sorts events by date");
        System.out.println("kulturalni -sort -name                                  -> sorts events by name");
        System.out.println("kulturalni -search -org -<\"organizer name\">           -> searches organiser");
        System.out.println("kulturalni -search -date -<start date> -<end date>      -> searches date");
        System.out.println("kulturalni -search -name -<event name>                  -> searches name");
        System.out.println("kulturalni -filter -name -<\"organizer name\">          -> searches name");
        System.out.println("kulturalni -filter -date -<start date> -<end date>      -> searches date");
        System.out.println();
    }

    public boolean parseThirdParameter(String parameter) {
        try {
            identifier = Math.abs(Integer.parseInt(parameter));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Podaj właściwy ID wydarzenia.");
            return false;
        }
    }
}
