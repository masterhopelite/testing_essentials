package introduction;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
		
		String newTabUrl = "https://www.linkedin.com/home";
		String newTabUrl1 = "https://www.google.com";
		((JavascriptExecutor) driver).executeScript("window.open('"+ newTabUrl +"')");
		((JavascriptExecutor) driver).executeScript("window.open('"+ newTabUrl1 +"')");
		Set<String> windows = driver.getWindowHandles();
//		driver.switchTo().Options.window()
		Iterator<String> it = windows.iterator();
		int i =0;
		while(it.hasNext()) {
		
			String child = it.next();
			driver.switchTo().window(child);
			 if (driver.getTitle().equals("Google")) {
				 	String yo = driver.getTitle();
				 	System.out.println(yo);
	                break;
	            }
			i++;
		}
		System.out.println(i);
		
		
		
	}

}
