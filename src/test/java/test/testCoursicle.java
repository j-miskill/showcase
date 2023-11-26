package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    // Purpose: Page 1 set up
    // Author: Jackson Miskill
    void page1SetUp() {
        driver = new FirefoxDriver();
        driver.get(url);
    }

    // purpose: Page 2 set up
    // Author: Jackson Miskill- Page 2 set up
    void page2SetUp() {
        driver = new FirefoxDriver();
        driver.get("https://www.coursicle.com/virginia");
    }

    @AfterEach
    // Author: Jackson Miskill
    void tearDown() {
        driver.quit();
    }

    // Author: Jackson Miskill


    @Test
    // purpose: ensure that we can get the base url of this website
    // input: simply the url to the page
    // expected: the title should be that "Coursicle | ..." string below
    // Author: Jackson Miskill
    void testSetup() {
        page1SetUp();
        String expected = "Coursicle | Plan your schedule and get into classes";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    // purpose: initial probing of the website in order to test that the sendKeys works with the JS
    // input: "UVA" string
    // expected: UVA displays on the page
    void testGetSchoolByAc() {
        page1SetUp();
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("UVA");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

    @Test
    // purpose: initial probing of the website, testing if we can send a state
    // input: "Virginia" string
    // expected: the page renders the UVA block to the screen
    void testGetSchoolByStateName() {
        page1SetUp();
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("Virginia");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

    @Test
    // purpose: page 1, test T1
    // input: click on "App"
    // expected: the "App" page will load in the browser
    // Author: Jackson Miskill
    void testT1GetAppPage() {
        page1SetUp();
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
        page1SetUp();
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
        page1SetUp();
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
        page1SetUp();
        driver.findElement(By.id("contactNavItem")).click();
        assertTrue(driver.findElement(By.id("contactUsModal"))!= null);
    }

    @Test
    // purpose: page 1, Test T5
    // input: click on the "+" button
    // expected:
    // Author: Jackson Miskill
    void testT5ClickPlusButton() {
        page1SetUp();
        driver.findElement(By.className("moreTile")).click();
        assertTrue(driver.getPageSource().contains("Purdue"));
    }

    @Test
    // purpose: page 1, Test T6
    // input: click on "W&M"
    // expected: page reloads as the W&M page
    // Author: Jackson Miskill
    void testT6ClickOnRandomSchool() {
        page1SetUp();
        String expected = "https://www.coursicle.com/wm/";
        driver.findElement(By.xpath("//a[@displayname='W&M']")).click();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @Test
    // purpose: page 1, Test T7
    // input: type in "University of Virginia
    // expected: University of Virginia becomes unhidden
    // Author: Jackson Miskill
    void testT7SendUniversityName() throws InterruptedException {
        page1SetUp();
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
    // Author: Jackson Miskill
    void testT8SendDoesNotExistUniversityName() {
        page1SetUp();
        String toSend = "Evergreen";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T9
    // input: null
    // expected: "We haven't added support for your college yet but you can use our schedule maker" or no change
    // Author: Jackson Miskill
    void testT9SendNullValue() {
        page1SetUp();
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("");
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T10
    // input: 7777
    // expected: We haven't added support for your college yet
    // Author: Jackson Miskill
    void testT10SendDoesNotExistUniversityNumber() {
        page1SetUp();
        String toSend = "7777";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 1, Test T11
    // input: "UVA"
    // expected:  UVA box will pull up
    // Author: Jackson Miskill
    void testT11SendUniversityShortName() throws InterruptedException {
        page1SetUp();
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
    // Author: Jackson Miskill
    void testT12SendDoesNotExistUniversityShortenedName() {
        page1SetUp();
        String toSend = "EGU";
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys(toSend);
        assertTrue(driver.getPageSource().contains("We haven't added support for your college yet"));
    }

    @Test
    // purpose: page 2, test T1
    // input: "Artificial Intelligence"
    // expected: The 4710 class pulls up on the left hand side of the page
    // Author: Jackson Miskill
    void testT1NormalClassFullName() throws InterruptedException {
        page2SetUp(); // needed to add a method to go to the next page because the @BeforeEach only sends to home
        // assertTrue(driver.getPageSource().contains("Artificial Intelligence")); --> returns false so it's not there originally, it pulls in
        String toSend = "Artificial Intelligence";
        assertFalse(driver.getPageSource().contains("Artificial Intelligence"));
        System.out.println("Got through first test");
        driver.findElement(By.id("quickSearchInput")).sendKeys(toSend);
        TimeUnit.SECONDS.sleep(2); // had to throw a pause because the Javascript doesn't load that fast
        assertTrue(driver.getPageSource().contains("Artificial Intelligence"));
    }

    @Test
    // purpose: page 2, test T2
    // input: "Introduction to Cooking"
    // expected: "No classes found."
    // Author: Jackson Miskill
    void testT2RequestForNonexistentClassByFullName() throws InterruptedException{
        page2SetUp();
        String toSend = "Introduction to Cooking";
        assertFalse(driver.getPageSource().contains("No classes found."));
        driver.findElement(By.id("quickSearchInput")).sendKeys(toSend);
        TimeUnit.SECONDS.sleep(2);
        assertTrue(driver.getPageSource().contains("No classes found."));
    }

    @Test
    // purpose: page 2: test T3
    // input: null
    // expected: "No classes found." or no changes in the system state
    void testT3RequestForEmptyString() throws InterruptedException {
        page2SetUp();
        String toSend = "";
        assertFalse(driver.getPageSource().contains("No classes found."));
        driver.findElement(By.id("quickSearchInput")).sendKeys(toSend);
        TimeUnit.SECONDS.sleep(2);
        assertTrue(driver.getPageSource().contains("Search: bio > 300, math"));
    }

    @Test
    // purpose: page 2, test T4
    // input: "CS 4710
    // expected: Artificial Intelligence class will load on the left hand side
    // Author: Jackson Miskill
}

