package trello.Pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import trello.Core.CSVFile;

public class BoardPage {
  WebDriver driver;
  WebElement createCard;
  WebElement createBox;
  WebElement addBtn;
  WebElement cardElement;
  WebElement descriptionBox;
  WebElement saveBtn;
  WebElement attachBtn;
  WebElement computerBtn;
  WebElement downloadFile;
  WebElement closeBtn;
  List<WebElement> Cards;
  WebElement LastCard;
  WebElement titleBox;
  WebElement archiveBtn;

  public BoardPage(WebDriver driver) {
    this.driver = driver;
  }

  public void CreateCard(String titleCard){
    createBox = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea"));
    createBox.sendKeys(titleCard);
    addBtn = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[2]/div[1]/input"));
    addBtn.click();
  }

  public void CreateNewCard(String titleCard){
    createCard = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[3]/a"));
    createCard.click();
    createBox = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea"));
    createBox.sendKeys(titleCard);
    addBtn = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[2]/div[1]/input"));
    addBtn.click();
  }

  public void CreateCardUsingActions() throws InterruptedException{
    cardElement = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a[1]/div[3]"));
    cardElement.click();
    Actions actions = new Actions(driver);
    Thread.sleep(4000);
    titleBox = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/div/div[3]/div[1]/textarea"));
    actions.doubleClick(titleBox).perform();
    actions.keyDown(Keys.CONTROL);
		actions.sendKeys("c");
		actions.keyUp(Keys.CONTROL);
    actions.build().perform();
    closeBtn = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/a"));
    closeBtn.click();
    Thread.sleep(2000);
    createCard = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[3]/a"));
    createCard.click();
    createBox = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/div/div[1]/div/textarea"));
    createBox.click();
    actions.keyDown(Keys.CONTROL);
		actions.sendKeys("v");
		actions.keyUp(Keys.CONTROL);
    actions.build().perform();
    createBox.sendKeys(Keys.ENTER);
  }

  public void EditDescription(String description) throws InterruptedException{
    cardElement = driver.findElement(By.xpath("//*[@id='board']/div[1]/div/div[2]/a/div[3]"));
    cardElement.click();
    Thread.sleep(2000);
    descriptionBox = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/div/div[4]/div[2]/div/div/div/div[2]/div/div/div[3]/textarea"));
    descriptionBox.sendKeys(description);
    saveBtn = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/div/div[4]/div[2]/div/div/div/div[2]/div/div/div[3]/div/input[1]"));
    saveBtn.click();
  }

  public void AttachFile(String CSVPath){
    attachBtn = driver.findElement(By.linkText("Attachment"));
    attachBtn.click();
    computerBtn = driver.findElement(By.name("file"));
    File file = new File(CSVPath);
    computerBtn.sendKeys(file.getAbsolutePath());
  }

  public void DownloadFile() throws InterruptedException{
    downloadFile = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/div/div[4]/div[9]/div[2]/div/div/p/a"));
    downloadFile.click();
    Thread.sleep(2000);
    closeBtn = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/a"));
    closeBtn.click();
  }

  public void CheckFiles(String CSVPath) throws Exception{
    List<String[]> CSVFileLines = CSVFile.readAllLines(CSVPath);
    List<String[]> downloadLines = CSVFile.readAllLines("./downloads/input.csv");
    String[] strList;
    for(int i=0; i<CSVFileLines.size(); i++){
      strList = CSVFileLines.get(i);
      for(int j=0; j<strList.length; j++){
        Assert.assertEquals(CSVFileLines.get(i)[j], downloadLines.get(i)[j], "Two files NOT Match !!!");
      }
    }
  }

  public void CheckCard(String CardName){
    Cards = driver.findElements(By.xpath("//*[@id='board']/div[1]/div/div[2]/a/div[3]/span"));
    LastCard = Cards.get(Cards.size()-1);
    Assert.assertEquals(LastCard.getText(), CardName, "Card Name NOT Match");
  }

  public void DeleteCard() throws InterruptedException{
    Cards = driver.findElements(By.xpath("//*[@id='board']/div[1]/div/div[2]/a/div[3]/span"));
    LastCard = Cards.get(Cards.size()-1);
    LastCard.click();
    Thread.sleep(2000);
    archiveBtn = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/div/div[5]/div[5]/div/a[5]"));
    archiveBtn.click();
    Thread.sleep(2000);
    closeBtn = driver.findElement(By.xpath("//*[@id='chrome-container']/div[3]/div/div/a"));
    closeBtn.click();
  }

  public int CardNumber(){
    Cards = driver.findElements(By.xpath("//*[@id='board']/div[1]/div/div[2]/a/div[3]/span"));
    return Cards.size();
  }

}
