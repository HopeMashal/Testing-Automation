package elementtask.Pages;

import java.util.ArrayList;
import java.util.List;

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

  /* public List<String[]> TableData(){
    List<String[]> returnData = new ArrayList<String[]>();
    String[] rowData;
    List<WebElement> data = myTable.getRowsElements();
    for(WebElement row:data){
      List<WebElement> rowElements = row.findElements(By.tagName("td"));
      rowData = new String[rowElements.size()-3];
      for(int i=0; i<rowElements.size()-3; i++){
        rowData[i] = rowElements.get(i).getText();
      }
      returnData.add(rowData);
    }
    return returnData;
  } */

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

  public void ShowClick(WebElement showBtn){
    showBtn.click();
  }

  public void EditClick(WebElement editBtn){
    editBtn.click();
  }

  public void DestroyClick(WebElement destroyBtn){
    destroyBtn.click();
  }

}
