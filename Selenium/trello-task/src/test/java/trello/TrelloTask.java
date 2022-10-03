package trello;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import trello.Core.OpenBrowser;
import trello.Core.TakeScreenShot;
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
  String CSVDownloadPath;

  @Parameters({ "CSVPath" })
  @BeforeSuite
  public void beforeSuite(String CSVPath) {
    TrelloTask.CSVPath = CSVPath;
  }

  @Parameters({ "Browser" })
  @BeforeTest
  public void beforeTest(String Browser) throws InterruptedException {
    TrelloTask.Browser = Browser;
    if (TrelloTask.Browser.equals("chrome")) driver =
      OpenBrowser.openChromeWithOptions(); else if (
      TrelloTask.Browser.equals("firefox")
    ) driver = OpenBrowser.openFireFoxWithOptions(); else driver =
      OpenBrowser.openBrowser(TrelloTask.Browser);
    driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    firstWindow = driver.getWindowHandle();
    driver.get(URL + "/login");
    LoginEmail loginEmail = new LoginEmail(driver);
    loginEmail.Continue("mashal.hope@gmail.com");
    Thread.sleep(4000);
    LoginPassword loginPassword = new LoginPassword(driver);
    loginPassword.Login("**");
  }

  @Test
  public void trelloTest() throws Exception {
    HomePage homePage = new HomePage(driver);
    Thread.sleep(8000);
    homePage.ClickCreateBoard();
    Thread.sleep(2000);
    homePage.CreateBoard(BoardName);
    Thread.sleep(2000);
    BoardPage boardPage = new BoardPage(driver);
    Thread.sleep(2000);
    TakeScreenShot takeSc = new TakeScreenShot(driver);
    takeSc.takeScreenShot(
      "downloads/" + TrelloTask.Browser + "BeforeCardCreation.jpg"
    );
    Thread.sleep(2000);
    boardPage.CreateCard(Card1Name);
    Thread.sleep(2000);
    takeSc.takeScreenShot(
      "downloads/" + TrelloTask.Browser + "AfterCardCreation.jpg"
    );
    Thread.sleep(2000);
    boardPage.EditDescription(Card1Description);
    Thread.sleep(2000);
    takeSc.takeScreenShot(
      "downloads/" + TrelloTask.Browser + "BeforeAttachFile.jpg"
    );
    Thread.sleep(2000);
    boardPage.AttachFile(CSVPath);
    Thread.sleep(6000);
    takeSc.takeScreenShot(
      "downloads/" + TrelloTask.Browser + "AfterAttachFile.jpg"
    );
    Thread.sleep(2000);
    if (
      TrelloTask.Browser.equals("firefox") ||
      TrelloTask.Browser.equals("chrome")
    ) {
      boardPage.DownloadFile();
      Thread.sleep(2000);
      if (TrelloTask.Browser.equals("chrome")) CSVDownloadPath =
        "./downloads/input.csv"; else CSVDownloadPath =
        "./downloads/input(1).csv";
      boardPage.CheckFiles(CSVPath, CSVDownloadPath);
    } else {
      boardPage.CloseEditCard();
    }
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
    int cardsNumber1 = boardPage.CardNumber();
    Thread.sleep(2000);
    boardPage.DeleteCard();
    Thread.sleep(2000);
    int cardsNumber2 = boardPage.CardNumber();
    Thread.sleep(2000);
    Assert.assertEquals(
      cardsNumber2,
      cardsNumber1 - 1,
      "Cards Number NOT Match"
    );
    driver.switchTo().window(secondWindow);
    Thread.sleep(2000);
    int cardsNumber3 = boardPage.CardNumber();
    Thread.sleep(2000);
    Assert.assertEquals(cardsNumber3, cardsNumber2, "Cards Number NOT Match");
    Thread.sleep(2000);
    boardPage.DeleteBoard();
    Thread.sleep(2000);
    takeSc.takeScreenShot(
      "downloads/" + TrelloTask.Browser + "AfterDeleteBoard.jpg"
    );
    Thread.sleep(2000);
  }

  @AfterTest
  public void afterTest() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
  }
}
