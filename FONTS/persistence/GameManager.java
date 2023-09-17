package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManager {

    final private String filePath = "data/Game.csv";

    /**
     * This method returns whether the Game.csv file exists or not.
     * @return A boolean indicating whether it exists or not.
     */
    public boolean existFile() {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * This method deletes the file Game.csv and Secret.cvs if exists.
     */
    public void deleteFile() {
        File file = new File(filePath);

        if (file.exists()) {
            boolean deleted = file.delete();
        }
    }

    /**
     * This method saves the game "game" by writing every element of the list in the Game.csv file
     * @param game List<String> containing the data of a game
     * @throws IOException
     */
    public void saveGame(List<String> game) throws IOException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
            boolean created = file.createNewFile();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.print(game.get(0));
                for (int i = 1; i < game.size(); ++i) {
                    writer.print("," + game.get(i));
                }
            }
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * This method loads the game by reading the data of the Game.csv file
     * @return List<String> containing the data of the rankings
     * @throws FileNotFoundException
     */
    public List<String> loadGame() throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        try (BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            return new ArrayList<>(Arrays.asList(tokens));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
