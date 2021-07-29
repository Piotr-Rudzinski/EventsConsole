package infoshareacademy;

import com.google.gson.Gson;
import events.EventJson;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventsDB {
    private List<Event> eventsDB = new ArrayList<>();
    private Path path = Paths.get("src", "main", "resources", "data.json");

    public void readEvent() {
        //boolean stateFlag = false
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader(String.valueOf(path));
            EventJson[] eventJson = gson.fromJson(reader, EventJson[].class);

            for (int i = 0; i < eventJson.length; i++) {
                Event event = new Event(eventJson[i]);
                eventsDB.add(event);
                //stateFlag = true;
            }


/*//            FileReader readerJson = new FileReader(String.valueOf(path));
            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();
            List<Event> eventsDB = new Gson().fromJson(reader, listType);

*//*
            System.out.println(eventsJsonDB.get(0).getName());
            System.out.println(eventsJsonDB.get(eventsJsonDB.size()-1).getName());
*/


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Błąd odczytu pliku .json.");
        }
        //return stateFlag;
    }

    public void displaySingleEvent(Integer id) {
        Integer eventPosition=0;

        for (int i = 0; i < eventsDB.size(); i++) {
            if (eventsDB.get(i).getEventJson().getID().equals(id)) {
                eventsDB.get(i).setDisplay(1);
                eventPosition = i;
            } else {
                eventsDB.get(i).setDisplay(0);
            }
        }

        displayHeaderOne();

        System.out.println("         ID: " + String.valueOf(eventsDB.get(eventPosition).getEventJson().getID()));
        System.out.println("      Nazwa: " + eventsDB.get(eventPosition).getEventJson().getName());
        String time = eventsDB.get(eventPosition).getEventJson().getStartDate();
        String[] arrOfStr = time.split("T");
        System.out.println("     Termin: " + arrOfStr[0]);
        System.out.println("    Godzina: " + arrOfStr[1]);
        System.out.println("    Miejsce: " + eventsDB.get(eventPosition).getEventJson().getPlace().getName());
        System.out.print  ("       Opis: ");
        String descShort = eventsDB.get(eventPosition).getEventJson().getDescShort();
        char[] descriptionS = descShort.toCharArray();
        for (int i = 0; i < descriptionS.length; i++) {
            if ((i+1)%105 != 0) {
                System.out.print(descriptionS[i]);
            } else {
                System.out.print(descriptionS[i]);
                System.out.println();
                System.out.print("           : ");
            }
        }
        System.out.println("Organizator: " + eventsDB.get(eventPosition).getEventJson().getOrganizer().getName());
        System.out.println("  Adres WWW: " + eventsDB.get(eventPosition).getEventJson().getUrls().getWww());
        System.out.println("  Załącznik: " + eventsDB.get(eventPosition).getEventJson().getAttachment().getFileName());
        String status = eventsDB.get(eventPosition).getEventJson().getActive();
        if (status.equals("1")) {
            status = "Aktywny";
        } else { status = "Odwołane";}
        System.out.println("     Status: " + status);
        String bilety = eventsDB.get(eventPosition).getEventJson().getTickets().getType();
        if (bilety.equals("unknown")) {
            bilety = "Brak danych";
        }
        System.out.println("     Bilety: " + bilety);
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }


    public void setAllEventsToDisplay() {
        for (int i = 0; i < eventsDB.size(); i++) {
            eventsDB.get(i).setDisplay(1);
        }
    }

    public void setAllFavouritiesToDisplay(Favourities favourities) {
        Integer id;

        for (int i = 0; i < eventsDB.size(); i++) {
            id = eventsDB.get(i).getEventJson().getID();

            if (favourities.isFavourite(id)) {
                eventsDB.get(i).setDisplay(1);
            } else {
                eventsDB.get(i).setDisplay(0);
            }
        }
    }

    public void setAllReservationsToDisplay(Reservations reservations) {
        Integer id;

        for (int i = 0; i < eventsDB.size(); i++) {
            id = eventsDB.get(i).getEventJson().getID();

            if (reservations.isReservation(id)) {
                eventsDB.get(i).setDisplay(1);
            } else {
                eventsDB.get(i).setDisplay(0);
            }
        }
    }

    public void setAllBoughtTicketsToDisplay(BoughtTickets boughtTickets) {
        Integer id;

        for (int i = 0; i < eventsDB.size(); i++) {
            id = eventsDB.get(i).getEventJson().getID();

            if (boughtTickets.isBoughtTicket(id)) {
                eventsDB.get(i).setDisplay(1);
            } else {
                eventsDB.get(i).setDisplay(0);
            }
        }
    }

    public void displayEvents(String type) {
        String id;
        String name;
        String date;
        String place;

        for (int i =0; i < eventsDB.size(); i++) {

            if (eventsDB.get(i).getDisplay() == 1) {
                id = String.valueOf(eventsDB.get(i).getEventJson().getID());
                name = eventsDB.get(i).getEventJson().getName();
                date = eventsDB.get(i).getEventJson().getStartDate();
                place = eventsDB.get(i).getEventJson().getPlace().getName();

                char[] idChar = id.toCharArray();
                char[] nameChar = name.toCharArray();
                char[] dateChar = date.toCharArray();
                char[] placeChar = place.toCharArray();

                StringBuilder dataToDisplay = generateDataToDisplay(idChar, nameChar, dateChar, placeChar, type);
                System.out.println(dataToDisplay);
            }
        }

        if (type.equals("1")) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
        } else if (type.equals("2")) {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void displayHeaderAll(String type) {
        if (type.equals("1")) {
            displayHeaderAllShort();
        } else if (type.equals("2")) {
            displayHeaderAllLong();
        } else {
            displayHeaderAllShort();
        }
    }

    public void displayHeaderAllShort() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Wydarzenie/a");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID        Nazwa                                                          Termin       Miejsce");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public void displayHeaderAllLong() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Wydarzenie/a");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID        Nazwa                                                          Termin                     Miejsce");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayHeaderOne() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Wydarzenie/a");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public void displayHeaderFavourite() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Wydarzenia zapisane w ulubionych");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public void displayHeaderReservations() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Zarezerwowane bilety");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public void displayHeaderBoughtTickets() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Zakupione bilety");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public StringBuilder generateDataToDisplay(char[] idChar, char[] nameChar, char[] dateChar, char[] placeChar, String type) {
        Integer idLength = 7;
        Integer nameLength = 60;
        Integer dateLengthShort = 10;
        Integer dateLengthLong = 24;
        Integer dateLength;
        Integer placeLength = 30;
        String blankSpace = " | ";
        Integer emptySpace;
        StringBuilder stringBuilder = new StringBuilder();

        if (type.equals("1")) {
            dateLength = dateLengthShort;
        } else if (type.equals("2")) {
            dateLength = dateLengthLong;
        } else {
            dateLength = dateLengthShort;
        }

        if (idChar.length < idLength) {
            stringBuilder.append(idChar);
            emptySpace = idLength - idChar.length;
            stringBuilder.append(fillEmptySpace(emptySpace));
        } else {
            for (int i = 0; i < idLength; i++) {
                char nextChar = idChar[i];
                stringBuilder.append(nextChar);
            }
        }
        stringBuilder.append(blankSpace);

        if (nameChar.length < nameLength) {
            stringBuilder.append(nameChar);
            emptySpace = nameLength - nameChar.length;
            stringBuilder.append(fillEmptySpace(emptySpace));
        } else {
            for (int i = 0; i < nameLength; i++) {
                char nextChar = nameChar[i];
                stringBuilder.append(nextChar);
            }
        }
        stringBuilder.append(blankSpace);

        if (dateChar.length < dateLength) {
            stringBuilder.append(dateChar);
            emptySpace = dateLength - dateChar.length;
            stringBuilder.append(fillEmptySpace(emptySpace));
        } else {
            for (int i = 0; i < dateLength; i++) {
                char nextChar = dateChar[i];
                stringBuilder.append(nextChar);
            }
        }
        stringBuilder.append(blankSpace);

        if (placeChar.length < placeLength) {
            stringBuilder.append(placeChar);
            emptySpace = placeLength - placeChar.length;
            stringBuilder.append(fillEmptySpace(emptySpace));
        } else {
            for (int i = 0; i < placeLength; i++) {
                char nextChar = placeChar[i];
                stringBuilder.append(nextChar);
            }
        }
        return stringBuilder;
    }

    public StringBuilder fillEmptySpace(Integer emptySpace) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < emptySpace; i++) {
            result.append(' ');
        }
        return result;
    }

    public void sortByConfiguration (String direction, String key) {
        if (key.equals("ID")) {
            setSortParameterID();
            if (direction.equals("ASC")) {
                Collections.sort(eventsDB);
            } else  if (direction.equals("DSC")) {
                Collections.sort(eventsDB, Collections.reverseOrder());
            } else {
                Collections.sort(eventsDB);
            }
        }else if (key.equals("NAME")) {
            setSortParameterName();
            if (direction.equals("ASC")) {
                Collections.sort(eventsDB);
            } else  if (direction.equals("DSC")) {
                Collections.sort(eventsDB, Collections.reverseOrder());
            } else {
                Collections.sort(eventsDB);
            }
        }else if (key.equals("DATE")) {
            setSortParameterDate();
            if (direction.equals("ASC")) {
                Collections.sort(eventsDB);
            } else  if (direction.equals("DSC")) {
                Collections.sort(eventsDB, Collections.reverseOrder());
            } else {
                Collections.sort(eventsDB);
            }
        } else {
            if (direction.equals("ASC")) {
                Collections.sort(eventsDB);
            } else  if (direction.equals("DSC")) {
                Collections.sort(eventsDB, Collections.reverseOrder());
            } else {
                Collections.sort(eventsDB);
            }
        }
    }

    public void setFoundEventsToDisplay(String parameter, String param3) {
        String stringToFind = param3;
        stringToFind = stringToFind.substring(1);

        setNoneEventsToDisplay();

        for (int i = 0; i < eventsDB.size(); i++) {
            if (parameter.equals("-ORG") && (eventsDB.get(i).getEventJson().getPlace().getName().toUpperCase().contains(stringToFind))) {
                eventsDB.get(i).setDisplay(1);
            } else if (parameter.equals("-DATE") && (eventsDB.get(i).getEventJson().getStartDate().contains(stringToFind))) {
                eventsDB.get(i).setDisplay(1);
            } else if (parameter.equals("-NAME") && (eventsDB.get(i).getEventJson().getName().contains(stringToFind))) {
                eventsDB.get(i).setDisplay(1);
            }
        }
    }

