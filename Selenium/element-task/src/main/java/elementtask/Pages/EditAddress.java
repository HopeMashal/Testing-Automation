package elementtask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EditAddress {

  WebDriver driver;
  WebElement FName;
  WebElement LName;
  WebElement Address1;
  WebElement Address2;
  WebElement City;
  WebElement State;
  WebElement ZIP;
  WebElement Country1, Country2;
  WebElement Birthday;
  WebElement Color;
  WebElement Age;
  WebElement Website;
  WebElement Phone;
  WebElement Common1, Common2, Common3;
  WebElement Note;
  WebElement UpdateBtn;

  public EditAddress(WebDriver Driver) {
    driver = Driver;
    FName = driver.findElement(By.name("address[first_name]"));
    LName = driver.findElement(By.name("address[last_name]"));
    Address1 = driver.findElement(By.name("address[address1]"));
    Address2 = driver.findElement(By.name("address[address2]"));
    City = driver.findElement(By.name("address[city]"));
    State = driver.findElement(By.name("address[state]"));
    ZIP = driver.findElement(By.name("address[zip_code]"));
    Country1 = driver.findElement(By.id("address_country_us"));
    Country2 = driver.findElement(By.id("address_country_canada"));
    Birthday = driver.findElement(By.name("address[birthday]"));
    Color = driver.findElement(By.name("address[color]"));
    Age = driver.findElement(By.name("address[age]"));
    Website = driver.findElement(By.name("address[website]"));
    Phone = driver.findElement(By.name("address[phone]"));
    Common1 =
      driver.findElement(By.xpath("/html/body/div/form/div[15]/input[2]"));
    Common2 =
      driver.findElement(By.xpath("/html/body/div/form/div[15]/input[4]"));
    Common3 =
      driver.findElement(By.xpath("/html/body/div/form/div[15]/input[6]"));
    Note = driver.findElement(By.name("address[note]"));
    UpdateBtn = driver.findElement(By.name("commit"));
  }

  public void FillData(
    String firstName,
    String lastName,
    String address1,
    String address2,
    String city,
    String state,
    String zip,
    String country,
    String birthday,
    String color,
    String age,
    String website,
    String phone,
    String common,
    String note
  ) {
    FName.clear();
    LName.clear();
    Address1.clear();
    Address2.clear();
    City.clear();
    ZIP.clear();
    // if(Country1.isSelected()) Country1.click();
    // if(Country2.isSelected()) Country2.click();
    Birthday.clear();
    Color.clear();
    Age.clear();
    Website.clear();
    Phone.clear();
    if (Common1.isSelected()) Common1.click();
    if (Common2.isSelected()) Common2.click();
    if (Common3.isSelected()) Common3.click();
    Note.clear();

    FName.sendKeys(firstName);
    LName.sendKeys(lastName);
    Address1.sendKeys(address1);
    Address2.sendKeys(address2);
    City.sendKeys(city);
    Select states = new Select(State);
    states.selectByValue(state);
    ZIP.sendKeys(zip);
    if (country.equals("us")) Country1.click();
    if (country.equals("canada")) Country2.click();
    Birthday.sendKeys(birthday);
    Color.sendKeys(color);
    Age.sendKeys(age);
    Website.sendKeys(website);
    Phone.sendKeys(phone);
    String[] Commons = common.split(" ");
    for (int i = 0; i < Commons.length; i++) {
      if (Integer.parseInt(Commons[i]) == 1) {
        Common1.click();
      } else if (Integer.parseInt(Commons[i]) == 2) {
        Common2.click();
      } else if (Integer.parseInt(Commons[i]) == 3) {
        Common3.click();
      }
    }
    Note.sendKeys(note);
    UpdateBtn.click();
  }

  public void CheckAddressData(
    String firstName,
    String lastName,
    String city,
    String state
  ) {
    Assert.assertEquals(
      FName.getAttribute("value"),
      firstName,
      "First name NOT Match !!"
    );
    Assert.assertEquals(
      LName.getAttribute("value"),
      lastName,
      "Last name NOT Match !!"
    );
    Assert.assertEquals(City.getAttribute("value"), city, "City NOT Match !!");
    //Assert.assertEquals(State.getAttribute("value"), state, "State NOT Match !!");
  }
}
