package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;

public class createAdminPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Cấu hình hệ thống')]")
    public WebElement setting;

    @FindBy(xpath = "//span[contains(text(),'Tài khoản admin')]")
    public WebElement accountAdmin;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement create_btn;

    @FindBy(xpath = "//input[@placeholder='Nhập email']")
    private WebElement email_input;

    @FindBy(xpath = "//input[@placeholder='Nhập họ']")
    private WebElement lastname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập tên']")
    private WebElement firstname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập mật khẩu']")
    private WebElement password_input;

    @FindBy(css = "a[class='button is-link']")
    private WebElement save_btn;

    public createAdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage using = new SignInPage(driver);

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean verifyTitle() {
        String a = "Admin hệ thống";
        return getTitle().equals(a);
    }

    public void create_account(String email, String firstname, String lastname, String password) {
        email_input.sendKeys(email);
        firstname_input.sendKeys(firstname);
        lastname_input.sendKeys(lastname);
        password_input.sendKeys(password);
        save_btn.click();
    }

    public void cleartxt() {
        try {
            email_input.clear();
            lastname_input.clear();
            firstname_input.clear();
            password_input.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Status: Passed");
        System.out.println("=========================");
        cleartxt();
    }
}
