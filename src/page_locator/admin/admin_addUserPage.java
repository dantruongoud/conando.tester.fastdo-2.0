package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;

public class admin_addUserPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='ml-2'][contains(text(),'Quản lý khách hàng')]")
    public WebElement crud_user;

    @FindBy(xpath = "//span[@class='is-size-7 has-text-weight-medium']")
    public WebElement add;

    @FindBy(xpath = "//input[@placeholder='Nhập email (*)']")
    private WebElement username_input;

    @FindBy(xpath = "//input[@placeholder='Số điện thoại (*)']")
    private WebElement phone_input;

    @FindBy(xpath = "//input[@placeholder='Nhập họ (*)']")
    private WebElement firstname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập tên (*)']")
    private WebElement lastname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập mật khẩu']")
    private WebElement password_input;

    @FindBy(xpath = "//span[contains(text(),'Tạo mới')]")
    private WebElement create;

    public admin_addUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage get = new SignInPage(driver);

    public String getTitle() {
        String titlePage = driver.getTitle();
        return titlePage;
    }

    public boolean verifyTitle() {
        String pageTitle = "Danh sách khách hàng";
        return getTitle().equals(pageTitle);
    }

    public void register(String username, String phone, String firstname, String lastname) {
        username_input.sendKeys(username);
        phone_input.sendKeys(phone);
        firstname_input.sendKeys(firstname);
        lastname_input.sendKeys(lastname);
        // enterPassword(password);
        create.click();
    }

    public void cleartxt() {
        username_input.clear();
        phone_input.clear();
        firstname_input.clear();
        lastname_input.clear();
        // password_create.clear();
    }

    public void print() {
        System.out.println("Passed");
        cleartxt();
        System.out.println("=========================");
    }
}
