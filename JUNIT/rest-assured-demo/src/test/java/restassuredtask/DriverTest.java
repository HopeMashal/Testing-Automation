package restassuredtask;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DriverTest {

  static String readPath = "./src/files/Tasks.csv";
  static String failurePath = "./src/files/Failure.csv";
  static ContentReader reader;
  static ContentWriter writer;
  static List<String[]> FailureList;

  @BeforeClass
  public static void beforeClass() {
    reader = new ContentReader();
    writer = new ContentWriter();
    FailureList = new ArrayList<String[]>();
  }

  @Test
  public void TestCaseOne() {
    List<ArrayList<String>> lines = reader.readDataLineByLine(readPath);
    for (ArrayList<String> line : lines) {
      Response response = RestAssured.get(
        "https://jsonplaceholder.typicode.com" + line.get(0)
      );
      if (Integer.parseInt(line.get(1)) != response.getStatusCode()) {
        String[] myString = new String[] {
          line.get(0),
          line.get(1),
          String.valueOf(response.getStatusCode()),
        };
        FailureList.add(myString);
      }
    }
    Assert.assertEquals(0, FailureList.size());
  }

  @After
  public void after() {
    System.out.println("After Test");
  }

  @AfterClass
  public static void afterClass() {
    String[] myStr = new String[] {
      "Route",
      "Expected Status",
      "Actual Status",
    };
    writer.writeDataLineByLine(failurePath, FailureList, myStr);
  }
}
