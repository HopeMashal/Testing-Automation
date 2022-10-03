package pomtest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pomtest.Core.OpenBrowser;
import pomtest.Pages.BankHome;
import pomtest.Pages.BankLogin;

public class POMTasking {

  static String Browser;
  static WebDriver driver;
  static String URL = "https://demo.guru99.com/V1/index.php";

  @Parameters({ "Browser" })
  @BeforeSuite
  public void beforeSuite(String Browser) throws Exception {
    POMTask.Browser = Browser;
    driver = OpenBrowser.openBrowser(POMTask.Browser);
    driver.manage().window().maximize();
    driver.get(URL);
    Thread.sleep(2000);
    BankLogin bankLogin = new BankLogin(driver);
    bankLogin.Login("mngr438269", "zegYzyh");
  }

  @DataProvider
  public static Object[][] getDataCSV() throws Exception {
    List<WebElement> lines = driver.findElements(
      By.xpath("/html/body/div[3]/div/ul/li/a")
    );
    lines.remove(lines.size() - 1);
    Object[][] data = new Object[lines.size()][1];
    for (int i = 0; i < lines.size(); i++) {
      data[i][0] = lines.get(i).getText();
    }
    return data;
  }

  @Test(dataProvider = "getDataCSV")
  public void testDataCSV(String linkText) throws InterruptedException {
    WebElement linkBtn = driver.findElement(By.linkText(linkText));
    String linkHref = linkBtn.getAttribute("href");
    Thread.sleep(2000);
    linkBtn.click();
    if (driver.getCurrentUrl().contains("#")) {
      WebElement frame1 = driver.findElement(
        By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")
      );
      driver.switchTo().frame(frame1);
      WebElement frame2 = driver.findElement(By.id("ad_iframe"));
      driver.switchTo().frame(frame2);
      driver
        .findElement(By.xpath("//div[@id='dismiss-button']/div/span"))
        .click();
      driver.switchTo().defaultContent();
      Thread.sleep(2000);
    }
    Thread.sleep(2000);
    System.out.println(
      "Link Text: " + linkText + "\t\t Title: " + driver.getTitle()
    );
    System.out.println(
      "Link Href: " + linkHref + "\t\t URL: " + driver.getCurrentUrl()
    );
    if (!driver.getTitle().contains(linkText)) Assert.fail(
      "Current Title NOT Contains LinkText"
    );
    if (!driver.getCurrentUrl().contains(linkHref)) Assert.fail(
      "Current URL NOT Contains LinkHref"
    );
    Thread.sleep(2000);
  }

  @AfterSuite
  public void afterSuite() {
    BankHome bankHome = new BankHome(driver);
    bankHome.Logout();
    driver.quit();
  }
}
