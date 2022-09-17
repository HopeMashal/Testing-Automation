package elementtask;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import elementtask.Core.OpenBrowser;
import elementtask.Pages.AddressPage;
import elementtask.Pages.CheckData;
import elementtask.Pages.HomePage;
import elementtask.Pages.NewAddress;
import elementtask.Pages.SignIn;

public class ViewElementTask {
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

  /* @Test
  public void testing() throws InterruptedException{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressPage = new AddressPage(driver);
    int addNum1 = addressPage.AddressCounter();
    System.out.println(addNum1);
    List<String[]> lines = addressPage.TableData();
    for(String[] line: lines){
      for(String el:line){
        System.out.print(el+" \t");
      }
      System.out.println("");
    }
  } */

  @DataProvider
  public static Object[][] viewTask() throws Exception{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressData = new AddressPage(driver);
    List<WebElement[]> lines = addressData.TableData();
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for(WebElement[] line: lines){
      data[index] = line;
      index++;
    }
    // for(WebElement[] line: lines){
    //   for(WebElement el:line){
    //     System.out.print(el.getText()+" \t");
    //   }
    //   System.out.println("");
    // }
    // for(int i=0; i<lines.size(); i++){
    //     System.out.println(lines.get(i)[0].getText());
    // }
    return data;
  }

  @Test(dataProvider = "viewTask")
  public void testViewTask(WebElement firstName,WebElement lastName, WebElement city, WebElement state, WebElement show, WebElement edit, WebElement destroy) throws InterruptedException{
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
    AddressPage addressPage = new AddressPage(driver);
    int addNum1 = addressPage.AddressCounter();
    System.out.println(addNum1);
    Thread.sleep(2000);
    //System.out.println(firstName.getText());
    /* addressPage.ShowClick(show);
    Thread.sleep(2000);
    CheckData checkData = new CheckData(driver);
    checkData.CheckAddressData(firstName.getText(), lastName.getText(), city.getText(), state.getText());
    Thread.sleep(2000);
    checkData.ListClick();
    Thread.sleep(2000); */
  }
    
  @AfterSuite
  public void afterSuite() {
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
  	driver.quit();
  }
}
