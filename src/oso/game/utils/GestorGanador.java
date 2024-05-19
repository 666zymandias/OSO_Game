package oso.game.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GestorGanador {
    private final String FILE_NAME;

    public GestorGanador(String file) {
        this.FILE_NAME = file;
    }
    
    private Map<String, Integer> readPlayersFromFile() throws IOException {
        Map<String, Integer> players = new HashMap<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            file.createNewFile();
            return players;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int gamesWon = Integer.parseInt(parts[1]);
                    players.put(playerName, gamesWon);
                }
            }
        }
        return players;
    }

    private void writePlayersToFile(Map<String, Integer> players) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : players.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
    }

    public void updatePlayerGamesWon(String playerName) throws IOException {
        Map<String, Integer> players = readPlayersFromFile();
        players.put(playerName, players.getOrDefault(playerName, 0) + 1);
        writePlayersToFile(players);
    }
}
