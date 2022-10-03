package pomtest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BankHome {

  WebDriver driver;
  WebElement logoutBtn;

  public BankHome(WebDriver Driver) {
    driver = Driver;
    logoutBtn = driver.findElement(By.linkText("Log out"));
  }

  public void Logout() {
    logoutBtn.click();
  }
}
