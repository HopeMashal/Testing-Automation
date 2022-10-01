package alluretask;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import alluretask.Core.OpenBrowser;
import alluretask.Pages.HomePage;
import alluretask.Pages.SignIn;

public class AllureTask {
  static WebDriver driver;
  static String Email;
  static String Password;
  static String URL = "http://a.testaddressbook.com";
  
  @BeforeTest
  public void beforeTest() throws IOException{
    FileReader readFile=new FileReader("file.properties");
		Properties prop= new Properties();
		prop.load(readFile);
		Email = prop.getProperty("email");
		Password = prop.getProperty("password");
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
