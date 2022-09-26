package trello;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import trello.Core.OpenBrowser;
import trello.Pages.BoardPage;
import trello.Pages.HomePage;
import trello.Pages.LoginEmail;
import trello.Pages.LoginPassword;

public class TrelloTask {
  static String Browser;
  static String CSVPath;
  static WebDriver driver;
  static String URL = "https://trello.com";
  String firstWindow, secondWindow;
  String BoardName = "HopeTest";
  String Card1Name = "Testing";
  String Card1Description = "Testing Description";
  String Card2Name = "Testing :)";
  
  @Parameters({"Browser","CSVPath"})
  @BeforeSuite
  public void beforeSuite(String Browser,String CSVPath) throws InterruptedException{
    TrelloTask.Browser = Browser;
    TrelloTask.CSVPath = CSVPath;
    //driver = OpenBrowser.openBrowser(TrelloTask.Browser);
    driver = OpenBrowser.openChromeWithOptions();
    driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    firstWindow = driver.getWindowHandle();
    driver.get(URL+"/login");
    LoginEmail loginEmail = new LoginEmail(driver);
    loginEmail.Continue("mashal.hope@gmail.com");
    Thread.sleep(2000);
    LoginPassword loginPassword = new LoginPassword(driver);
    loginPassword.Login("**");
  }

  @Test
  public void trelloTest() throws Exception{
    HomePage homePage = new HomePage(driver);
    Thread.sleep(4000);
    homePage.ClickCreateBoard();
    Thread.sleep(2000);
    homePage.CreateBoard(BoardName);
    Thread.sleep(2000);
    BoardPage boardPage = new BoardPage(driver);
    Thread.sleep(2000);
    boardPage.CreateCard(Card1Name);
    Thread.sleep(2000);
    boardPage.EditDescription(Card1Description);
    Thread.sleep(2000);
    boardPage.AttachFile(CSVPath);
    Thread.sleep(4000);
    boardPage.DownloadFile();
    Thread.sleep(2000);
    boardPage.CheckFiles(CSVPath);
    Thread.sleep(2000);
    String URL = driver.getCurrentUrl();
    driver.switchTo().newWindow(WindowType.TAB);
    driver.get(URL);
    Thread.sleep(2000);
    secondWindow = driver.getWindowHandle();
    driver.switchTo().window(firstWindow);
    Thread.sleep(2000);
    boardPage.CreateNewCard(Card2Name);
    Thread.sleep(2000);
    driver.switchTo().window(secondWindow);
    Thread.sleep(2000);
    boardPage.CheckCard(Card2Name);
    Thread.sleep(2000);
    boardPage.CreateCardUsingActions();
    Thread.sleep(2000);
    driver.switchTo().window(firstWindow);
    Thread.sleep(2000);
    boardPage.CheckCard(Card1Name);
    Thread.sleep(2000);
    boardPage.DeleteCard();
    Thread.sleep(2000);
  }

  @AfterSuite
  public void afterSuite() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
  }

}
