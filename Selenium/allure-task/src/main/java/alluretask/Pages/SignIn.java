package alluretask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {
  WebDriver driver;
	WebElement emailBox;
	WebElement passwordBox;
  WebElement loginBtn;
	
	public SignIn(WebDriver Driver) {
		driver = Driver;
    emailBox = driver.findElement(By.name("session[email]"));
    passwordBox = driver.findElement(By.name("session[password]"));
    loginBtn = driver.findElement(By.name("commit"));
	}
	
	public void Login(String email, String password) {
		emailBox.sendKeys(email);
    passwordBox.sendKeys(password);
		loginBtn.click();
	}
}
