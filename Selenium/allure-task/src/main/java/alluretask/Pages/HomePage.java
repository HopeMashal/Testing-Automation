package alluretask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
  WebDriver driver;
  WebElement logoutBtn;
	
	public HomePage(WebDriver Driver) {
		driver = Driver;
    logoutBtn = driver.findElement(By.linkText("Sign out"));
	}

  public void Logout(){
    logoutBtn.click();
  }
}
