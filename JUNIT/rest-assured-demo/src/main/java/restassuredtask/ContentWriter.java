package restassuredtask;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContentWriter {

  public void writeDataLineByLine(
    String filePath,
    List<String[]> data,
    String[] headers
  ) {
    File file = new File(filePath);
    try {
      FileWriter outputfile = new FileWriter(file);
      CSVWriter writer = new CSVWriter(outputfile);

      writer.writeNext(headers);
      for (String[] line : data) {
        writer.writeNext(line);
      }
      // closing writer connection
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
