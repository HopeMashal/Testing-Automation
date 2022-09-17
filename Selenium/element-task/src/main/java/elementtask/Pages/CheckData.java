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
    Assert.assertEquals(FName.getText(), firstName, "First name NOT Match !!");
    Assert.assertEquals(LName.getText(), lastName, "Last name NOT Match !!");
    Assert.assertEquals(Address1.getText(), address1, "Address 1 NOT Match !!");
    Assert.assertEquals(Address2.getText(), address2, "Address 2 NOT Match !!");
    Assert.assertEquals(City.getText(), city, "City NOT Match !!");
    Assert.assertEquals(State.getText(), state, "State NOT Match !!");
    Assert.assertEquals(ZIP.getText(), zip, "ZIP Code NOT Match !!");
    Assert.assertEquals(Country.getText(), country, "Country NOT Match !!");
    Assert.assertEquals(Birthday.getText(), birthday, "Birthday NOT Match !!");
    //Assert.assertEquals(Color.getAttribute("style"), ("width: 25px; height: 25px; background: "+color), "Color NOT Match !!");
    Assert.assertEquals(Age.getText(), age, "Age NOT Match !!");
    Assert.assertEquals(Website.getText(), website, "Website NOT Match !!");
    Assert.assertEquals(Phone.getText(), phone, "Phone NOT Match !!");
    String[] Commons = common.split(" ");
    for(int i=0; i<Commons.length; i++){
      if(Integer.parseInt(Commons[i]) == 1){
        Assert.assertEquals(Common1.getText().toUpperCase(), "YES", "Common Interests 1 NOT Match !!");
      } else if(Integer.parseInt(Commons[i]) == 2){
        Assert.assertEquals(Common2.getText().toUpperCase(), "YES", "Common Interests 2 NOT Match !!");
      } else if(Integer.parseInt(Commons[i]) == 3){
        Assert.assertEquals(Common3.getText().toUpperCase(), "YES", "Common Interests 3 NOT Match !!");
      }
    }
    Assert.assertEquals(Note.getText(), note, "Note NOT Match !!");
  }

  public void ListClick(){
    ListBtn.click();
  }

  public void EditClick(){
    EditBtn.click();
  }
}