// Metoda do menu tekstowego w konsoli
public void setFilterByName(String name) {
    setNoneEventsToDisplay();

    for (int i = 0; i < eventsDB.size(); i++) {
        if (eventsDB.get(i).getEventJson().getOrganizer().getName().toUpperCase().equals(name.toUpperCase())) {
            eventsDB.get(i).setDisplay(1);
        }
    }
}



// Metoda do menu uruchamianego z linii poleceń
/*    public void setFilterByName(String[] args) {
        setNoneEventsToDisplay();
        String filterString = args[2];

        if (args.length > 3) {
            for (int i = 3; i < args.length; i++) {
                filterString = filterString + " " + args[i];
            }
            filterString = filterString.substring(2, filterString.length() - 1);
        }

        for (int i = 0; i < eventsDB.size(); i++) {
            if (eventsDB.get(i).getEventJson().getOrganizer().getName().toUpperCase().equals(filterString)) {
                eventsDB.get(i).setDisplay(1);
            }
        }
    }*/


//Metoda do menu tekstowego w konsoli
public void setFilterByDate(LocalDate startDateLocalDate, LocalDate endDateLocalDate) {
/*    LocalDate startDateLocalDate = LocalDate.parse(startingTime);
    LocalDate endDateLocalDate = LocalDate.parse(endingTime);*/
    LocalDate eventDate;

    setNoneEventsToDisplay();

        for (int i = 0; i < eventsDB.size(); i++) {
            String dateString = eventsDB.get(i).getEventJson().getStartDate();
            String[] arrOfStr = dateString.split("T");
            eventDate = LocalDate.parse(arrOfStr[0]);

            if (startDateLocalDate.equals(endDateLocalDate)) {
                if (arrOfStr[0].equals(endDateLocalDate)) {
                    eventsDB.get(i).setDisplay(1);
                }
            } else {
                if (eventDate.isEqual(startDateLocalDate) || eventDate.isEqual(endDateLocalDate) || (eventDate.isAfter(startDateLocalDate) && eventDate.isBefore(endDateLocalDate))) {
                    eventsDB.get(i).setDisplay(1);
                }
            }
        }
}


