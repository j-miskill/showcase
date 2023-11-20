package main;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class executor {
    private WebDriver driver;
    private WebElement element;
    private String url = "https://www.coursicle.com/";

    public void testHeader() {

        String out = driver.getTitle();
        System.out.println(out);
    }

    public void testGetSchool() {
        // how to enter text into the find school
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("UVA");
        // System.out.println("displayName=\"UVA\"");
        System.out.println(driver.getPageSource().contains("UVA"));

    }

    public void run() {
        driver = new FirefoxDriver();
        driver.get(url);
        System.out.println("CS 3250 Showcase");
        this.testHeader();
        this.testGetSchool();
    }
}


