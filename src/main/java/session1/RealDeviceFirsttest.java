// Planon Enterprise Edition Source file: RealDeviceFirsttest.java
// Copyright Planon 1997-2023. All Rights Reserved.
package session1;

import io.appium.java_client.android.*;
import io.appium.java_client.android.nativekey.*;
import io.appium.java_client.remote.*;

import org.apache.commons.io.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;

/**
 * DOCUMENT ME!
 */
public class RealDeviceFirsttest
{
  //~ Static Variables & Initializers --------------------------------------------------------------

  private static WebDriverWait wait;

  public static String FILELOCATION = "C:\\RealDevice Testing\\outputfiles\\";

  //~ Instance Variables ---------------------------------------------------------------------------

  private AndroidDriver driver;

  //~ Methods --------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws MalformedURLException DOCUMENT ME!
   */
  @Before public void setUp() throws MalformedURLException
  {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
    cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

    File appDirectory = new File("C:\\RealDevice Testing\\PlanonLive_API30_Debug.apk");
    cap.setCapability(MobileCapabilityType.APP, appDirectory.getAbsolutePath());
    cap.setCapability("appPackage", "com.planonsoftware.mobileplatform");
    cap.setCapability("appActivity", ".MainActivity");
//    cap.setCapability("fullReset", false);
//    cap.setCapability("noReset", true);

    cap.setCapability("autoGrantPermissions", true);

    URL url = new URL("http://127.0.0.1:4545");
    driver = new AndroidDriver(url, cap);

    wait = new WebDriverWait(driver, Duration.ofSeconds(120));
  }


  /**
   * quit the driver after the test run
   */
  @After public void tearDown()
  {
    driver.quit();
  }


  /**
   * My first test case
   *
   * @throws IOException
   * @throws InterruptedException
   */
  @Test public void firstCase() throws IOException, InterruptedException
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.EditText")));

    driver.findElement(By.className("android.widget.EditText")).sendKeys("https://uagant2256-prod.plnd.cloud/");
    driver.findElement(By.xpath("//android.widget.Button[@text='Login']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='j_username']"))).sendKeys("supervisor");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='j_password']"))).sendKeys("a");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='Login']"))).click();

    clickOnButton("More");
    clickOnButton("wa");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys("521.00");
    new Actions(driver).sendKeys(Keys.ENTER).perform();

    clickWidgetTextView("521.00");

    takeScreenShot(FILELOCATION, "InsummaryPageScreenshot.png");

    clickOnButton("Accept");
    clickOnButton("Work");
    waitForPage("Work assignment details");
    clickOnButton("Submit");
    clickWidgetTextView("Images");
    clickOnButton("Add image");
    clickWidgetTextView("Take photo");
    Thread.sleep(1000);
    driver.pressKey(new KeyEvent(AndroidKey.CAMERA));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@resource-id='com.android.camera2:id/done_button']"))).click();
    Thread.sleep(1000);
    enterTextInField("Description", "image1");
    clickOnButton("Submit");
  }


  /**
   * clicks on a button containing the text
   *
   * @param aButtonText Button name
   */
  public static void clickOnButton(String aButtonText)
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@text, '" + aButtonText + "'" + ")]"))).click();
  }


  /**
   * clicks on a Widget text view containing the text
   *
   * @param aText aButtonText contains text
   */
  public void clickWidgetTextView(String aText)
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, '" + aText + "'" + ")]"))).click();
  }


  /**
   * Take screen shot of the screen
   *
   * @param  filePath  path where you wan to save
   * @param  imageName Image name
   *
   * @throws IOException
   */
  public void takeScreenShot(String filePath, String imageName) throws IOException
  {
    File summaryPageScreenshot = driver.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(summaryPageScreenshot, new File("" + filePath + "" + Calendar.getInstance().getTimeInMillis() + imageName));
  }


  /**
   * clicks on a button containing the text
   *
   * @param aText aButtonText DOCUMENT ME!
   */
  public void waitForPage(String aText)
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, '" + aText + "'" + ")]")));
  }


  /**
   * DOCUMENT ME!
   *
   * @param aFieldName DOCUMENT ME!
   * @param aText      DOCUMENT ME!
   */
  public void enterTextInField(String aFieldName, String aText)
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, '" + aFieldName + "'" + ")]"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, '" + aFieldName + "'" + ")]/following-sibling::android.widget.EditText[1]"))).sendKeys(aText);
  }
}
