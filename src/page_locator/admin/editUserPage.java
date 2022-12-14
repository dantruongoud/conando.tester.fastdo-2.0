package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class editUserPage {
    public WebDriver driver;

    @FindBy(css = "a[title='Chỉnh sửa']")
    public WebElement edit_btn;

    @FindBy(xpath = "//input[@placeholder='Số điện thoại (*)']")
    private WebElement phone_input;

    @FindBy(xpath = "//input[@placeholder='Nhập họ (*)']")
    private WebElement firstname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập tên (*)']")
    private WebElement lastname_input;

    @FindBy(css = ".title.has-text-white")
    private WebElement popup;

    @FindBy(xpath = "//span[contains(text(),'Cập nhật')]")
    private WebElement save;

    public editUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String get_title() {
        return popup.getText().strip();
    }

    public boolean verify_title() {
        String a = "Thông tin khách hàng";
        return get_title().equals(a);
    }

    public void cleartxt() {
        try {
            phone_input.clear();
            firstname_input.clear();
            lastname_input.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Status: Passed");
        System.out.println("=========================");
        cleartxt();
    }

    public void edit(String phone, String firstname, String lastname) {
        phone_input.sendKeys(phone);
        firstname_input.sendKeys(firstname);
        lastname_input.sendKeys(lastname);
        save.click();
    }
}
