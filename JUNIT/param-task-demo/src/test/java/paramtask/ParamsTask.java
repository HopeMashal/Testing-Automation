package paramtask;

import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParamsTask {

  @Parameterized.Parameter(0)
  public String firstNum;

  @Parameterized.Parameter(1)
  public String secondNum;

  @Parameterized.Parameter(2)
  public String thirdNum;

  @Parameters
  public static Collection<String[]> input() throws Exception {
    String path = "./src/files/ParamTask.csv";
    ContentReader contentReader = new ContentReader();
    List<String[]> lines = contentReader.readAllLines(path);
    lines.remove(0);
    return lines;
  }

  @Test
  @Parameters
  public void testParam() {
    int fNum = Integer.parseInt(firstNum);
    int sNum = Integer.parseInt(secondNum);
    int tNum = Integer.parseInt(thirdNum);
    Assert.assertEquals(tNum, Math.max(fNum, sNum));
  }
}
