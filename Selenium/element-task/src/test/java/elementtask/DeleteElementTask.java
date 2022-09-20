package elementtask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import elementtask.Core.OpenBrowser;
import elementtask.Pages.AddressPage;
import elementtask.Pages.HomePage;
import elementtask.Pages.SignIn;

public class DeleteElementTask {
  static String Browser;
  static WebDriver driver;
  static String URL = "http://a.testaddressbook.com";
  
  @Parameters({"Browser"})
  @BeforeSuite
  public void beforeSuite(String Browser){
    ViewElementTask.Browser = Browser;
    driver = OpenBrowser.openBrowser(ViewElementTask.Browser);
    driver.manage().window().maximize();
    driver.get(URL+"/sign_in");
    SignIn signIn = new SignIn(driver);
    signIn.Login("hope.mashal@gmail.com", "hope1234");
  }

  @DataProvider
  public static Object[][] viewTask() throws Exception{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressData = new AddressPage(driver);
    List<String[]> lines = addressData.TableDataXPath();
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for(String[] line: lines){
      data[index] = line;
      index++;
    }
    Object[][] ReturnData = new Object[lines.size()][1];
    for(int i=0; i < data.length; i++){
      ReturnData[i][0] = data[0][6];
      System.out.println(ReturnData[i][0]);
    }
    return ReturnData;
  }

  @Test(dataProvider = "viewTask")
  public void testViewTask(String delete) throws InterruptedException{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressPage = new AddressPage(driver);
    int addNum1 = addressPage.AddressCounter();
    System.out.println(addNum1);
    Thread.sleep(2000);
    WebElement Delete = driver.findElement(By.xpath(delete+"/a"));
    Thread.sleep(2000);
    Delete.click();
    driver.switchTo().alert().accept();
    Thread.sleep(2000);
  }
    
  @AfterSuite
  public void afterSuite() {
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
    driver.quit();
  }
}
