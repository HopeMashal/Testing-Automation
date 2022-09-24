package trello.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginEmail {
  WebDriver driver;
  WebElement emailBox;
  WebElement continueBtn;
  
  public LoginEmail(WebDriver driver) {
    this.driver = driver;
    emailBox = driver.findElement(By.name("user"));
    continueBtn = driver.findElement(By.id("login"));
  }

  public void Continue(String email){
    emailBox.sendKeys(email);
    continueBtn.click();
  }
}
