package elementtask.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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

  public int ColumnCounter(){
    if(AddressCounter() > 0){
      List<WebElement> data = myTable.getRowsElements();
      List<WebElement> rowElements = data.get(0).findElements(By.tagName("td"));
      return rowElements.size();
    }
    return 4;
  }

  public List<WebElement[]> TableData(){
    List<WebElement[]> returnData = new ArrayList<WebElement[]>();
    WebElement[] rowData;
    List<WebElement> data = myTable.getRowsElements();
    for(WebElement row:data){
      List<WebElement> rowElements = row.findElements(By.tagName("td"));
      rowData = new WebElement[rowElements.size()];
      for(int i=0; i<rowElements.size(); i++){
        rowData[i] = rowElements.get(i);
      }
      returnData.add(rowData);
    }
    return returnData;
  }

  public List<String[]> TableDataXPath(){
    List<String[]> returnDataXPath = new ArrayList<String[]>();
    String[] rowDataXPath;
    for(int i=0; i<AddressCounter(); i++){
      rowDataXPath = new String[ColumnCounter()];
      for(int j=0; j<ColumnCounter(); j++){
        rowDataXPath[j] = "/html/body/div/table/tbody/tr["+(i+1)+"]/td["+(j+1)+"]";
      }
      returnDataXPath.add(rowDataXPath);
    }
    return returnDataXPath;
  }

  public List<String> TableDataXPath(int index){
    List<String> returnDataXPath = new ArrayList<String>();
    for(int j=0; j<ColumnCounter(); j++){
      returnDataXPath.add("/html/body/div/table/tbody/tr["+(index)+"]/td["+(j+1)+"]");
    }
    return returnDataXPath;
  }

  public void CheckAddressData(String firstName, String FNameCurVal,String lastName, String LNameCurVal, String city, String CityCurVal, String state, String StateCurVal){
    Assert.assertEquals(FNameCurVal, firstName, "First name NOT Match !!");
    Assert.assertEquals(LNameCurVal, lastName, "Last name NOT Match !!");
    Assert.assertEquals(CityCurVal, city, "City NOT Match !!");
    Assert.assertEquals(StateCurVal, state, "State NOT Match !!");
  }

}
