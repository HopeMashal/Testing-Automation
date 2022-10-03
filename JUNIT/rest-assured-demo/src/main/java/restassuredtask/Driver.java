package restassuredtask;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class Driver {

  public static void main(String[] args) {
    String readPath = "./src/files/Tasks.csv";
    ContentReader contentReader = new ContentReader();
    List<ArrayList<String>> lines = contentReader.readDataLineByLine(readPath);
    for (ArrayList<String> line : lines) {
      Response response = RestAssured.get(
        "https://jsonplaceholder.typicode.com" + line.get(0)
      );
      System.out.println(
        "\n----------------------TEST----------------------\n"
      );
      System.out.println("The route is: " + line.get(0));
      System.out.println("The expected status is: " + line.get(1));
      System.out.println("The actual status is: " + response.getStatusCode());
      if (
        Integer.parseInt(line.get(1)) == response.getStatusCode()
      ) System.out.println(
        "The expected status is equal to the actual status"
      ); else System.out.println(
        "The expected status is NOT equal to the actual status"
      );
      //System.out.println("The Data:-");
      //System.out.println(response.asPrettyString());
    }
  }
}
