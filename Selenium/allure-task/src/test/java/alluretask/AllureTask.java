package alluretask;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import alluretask.Core.OpenBrowser;
import alluretask.Pages.HomePage;
import alluretask.Pages.SignIn;

public class AllureTask {
  static WebDriver driver;
  static String Email;
  static String Password;
  static String URL = "http://a.testaddressbook.com";
  
  @Parameters({"Email","Password"})
  @BeforeTest
  public void beforeTest(String Email, String Password){
    AllureTask.Email = Email;
    AllureTask.Password = Password;
    driver = OpenBrowser.openChromeWithOptions();
    driver.get(URL+"/sign_in");
  }

  @Test
  public void AllureTest() throws InterruptedException{
    SignIn signIn = new SignIn(driver);
    signIn.Login(Email, Password);
    Thread.sleep(2000);
    HomePage homePage = new HomePage(driver);
    homePage.AddressClick();
    Thread.sleep(2000);
  }

  @AfterTest
  public void afterTest(){
    driver.quit();
  }
}
