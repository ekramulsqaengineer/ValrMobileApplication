package Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    
    public static AndroidDriver driver;
    static WebDriverWait wait;
    
    public static void main(String[] args) throws MalformedURLException {
        
DesiredCapabilities caps = new DesiredCapabilities();
        


// ১. প্ল্যাটফর্ম এবং ডিভাইস সেটিংস
caps.setCapability(CapabilityType.PLATFORM_NAME, "Android");
caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16"); 
caps.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk_gphone64_x86_64");

// ২. অটোমেশন নাম (UiAutomator2 বাধ্যতামূলক)
caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

// ৩. অ্যাপ ডিটেইলস (Package and Activity)
caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.valr.valr");
caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.valr.valr.MainActivity");

// ৪. রিসেট সেটিংস
caps.setCapability("noReset", false);
caps.setCapability("adbExecTimeout", 60000); // ADB কমান্ডের জন্য ওয়েট টাইম বাড়ানো হয়েছে


// ৫. URL সমাধান (Appium Server GUI এর জন্য /wd/hub ব্যবহার করা হয়েছে)
String appiumURL = "http://127.0.0.1:4723/wd/hub"; 

System.out.println("Try to statr Appium: " + appiumURL);

try {
    URL url = new URL(appiumURL);
    driver = new AndroidDriver(url, caps);
    System.out.println("✅ Appium Session Started Successfully!");
    
    // (Explicit Wait)
     WebDriverWait wait = new WebDriverWait(driver, 20);
     driver.findElement(By.xpath("//android.view.View[@content-desc=\"Skip\"]")).click();
	 driver.findElement(By.xpath("//android.view.View[@content-desc=\"SIGN IN\"]")).click();
    
    
    String emailXPath = "//android.view.View[@content-desc='ABC@EMAIL.COM']/android.widget.EditText";
    
    System.out.println("Search Email");
    WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath)));
    
    // Email input
    emailField.click();
    emailField.sendKeys("ekramulsqaengineer@gmail.com");
    System.out.println("Email Input Successfully");
    
    // Password Input
    String passwordXPath = "//android.view.View[@content-desc='ENTER YOUR PASSWORD']/android.widget.EditText";
    System.out.println("Search Password Field");
    WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordXPath)));
    passwordField.click();
    passwordField.sendKeys("Password123@");
    System.out.println("Password Set Successfully");
    
    // scroll and search login button
    System.out.println("Scroll Dwon and Search Login Button");
    
    driver.findElementByAndroidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
        ".scrollIntoView(new UiSelector().description(\"LOG IN\"))"
    ).click();

    System.out.println("Log In Button click successfully");
    
    
  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\\\"ENTER YOUR PASSWORD\\\"]/android.widget.EditText"))).sendKeys("Password123@");
  //driver.findElement(By.xpath("//android.view.View[@content-desc=\"LOG IN\"]")).click();
    
    
    if (driver != null) {
        System.out.println("VALR Application Open Successfully");
    }
    
} catch (Exception e) {
    System.out.println(" sessor error: " + e.getMessage());
    return; 
}
   
	//  driver.findElement(By.xpath("//android.view.View[@content-desc=\"Skip\"]")).click();
	 // driver.findElement(By.xpath("//android.view.View[@content-desc=\"SIGN IN\"]")).click();
	 // driver.findElement(By.xpath("/android.view.View[@content-desc=\\\\\\\"ABC@EMAIL.COM\\\\\\\"]/android.widget.EditText")).sendKeys("ekramulsqaengineer@gmail.com");
	  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/android.view.View[@content-desc=\\\"ABC@EMAIL.COM\\\"]/android.widget.EditText"))).sendKeys("ekramulsqaengineer@gmail.com");
     // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\\\"ENTER YOUR PASSWORD\\\"]/android.widget.EditText"))).sendKeys("Password123@");
      //driver.findElement(By.xpath("//android.view.View[@content-desc=\"LOG IN\"]")).click();
 }
}