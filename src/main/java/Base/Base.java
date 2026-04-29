package Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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
    
    public static AndroidDriver<WebElement> driver;
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
   // wait = new WebDriverWait(driver, Duration.ofSeconds(20));
   
    String emailXPath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]";
    WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath)));
   
    // Email input
    emailField.click();
    emailField.sendKeys("superAdmin@gmail.com");
    System.out.println("Email Input Successfully");
    
    // Password Input
    By passwordLocator = By.xpath("//android.widget.EditText[@password='true']");
    WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(passwordLocator));

    passwordField.click();
    passwordField.sendKeys("password");

    System.out.println("Password Set Successfully");
    
    Thread.sleep(2000);
    System.out.println("Scroll Down to Login Button");
    // Scroll using UiScrollable (BEST WAY)
    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().description(\"Login\"));");
  
    Thread.sleep(2000);
    By loginBtn = By.xpath("//android.widget.Button[@content-desc='Login']");
	WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
	login.click();
    System.out.println("Login Button Clicked Successfully");
    
    Thread.sleep(2000);
    By nextBtn= By.xpath("//android.widget.Button[@content-desc=\"Continue\"]");
    WebElement next = wait.until(ExpectedConditions.visibilityOfElementLocated(nextBtn));
	next.click();
	System.out.println("Continue Button Clicked Successfully");
	 
	Thread.sleep(2000);
	By fullName = By.xpath("//android.widget.EditText");
	WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(fullName));
	name.click();
	name.sendKeys("Ekramul");
	System.out.println("Full Name Input Successfully");
	
	
	Thread.sleep(2000);
	By email = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
	WebElement mail = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
	mail.click();
	mail.sendKeys("ekramulcsediu2016@gmail.com");
	System.out.println("Email Input Successfully");
	
	
	Thread.sleep(2000);
	By Phone = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
	WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(Phone));
	phone.click();
	phone.sendKeys("01758871165");
	System.out.println("Phone Input Successfully");;
	
	
	// Referral Source button click
	By referals = By.xpath("//android.widget.Button[@content-desc='Referral Source']");
	WebElement referal = wait.until(ExpectedConditions.visibilityOfElementLocated(referals));
	referal.click();

	System.out.println("Dropdown Opened");

	// Facebook option select
	Thread.sleep(2000);
	By facebookOption = By.xpath("//android.widget.Button[@content-desc=\"Facebook\"]");
	//By facebookOption = By.xpath("//*[contains(@text,'Facebook')]");
	WebElement facebook = wait.until(ExpectedConditions.visibilityOfElementLocated(facebookOption));
	facebook.click();
	System.out.println("Facebook Selected Successfully");
	
	

	Thread.sleep(2000);
	driver.findElementByAndroidUIAutomator(
	"new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().descriptionContains(\"Current\"))");

	Thread.sleep(2000);
	List<WebElement> inputs = driver.findElements(By.className("android.widget.EditText"));
	WebElement current = null;
	for (WebElement el : inputs) {
	    if (el.isDisplayed() && el.isEnabled()) {
	        current = el;   // last visible enabled input
	    }
	}

	if (current != null) {
	    current.click();
	    current.clear();
	    current.sendKeys("Good");
	    System.out.println("Current Va Rating Input Successfully");
	} else {
	    System.out.println("❌ Current Va Rating field not found");
	}
	
	driver.findElementByAndroidUIAutomator(
		    "new UiScrollable(new UiSelector().scrollable(true))" +".scrollIntoView(new UiSelector().descriptionContains(\"Prior\"))");
	
	
	Thread.sleep(2000);

	List<WebElement> toggles = driver.findElements(By.className("android.widget.Switch"));

	for (WebElement tg : toggles) {
	    if (tg.isDisplayed() && tg.isEnabled()) {
	        tg.click();
	        System.out.println("Prior Denial History Clicked Successfully");
	        break;
	    }
	}
	 Thread.sleep(2000);
	    By Continue= By.xpath("//android.widget.Button[@content-desc=\"Continue\"]");
	    WebElement conti = wait.until(ExpectedConditions.visibilityOfElementLocated(Continue));
	    conti.click();
		System.out.println("Continue Button Clicked Successfully");
	

	
	
       
    if (driver != null) {
        System.out.println("VALR Application Open Successfully");
    }
    
} catch (Exception e) {
    System.out.println(" session error: " + e.getMessage());
    return; 
}
   
	
 }
}