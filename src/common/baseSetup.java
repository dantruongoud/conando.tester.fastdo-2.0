package common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page_locator.SignInPage;

public class baseSetup {
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	// Khởi tạo cấu hình của các Browser để đưa vào Switch Case
	public WebDriver initChromeDriver() {

		ChromeOptions userAgent = new ChromeOptions();
		SignInPage index = new SignInPage(driver);
		userAgent.addArguments("disable-notifications");

		driver = new ChromeDriver(userAgent);
		System.out.println("Launching Chrome browser...");
		driver.manage().window().maximize();
		driver.get("https://beta.fastdo.vn/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		index.waitForPageLoaded();
		return driver;
	}

}
