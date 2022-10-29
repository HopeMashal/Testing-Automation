package appiumtask.Core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVFile {
  public static List<String[]> readAllLines(String filePath) throws Exception {
    CSVReader reader = new CSVReader(new FileReader(filePath));

    List<String[]> data = new ArrayList<String[]>();

    // read line by line
    String[] record = null;

    while ((record = reader.readNext()) != null) {
      data.add(record);
    }
    reader.close();
    return data;
  }

  public static void writeDataLineByLine(
      String filePath,
      List<String[]> data,
      String[] headers) {
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
