package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class editAdminPage {
    private WebDriver driver;

    @FindBy(css = "a[class='icon-text']")
    public WebElement naviga;

    @FindBy(css = "input[placeholder='Nhập họ (*)']")
    private WebElement lastname_input;

    @FindBy(css = "input[placeholder='Nhập tên (*)']")
    private WebElement firstname_input;

    @FindBy(css = "input[placeholder='Nhập số điện thoại (*)']")
    private WebElement phone_input;

    @FindBy(css = "input[placeholder='Nhập mật khẩu cũ']")
    private WebElement passwordold_input;

    @FindBy(css = "input[placeholder='Nhập mật khẩu mới']")
    private WebElement passwordnew_input;

    public editAdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean verify_title(String title) {
        return getTitle().equals(title);
    }

    public void editAdmin(String lastname, String firstname, String phone, String passwordold, String passwordnew) {
        lastname_input.sendKeys(lastname);
        firstname_input.sendKeys(firstname);
        phone_input.sendKeys(phone);
        passwordold_input.sendKeys(passwordold);
        passwordnew_input.sendKeys(passwordnew);
    }

    public void print() {
        System.out.println("PASSED");
        System.out.println("=========================");
        cleartxt();
    }

    public void cleartxt() {
        lastname_input.clear();
        firstname_input.clear();
        phone_input.clear();
        passwordold_input.clear();
        passwordnew_input.clear();
    }
}
