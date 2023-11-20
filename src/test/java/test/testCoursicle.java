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
    void testSetup() {

        String expected = "Coursicle | Plan your schedule and get into classes";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    void testGetSchoolByAc() {
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("UVA");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

    @Test
    void testGetSchoolByStateName() {
        driver.findElement(By.id("tileSearchBoxInput")).sendKeys("Virginia");
        assertTrue(driver.getPageSource().contains("UVA"));
    }

}
