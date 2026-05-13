package exselenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestaGoogle {

	protected WebDriver driver;
	
    @BeforeEach
    public void createDriver() {  
    
		driver = new ChromeDriver();
        driver.get("https://www.google.com.br");
    }	

	@Test
	public void test() {
		driver.get("www.youtube.com.br");
	}
	
    @AfterAll
    public static void quitDriver() {
      // driver.quit();
    }
}
