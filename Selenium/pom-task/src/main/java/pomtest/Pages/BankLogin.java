package pomtest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BankLogin {

  WebDriver driver;
  WebElement userIDBox;
  WebElement userIDMsg;
  WebElement passwordBox;
  WebElement passwordMsg;
  WebElement loginBtn;
  WebElement resetBtn;

  public BankLogin(WebDriver Driver) {
    driver = Driver;
    userIDBox = driver.findElement(By.name("uid"));
    userIDMsg = driver.findElement(By.id("message23"));
    passwordBox = driver.findElement(By.name("password"));
    passwordMsg = driver.findElement(By.id("message18"));
    loginBtn = driver.findElement(By.name("btnLogin"));
    resetBtn = driver.findElement(By.name("btnReset"));
  }

  public String checkPasswordMsg() {
    driver.findElement(By.tagName("table")).click();
    return passwordMsg.getText();
  }

  public String checkUserMsg() {
    return userIDMsg.getText();
  }

  public void Login(String userID, String password) {
    userIDBox.sendKeys(userID);
    passwordBox.sendKeys(password);
    String passMsg = checkPasswordMsg();
    String userMsg = checkUserMsg();
    if (passMsg != "" && userMsg != "") Assert.fail(
      "PLEASE!! Enter the user ID and password"
    ); else if (passMsg != "") Assert.fail(
      "PLEASE!! Enter the password"
    ); else if (userMsg != "") Assert.fail("PLEASE!! Enter the user ID");
    loginBtn.click();
  }

  public void Reset(String userID, String password) {
    userIDBox.sendKeys(userID);
    passwordBox.sendKeys(password);
    resetBtn.click();
    Assert.assertEquals(userIDBox.getText(), "", "Reset Failed");
    Assert.assertEquals(passwordBox.getText(), "", "Reset Failed");
  }
}
