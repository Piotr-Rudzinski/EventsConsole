package infoshareacademy;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reservations {
    private List<Integer> reservationsDB = new ArrayList<>();
    private Path path = Paths.get("src", "main", "resources", "reservations.json");

    public void readReservations () {
        String readString;
        Gson gson = new Gson();

        try {
            readString = Files.readString(path);
            readString = readString.replace("[", "");
            readString = readString.replace("]", "");

            String[] reservation = readString.split(",");

            if (reservation[0].equals("")) {
                reservation[0] = "0";
            }

            for (int i = 0; i < reservation.length; i++) {
                reservationsDB.add(Integer.parseInt(reservation[i]));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku reservation.json.");
            createReservations();
        }
    }

    private void createReservations() {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć pliku rezerwacji.");
        }

        Integer number = 100;
        reservationsDB.add(0);

        Integer[] reservations = new Integer[1];
        reservations[0] = reservationsDB.get(0);
        System.out.println(reservations[0]);

        Gson gson = new Gson();
        String jsonString = gson.toJson(reservations[0]);

        try {
            Files.writeString(path, jsonString);
        } catch (IOException e) {
            System.out.println("Nie można zapisać pliku rezerwacji.");
        }
    }

    public void saveReservations() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(reservationsDB);

        try {
            Files.writeString(path, jsonString);
        } catch (IOException e) {
            System.out.println("Nie można zapisać pliku rezerwacji.");
        }
    }

    public boolean isReservation(Integer id) {
        return reservationsDB.contains(id);
    }

    public void addReservation(Integer id) {
        if (isReservation(id)) {
            reservationsDB.add(id);
            saveReservations();
            reservationsDB.clear();
            readReservations();
            System.out.println("    Bilety zostały zarezerwowane.");
        } else {
            System.out.println("Na to wydarzenie bilety zostały juz zarezerwowane.");
        }
    }

    public void removeReservation(Integer id) {
        while (isReservation(id)) {
            int position = reservationsDB.indexOf(id);
            reservationsDB.remove(position);
        }
    }
}
