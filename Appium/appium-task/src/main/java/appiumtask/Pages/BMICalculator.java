package appiumtask.Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BMICalculator {
  AndroidDriver<AndroidElement> driver;

  public BMICalculator(AndroidDriver<AndroidElement> driver) {
    this.driver = driver;
  }

  public void clickOK() {
    AndroidElement btn = driver.findElement(MobileBy.xpath("//*[@text='OK']"));
    btn.click();
  }

  public void fullData(String heightFT, String heightIn, String heightUnit, String weight, String weightUnit) {
    AndroidElement heightBox1 = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/height_input_1"));
    heightBox1.sendKeys(heightFT);
    AndroidElement heightBox2 = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/height_input_2"));
    heightBox2.sendKeys(heightIn);
    AndroidElement weightBox = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/weight_input_1"));
    weightBox.sendKeys(weight);
    AndroidElement calculateBtn = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/calculate_button"));
    calculateBtn.click();
  }

  public void clearAll() {
    AndroidElement heightBox1 = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/height_input_1"));
    heightBox1.clear();
    AndroidElement heightBox2 = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/height_input_2"));
    heightBox2.clear();
    AndroidElement weightBox = driver.findElement(MobileBy.id("com.codium.bmicalculator:id/weight_input_1"));
    weightBox.clear();
  }
}
