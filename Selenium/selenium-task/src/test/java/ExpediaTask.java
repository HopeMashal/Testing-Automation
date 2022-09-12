import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExpediaTask {
    static String Browser;
    static String CSVPath;
    static WebDriver driver;
    static List<String[]> ResultList;
    static List<Integer> NumberList;
    
    @Parameters({"Browser","CSVPath"})
    @BeforeSuite
    public void beforeSuite(String Browser,String CSVPath){
    	SearchTest.Browser = Browser;
    	SearchTest.CSVPath = CSVPath;
      ResultList = new ArrayList<String[]>();
      NumberList = new ArrayList<Integer>();
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
    public void testDataCSV(String Id, String Name, String City, String Address) throws InterruptedException{
    	  driver.get("https://www.bing.com/");
		    WebElement searchBar = driver.findElement(By.name("q"));
        String Search = Name + " " + City + " expedia";
		    searchBar.sendKeys(Search);
		    WebElement btn = driver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		    btn.click();
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"b_results\"]//*/h2/a")); 
        ArrayList<String> URLList = new ArrayList<String>();
        int counter = 0;
        for(WebElement result: results) {
            String URL = result.getAttribute("href");
            if(URL.contains("https://www.expedia.com/") && URL.contains(".Hotel-Information")){
                URLList.add(URL);
                counter+=1;
            } 
        }
        NumberList.add(counter);
        if(counter == 0){
            int Size = URLList.size() + 1;
            String[] resultList = new String[Size];
            resultList[0] = Id;
            resultList[1] = Name;
            resultList[2] = City;
            resultList[3] = Address;
            resultList[4] = "Hotel NOT Found";
            ResultList.add(resultList);
        } else {
            int Size = URLList.size() + 4;
            String[] resultList = new String[Size];
            resultList[0] = Id;
            resultList[1] = Name;
            resultList[2] = City;
            resultList[3] = Address;
            for(int i=0; i < URLList.size(); i++){
                resultList[i+4] = URLList.get(i);
            }
            ResultList.add(resultList);
        }
    }
    
    @AfterSuite
    public void afterSuite() {
    	driver.quit();
        Integer maxNumber = Collections.max(NumberList);
        String[] NumList = new String[maxNumber];
        for(int i=0; i<maxNumber; i++){
            NumList[i] = "Link"+(i+1);
        }
        String[] myStr = new String[]{"hotel_id","name","city","address"};
        int aLen = myStr.length;
        int bLen = NumList.length;
        String[] result = new String[aLen + bLen];
        System.arraycopy(myStr, 0, result, 0, aLen);
        System.arraycopy(NumList, 0, result, aLen, bLen);
        CSVFile.writeDataLineByLine("output.csv", ResultList, result);
    }
}
