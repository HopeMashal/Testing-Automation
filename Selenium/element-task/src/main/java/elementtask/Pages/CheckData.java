package elementtask.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckData {
  WebDriver driver;
	WebElement FName;
  WebElement LName;
  WebElement Address1;
  WebElement Address2;
  WebElement City;
  WebElement State;
  WebElement ZIP;
  WebElement Country;
  WebElement Birthday;
  WebElement Color;
  WebElement Age;
  WebElement Website;
  WebElement Phone;
  WebElement Common1, Common2, Common3;
  WebElement Note;
  WebElement EditBtn;
  WebElement ListBtn;

  public CheckData(WebDriver Driver) {
    driver = Driver;
    FName = driver.findElement(By.xpath("/html/body/div/p[1]/span[2]"));
    LName = driver.findElement(By.xpath("/html/body/div/p[2]/span[2]"));
    Address1 = driver.findElement(By.xpath("/html/body/div/p[3]/span[2]"));
    Address2 = driver.findElement(By.xpath("/html/body/div/p[4]/span[2]"));
    City = driver.findElement(By.xpath("/html/body/div/p[5]/span[2]"));
    State = driver.findElement(By.xpath("/html/body/div/p[6]/span[2]"));
    ZIP = driver.findElement(By.xpath("/html/body/div/p[7]/span[2]"));
    Country = driver.findElement(By.xpath("/html/body/div/p[8]/span[2]"));
    Birthday = driver.findElement(By.xpath("/html/body/div/p[9]/span[2]"));
    Color = driver.findElement(By.xpath("/html/body/div/p[10]/span[2]"));
    Age = driver.findElement(By.xpath("/html/body/div/p[11]/span[2]"));
    Website = driver.findElement(By.xpath("/html/body/div/p[12]/span[2]"));
    Phone = driver.findElement(By.xpath("/html/body/div/p[13]/span[2]"));
    Common1 = driver.findElement(By.xpath("/html/body/div/p[14]/span[2]"));
    Common2 = driver.findElement(By.xpath("/html/body/div/p[15]/span[2]"));
    Common3 = driver.findElement(By.xpath("/html/body/div/p[16]/span[2]"));
    Note = driver.findElement(By.xpath("/html/body/div/p[17]/span[2]"));
    EditBtn = driver.findElement(By.linkText("Edit"));
    ListBtn = driver.findElement(By.linkText("List"));
  }

  public void CheckAddressData(String firstName,String lastName, String address1, String address2, String city, String state, String zip, String country, String birthday, String color, String age, String website, String phone, String common, String note){
    if(FName.getText() != firstName) Assert.fail("First name NOT Match !!");
    if(LName.getText() != lastName) Assert.fail("Last name NOT Match !!");
    if(Address1.getText() != address1) Assert.fail("Address 1 NOT Match !!");
    if(Address2.getText() != address2) Assert.fail("Address 2 NOT Match !!");
    if(City.getText() != city) Assert.fail("City NOT Match !!");
    if(State.getText() != state) Assert.fail("State NOT Match !!");
    if(ZIP.getText() != zip) Assert.fail("ZIP Code NOT Match !!");
    if(Country.getText() != country) Assert.fail("Country NOT Match !!");
    if(Birthday.getText() != birthday) Assert.fail("Birthday NOT Match !!");
    if(Color.getText() != color) Assert.fail("Color NOT Match !!");
    if(Age.getText() != age) Assert.fail("Age NOT Match !!");
    if(Website.getText() != website) Assert.fail("Website NOT Match !!");
    if(Phone.getText() != phone) Assert.fail("Phone NOT Match !!");
    String[] Commons = common.split(" ");
    for(int i=0; i<Commons.length; i++){
      if(Integer.parseInt(Commons[i]) == 1){
        if(Common1.getText().toUpperCase() != "YES") Assert.fail("Common Interests 1 NOT Match !!");
      } else if(Integer.parseInt(Commons[i]) == 2){
        if(Common2.getText().toUpperCase() != "YES") Assert.fail("Common Interests 2 NOT Match !!");
      } else if(Integer.parseInt(Commons[i]) == 3){
        if(Common3.getText().toUpperCase() != "YES") Assert.fail("Common Interests 3 NOT Match !!");
      }
    }
    if(Note.getText() != note) Assert.fail("Note NOT Match !!");
  }

  public void ListClick(){
    ListBtn.click();
  }

  public void EditClick(){
    EditBtn.click();
  }
}
