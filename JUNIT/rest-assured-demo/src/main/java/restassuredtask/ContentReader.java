package restassuredtask;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ContentReader {

  public static List<ArrayList<String>> readFileCSV = new ArrayList<ArrayList<String>>();

  public List<ArrayList<String>> readDataLineByLine(String file) {
    try {
      FileReader filereader = new FileReader(file);
      CSVReader csvReader = new CSVReaderBuilder(filereader)
        .withSkipLines(1)
        .build();
      List<String[]> allData = csvReader.readAll();
      ArrayList<String> myList;
      for (String[] row : allData) {
        myList = new ArrayList<String>();
        for (String cell : row) {
          myList.add(cell);
        }
        readFileCSV.add(myList);
      }
      csvReader.close();
      filereader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return readFileCSV;
  }
}
