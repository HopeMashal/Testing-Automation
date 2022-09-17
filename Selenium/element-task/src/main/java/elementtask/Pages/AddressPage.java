package elementtask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import elementtask.Core.Table;

public class AddressPage {
  WebDriver driver;
	WebElement newAddressBtn;
  WebElement table;
  Table myTable;

  public AddressPage(WebDriver Driver) {
    driver = Driver;
    newAddressBtn = driver.findElement(By.linkText("New Address"));
    table = driver.findElement(By.className("table"));
    myTable = new Table(driver, table);
  }
  
  public void newAddressClick(){
    newAddressBtn.click();
  }

  public int AddressCounter(){
    return myTable.getRowNumber();
  }
}
