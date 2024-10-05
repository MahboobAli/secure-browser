package me.infomahboobali.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;

import java.io.IOException;
import java.util.Scanner;

class Test {
    private static final String testUrl = "https://www.scaler.com/users/sign_in/";
    public static void main(String[] args) throws IOException, ReflectiveOperationException, InterruptedException {
        //test1();
       // test2();
       // test3();
        cloudflareTest();
    }
    public static void w() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press [ENTER] to quit");
        scanner.nextLine();
        scanner.close();
        System.out.println("Quitting");
    }
    public static void test1() throws IOException, ReflectiveOperationException, InterruptedException {
        // UC, driverFromCFT currently defaults to false (which only allows versions <= 113)
        UndetectedChromeDriver driver = UndetectedChromeDriver.builder()
                .build();

        driver.cloudflareGet(testUrl);
        WebElement userId = driver.findElement(By.xpath("//input[@placeholder='Enter your email']"));
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
        WebElement loginButton = driver.findElement(By.xpath("(//button[normalize-space()='Login'])[1]"));


        Thread.sleep(2000);
        userId.sendKeys("techprakashmaurya@gmail.com");
        Thread.sleep(2000);
        password.sendKeys("Nitya@1990");
        Thread.sleep(3000);
        loginButton.click();

        w();
        driver.quit();
    }
    public static void test2() {
        ChromeDriver driver = new ChromeDriver();
        SeleniumStealthOptions.getDefault().apply(driver);
        driver.get(testUrl);
        w();
        driver.quit();
    }
    public static void test3() throws IOException, ReflectiveOperationException {
        UndetectedChromeDriver driver = UndetectedChromeDriver.builder()
                .pageLoadStrategy(PageLoadStrategy.NONE)
                .headless(false)
                .driverFromCFT(true)
                .versionMain(115)
                .autoOpenDevtools(true)
                .serviceBuilder(new ChromeDriverService.Builder().withSilent(true).withLogLevel(ChromiumDriverLogLevel.OFF))
                .seleniumStealth(SeleniumStealthOptions.getDefault()).build();
        System.out.println("Bypassed: " + driver.cloudflareGet(testUrl));
        w();
        driver.quit();
    }
    public static void cloudflareTest() throws IOException, ReflectiveOperationException, InterruptedException {
        int success = 0;
        int fail = 0;
        int attempts = 2;
        boolean headless = false;
        for(int i = 0; i < attempts; i++) {
            UndetectedChromeDriver driver = UndetectedChromeDriver.builder()
                    .pageLoadStrategy(PageLoadStrategy.NONE)
                    .headless(headless)
                    .driverFromCFT(true)
                    .versionMain(115)
                    .autoOpenDevtools(true)
                    .seleniumStealth(SeleniumStealthOptions.getDefault()).build();
            if(driver.cloudflareGet(testUrl)) success++;
            else fail++;
            Thread.sleep(2391);
            driver.quit();
            System.out.println((headless ? "Headless" : "Headful") + " Success: " + success + " Fail: " + fail + " | Success Rate: " + (success / attempts * 100d) + "% Attempts = " + (i+1) + "/" + attempts + " (" + ((i+1)/attempts*100d)+"%)");
        }
        System.out.println((headless ? "Headless" : "Headful") + " Success: " + success + " Fail: " + fail + " | Success Rate: " + (success / attempts * 100d) + "%");
    }
}
