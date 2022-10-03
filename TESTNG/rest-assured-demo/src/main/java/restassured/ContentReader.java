package restassured;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ContentReader {

  public List<String[]> readAllLines(String filePath) throws Exception {
    FileReader filereader = new FileReader(filePath);
    CSVReader reader = new CSVReader(filereader);

    List<String[]> data = new ArrayList<String[]>();

    // read line by line
    String[] record = null;

    while ((record = reader.readNext()) != null) {
      data.add(record);
    }
    reader.close();
    return data;
  }
}
