package datadriventests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UnSuccessfulLoginDataDrivenBasicTest {
		
	//Create the Data Provider and give the data provider a name
	@DataProvider(name="user-ids-data-provider")
	public Object[][] userIdsDataProvider() {
		return new Object[][]{"in28minutes","adam","eve"};
	}	
	
	//Use the data provider
	@Test(dataProvider="user-ids-data-provider")
	public void testUnsuccessfulLoginWithIn28Minutes(String userId) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("name")).sendKeys(userId);
		//driver.findElement(By.id("name")).sendKeys("in28minutes");
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys("");
		passwordElement.submit();
		// driver.findElement(By.id("submit")).click();

		// welcome-message
		String errorMessageText = driver.findElement(By.id("error-message")).getText();
		System.out.println(errorMessageText);
		assertEquals(errorMessageText,"Invalid Credentials");
		driver.quit();
	}
}
