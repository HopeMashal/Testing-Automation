package alluretask;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import alluretask.Core.OpenBrowser;
import alluretask.Core.TakeScreenShot;
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
    driver.manage().window().setSize(new Dimension(1200,800));
    driver.get(URL+"/sign_in");
  }

  @Test
  public void AllureTest() throws InterruptedException, IOException{
    System.out.println("Email:- "+Email);
    System.out.println("Password:- "+Password);
    SignIn signIn = new SignIn(driver);
    signIn.Login(Email, Password);
    Thread.sleep(5000);
    TakeScreenShot takeSc = new TakeScreenShot(driver);
		takeSc.takeScreenShot("AfterSignIn.jpg");
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
    Thread.sleep(5000);
  }

  @AfterTest
  public void afterTest(){
    driver.quit();
  }
}
