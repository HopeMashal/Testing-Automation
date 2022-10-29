package appiumtask;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import appiumtask.Core.CSVFile;
import appiumtask.Core.TakeScreenShot;
import appiumtask.Pages.BMICalculator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {
  AndroidDriver<AndroidElement> driver;
  static String CSVFilesPath = "./src/files/";
  static String InputFile = "input.csv";

  @BeforeSuite
  public void beforeSuite() throws IOException {
    String path = new File("BMI Calculator_2.3.1_Apkpure.apk").getAbsoluteFile().getCanonicalFile().getPath();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
    capabilities.setCapability("app", path);
    capabilities.setCapability("appPackage", "com.codium.bmicalculator");
    driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
  }

  @DataProvider
  public static Object[][] getInputCSVData() throws Exception {
    String inputFile = CSVFilesPath + InputFile;
    List<String[]> lines = CSVFile.readAllLines(inputFile);
    lines.remove(0);
    Object[][] data = new Object[lines.size()][lines.get(0).length];
    int index = 0;
    for (String[] line : lines) {
      data[index] = line;
      index++;
    }
    return data;
  }

  @Test(dataProvider = "getInputCSVData")
  public void AppiumTestTask(String heightFt, String heightIn, String heightUnit, String weight, String weightUnit)
      throws IOException {
    BMICalculator bmiPage = new BMICalculator(driver);
    bmiPage.clickOK();
    bmiPage.fullData(heightFt, heightIn, heightUnit, weight, weightUnit);
    TakeScreenShot.takeScreenShot(driver,
        CSVFilesPath + "heightFT_" + heightFt + "_heightIn_" + heightIn + "_weight_" + weight + ".jpg");
    bmiPage.clearAll();
  }

  @AfterSuite
	public void afterSuite() {
		driver.quit();
	}
}
