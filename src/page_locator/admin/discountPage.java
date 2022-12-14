package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;

public class discountPage {
    private WebDriver driver;

    @FindBy(css = "a[href='/admin/config/promotions']")
    public WebElement naviga;

    @FindBy(css = ".icon-text.has-text-link")
    private WebElement naviga_create;

    @FindBy(xpath = "//input[@placeholder='Tiêu đề ưu đãi']")
    private WebElement header_input;

    @FindBy(css = "div[class='control has-icons-left'] input[type='number']")
    private WebElement discount_input;

    @FindBy(css = "div[class='field is-grouped mb-0'] input[type='number']")
    private WebElement condition_input;

    @FindBy(css = "a[class='button is-link']")
    private WebElement save;

    public discountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage using = new SignInPage(driver);

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean verifyTitle() {
        String a = "Ưu đãi";
        return getTitle().equals(a);
    }

    public void navigation_create() {
        try {
            if (naviga_create.isDisplayed()) {
                naviga_create.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_discount(String header, String discount, String condition) {
        header_input.sendKeys(header);
        discount_input.sendKeys(discount);
        condition_input.sendKeys(condition);
        save.click();
    }

    public void cleartxt() {
        try {
            header_input.clear();
            discount_input.clear();
            condition_input.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Passed");
        cleartxt();
        System.out.println("=========================");
    }
}
