package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

}
