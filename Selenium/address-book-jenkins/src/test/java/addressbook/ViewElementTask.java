package addressbook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import addressbook.Core.OpenBrowser;
import addressbook.Pages.AddressPage;
import addressbook.Pages.CheckData;
import addressbook.Pages.HomePage;
import addressbook.Pages.SignIn;

public class ViewElementTask {
  static WebDriver driver;
  static String URL = "http://a.testaddressbook.com";
  
  @BeforeTest
  public void beforeTest(){
    driver = OpenBrowser.openChromeWithOptions();
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
    Object[][] ReturnData = new Object[lines.size()][5];
    for(int i=0; i < data.length; i++){
      for(int j=0; j<5; j++){
        ReturnData[i][j] = data[i][j];
      }
    }
    return ReturnData;
  }

  @Test(dataProvider = "viewTask")
  public void testViewTask(String firstName,String lastName, String city, String state, String show) throws InterruptedException{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressPage = new AddressPage(driver);
    int addNum1 = addressPage.AddressCounter();
    System.out.println(addNum1);
    Thread.sleep(2000);
    String FirstName = driver.findElement(By.xpath(firstName)).getText();
    String LastName = driver.findElement(By.xpath(lastName)).getText();
    String City = driver.findElement(By.xpath(city)).getText();
    String State = driver.findElement(By.xpath(state)).getText();
    WebElement Show = driver.findElement(By.xpath(show+"/a"));
    Thread.sleep(2000);
    Show.click();
    Thread.sleep(2000);
    CheckData checkData = new CheckData(driver);
    checkData.CheckAddressData(FirstName, LastName, City, State);
    Thread.sleep(2000);
    checkData.ListClick();
    Thread.sleep(2000);
  }
    
  @AfterTest
  public void afterTest() {
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
    driver.quit();
  }
}
