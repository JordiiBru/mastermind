package persistence;


import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordsManager {

    final private String filePath = "data/Records.csv";


    /**
     * Default empty constructor of the RecordsManager class
     */
    public RecordsManager(){}

    /**
     * This method saves the records "records" by writing every element of the list in the Rankings.csv file
     * @param records List<String> containing the data of a game
     * @throws IOException
     */
    public void saveRecords(List<String> records) throws IOException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
            boolean created = file.createNewFile();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.print(records.get(0));
                for (int i = 1; i < records.size(); ++i) {
                    writer.print("," + records.get(i));
                }
            }
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * This method loads the rankings by reading the data of the Records.csv file
     * @return List<String> containing the data of the records
     * @throws FileNotFoundException
     */
    public List<String> loadRecords() throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        try (BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            String[] tokens = line.split(",");
            return new ArrayList<>(Arrays.asList(tokens));
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}
