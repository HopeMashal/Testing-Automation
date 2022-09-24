package trello.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPassword {
  WebDriver driver;
  WebElement passwordBox;
  WebElement loginBtn;
  
  public LoginPassword(WebDriver driver) {
    this.driver = driver;
    passwordBox = driver.findElement(By.name("password"));
    loginBtn = driver.findElement(By.id("login-submit"));
  }

  public void Login(String password){
    passwordBox.sendKeys(password);
    loginBtn.click();
  }
}
