package alluretask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
  WebDriver driver;
  WebElement AddressBtn;
  WebElement logoutBtn;
	
	public HomePage(WebDriver Driver) {
		driver = Driver;
    AddressBtn = driver.findElement(By.linkText("Addresses"));
    logoutBtn = driver.findElement(By.linkText("Sign out"));
	}

  public void AddressClick(){
    AddressBtn.click();
  }

  public void Logout(){
    logoutBtn.click();
  }
}
