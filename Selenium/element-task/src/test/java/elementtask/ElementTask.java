package elementtask;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import elementtask.Core.CSVFile;
import elementtask.Core.OpenBrowser;
import elementtask.Pages.AddressPage;
import elementtask.Pages.CheckData;
import elementtask.Pages.HomePage;
import elementtask.Pages.NewAddress;
import elementtask.Pages.SignIn;

public class ElementTask {
  static String Browser;
  static String CSVPath;
  static WebDriver driver;
  static String URL = "http://a.testaddressbook.com";
  
  @Parameters({"Browser","CSVPath"})
  @BeforeSuite
  public void beforeSuite(String Browser,String CSVPath){
  	ElementTask.Browser = Browser;
  	ElementTask.CSVPath = CSVPath;
  	driver = OpenBrowser.openBrowser(ElementTask.Browser);
	  driver.manage().window().maximize();
    driver.get(URL+"/sign_in");
    SignIn signIn = new SignIn(driver);
    signIn.Login("hope.mashal@gmail.com", "hope1234");
  }

  @DataProvider
  public static Object[][] createTask() throws Exception{
    List<String[]> lines = CSVFile.readAllLines(ElementTask.CSVPath);
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
    driver.get(URL);
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
    int addNum2 = addressPage.AddressCounter();
    if((addNum1+1) != addNum2) Assert.fail("Address Numbers NOT Match!!");
    homePage.Logout();
  }
    
  @AfterSuite
  public void afterSuite() {
  	driver.quit();
  }
}
