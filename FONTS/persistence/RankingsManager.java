package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankingsManager {
    final private String filePath = "data/Rankings.csv";

    /**
     * Default empty constructor of the RankingsManager class
     */
    public RankingsManager() {}

    /**
     * This method saves the rankings "rankings" by writing every element of the list in the Rankings.csv file
     * @param rankings List<String> containing the data of a game
     * @throws IOException
     */
   public void saveRankings(List<String> rankings) throws IOException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
            boolean created = file.createNewFile();
            }
            if(!rankings.isEmpty()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                    writer.print(rankings.get(0));
                    for (int i = 1; i < rankings.size(); ++i) {
                        writer.print("," + rankings.get(i));
                    }
                }
            }
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * This method loads the rankings by reading the data of the Rankings.csv file
     * @return List<String> containing the data of the rankings
     * @throws FileNotFoundException
     */
    public List<String> loadRankings() throws FileNotFoundException {
        File file = new File(filePath);
        FileReader fr = new FileReader(filePath);
        if(file.exists() && file.length() == 0) return new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            return new ArrayList<>(Arrays.asList(tokens));
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }


}
