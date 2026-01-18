package Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {
    
    public static AndroidDriver driver;

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

System.out.println("⏳ Appium সেশন শুরু করার চেষ্টা করা হচ্ছে: " + appiumURL);

try {
    URL url = new URL(appiumURL);
    driver = new AndroidDriver(url, caps);
    System.out.println("✅ Appium Session Started Successfully!");
    
    if (driver != null) {
        System.out.println("📱 VALR অ্যাপটি সাকসেসফুলি ওপেন হয়েছে।");
    }
    
} catch (Exception e) {
    System.out.println("❌ সেশন এরর: " + e.getMessage());
    System.out.println("\n💡 সলিউশন টিপস:");
    System.out.println("- নিশ্চিত করুন JAVA_HOME এবং ANDROID_HOME এনভায়রনমেন্ট ভেরিয়েবল সেট আছে।");
    System.out.println("- Appium Server GUI-তে 'Edit Configurations' থেকে পাথগুলো চেক করুন।");
    System.out.println("- অ্যাপটি পুনরায় ইন্সটল করে ট্রাই করুন।");
    return; 
}
       
    }
}