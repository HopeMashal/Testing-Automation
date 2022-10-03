package alluretask;

import alluretask.Core.OpenBrowser;
import alluretask.Core.TakeScreenShot;
import alluretask.Pages.HomePage;
import alluretask.Pages.SignIn;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AllureTask {

  static WebDriver driver;
  static String Email;
  static String Password;
  static String URL = "http://a.testaddressbook.com";

  @BeforeTest
  public void beforeTest() throws IOException {
    FileReader readFile = new FileReader("file.properties");
    Properties prop = new Properties();
    prop.load(readFile);
    Email = prop.getProperty("email");
    Password = prop.getProperty("password");
    driver = OpenBrowser.openChromeWithOptions();
    driver.manage().window().setSize(new Dimension(1200, 800));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    driver.get(URL + "/sign_in");
  }

  @Test
  public void AllureTest() throws InterruptedException, IOException {
    System.out.println("Email:- " + Email);
    System.out.println("Password:- " + Password);
    TakeScreenShot takeSc = new TakeScreenShot(driver);
    SignIn signIn = new SignIn(driver);
    File SignPage = takeSc.takeScreenShot("BeforeSignIn.jpg");
    Allure.addAttachment(
      SignPage.getName(),
      FileUtils.openInputStream(SignPage)
    );
    signIn.Login(Email, Password);
    File HomePage = takeSc.takeScreenShot("AfterSignIn.jpg");
    Allure.addAttachment(
      HomePage.getName(),
      FileUtils.openInputStream(HomePage)
    );
    HomePage homePage = new HomePage(driver);
    homePage.Logout();
  }

  @AfterTest
  public void afterTest() {
    driver.quit();
  }
}
