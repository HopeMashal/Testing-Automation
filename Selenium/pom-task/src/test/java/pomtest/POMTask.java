package pomtest;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pomtest.Core.CSVFile;
import pomtest.Core.OpenBrowser;
import pomtest.Pages.BankHome;
import pomtest.Pages.BankLogin;

public class POMTask {
  static String Browser;
  static String CSVPath;
  static WebDriver driver;
  static String URL = "https://demo.guru99.com/V1/index.php";
  
  @Parameters({"Browser","CSVPath"})
  @BeforeSuite
  public void beforeSuite(String Browser,String CSVPath){
  	POMTask.Browser = Browser;
  	POMTask.CSVPath = CSVPath;
  	driver = OpenBrowser.openBrowser(POMTask.Browser);
	  driver.manage().window().maximize();
  }

  @DataProvider
  public static Object[][] getDataCSV() throws Exception{
    List<String[]> lines = CSVFile.readAllLines(POMTask.CSVPath);
    lines.remove(0);
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for(String[] line: lines){
      data[index] = line;
      index++;
    }
    return data;
  }

  @Test(dataProvider = "getDataCSV")
  public void testDataCSV(String Id, String Password) throws InterruptedException{
  	driver.get(URL);
    Thread.sleep(2000);
	  BankLogin bankLogin = new BankLogin(driver);
    bankLogin.Login(Id, Password);
    Thread.sleep(2000);
    try {
      BankHome bankHome = new BankHome(driver);
      bankHome.Logout();
      try {
        driver.switchTo().alert().accept();
      } catch (Exception e) {
        Assert.fail("Logout Failed");
      }
    } catch (Exception e) {
      Assert.fail("Login Failed");
    }
  }

  @Test(dataProvider = "getDataCSV")
  public void testResetDataCSV(String Id, String Password) throws InterruptedException{
  	driver.get(URL);
    Thread.sleep(2000);
	  BankLogin bankLogin = new BankLogin(driver);
    bankLogin.Reset(Id, Password);
  }
    
  @AfterSuite
  public void afterSuite() {
  	driver.quit();
  }
}
