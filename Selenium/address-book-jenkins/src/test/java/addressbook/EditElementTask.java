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
import addressbook.Pages.EditAddress;
import addressbook.Pages.HomePage;
import addressbook.Pages.SignIn;

public class EditElementTask {
  static WebDriver driver;
  static String URL = "http://a.testaddressbook.com";
  static int index = 3;
  static String[] updateData = new String[]{"Amal", "Akira", "Jerusalem", "Jerusalem", "Jerusalem", "DC", "12222", "canada", "12/11/1997", "#e40486", "25", "https://www.google.com/", "052-433-4233", "3 1", "Be Happy :)"};
  
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
    List<String> line = addressData.TableDataXPath(index);
    Object[][] data = new Object[1][line.size()];
    for(int i=0; i<line.size(); i++){
      data[0][i]=line.get(i);
    }
    Object[][] ReturnData = new Object[1][5];
    for(int i=0; i < data.length; i++){
      for(int j=0; j<4; j++){
        ReturnData[i][j] = data[i][j];
      }
      ReturnData[i][4] = data[i][5]; 
    }
    return ReturnData;
  }

  @Test(dataProvider = "viewTask")
  public void testViewTask(String firstName,String lastName, String city, String state, String edit) throws InterruptedException{
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
    WebElement Edit = driver.findElement(By.xpath(edit+"/a"));
    Thread.sleep(2000);
    Edit.click();
    Thread.sleep(2000);
    EditAddress editData = new EditAddress(driver);
    editData.CheckAddressData(FirstName, LastName, City, State);
    Thread.sleep(2000);
    editData.FillData(updateData[0], updateData[1], updateData[2], updateData[3], updateData[4], updateData[5], updateData[6], updateData[7], updateData[8], updateData[9], updateData[10], updateData[11], updateData[12], updateData[13], updateData[14]);
    Thread.sleep(2000);
    CheckData checkData = new CheckData(driver);
    checkData.CheckAddressData(updateData[0], updateData[1], updateData[2], updateData[3], updateData[4], updateData[5], updateData[6], updateData[7], updateData[8], updateData[9], updateData[10], updateData[11], updateData[12], updateData[13], updateData[14]);
    Thread.sleep(2000);
    checkData.ListClick();
    Thread.sleep(2000);
    AddressPage addressPage2 = new AddressPage(driver);
    String FirstName1 = driver.findElement(By.xpath(firstName)).getText();
    String LastName1 = driver.findElement(By.xpath(lastName)).getText();
    String City1 = driver.findElement(By.xpath(city)).getText();
    String State1 = driver.findElement(By.xpath(state)).getText();
    addressPage2.CheckAddressData(updateData[0], FirstName1, updateData[1], LastName1, updateData[4], City1, updateData[5], State1);
    Thread.sleep(2000);
  }
    
  @AfterTest
  public void afterTest() {
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
    driver.quit();
  }
}
