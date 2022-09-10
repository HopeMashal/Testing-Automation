import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest {
	static String Browser;
    static String CSVPath;
    static WebDriver driver;
    
    @Parameters({"Browser","CSVPath"})
    @BeforeSuite
    public void beforeSuite(String Browser,String CSVPath){
    	SearchTest.Browser = Browser;
    	SearchTest.CSVPath = CSVPath;
    	driver = OpenBrowser.openBrowser(SearchTest.Browser);
		driver.manage().window().maximize();
    }

    @DataProvider
    public static Object[][] getDataCSV() throws Exception{
        List<String[]> lines = CSVFile.readAllLines(SearchTest.CSVPath);
        lines.remove(0);
        Object[][] data = new Object[lines.size()][lines.get(0).length];
        int index = 0;
        for(String[] line: lines){
            data[index] = line;
            index++;
        }
        return data;
    }


    @Test(dataProvider = "getDataCSV")
    public void testDataCSV(String Search) throws InterruptedException{
    	driver.get("https://www.bing.com/");
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys(Search);
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		btn.click();
		String result = driver.findElement(By.className("b_title")).getText();
		Assert.assertEquals(result, Search);
		Thread.sleep(5000);
		//driver.navigate().back();
    }
    
    @AfterSuite
    public void afterSuite() {
    	driver.close();
    }
    
    
//	@Parameters({"Browser","Search"})
//    @Test
//    public void checkBrowser(String Browser, String Search) throws InterruptedException{
//		WebDriver driver = OpenBrowser.openBrowser(Browser);
//		driver.manage().window().maximize();
//		driver.get("https://www.bing.com/");
//		Thread.sleep(5000);
//		WebElement searchBar = driver.findElement(By.name("q"));
//		searchBar.sendKeys(Search);
//		WebElement btn = driver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
//		btn.click();
//		String result = driver.findElement(By.className("b_title")).getText();
//		Assert.assertEquals(result, Search);
//		Thread.sleep(5000);
//		driver.quit();
//    }
}
