package trello.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
  WebDriver driver;
  WebElement createBtn;
  WebElement boardTitle;
  WebElement createBoardBtn;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    createBtn = driver.findElements(By.xpath("//*[@id='content']//ul/li/div")).get(2);
  }

  public void ClickCreateBoard(){
    createBtn.click();
  }

  public void CreateBoard(String boardName){
    boardTitle = driver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/div[1]/label/input"));
    createBoardBtn = driver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/button"));
    boardTitle.sendKeys(boardName);
    createBoardBtn.click();
  }
}
