package infoshareacademy;


import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BoughtTickets {
    private List<Integer> boughtTicketsDB = new ArrayList<>();
    private Path path = Paths.get("src", "main", "resources", "boughtTickets.json");

    public void readBoughtTickets () {
        String readString;
        Gson gson = new Gson();

        try {
            readString = Files.readString(path);
            readString = readString.replace("[", "");
            readString = readString.replace("]", "");

            String[] tickets = readString.split(",");

            if (tickets[0].equals("")) {
                tickets[0] = "0";
            }

            for (int i = 0; i < tickets.length; i++) {
                boughtTicketsDB.add(Integer.parseInt(tickets[i]));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku boughtTickets.json.");
            createBoughtTicket();
        }
    }

    private void createBoughtTicket() {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Nie można utworzyć pliku dla zakupionych biletów.");
        }

        Integer number = 100;
        boughtTicketsDB.add(0);

        Integer[] tickets = new Integer[1];
        tickets[0] = boughtTicketsDB.get(0);
        System.out.println(tickets[0]);

        Gson gson = new Gson();
        String jsonString = gson.toJson(tickets[0]);

        try {
            Files.writeString(path, jsonString);
        } catch (IOException e) {
            System.out.println("Nie można zapisać pliku dla zakupionych biletów.");
        }
    }

    public void saveBoughtTicket() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(boughtTicketsDB);

        try {
            Files.writeString(path, jsonString);
        } catch (IOException e) {
            System.out.println("Nie można zapisać pliku dla zakupionych biletów.");
        }
    }

    public boolean isBoughtTicket(Integer id) {
        return boughtTicketsDB.contains(id);
    }

    public void addBoughtTicket(Integer id) {
        boughtTicketsDB.add(id);
        saveBoughtTicket();
        boughtTicketsDB.clear();
        readBoughtTickets();
        System.out.println("    Bilety zostały kupione.");
    }
}