// Metoda do menu uruchamianego z linii poleceń
/*    public boolean setFilterByDate(String [] args) {
        String startingTime;
        String endingTime;

        setNoneEventsToDisplay();

        if (args.length > 3) {
            startingTime = args[2].substring(1);
            endingTime = args[3].substring(1);
        } else {
            startingTime = args[2].substring(1);
            endingTime = "2099-12-31";
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateValidatorUsingLocalDate dateValidatorUsingLocalDate = new DateValidatorUsingLocalDate(dateTimeFormatter);

        if (dateValidatorUsingLocalDate.isValid(startingTime) && dateValidatorUsingLocalDate.isValid(endingTime)) {
            LocalDate startDateLocalDate = LocalDate.parse(startingTime);
            LocalDate endDateLocalDate = LocalDate.parse(endingTime);
            LocalDate eventDate;
            if (startDateLocalDate.isAfter(endDateLocalDate)) {
                startDateLocalDate = LocalDate.parse(endingTime);
                endDateLocalDate = LocalDate.parse(startingTime);
            }

            for (int i = 0; i < eventsDB.size(); i++) {
                String dateString = eventsDB.get(i).getEventJson().getStartDate();
                String[] arrOfStr = dateString.split("T");
                eventDate = LocalDate.parse(arrOfStr[0]);

                if (startDateLocalDate.equals(endDateLocalDate)) {
                    if (arrOfStr[0].equals(endDateLocalDate)) {
                        eventsDB.get(i).setDisplay(1);
                    }
                } else {
                    if (eventDate.isEqual(startDateLocalDate) || eventDate.isEqual(endDateLocalDate) || (eventDate.isAfter(startDateLocalDate) && eventDate.isBefore(endDateLocalDate))) {
                        eventsDB.get(i).setDisplay(1);
                    }
                }
            }
            return  true;
        } else {
            System.out.println("Podaj datę we właściwym formacie.");
            return false;
        }
    }*/

    public void setNoneEventsToDisplay() {
        for (int i = 0; i < eventsDB.size(); i++) {
            eventsDB.get(i).setDisplay(0);
        }
    }

    public void setSortParameterID() {
        for (Event event: eventsDB) {
            Integer id = event.getEventJson().getID();
            event.setSortParameter(id.toString());
        }
    }

    public void setSortParameterName() {
        for (Event event: eventsDB) {
            String name = event.getEventJson().getName();
            event.setSortParameter(name);
        }
    }

    public void setSortParameterDate() {
        for (Event event: eventsDB) {
            String[] date = event.getEventJson().getStartDate().split("T");
            event.setSortParameter(date[0]);
        }
    }

/*    public void displayFavourities() {


    }*/

    public void sortByID(String direction, String key) {
        sortByConfiguration(direction, key);
        setSortParameterID();
        Collections.sort(eventsDB);
    }

    public void sortByOrganizer(String direction, String key) {
        sortByConfiguration(direction, key);
        setSortParameterName();
        Collections.sort(eventsDB);
    }

    public void sortByDate(String direction, String key) {
        sortByConfiguration(direction, key);
        setSortParameterDate();
        Collections.sort(eventsDB);
    }


    public void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public boolean isEvent(Integer ID) {
        boolean result = false;
        for (int i = 0; i < eventsDB.size(); i++) {
            Event event = eventsDB.get(i);
            EventJson eventJson = event.getEventJson();
            Integer inte = eventJson.getID();

            if (inte.equals(ID)) {
                result = true;
            }
        }
        return result;
    }
}

