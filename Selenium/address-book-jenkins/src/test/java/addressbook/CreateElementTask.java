package addressbook;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import addressbook.Core.CSVFile;
import addressbook.Core.OpenBrowser;
import addressbook.Pages.AddressPage;
import addressbook.Pages.CheckData;
import addressbook.Pages.HomePage;
import addressbook.Pages.NewAddress;
import addressbook.Pages.SignIn;

public class CreateElementTask {
  static String CSVPath;
  static WebDriver driver;
  static String URL = "http://a.testaddressbook.com";
  
  @Parameters({"CSVPath"})
  @BeforeTest
  public void beforeSuite(String CSVPath){
    CreateElementTask.CSVPath = CSVPath;
    driver = OpenBrowser.openChromeWithOptions();
    driver.manage().window().maximize();
    driver.get(URL+"/sign_in");
    SignIn signIn = new SignIn(driver);
    signIn.Login("hope.mashal@gmail.com", "hope1234");
  }

  @DataProvider
  public static Object[][] createTask() throws Exception{
    List<String[]> lines = CSVFile.readAllLines(CreateElementTask.CSVPath);
    lines.remove(0);
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for(String[] line: lines){
      data[index] = line;
      index++;
    }
    return data;
  }

  @Test(dataProvider = "createTask")
  public void testCreateTask(String firstName,String lastName, String address1, String address2, String city, String state, String zip, String country, String birthday, String color, String age, String website, String phone, String common, String note) throws InterruptedException{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressPage = new AddressPage(driver);
    int addNum1 = addressPage.AddressCounter();
    addressPage.newAddressClick();
    Thread.sleep(2000);
    NewAddress newAddress = new NewAddress(driver);
    newAddress.FillData(firstName, lastName, address1, address2, city, state, zip, country, birthday, color, age, website, phone, common, note);
    Thread.sleep(2000);
    CheckData checkData = new CheckData(driver);
    checkData.CheckAddressData(firstName, lastName, address1, address2, city, state, zip, country, birthday, color, age, website, phone, common, note);
    Thread.sleep(2000);
    checkData.ListClick();
    Thread.sleep(2000);
    AddressPage addressPage2 = new AddressPage(driver);
    int addNum2 = addressPage2.AddressCounter();
    Assert.assertEquals(addNum2, (addNum1+1), "Address Numbers NOT Match!!");
  }
    
  @AfterTest
  public void afterSuite() {
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
    driver.quit();
  }
}
