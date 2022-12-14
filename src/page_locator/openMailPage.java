package page_locator;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class openMailPage {
    private WebDriver driver;

    private By mail_input = By.id("identifierId");

    private By contineu_btn = By.xpath("//span[contains(text(),'Next')]");

    private By password_input = By.name("password");

    public WebDriver getDriver() {
        return driver;
    }

    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public void enterMail(String email) {
        try {
            WebElement mailtxt = driver.findElement(mail_input);
            if (mailtxt.isDisplayed()) {
                mailtxt.sendKeys(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_contineu() {
        try {
            WebElement contineuBtn = driver.findElement(contineu_btn);
            if (contineuBtn.isDisplayed()) {
                contineuBtn.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterPass(String password) {
        try {
            WebElement passwordtxt = driver.findElement(password_input);
            if (passwordtxt.isDisplayed()) {
                passwordtxt.sendKeys(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginMail(String email, String password) {
        enterMail(email);
        click_contineu();
        enterPass(password);
        click_contineu();
    }
}
