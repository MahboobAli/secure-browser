package me.infomahboobali.browser;


import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
//        // mimics undetected-chromedriver
//        UndetectedChromeDriver uc = UndetectedChromeDriver.builder().build();
//        // mimics selenium-stealth (that uses puppeteer stealth)
//        ChromeDriver chromeDriver = new ChromeDriver();
//
//        SeleniumStealthOptions.getDefault().apply(chromeDriver);
//
//        // mimics both undetected-chromedriver and selenium-stealth
//        // warning: may cause issues
//        UndetectedChromeDriver uc2 = UndetectedChromeDriver.builder()
//                .seleniumStealth(SeleniumStealthOptions.getDefault())
//                .build();
//
//        // selenium-stealth custom options
//        SeleniumStealthOptions.builder()
//                .languageArgument("de")
//                .userAgent("CustomUserAgent")
//                .renderer("Custom Renderer")
//                .webglVendor("Custom WebGL Vendor")
//                .platform("Custom Platform")
//                .build()
//                .apply(chromeDriver);

        // undetected-chromedriver custom options
        UndetectedChromeDriver uc3 = UndetectedChromeDriver.builder()
                .versionMain(125)
                .driverExecutable(new File("C:\\Users\\Public\\google_driver\\chromedriver-win64\\chromedriver.exe")) // chromedriver.exe
                .binaryExecutable(new File("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")) // chrome.exe
                //.userDataDir("Local/Temp/java_undetected_chromedriver") // defaults to using temp folder
                //.userDataDir("C:/Users/Mahboob_Ali/AppData/Local/Temp/java_undetected_chromedriver") // defaults to using temp folder
                .headless(true)
                .patchProvidedDriver(true) // defaults to false if you use custom driver, else it defaults to true
                .build();
        ChromeDriver underlyingDriverUsed = uc3.getDriver();

        // CF bypass that works sometimes but also slow (10s)
        // Use with caution.
        boolean success = uc3.cloudflareGet("https://www.scaler.com/users/sign_in/");
        System.out.println(success);
        uc3.quit();
    }
}

