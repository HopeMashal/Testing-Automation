package testng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static testng.App.CalculateSum;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppTest {

  @Parameters({ "first", "second" })
  @Test
  public void checkCalculateSum(int first, int second) {
    assertEquals(CalculateSum(first, second), Integer.sum(first, second));
  }
}
