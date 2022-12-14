package page_locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	private WebDriver driver;

	@FindBy(xpath = "//input[@placeholder='Nhập email...']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@placeholder='Nhập mật khẩu...']")
	private WebElement passwordInput;

	@FindBy(xpath = "//span[contains(text(),'Đăng nhập')]")
	private WebElement btn_button;

	@FindBy(xpath = "//span[@class='has-text-weight-medium']")
	private WebElement title_page;

	@FindBy(how = How.XPATH, xpath = "//div[@class='has-text-danger has-text-centered is-italic mb-3']")
	private List<WebElement> validation;

	@FindBy(css = "a[class='button is-link']")
	@CacheLookup
	public WebElement button;

	@FindBy(how = How.ID, id = "notify")
	private List<WebElement> tagline;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return title_page.getText().strip();
	}

	public boolean verifyPageTitle() {
		String nameTitle = "Đăng xuất";
		return getTitle().equals(nameTitle);
	}

	public void signin(String username, String password) {
		waitForPageLoaded();
		emailInput.sendKeys(username);
		passwordInput.sendKeys(password);
		btn_button.click();
	}

	public String messgaeError() {
		String tagline = "";
		for (int i = 0; i < validation.size(); i++) {
			tagline = validation.get(i).getText().strip();
			if (tagline.length() > 0) {
				break;
			}
		}
		return tagline;
	}

	public void clearTxt() {
		emailInput.clear();
		passwordInput.clear();
	}

	public void chose_company() {
		WebElement form_company = driver.findElement(By.tagName("form"));
		List<WebElement> company = form_company.findElements(By.tagName("a"));

		for (WebElement row : company) {
			String list_company = row.getText().strip();
			if (list_company.equals("Quản lý tổ chức")) {
				row.click();
				break;
			}
		}
	}

	public void login() {
		try {
			Thread.sleep(2000);
			emailInput.sendKeys("ndtruong.conando@gmail.com");
			Thread.sleep(1000);
			passwordInput.sendKeys("dantruong2410");
			Thread.sleep(1000);
			btn_button.click();
			waitForPageLoaded();
			chose_company();
			waitForPageLoaded();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chose_admin() {
		WebElement form_company = driver.findElement(By.tagName("form"));
		List<WebElement> company = form_company.findElements(By.tagName("a"));

		for (WebElement row : company) {
			String list_company = row.getText().strip();
			if (list_company.equals("Fastdo CMS")) {
				row.click();
			}
		}
	}

	public void login_admin() {
		try {
			Thread.sleep(2000);
			emailInput.sendKeys("ndtruong.conando@gmail.com");
			Thread.sleep(1000);
			passwordInput.sendKeys("dantruong2410");
			waitForPageLoaded();
			btn_button.click();
			waitForPageLoaded();
			chose_admin();
			waitForPageLoaded();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}

	public String messgaeError_tagline() {

		String validation = "";

		if (tagline.size() > 0) {
			validation = tagline.get(0).getText().strip();
			System.out.println(validation);
		}
		return validation;
	}

	public void passed() {
		System.out.println("Status: PASSED");
		System.out.println("=========================");
	}

	public void failed() {

	}
}
