// Planon Enterprise Edition Source file: RealDeviceFirsttest.java
// Copyright Planon 1997-2023. All Rights Reserved.
package session1;

import io.appium.java_client.android.*;
import io.appium.java_client.remote.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.net.*;
import java.time.*;

/**
 * DOCUMENT ME!
 */
public class RealDeviceFirsttest
{
  //~ Instance Variables ---------------------------------------------------------------------------

  private AndroidDriver driver;
  private WebDriverWait wait;

  //~ Methods --------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws MalformedURLException DOCUMENT ME!
   */
  @Before public void setUp() throws MalformedURLException
  {
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
    cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

    File appDirectory = new File("C:\\RealDevice Testing\\PlanonLive_API30_Debug.apk");
    cap.setCapability(MobileCapabilityType.APP, appDirectory.getAbsolutePath());
    cap.setCapability("appPackage", "com.planonsoftware.mobileplatform");
    cap.setCapability("appActivity", ".MainActivity");

    URL url = new URL("http://127.0.0.1:4545");
    driver = new AndroidDriver(url, cap);

    wait = new WebDriverWait(driver, Duration.ofSeconds(120));
  }


  /**
   * DOCUMENT ME!
   */
  @After public void tearDown()
  {
    driver.quit();
  }


  /**
   * My first test case
   *
   * @throws MalformedURLException
   */
  @Test public void firstCase() throws MalformedURLException
  {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.EditText")));

    driver.findElement(By.className("android.widget.EditText")).sendKeys("https://peddapmp2973-prod.plnd.cloud/");
    driver.findElement(By.xpath("//android.widget.Button[@text='Login']")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='j_username']"))).sendKeys("Supervisor");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='j_password']"))).sendKeys("a");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='Login']"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@text, 'More')]")));
  }
}
