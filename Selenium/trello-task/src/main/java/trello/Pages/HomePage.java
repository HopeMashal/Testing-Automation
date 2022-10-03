package trello.Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

  WebDriver driver;
  WebElement createBtn;
  WebElement boardTitle;
  WebElement createBoardBtn;
  WebElement userBtn;
  WebElement logoutBtn;

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void ClickCreateBoard() {
    List<WebElement> Btns = driver.findElements(
      By.xpath("//*[@id='content']//ul/li/div")
    );
    createBtn = Btns.get(Btns.size() - 1);
    createBtn.click();
  }

  public void CreateBoard(String boardName) {
    boardTitle =
      driver.findElement(
        By.xpath("/html/body/div[3]/div/section/div/form/div[1]/label/input")
      );
    createBoardBtn =
      driver.findElement(
        By.xpath("/html/body/div[3]/div/section/div/form/button")
      );
    boardTitle.sendKeys(boardName);
    createBoardBtn.click();
  }
}
