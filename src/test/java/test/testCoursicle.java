package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class testCoursicle {
    private WebDriver driver;
    private WebElement element;
    private String url = "https://www.coursicle.com/";


    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    // purpose: ensure that we can get the base url of this website
    // input: simply the url to the page
    // expected: the title should be that "Coursicle | ..." string below
    void testSetup() {

        String expected = "Coursicle | Plan your schedule and get into classes";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    // purpose: initial probing of the website in order to test that the sendKeys works with the JS
    // input: "UVA" string
    // expected: UVA displays on the page
    void testGetSchoolByAc() {
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("UVA");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

    @Test
    // purpose: initial probing of the website, testing if we can send a state
    // input: "Virginia" string
    // expected: the page renders the UVA block to the screen
    void testGetSchoolByStateName() {
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("Virginia");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

    @Test
    // purpose: page 1, test T1
    // input: click on "App"
    // expected: the "App" page will load in the browser
    // Author: Jackson Miskill
    void testT1GetAppPage() {
        String expected = "https://www.coursicle.com/app/";
        driver.findElement(By.id("appNavItem")).click();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @Test
    // purpose: page 1, Test T2
    // input:
    // expected:
    // Author: Jackson Miskill
    void testT2NoRealClickOccurs() {
        String expected = "https://www.coursicle.com/";
        driver.findElement(By.id("tileSearchBox")).click();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @Test
    // purpose: page 1, test T3
    // input: click on "Blog"
    // expected: the blog page will load
    // Author: Jackson Miskill
    void testT3GetBlogPage() {
        String expected = "https://www.coursicle.com/blog/";
        driver.findElement(By.id("blogNavItem")).click();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @Test
    // purpose: page 1, Test T4
    // input: click on "contact"
    // expected: the contact page will load
    // Author: Jackson Miskill
    void testT4GetContactPage() {
        driver.findElement(By.id("contactNavItem")).click();
        assertTrue(driver.findElement(By.id("contactUsModal"))!= null);
    }

    @Test
    // purpose: page 1, Test T5
    // input: click on the "+" button
    // expected:
    // Author: Jackson Miskill
    void testT5ClickPlusButton() {
        driver.findElement(By.className("moreTile")).click();
        assertTrue(driver.getPageSource().contains("Purdue"));
    }

    @Test
    // purpose: page 1, Test T6
    // input: click on "W&M"
    // expected: page reloads as the W&M page
    void testT6ClickOnRandomSchool() {
        String expected = "https://www.coursicle.com/wm/";
        driver.findElement(By.xpath("//a[@displayname='W&M']")).click();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @Test
    // purpose: page 1, Test T7
    // input: type in "University of Virginia
    // expected: University of Virginia becomes unhidden
    void testT7SendUniversityName() throws InterruptedException {
        String toSend = "University of Virginia";
        String expected = "tileElement showTile";
        WebElement we1 = driver.findElement(By.xpath("//a[@displayname='UVA']"));
        assertEquals("tileElement", we1.getAttribute("class")); // first, check it is hidden
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        TimeUnit.SECONDS.sleep(2); // had to throw a pause because the Javascript doesn't load that fast
        WebElement we2 = driver.findElement(By.xpath("//a[@displayname='UVA']"));
        System.out.println("hErERhERhEhREHrHERhErHErhEhr!!!!!!!!........------");
        System.out.println(we2.getAttribute("style"));
        assertTrue(we2.getAttribute("style").contains("left") && we2.getAttribute("style").contains("top"));
    }

    @Test
    // purpose: page 1, Test T8
    // input: type in "Evergreen"- this is a university that does not exist in their database
    // expected: "We haven't added support for your college yet but you can use our schedule maker"
    void testT8SendDoesNotExistUniversityName() {
        String toSend = "Evergreen";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T9
    // input: null
    // expected: "We haven't added support for your college yet but you can use our schedule maker" or no change
    void testT9SendNullValue() {
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("");
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T10
    // input: 7777
    // expected: We haven't added support for your college yet
    void testT10SendDoesNotExistUniversityNumber() {
        String toSend = "7777";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T11
    // input: "UVA"
    // expected:  UVA box will pull up
    void testT11SendUniversityShortName() throws InterruptedException {
        String toSend = "UVA";
        String expected = "tileElement showTile";
        WebElement we1 = driver.findElement(By.xpath("//a[@displayname='UVA']"));
        assertEquals("tileElement", we1.getAttribute("class")); // first, check it is hidden
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        TimeUnit.SECONDS.sleep(2); // had to throw a pause because the Javascript doesn't load that fast
        WebElement we2 = driver.findElement(By.xpath("//a[@displayname='UVA']"));
        System.out.println(we2.getAttribute("style"));
        assertTrue(we2.getAttribute("style").contains("left") && we2.getAttribute("style").contains("top"));
    }

    @Test
    // purpose: page 1, test T12
    // input: "EG"
    // We haven't added support for your college yet
    void testT12SendDoesNotExistUniversityShortenedName() {
        String toSend = "EGU";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }
}

