import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OpenBrowserTest {

  @Parameters({ "Browser" })
  @Test
  public void checkBrowser(String Browser) throws InterruptedException {
    WebDriver driver = OpenBrowser.openBrowser(Browser);
    driver.manage().window().maximize();
    driver.get("https://www.google.com/");
    Thread.sleep(5000);
    driver.get("https://www.bing.com/");
    Thread.sleep(5000);
    driver.navigate().back();
    Thread.sleep(5000);
    driver.navigate().forward();
    Thread.sleep(5000);
    driver.quit();
  }
}
